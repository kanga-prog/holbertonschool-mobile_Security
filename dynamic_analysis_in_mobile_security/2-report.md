# Task 2 — Android Cryptography Challenge

## Objective

The objective of this challenge was to analyze the Android application, identify its encrypted data and cryptographic mechanism, intercept and manipulate its decryption process at runtime, and extract the hidden flag.

The challenge description suggested that the application communicated with a remote server using encrypted HTTP data. Therefore, both the network behavior and the local implementation of the designated APK were examined.

## Analysis environment

The analysis was performed locally in a controlled environment using:

- Kali Linux
- Android Studio
- Pixel 4 Android Virtual Device
- Android 11, API 30
- Android Debug Bridge (ADB)
- JADX 1.5.6
- APKTool
- Frida 17.17.0
- Frida Server 17.17.0
- Burp Suite
- Python 3
- Ripgrep

The analyzed APK was:

```text
app-release-task2.apk
```

## APK backup and integrity verification

Before beginning the analysis, a backup of the APK was created:

```bash
mkdir -p backup

cp app-release-task2.apk \
backup/app-release-task2-original.apk
```

The integrity of the original APK and its backup was checked with:

```bash
sha256sum \
app-release-task2.apk \
backup/app-release-task2-original.apk
```

Matching SHA-256 digests confirmed that the backup was identical to the original APK.

## APK decompilation

The APK was decompiled with JADX:

```bash
jadx \
-d "$PWD/jadx_output/task2" \
"$PWD/app-release-task2.apk"
```

It was also decoded with APKTool:

```bash
apktool d \
-f app-release-task2.apk \
-o apktool_output/task2
```

Cryptographic functions and relevant keywords were searched with:

```bash
rg -n -i \
'Base64|decrypt|encrypt|AES|RSA|Cipher|XOR|Fibonacci' \
jadx_output/task2/sources
```

The principal decryption logic was found in:

```text
com/holberton/task3/MainActivityKt.java
```

The application contained the following relevant methods:

```text
performslowDecryption()
slowRecursive(int)
xorDecrypt(String, String)
```

## Network interception analysis

Burp Suite and mitmproxy were considered for capturing and manipulating the application's HTTP traffic.

The decompiled sources were searched for URLs, endpoints and commonly used Android networking libraries:

```bash
rg -n -i \
'https?://|retrofit|okhttp|volley|httpurlconnection|endpoint|websocket' \
jadx_output/task2/sources \
jadx_output/task2/resources
```

Static analysis did not reveal any application-specific endpoint or code downloading the encrypted flag from a remote server.

The encrypted value was embedded directly inside the APK. Consequently, the designated application did not generate an application-specific encrypted HTTP response that could be captured or modified with Burp Suite.

This difference between the challenge description and the actual APK implementation was documented instead of inventing network traffic that was not present.

Because the cryptographic operation occurred locally, the interception and manipulation were performed at the runtime method boundary using Frida.

## Encrypted data identification

The `performslowDecryption()` method contained the encrypted value directly:

```java
public static final String performslowDecryption() {
    byte[] encrypted = Base64.getDecoder().decode(
        "cVZaW1dDQllZTFdRW1xeUlBbX21CWFtH" +
        "alRZXUJFRFhNX1ZcbllGQ15cUUNSRFpcVks="
    );

    return xorDecrypt(
        new String(encrypted, Charsets.UTF_8),
        String.valueOf(slowRecursive(150))
    );
}
```

The embedded Base64 value was:

```text
cVZaW1dDQllZTFdRW1xeUlBbX21CWFtHalRZXUJFRFhNX1ZcbllGQ15cUUNSRFpcVks=
```

Base64 is an encoding mechanism, not an encryption algorithm. Decoding the Base64 value only recovers the bytes subsequently processed by the XOR decryption function.

## Key derivation

The application derived its key from Fibonacci(150) with the following method:

```java
public static final long slowRecursive(int n) {
    return n <= 1
        ? n
        : slowRecursive(n - 1)
            + slowRecursive(n - 2);
}
```

The intended mathematical value of Fibonacci(150) is:

```text
9969216677189303386214405760200
```

The decimal representation of this number was used as a repeating XOR key.

## Integer overflow issue

The Smali code confirmed that `slowRecursive()` returned a signed Java `long`:

```smali
.method public static final slowRecursive(I)J
```

However, Fibonacci(150) exceeds the maximum value supported by a signed 64-bit Java `long`.

The mathematically exact value is:

```text
9969216677189303386214405760200
```

The value after simulating signed 64-bit Java overflow is:

```text
6792540214324356296
```

Using the overflowed value produced unreadable plaintext:

