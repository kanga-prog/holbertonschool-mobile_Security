 
# Task 1 — Hooking Native Functions in Android

## 1. Objective

The objective of this task was to dynamically analyze an Android
application using native code through the Java Native Interface (JNI).

The goal was to identify and hook the native function responsible for
decrypting a secret message, then extract the hidden flag even though
it was not displayed in the application interface.

## 2. Analysis environment

The analysis was performed in a secure and controlled local
environment using:

- Kali Linux running in VirtualBox
- Windows 11
- Android Studio
- Pixel 4 Android Virtual Device
- Android 11, API 30
- Google APIs x86_64 system image
- Android Debug Bridge with root privileges
- JADX 1.5.6
- Frida 17.17.0
- Frida Server 17.17.0
- GNU Readelf
- GNU Objdump

## 3. APK identification

The APK used for this task was:

```text
task1_d.apk
Its package information was obtained with:

aapt dump badging task1_d.apk |
grep -E "package:|application-label:|launchable-activity:|native-code:"

The command returned the following relevant information:

Package: com.holberton.task2_d
Application label: task2_d
Main activity: com.holberton.task2_d.MainActivity
Native architectures: arm64-v8a, armeabi-v7a, x86 and x86_64

The application supported the x86_64 architecture used by the Android
emulator.

4. Identification of the native library

The native libraries contained in the APK were listed with:

unzip -l task1_d.apk |
rg 'lib/.+\.so$'

The APK contained the following versions of the native library:

lib/arm64-v8a/libnative-lib.so
lib/armeabi-v7a/libnative-lib.so
lib/x86/libnative-lib.so
lib/x86_64/libnative-lib.so

Because the emulator used the x86_64 architecture, the corresponding
library was extracted:

mkdir -p native/task1_d

unzip -j task1_d.apk \
'lib/x86_64/*.so' \
-d native/task1_d

The extracted library was examined with:

file native/task1_d/libnative-lib.so

The result identified it as:

ELF 64-bit LSB shared object
Architecture: x86-64
Android API: 24
Android NDK: r25b
Stripped binary

Its SHA-256 digest was calculated with:

sha256sum native/task1_d/libnative-lib.so

Result:

58ffef3a2f5aae828dc56bd120c7748681d38ed1bb239cab5094d4605b95d105
5. Identification of the native function

The exported symbols were inspected with:

readelf -Ws native/task1_d/libnative-lib.so |
rg -i 'getSecretMessage|secret|flag|JNI_OnLoad|Java_'

The following JNI function was found:

Java_com_holberton_task2_1d_MainActivity_getSecretMessage

The function was located at virtual address 0x860 and had a size of
225 bytes.

The JNI symbol indicated that the native method was named:

getSecretMessage

The Android package was com.holberton.task2_d, while the exported
JNI symbol contained task2_1d. Therefore, the native symbol was
used exactly as exported instead of being reconstructed from the
package name.

6. Static analysis of the native function

The target function was disassembled with:

objdump -d -M intel \
--disassemble=Java_com_holberton_task2_1d_MainActivity_getSecretMessage \
native/task1_d/libnative-lib.so

The disassembly showed that the function performed the following
operations:

It copied 49 obfuscated bytes into a local buffer.
It calculated the length of the buffer.
It iterated over every encrypted byte.
It calculated the current index modulo 10.
It called the native function lit() with that value.
It subtracted the result returned by lit() from the encrypted
byte.
It stored the decrypted character back into the local buffer.
It passed the decrypted buffer to the JNI function NewStringUTF.
It returned the resulting Java string.

The relevant logic can be summarized as:

decrypted_byte = encrypted_byte - lit(index % 10)

The library was also searched for readable strings:

strings -a -t x native/task1_d/libnative-lib.so |
rg -i 'Holberton|secret|flag|decrypt'

The flag was not present as a readable static string. This confirmed
that it was reconstructed dynamically while the native function was
executing.

7. Emulator preparation

A Pixel 4 virtual device was created with the following configuration:

Android version: Android 11
API level: 30
System image: Google APIs
Architecture: x86_64

ADB detected the emulator:

adb devices -l

The emulator was restarted with root privileges:

adb -s emulator-5554 root

Root access was verified with:

adb -s emulator-5554 shell id

The result was:

uid=0(root)
8. APK installation and execution

The APK was installed with:

adb -s emulator-5554 install -r task1_d.apk

The application was launched with:

adb -s emulator-5554 shell am start `
-n com.holberton.task2_d/.MainActivity

