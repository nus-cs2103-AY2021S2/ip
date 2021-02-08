# Elaina

![Checkstyle](https://github.com/lirc572/ip/workflows/Checkstyle/badge.svg)
![Unit Test](https://github.com/lirc572/ip/workflows/Unit%20Test/badge.svg)
![Create JAR](https://github.com/lirc572/ip/workflows/Create%20JAR/badge.svg)

Elaina is the ultimate personal assistant chatbot.

## Setting up in IntelliJ

Prerequisites: JDK 11, update IntelliJ to the most recent version.

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project in IntelliJ as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/com/lirc572/ip/Starter.java` file, right-click it, and choose `Run ...`. If the setup is correct, you should see a GUI window pop up.

## Setting up in Visual Studio Code

Prerequisites: JDK 11, update Visual Studio Code to the most recent version.

1. Open VSCode
1. Open the project in VS Code as follows:
   1. Click `File`.
   1. Click `Open Folder...`.
   1. Select the project directory, and click `Select Folder`.
   1. If there is a prompt asking you to install the recommended extensions, click `Install`.
1. After that, open a terminal in the project directory and run `./gradlew run`. If the setup is correct, you should see a GUI window pop up.
