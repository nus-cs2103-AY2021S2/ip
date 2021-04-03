# Duke project Jiaxiang

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## User Guide
You can find the [user guide](https://litone01.github.io/ip/) over here.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` 
   to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in 
   [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. 
   If the setup is correct, you should see something like the below:
   ```
    ___________________________________________________________________________
      ____        _        
     |  _ \ _   _| | _____ 
     | | | | | | | |/ / _ \
     | |_| | |_| |   <  __/
     |____/ \__,_|_|\_\___|

     Greetings! I'm Your Personal Assistant Duke:)
     What can I do for you today?
    ___________________________________________________________________________
   ```

## Acknowledgements
The folder structure and certain naming conventions of functions in this project are heavily adapted from
[addressbook-level2](https://github.com/se-edu/addressbook-level2) 
by the [SE-EDU initiative](https://se-education.org/).