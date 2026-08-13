# Task 2 — Android Cryptography Challenge

## Objective

The objective was to inspect the Android application, identify its
encrypted data and cryptographic mechanism, reproduce the decryption,
and extract the hidden flag.

## Tools

- JADX
- APKTool
- ADB
- Android Studio emulator
- Burp Suite
- Python 3
- Ripgrep

## APK analysis

The APK was decompiled with JADX:

```bash
jadx \
-d "$PWD/jadx_output/task2" \
"$PWD/app-release-task2.apk"

Cryptographic functions were searched with:

rg -n -i \
'Base64|decrypt|encrypt|AES|RSA|Cipher|XOR|Fibonacci' \
jadx_output/task2/sources

The relevant code was found in:

com/holberton/task3/MainActivityKt.java

The application contained the following methods:

performslowDecryption()
slowRecursive(int)
xorDecrypt(String, String)
Network interception

Burp Suite was considered for capturing the application's HTTP
traffic. However, static analysis did not reveal an application
endpoint or code downloading the encrypted flag from a remote server.

The encrypted value was embedded directly inside the APK. Therefore,
there was no application-specific encrypted HTTP response to intercept
or modify in the provided implementation.

This observation was verified by searching the decompiled sources for
URLs and common networking libraries:

rg -n -i \
'https?://|retrofit|okhttp|volley|httpurlconnection|endpoint' \
jadx_output/task2/sources \
jadx_output/task2/resources

No relevant remote endpoint used to retrieve the flag was identified.

Encrypted data

The application decoded the following Base64 value:

cVZaW1dDQllZTFdRW1xeUlBbX21CWFtHalRZXUJFRFhNX1ZcbllGQ15cUUNSRFpcVks=

The relevant decompiled logic was:

byte[] encrypted = Base64.getDecoder().decode(
    "cVZaW1dDQllZTFdRW1xeUlBbX21CWFtH" +
    "alRZXUJFRFhNX1ZcbllGQ15cUUNSRFpcVks="
);

return xorDecrypt(
    new String(encrypted, Charsets.UTF_8),
    String.valueOf(slowRecursive(150))
);

Base64 is an encoding method, not an encryption algorithm.

Key derivation

The key was derived from Fibonacci(150):

public static final long slowRecursive(int n) {
    return n <= 1
        ? n
        : slowRecursive(n - 1) + slowRecursive(n - 2);
}

The intended mathematical value was:

9969216677189303386214405760200

Its decimal representation was used as the repeating XOR key.

Decryption algorithm

The application performed repeating-key XOR:

plaintext[i] =
    ciphertext[i] XOR key[i modulo key length]

The decompiled code applied:

key.charAt(index % key.length())
    ^ encryptedFlag.charAt(index)

Because XOR is reversible, the same key recovers the plaintext from
the ciphertext.

Decryption script

The following script reproduced the intended algorithm:

import base64

encoded = (
    "cVZaW1dDQllZTFdRW1xeUlBbX21CWFtH"
    "alRZXUJFRFhNX1ZcbllGQ15cUUNSRFpcVks="
)

ciphertext = base64.b64decode(encoded).decode("utf-8")

a, b = 0, 1

for _ in range(150):
    a, b = b, a + b

key = str(a)

plaintext = "".join(
    chr(
        ord(character)
        ^ ord(key[index % len(key)])
    )
    for index, character in enumerate(ciphertext)
)

print(plaintext)

The script returned:

Holberton{fibonacci_slow_computation_optimization}
Manipulation

The expensive recursive Fibonacci calculation was replaced during
analysis by an iterative calculation. This produced the same intended
mathematical key without waiting for the impractical recursive
execution.

The embedded Base64 value was decoded outside the application and the
XOR transformation was reproduced locally.

Challenges encountered

The main challenges were:

The task description referred to remote encrypted communication,
but the APK stored the ciphertext locally.
The application did not use AES or RSA for the flag.
Base64 initially obscured the encrypted bytes but provided no
cryptographic security.
The recursive calculation of Fibonacci(150) was deliberately slow.
Fibonacci(150) exceeds the range of a Java signed 64-bit long,
while Python supports arbitrary-precision integers.
Security findings

The application exposed all elements necessary to recover the secret:

ciphertext embedded in the APK;
key-generation method stored in client code;
reversible XOR algorithm implemented locally;
deterministic key;
no trusted server-side authorization.

Repeating-key XOR is not suitable for protecting sensitive data.
Client-side secrets must be considered recoverable by an attacker who
can inspect the APK.

Recommendations
Do not embed sensitive secrets in an APK.
Keep protected data and authorization decisions on a trusted server.
Use authenticated encryption such as AES-GCM when required.
Store local cryptographic keys with Android Keystore.
Do not use Base64 as a security mechanism.
Avoid custom cryptographic algorithms such as repeating-key XOR.
Replace inefficient recursive calculations with iterative or
memoized implementations.
Result

The extracted flag was:

Holberton{fibonacci_slow_computation_optimization}

The flag was saved in:

2-flag.txt
## Conclusion

The encrypted data was not received from a remote server. It was
embedded in the APK as Base64 and decrypted locally using repeating-key
XOR with the decimal representation of Fibonacci(150).

JADX exposed the ciphertext, key derivation and XOR implementation.
Reproducing the algorithm with Python recovered the hidden flag.
