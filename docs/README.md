# Duke - User Guide
By: `@jxrrelo`      Since: `Feb 2021`      Licence: `MIT`

## Introduction

Duke is for those who *prefer to use a desktop app for managing tasks*. More importantly, Duke is personal assistant *optimized for those who prefer to work with a Command Line Interface* (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can manage your tasks faster than traditional GUI apps. Interested? Jump to the <<Quick Start>> to get started. Enjoy!

## Quick Start

- Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) installed on your Computer.
- Download the latest `ip.jar` [here](https://github.com/jxrrelo/duke/releases).
- Copy the file to the folder you want to use as the home folder for Duke.
- Double-click the file to start the app. This might take up to a few seconds.

![](Ui.png?raw=true)

-  Type the command in the command box and press kbd:[Enter] to execute it. +
   e.g. typing *`help`* and pressing kbd:[Enter] will open the help window.
-  Some example commands you can try:

    * `list`
    * `done`
    * `find`
    * `todo`
    * `deadline`
    * `event`
    * `delete`
    * `update`
    * `help`
    * `exit`

## Features

### List all tasks : `list`
+ Format: `list`
+ Example: `list`
+ Effect: Outputs the list of tasks

[TIP]
List of tasks is saved automatically onto your computer's local hard drive and will be loaded the next time you use Duke.

### Marking a task done: `done`
+ Format: `done INDEX`
+ Example: `done 2`
+ Effect: Marks task 2 as done

[TIP] <br />
Index has to be within the valid range, depending on how many tasks there are in the list. <br />
You may call `done` on the same task again to mark it as undone.

### Finding a task: `find`
+ Format: `find DESCRIPTION`
+ Example: `find homework`
+ Effect: Finds and outputs all tasks containing the word "homework"

[TIP] <br />
Description permits more than a single word (i.e. `find Math Homework`). <br />
The query is case insensitive (i.e. `homework` will match `Homework`).

### Adding a todo: `todo`
+ Format: `todo DESCRIPTION`
+ Example: `todo lunch`
+ Effect: Adds the todo "lunch"

[TIP] <br />
Description permits more than a single word (i.e. `todo Throw Garbage`)

### Adding a deadline: `deadline`
+ Format: `deadline DESCRIPTION /by DATE TIME`
+ Example: `deadline Homework /by 02/04/2021 1800`
+ Effect: Adds the deadline "Homework" due on 2 April 2021 at 1800 hrs

[TIP] <br />
DATE TIME only permits the format `dd/MM/yyyy HHmm`

### Adding a event: `event`
+ Format: `event DESCRIPTION /at DATE TIME`
+ Example: `event Concert /at 02/04/2021 1800`
+ Effect:  Adds the event "Concert" happening on 2 April 2021 at 1800 hrs

[TIP] <br />
DATE TIME only permits the format `dd/MM/yyyy HHmm`

### Deleting a task: `delete`
+ Format: `delete INDEX`
+ Example: `delete 2`
+ Effect: Deletes task 2

[TIP] <br />
Index has to be within the valid range, depending on how many tasks there are in the list. <br />

### Updating a task : `update`
+ Format: `update INDEX TASK_TYPE_COMMAND`
+ Example: `update 2 deadline Science Homework /by 02/04/2021 1800`
+ Effect: Updates task 2 as a deadline with description "Science Homework" to be done by 2 April 2021 at 1800 hrs

[TIP] <br />
INDEX has to be within the valid range, depending on how many tasks there are in the list. <br />
TASK_TYPE_COMMAND has to be a valid task type (i.e. todo, deadline, event) in their respective valid formats.

### Asking Duke for help: `help`
+ Format: `help`
+ Example: `help`
+ Effect: Outputs the help command list

### Terminating the program : `exit`
+ Format: `exit`
+ Example: `exit`
+ Effect: Terminates Duke

## FAQ
*Q*: How do I transfer my data to another Computer? +
*A*: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

## Command Summary
* *List* : `list`
* *Done* : `done INDEX`
* *Find* : `find DESCRIPTION`
* *Todo* : `todo DESCRIPTION`
* *Deadline* : `deadline DESCRIPTION /by DATE TIME`
* *Event* : `event DESCRIPTION /at DATE TIME`
* *Delete* : `delete INDEX`
* *Update* : `update INDEX TASK_TYPE_COMMAND`
* *Help* : `help`
* *Exit* : `exit`

This user guide format has been adapted from [addressbook level 3](https://github.com/nus-cs2103-AY1920S2/addressbook-level3/blob/master/docs/UserGuide.adoc)
