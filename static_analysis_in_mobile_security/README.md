# Mobile Security

This repository contains projects and exercises related to mobile application security, static analysis, and reverse engineering.

## Static Analysis in Mobile Security

The objective of this project is to analyze an Android application without executing it. Static analysis makes it possible to inspect an APK's structure, resources, manifest, bytecode, and decompiled source code.

### Learning objectives

* Understand the internal structure of an APK
* Extract and inspect APK contents
* Analyze the Android manifest
* Decompile Dalvik bytecode
* Identify encoded or obfuscated strings
* Understand input-validation logic
* Reconstruct hidden information
* Use static-analysis tools in a controlled environment

## Tools

The following tools were used:

* `unzip` — extracts the APK contents
* JADX — converts DEX bytecode into readable Java code
* Apktool — extracts resources and produces Smali code
* Ripgrep (`rg`) — searches efficiently through source files
* `file`, `strings`, `xxd` and `base64` — inspect and decode data

## Repository structure

```text
holbertonschool-mobile_Security/
├── README.md
└── static_analysis_in_mobile_security/
    └── 0-flag.txt
```

The APK and generated analysis directories are not included in the repository.

## Static analysis methodology

The APK was first extracted to inspect its internal files:

```bash
mkdir -p analysis/apk_unzip
unzip -q APK0 -d analysis/apk_unzip
```

The application was then decompiled with JADX:

```bash
jadx \
  -d "$(pwd)/analysis/jadx" \
  "$(pwd)/APK0"
```

The application-specific source code was identified and searched for relevant validation logic:

```bash
rg -n -i \
  'flag|correct|incorrect|check|verify|equals|xor|Base64' \
  analysis/jadx/sources
```

The analysis focused on:

* string construction;
* hexadecimal values;
* ASCII character arrays;
* XOR operations;
* user-input comparisons;
* validation messages.

When JADX could not reconstruct a method correctly, Apktool and Smali code could be used as an alternative:

```bash
apktool d -f APK0 -o analysis/apktool
```

## Task 0 — Android App Security

The objective of this task was to recover the input expected by the Android application using static analysis only.

The validation logic was located in the application's decompiled source code. The expected value was constructed from several elements:

* hexadecimal strings;
* ASCII character arrays;
* plain-text fragments;
* reversible XOR transformations.

The reconstructed value was saved in:

```text
static_analysis_in_mobile_security/0-flag.txt
```

## Security considerations

The analyzed APK and generated files must not be committed unless explicitly required.

A suitable `.gitignore` configuration is:

```gitignore
APK0
*.apk
analysis/
jadx*/
apktool*/
```

Mobile application analysis must only be performed on applications that you own or are explicitly authorized to examine.

## Author

Kanga Kouakiu Brice

