# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _
   |  _ \ _   _| | _____
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## User Guide

This is a CLI chatbot called Duke.

**Commands**

1. `list` - Displays the tasks contained in the task list.
1. `todo <task description>` - Adds a task with no time limit into the task list.
1. `deadline <task description> \by <time>` - Adds a task that needs to be done before a specific date/time into the task list.
1. `event <task description> \at <time>` - Adds a task that start at a specific time and ends at a specific time into the task list.
1. `done <index>` - Mark a specific task in the task list as done. E.g. `done 2` marks the second task in the task list as done.
1. `delete <index>` - Remove a specific task from the task list. E.g. `delete 2` removes the second task from the task list.
1. `bye` - Exits the program
