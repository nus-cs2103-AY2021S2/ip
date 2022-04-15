# Travis Task Handler

This project is a simple chatbot that handles your task as a markdown list.

## Setting up using Gradle

Prerequisites: JDK 11, update Intellij to the most recent version, Gradle.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. Open the Gradle option on the right and click run, you should see the ui appear.

## Using the bot

These are some simple commands for you to manipulate the bot

### Task creation

- todo {name of your todo task}
- deadline {name of you deadline task} /by {date in 'dd/mm/yyyy hhhh' format}
- event {name of you event task} /at {date in 'dd/mm/yyyy hhhh' format}

### Task manipulation

The task index is 1 based.

- delete {task index}
- snooze {task index} '/at' or '/by' {date in 'dd/mm/yyyy hhhh' format}
- done {task index}
- find {string you want to find in task name}
- list

Explanations on some functions:

- find will list all task that have the string you input
- list will list out all tasks you have inputted and not deleted
