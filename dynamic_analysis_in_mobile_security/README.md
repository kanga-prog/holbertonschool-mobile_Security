# Dynamic Analysis in Mobile Security

## Task 0 — Android App Security

### Description

This challenge consists of dynamically analyzing an Android APK to recover a hidden flag.

The application generates a string from an integer seed. The objective is to identify the generation method, intercept it at runtime with Frida, test multiple seed values, and recover the string matching the expected format:

```text
Holberton{...}
```

## Authorized tools

* Android Studio
* Android Emulator
* Android Debug Bridge (ADB)
* JADX
* Frida
* PowerShell
* Kali Linux

## Analysis environment

The analysis was performed in a controlled local environment:

* Kali Linux running in VirtualBox;
* Android Studio installed on Windows;
* Pixel 4 Android Virtual Device;
* Android 11, API 30;
* Google APIs x86_64 system image;
* ADB running with root privileges;
* Frida client and Frida Server 17.17.0.

## APK information

APK filename:

```text
task0_d.apk
```

SHA-256:

```text
bb8cfb41120619000534075d79eb0752c08642f96c497679501be6348ed59012
```

Package name:

```text
com.holberton.task1_d
```

Main activity:

```text
com.holberton.task1_d.MainActivity
```

Although the APK was named `task0_d.apk`, its internal package and application label used the name `task1_d`.

## 1. APK backup and integrity verification

The APK was copied into the project directory, and a backup was created:

```bash
mkdir -p backup scripts jadx_output
cp task0_d.apk backup/task0_d-original.apk
```

The integrity of both files was verified:

```bash
sha256sum task0_d.apk backup/task0_d-original.apk
```

Both files produced the same SHA-256 digest.

The APK signature was also verified:

```bash
apksigner verify --verbose --print-certs task0_d.apk
```

The APK was correctly signed using Android APK Signature Scheme v2 with an Android debug certificate.

## 2. Static analysis with JADX

The APK was decompiled with JADX:

```bash
jadx \
  -d "$PWD/jadx_output/task0_d" \
  "$PWD/task0_d.apk"
```

Interesting strings and methods were searched:

```bash
rg -n -i \
"generateString|generateStringFromSeed|Holberton|flag|seed" \
jadx_output/task0_d
```

The main class was found at:

```text
jadx_output/task0_d/sources/com/holberton/task1_d/MainActivity.java
```

Two important methods were identified:

```java
private String generateString(int seed)
private String generateStringFromSeed(int seed)
```

The application button normally executes:

```java
generateString(0)
```

The method `generateStringFromSeed()` performs the following operations:

1. It creates a Java `Random` object using the supplied seed.
2. It processes every integer in `obfuscatedFlagData`.
3. It generates a pseudo-random number between 0 and 255.
4. It applies XOR between the stored value and the random number.
5. It converts the result into a character.
6. It concatenates all characters to produce the final string.

Simplified logic:

```java
Random random = new Random(seed);

for (int obfuscatedValue : obfuscatedFlagData) {
    int randomNum = random.nextInt(256);
    char generatedChar = (char) (obfuscatedValue ^ randomNum);
    stringBuilder.append(generatedChar);
}
```

With seed `0`, the application displayed an unreadable string. This confirmed that the default seed was incorrect.

## 3. Android emulator configuration

A Pixel 4 virtual device was created with:

```text
Android 11
API 30
Google APIs
x86_64
```

ADB detected the emulator:

```powershell
adb devices -l
```

The emulator was restarted with root privileges:

```powershell
adb -s emulator-5554 root
adb -s emulator-5554 shell id
```

The command returned:

```text
uid=0(root)
```

## 4. APK installation and execution

The APK was installed with:

```powershell
adb -s emulator-5554 install -r task0_d.apk
```

The main activity was launched with:

```powershell
adb -s emulator-5554 shell am start `
-n com.holberton.task1_d/.MainActivity
```

The application displayed a button named:

```text
Generate String!
```

Pressing this button generated an unreadable string because the application called `generateString(0)`.

## 5. Frida installation

Frida Tools was installed on Windows:

```powershell
python -m pip install --user --upgrade frida-tools
```

Installed versions:

```text
frida: 17.17.0
frida-tools: 14.10.4
```

The Frida executable directory was added to the current PowerShell session:

```powershell
$FridaPath = "C:\Users\HP x360\AppData\Local\Packages\PythonSoftwareFoundation.Python.3.11_qbz5n2kfra8p0\LocalCache\local-packages\Python311\Scripts"

$env:Path += ";$FridaPath"
```

## 6. Frida Server installation

The following file was downloaded:

```text
frida-server-17.17.0-android-x86_64.xz
```

After extraction, the server was transferred into the emulator:

```powershell
adb -s emulator-5554 push `
frida-server-17.17.0-android-x86_64 `
/data/local/tmp/frida-server.bin
```

Execution permission was added:

```powershell
adb -s emulator-5554 shell chmod 755 `
/data/local/tmp/frida-server.bin
```

The server was then started:

```powershell
adb -s emulator-5554 shell `
/data/local/tmp/frida-server.bin
```

The connection was verified with:

```powershell
frida-ps -D emulator-5554
```

The target application appeared as:

```text
7492  task1_d
```

## 7. Frida script

The following Frida script was used to find the current `MainActivity` instance and call the private decoding method with seeds from 0 to 1000:

```javascript
Java.perform(function () {
    console.log("[+] Searching for MainActivity...");

    const MainActivity = Java.use(
        "com.holberton.task1_d.MainActivity"
    );

    const decoder =
        MainActivity.generateStringFromSeed.overload("int");

    Java.choose("com.holberton.task1_d.MainActivity", {
        onMatch: function (activity) {
            console.log("[+] MainActivity instance found");

            for (let seed = 0; seed <= 1000; seed++) {
                try {
                    const candidate = decoder
                        .call(activity, seed)
                        .toString();

                    if (
                        candidate.startsWith("Holberton{") &&
                        candidate.endsWith("}")
                    ) {
                        console.log("[+] Seed found: " + seed);
                        console.log("[+] FLAG: " + candidate);

                        return "stop";
                    }
                } catch (error) {
                    console.log(
                        "[-] Error with seed " +
                        seed + ": " + error
                    );
                }
            }

            console.log(
                "[-] No flag found between 0 and 1000"
            );
        },

        onComplete: function () {
            console.log("[+] Search completed");
        }
    });
});
```

The script was executed with:

```powershell
frida -D emulator-5554 `
-n task1_d `
-l find_flag.js
```

## 8. Result

Frida tested the seeds dynamically by calling the real application method.

The correct seed was:

```text
837
```

The recovered flag was:

```text
Holberton{Good_job_finishing_your_first_dynamic_exercise}
```

The flag was saved in:

```text
0-flag.txt
```

## Conclusion

This challenge combined static and dynamic analysis.

JADX revealed the application structure, the target methods, and the decoding algorithm. However, Frida was used to interact directly with the running application, call its private method, automate seed testing, and recover the hidden flag.

The exercise demonstrated how runtime instrumentation can expose values and behaviors that are not directly accessible through the normal application interface.

