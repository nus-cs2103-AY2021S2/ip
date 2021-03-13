# **Duke**

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project
   first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained
   in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()`.

--------------------

# User Guide

Duke is a desktop app for managing tasks (todo, events, deadlines), optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact
management tasks done faster than traditional GUI apps.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.

1. Download the latest duke.jar from [here](https://github.com/tsh22/ip/releases).

1. Copy the file to the folder you want to use as the home folder for your Duke.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

1. Refer to the Features below for details of each command that can be used.

![Image of Duke](https://raw.githubusercontent.com/tsh22/ip/master/docs/Ui.png)

## Features

### Notes about the command format:

* Words in UPPER_CASE are the parameters to be supplied by the user.
* Commands are case insensitive.
    * E.g. TODO will match todo.

* All fields specified in UPPER_CASE must be provided for the command to be valid.

### Adding a Todo task: `todo`

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Examples:

* `todo read book`
* `TODO have a meal`

### Adding a Deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DATE`

* `DATE` must be in the format YYYY-MM-DD.

Examples:

* `deadline return book /by 2021-10-10`
* `Deadline do homework /by 2021-10-12`

### Adding an Event task: `event`

Adds an event task to the task list.

Format: `event DESCRIPTION /at DATE`

* `DATE` must be in the format YYYY-MM-DD.

Examples:

* `event return book /at 2021-10-10`
* `EVENT do homework /at 2021-10-12`

### Updating a task description: `update`

Updates the description of an existing task in the task list.

Format: `update NUMBER DESCRIPTION`

* `NUMBER` must be a valid task number.

Examples:

* `update 2 return book`
* `update 6 do homework`

### Find a task using keywords: `find`

Finds an existing task that matches the keyword(s).

Format: `find KEYWORD`

* `KEYWORD` can be a single term or multiple words.
* `KEYWORD` is case sensitive.

Examples:

* `find return book`
* `find book`

### Lists all current tasks: `list`

Lists all existing tasks in the task list.

Format: `list`

### Mark a task as done: `done`

Marks one of the tasks as done.

Format: `done NUMBER`

* `NUMBER` must be a valid task number.

Examples:

* `done 3`
* `DONE 2`

### Delete an existing task: `delete`

Deletes an existing task in the task list.

Format: `delete NUMBER`

* `NUMBER` must be a valid task number.

Examples:

* `delete 3`
* `Delete 1`

### Exiting: `bye`

Ends the program and closes the window.

Format: `bye`

### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

## Command Summary

Action | Format, Examples
------- | ----------------
todo | `todo DESCRIPTION` e.g. `todo read book`, `TODO have a meal`
deadline | `deadline DESCRIPTION /by DATE` e.g. `deadline return book /by 2021-10-10`, `Deadline do homework /by 2021-10-12`
event | `event DESCRIPTION /at DATE` e.g. `event return book /at 2021-10-10`, `EVENT do homework /at 2021-10-12`
update | `update NUMBER DESCRIPTION` e.g. `update 2 return book`, `update 6 do homework`
find | `find KEYWORD` e.g. `find return book`, `find book`
list | `list`
done | `done NUMBER` e.g. `done 3`, `DONE 2`
delete | `delete NUMBER` e.g. `delete 3`, `Delete 1`
bye | `bye`

## Acknowledgement

* Background image: [link](https://i.pinimg.com/564x/4b/78/f2/4b78f244924f21ba1fc5d150d2c05c35.jpg)

* User
image: [link](https://static.wikia.nocookie.net/undertale-au-fanon/images/1/1e/Undertale_frisk_Sprite.png/revision/latest?cb=20200624132041)

* Duke
image: [link](https://www.google.com/url?sa=i&url=https%3A%2F%2Fdebatesjungle.fandom.com%2Fwiki%2FMettaton&psig=AOvVaw3srOl5r0-3p2tt80THoApY&ust=1613828616339000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJipup2K9u4CFQAAAAAdAAAAABAD)