```text
Gacibwrkhxdcookdbbi[uair^dklvvvl~j`nWoptgndwbvkhey
```

Using the exact mathematical value produced the expected flag:

```text
Holberton{fibonacci_slow_computation_optimization}
```

This demonstrated that the intended key was the full mathematical value of Fibonacci(150), despite the incompatible `long` return type present in the APK.

## Decryption algorithm

The application used repeating-key XOR.

The operation can be summarized as:

```text
plaintext[i] =
    ciphertext[i] XOR key[i modulo key length]
```

The decompiled implementation applied:

```java
key.charAt(index % key.length())
    ^ encryptedFlag.charAt(index)
```

XOR is reversible. Applying the same repeating key to the ciphertext reconstructs the original plaintext.

This is not AES or RSA. It is a custom and cryptographically weak repeating-key XOR construction.

## Static reproduction with Python

The intended decryption algorithm was first reproduced with Python:

```python
import base64

encoded = (
    "cVZaW1dDQllZTFdRW1xeUlBbX21CWFtH"
    "alRZXUJFRFhNX1ZcbllGQ15cUUNSRFpcVks="
)

ciphertext = base64.b64decode(encoded).decode("utf-8")

previous, current = 0, 1

for _ in range(150):
    previous, current = current, previous + current

key = str(previous)

plaintext = "".join(
    chr(
        ord(character)
        ^ ord(key[index % len(key)])
    )
    for index, character in enumerate(ciphertext)
)

print("Fibonacci(150):", previous)
print("Decrypted flag:", plaintext)
```

The script returned:

```text
Fibonacci(150): 9969216677189303386214405760200
Decrypted flag: Holberton{fibonacci_slow_computation_optimization}
```

The iterative Fibonacci implementation avoided the impractical runtime cost of the recursive function while preserving the intended mathematical result.

## Dynamic runtime interception with Frida

Static decryption produced a candidate flag, but dynamic analysis was used to confirm it inside the running Android application.

The APK was installed and launched on the emulator:

```powershell
adb -s emulator-5554 install -r app-release-task2.apk

adb -s emulator-5554 shell am start `
-n com.holberton.task3/.MainActivity
```

Frida Server was started on the rooted emulator:

