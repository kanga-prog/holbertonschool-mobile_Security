3. Android Security Challenge: Revealing Hidden Functions
Welcome to the Revealing Hidden Functions challenge! This task is designed to enhance your skills in reverse engineering, dynamic analysis, and application security. You will work with a custom Android application that contains hidden functions responsible for retrieving a secret message (the flag). Your objective is to locate and invoke these hidden functions using advanced analysis tools.

In this challenge, you will interact with an Android application that includes concealed functions, which decrypt and display a secret flag. These functions are not called during the app's normal execution, making them inaccessible through standard usage. To retrieve the flag, you must employ dynamic analysis tools to locate and invoke the hidden functions without altering the app's source code.

Objectives:

By completing this challenge, you will:

Enhance Reverse Engineering Skills: Learn to dissect and understand application binaries without source code access.

Master Dynamic Analysis Tools: Gain hands-on experience with tools like Frida, Objection, and GDB to manipulate and analyze running applications.

Understand Application Security: Appreciate the importance of secure coding practices to protect sensitive functions and data within applications.

tools:

Ensure you have the following tools and environments set up before starting:

Android Studio (latest version recommended)
Android Device or Emulator (API level 24 or higher)
Frida
Objection
GDB
APKTool
jadx
Python (for running helper scripts)
Instructions:
Your goal is to retrieve the hidden secret by performing the following tasks:
- Decompile the Android application using tools like APKTool or jadx to inspect its code and understand its structure.
- Identify any obfuscated or hidden methods that could potentially reveal the secret.
- Use dynamic analysis tools such as Frida or Objection to hook into the application’s runtime.
- Locate the hidden functions responsible for decrypting and displaying the secret.
- Invoke these functions to retrieve the secret message.
- Understand the encoding mechanism used within the hidden functions.
- Reverse the encoding to reveal the original secret message.
Hints

Utilize Frida scripts to dynamically hook into method calls and alter application behavior.

Use Objection to make it easier to explore the app’s runtime and bypass security features.

Monitor log outputs during execution to identify any relevant information about hidden functions.

Pay attention to how data is passed between functions; understanding parameters will aid in successfully invoking them.

Deliverables

A report detailing the process of locating, invoking, and retrieving the hidden functions, including any challenges faced.

The decrypted flag obtained from the hidden functions.

Target APK : Apk_task3

Repo:

GitHub repository: holbertonschool-mobile_Security
Directory: dynamic_analysis_in_mobile_security
File: 3-flag.txt, 3-report.md
Score of the task
0 /1 pt
0.0%
My GitHub
Connect as: kanga-prog