The application process was identified as:

task2_d

Its package name was:

com.holberton.task2_d
9. Frida installation and configuration

Frida Tools was installed on Windows with:

python -m pip install --user --upgrade frida-tools

The installed versions were:

Frida: 17.17.0
Frida Tools: 14.10.4

The Frida executable directory was added to the PowerShell PATH:

$FridaPath = "C:\Users\HP x360\AppData\Local\Packages\PythonSoftwareFoundation.Python.3.11_qbz5n2kfra8p0\LocalCache\local-packages\Python311\Scripts"

$env:Path += ";$FridaPath"
10. Frida Server setup

The Frida Server binary matching both the client version and emulator
architecture was downloaded:

frida-server-17.17.0-android-x86_64

It was transferred to the emulator:

adb -s emulator-5554 push `
frida-server-17.17.0-android-x86_64 `
/data/local/tmp/frida-server.bin

Execution permission was added:

adb -s emulator-5554 shell chmod 755 `
/data/local/tmp/frida-server.bin

Frida Server was then started:

adb -s emulator-5554 shell `
/data/local/tmp/frida-server.bin

The connection was verified with:

frida-ps -D emulator-5554 -ai

The target application appeared in the process list:

7951  task2_d  com.holberton.task2_d
11. Native function hook

The loaded module was identified at runtime with:

Process.findModuleByName("libnative-lib.so");

The address of the exported JNI function was obtained with:

module.findExportByName(
    "Java_com_holberton_task2_1d_MainActivity_getSecretMessage"
);

The function was then intercepted with:

Interceptor.attach(target, {
    onEnter: function (args) {
        console.log("[+] getSecretMessage() called");
    },

    onLeave: function (retval) {
        console.log("[+] jstring returned: " + retval);

        Java.perform(function () {
            const JavaString = Java.use("java.lang.String");

            const decoded = Java
                .cast(retval, JavaString)
                .toString();

            console.log("[+] Decrypted string: " + decoded);
        });
    }
});

The onEnter callback confirmed that the native function had been
called.

The onLeave callback intercepted the JNI reference returned by the
native function. The returned jstring was converted into a readable
Java string using Java.cast().

The complete script was loaded with:

frida -D emulator-5554 `
-n task2_d `
-l hook_native.js
12. Dynamic analysis result

After the hook was installed, the application triggered the native
function.

Frida produced the following output:

[+] Library: libnative-lib.so
[+] Native hook installed
[+] getSecretMessage() called
[+] jstring returned: 0x91
[+] Decrypted string:
Holberton{native_hooking_is_no_different_at_all}

The value 0x91 was a JNI object reference and not the flag itself.
Frida converted this reference into the corresponding Java string.

13. Decrypted flag

The extracted flag was:

Holberton{native_hooking_is_no_different_at_all}

The flag was stored in:

1-flag.txt
14. Conclusion

The static analysis identified the native library, the exported JNI
function and the decryption algorithm. However, the complete flag was
not visible as a static string because it was reconstructed only when
the native function executed.

Frida's Interceptor.attach() method was used to intercept
getSecretMessage() directly inside libnative-lib.so.

The returned JNI object was converted into a Java string, allowing the
decrypted flag to be extracted even though the application interface
did not display it.

This task demonstrated how dynamic instrumentation can bridge the
Java and native layers of an Android application and expose sensitive
data processed at runtime.
EOF


Crée aussi le fichier du flag en une commande :

```bash
printf '%s\n' \
'Holberton{native_hooking_is_no_different_at_all}' \
> 1-flag.tx:
