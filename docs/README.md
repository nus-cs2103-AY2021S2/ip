# Duke project User Guide

Duke Application manages the everyday tasks of users.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()`.

## Features 
* Add a todo Task
   * **Format:** `todo TASK_DESCRIPTION`
   * **Parameters**
      1. Description of the task
   * **Example:** `todo make breakfast`
     <br><br>
     
* Add a deadline Task
   * **Format:** `deadline TASK_DESCRIPTION /by DEADLINE`
   * **Parameters**
      1. Description of the task
      2. Deadline of the task 
   * **Example:** `deadline Submit ME3103 report /by 2020-02-19`
     <br><br>
     
* Add an event Task
   * **Format:** `event TASK_DESCRIPTION /at EVENT_LOCATION_OR_TIME`
   * **Parameters**
      1. Description of the task
      2. Event location or time
   * **Example:** `event project meeting /at Mon 2-4pm`
   <br><br>
     
* List all tasks
   * **Format:** `list`
   * **Example:** `list`
     <br><br>
     
* Mark a task as done
   * **Format:** `done TASK_INDEX`
   * **Parameters**
      1. Index of the task
   * **Example:** `done 2`
   <br><br>

* Delete single/multiple tasks
   * **Format:** `delete INDEX_1 INDEX_2 ...`
   * **Parameters**
      1. Index of the task to delete 
   * **Example:** `delete 2`, `delete 1 2 5` 
   <br><br>

* Find tasks with keywords
   * **Format:** `find KEYWORD`
   * **Parameters**
      1. A keyword 
   * **Example:** `find egg`, `find CS2103`
   <br><br>
     
* Save all changes and exit
   * **Format:** `bye`
   * **Example:** `bye`
   
## Command Summary

| Actions | Format |
| ------ | ------ |
| Add a todo task | todo TASK_DESCRIPTION |
| Add a deadline | deadline TASK_DESCRIPTION /by DEADLINE |
| Add an event Task | event TASK_DESCRIPTION /at EVENT_LOCATION_OR_TIME |
| List all tasks | list |
| Mark a task as done | done TASK_INDEX |
| Delete single/multiple tasks | delete INDEX_1 INDEX_2 ... |
| Find tasks with keywords | find KEYWORD |
| Save all changes and exit | bye |
   

   

   
   