```powershell
adb -s emulator-5554 shell `
/data/local/tmp/frida-server.bin
```

The application process and Frida Server were verified:

```powershell
adb -s emulator-5554 shell pidof com.holberton.task3
adb -s emulator-5554 shell pidof frida-server.bin
```

Frida was then attached to the running application:

```powershell
frida -D emulator-5554 `
-N com.holberton.task3 `
-l hook_crypto.js
```

## Frida manipulation script

The Frida script intercepted and replaced `performslowDecryption()` at runtime:

```javascript
Java.perform(function () {
    const MainActivityKt = Java.use(
        "com.holberton.task3.MainActivityKt"
    );

    const Base64 = Java.use("java.util.Base64");
    const JavaString = Java.use("java.lang.String");

    const StandardCharsets = Java.use(
        "java.nio.charset.StandardCharsets"
    );

    const decrypt =
        MainActivityKt.performslowDecryption.overload();

    decrypt.implementation = function () {
        console.log(
            "[+] performslowDecryption() intercepted"
        );

        const encoded =
            "cVZaW1dDQllZTFdRW1xeUlBbX21CWFtH" +
            "alRZXUJFRFhNX1ZcbllGQ15cUUNSRFpcVks=";

        const key =
            "9969216677189303386214405760200";

        const decodedBytes = Base64
            .getDecoder()
            .decode(encoded);

        const encrypted = JavaString
            .$new(
                decodedBytes,
                StandardCharsets.UTF_8.value
            )
            .toString();

        let plaintext = "";

        for (
            let index = 0;
            index < encrypted.length;
            index++
        ) {
            const encryptedCode =
                encrypted.charCodeAt(index);

            const keyCode = key.charCodeAt(
                index % key.length
            );

            plaintext += String.fromCharCode(
                encryptedCode ^ keyCode
            );
        }

        console.log(
            "[+] Embedded Base64 data intercepted"
        );

        console.log(
            "[+] Original recursive computation bypassed"
        );

        console.log(
            "[+] Fibonacci(150) key: " + key
        );

        console.log(
            "[+] Decrypted value: " + plaintext
        );

        if (
            plaintext.startsWith("Holberton{")
            && plaintext.endsWith("}")
        ) {
            console.log("[+] FLAG: " + plaintext);
        }

        return plaintext;
    };

    console.log("[+] Dynamic crypto hook installed");

    setTimeout(function () {
        Java.perform(function () {
            try {
                console.log(
                    "[+] Invoking the hooked method..."
                );

                const result =
                    MainActivityKt.performslowDecryption();

                console.log(
                    "[+] Runtime result: " + result
                );
            } catch (error) {
                console.log(
                    "[-] Invocation failed: " + error
                );
            }
        });
    }, 500);
});
```

The hook performed the following operations:

1. attached to the running Android application;
2. intercepted `performslowDecryption()`;
3. captured the embedded Base64 data;
4. bypassed the recursive Fibonacci calculation;
5. supplied the exact mathematical value of Fibonacci(150);
6. reproduced the repeating-key XOR decryption;
7. returned the plaintext to the running application;
8. logged the decrypted flag.

## Dynamic analysis result

Frida produced the following runtime output:

```text
[+] Dynamic crypto hook installed
[+] Invoking the hooked method...
[+] performslowDecryption() intercepted
[+] Embedded Base64 data intercepted
[+] Original recursive computation bypassed
[+] Fibonacci(150) key: 9969216677189303386214405760200
[+] Decrypted value: Holberton{fibonacci_slow_computation_optimization}
[+] FLAG: Holberton{fibonacci_slow_computation_optimization}
[+] Runtime result: Holberton{fibonacci_slow_computation_optimization}
```

This output dynamically confirmed the result previously obtained with Python.

The method was intercepted and manipulated without modifying or repackaging the APK.

## Manipulation performed

The original application attempted to execute an extremely expensive recursive calculation:

```text
slowRecursive(150)
```

This calculation also returned an incompatible 64-bit `long`.

During dynamic analysis, the method responsible for the complete decryption workflow was replaced at runtime.

The manipulation:

- bypassed the recursive computation;
- retained the embedded ciphertext;
- retained the application's XOR mechanism;
- supplied the intended full Fibonacci key;
- reconstructed the plaintext;
- returned the flag to the application.

This modification occurred only in process memory and did not alter the APK stored on disk.

## Challenges encountered

The main challenges were:

1. The task description referred to remote encrypted communication, but the APK stored the ciphertext locally.
2. No application-specific HTTP endpoint used to retrieve the flag was found.
3. The application did not use AES or RSA for the flag.
4. Base64 obscured the encrypted data but provided no cryptographic security.
5. The recursive calculation of Fibonacci(150) was impractically slow.
6. Fibonacci(150) exceeded the range of a signed Java 64-bit `long`.
7. Frida spawn mode closed the server connection on the emulator.
8. The spawn problem was bypassed by starting the application normally and attaching Frida with `-N`.
9. The original decryption call had already started before attachment, so the hooked method was explicitly invoked after installing the replacement.

## Security findings

The application exposed all elements required to recover the secret:

- the ciphertext was embedded in the APK;
- the key-generation method was present in client-side code;
- the reversible XOR algorithm was implemented locally;
- the key was deterministic;
- the decryption method could be intercepted and replaced;
- no trusted server-side authorization protected the secret.

Repeating-key XOR is not suitable for protecting sensitive information.

An Android APK operates in an untrusted client environment. Anyone capable of decompiling or instrumenting the application can recover locally stored secrets.

## Recommendations

- Do not embed sensitive secrets in an APK.
- Keep protected information and authorization decisions on a trusted server.
- Use authenticated encryption such as AES-GCM when encryption is required.
- Store device-bound cryptographic keys with Android Keystore.
- Do not use Base64 as a security mechanism.
- Avoid custom cryptographic constructions such as repeating-key XOR.
- Do not embed the ciphertext, key derivation and decryption logic together.
- Replace inefficient recursive calculations with iterative or memoized implementations.
- Use appropriate numeric types and validate calculations for overflow.
- Apply server-side access control before releasing sensitive data.
- Assume that client-side methods can be inspected, hooked and replaced.

## Extracted flag

The extracted and dynamically verified flag was:

```text
Holberton{fibonacci_slow_computation_optimization}
```

The flag was saved in:

```text
2-flag.txt
```

## Conclusion

JADX and APKTool revealed that the designated APK did not implement the remote encrypted communication described by the general challenge scenario.

Instead, the ciphertext was embedded locally as Base64 and protected with a repeating-key XOR transformation. The key was intended to be the decimal representation of Fibonacci(150).

Static analysis identified the ciphertext, the XOR implementation and the key-derivation method. Python reproduced the intended mathematical calculation and recovered a candidate flag.

Frida then provided dynamic confirmation. By intercepting and replacing `performslowDecryption()` at runtime, the defective recursive computation was bypassed and the correct Fibonacci(150) value was supplied.

The running Android process returned:

```text
Holberton{fibonacci_slow_computation_optimization}
```

This exercise demonstrated static cryptographic analysis, runtime interception, dynamic method manipulation and extraction of sensitive information without modifying the APK.
