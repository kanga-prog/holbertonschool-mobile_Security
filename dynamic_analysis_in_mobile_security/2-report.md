# Task 2 — Android Cryptography Challenge

## 1. Objective

The objective of this challenge was to analyze the cryptographic
implementation of an Android application, identify the encrypted data
and its decryption mechanism, and recover the hidden flag.

The challenge description mentioned encrypted HTTP communication and
the possible use of algorithms such as AES or RSA. The implementation
found in the provided APK was therefore examined to verify its actual
behavior.

## 2. Analysis environment

The analysis was performed locally in a controlled environment using:

- Kali Linux
- Android Studio
- Pixel 4 Android Virtual Device
- Android 11, API 30
- Android Debug Bridge
- JADX 1.5.6
- APKTool
- Ripgrep
- Python 3

The analyzed APK was:

```text
app-release-task2.apk
