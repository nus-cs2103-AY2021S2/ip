# Duke Project

Say hello to Duke! It is a personal assistant chatbot to help you manage your tasks.\
Given below are instructions on how to use it so give it a go!

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/duke/launcher.java` file, right-click it, and choose `Run Launcher.main()`. There should be a popup for you to interact with Duke.

## Commands

Here are some of the ways for Duke to understand you:

Action | Command Format | Examples
------------ | ------------- | -------------
To list all current tasks | `list` | e.g. `list`
To add a todo task | `todo` | e.g. `todo homework`
To add a deadline task | `deadline` | e.g. `deadline project /by 2020-01-01 23:59`
To add an event task | `event` | e.g. `event party /at 2020-01-01 18:00`
To delete a task | `delete` | e.g. `delete 2` will delete the 2nd task in the list
To mark a task as done | `done` | e.g. `done 3` will mark the 3rd task in the list as done
To find tasks with a <br />specific keyword | `find` | e.g. `find book` will list all existing tasks with the <br />word `book`
To list tasks with a <br />specific date | `taskdate` | e.g. `taskdate 2020-01-01` will list all existing tasks <br />with the date `2020-01-01`
To exit | `bye` | e.g. `bye`