# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI).

* Quick start
* Features
    * Viewing help: `help`
    * Adding a ToDo: `todo`
    * Adding a Deadline: `deadline`
    * Adding an Event: `event`  
    * Listing all tasks: `list`
    * Searching for a task using keywords: `find`
    * Tagging a task: `tag`
    * Marking a task as completed: `done`
    * Deleting a task: `delete`
    * Exiting the program: `bye`
    * Saving the data
    * Editing the data file
* FAQ
* Command summary

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.

1. Download the latest duke.jar from https://github.com/mabel-kang/ip/releases.

1. Copy the file to the folder you want to use as the home folder for your Duke.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 

    ![Screenshot of GUI](Ui.png)

1. Type the command in the command box and press Enter to execute it. Eg, typing `help` and pressing Enter
    will open the help information.
    Some example commands you can try: 
   * `list`: Lists all tasks.
   * `todo cook`: Adds a ToDo task with description 'cook'.
   * `delete 2`: Deletes the 2nd task shown in the current list.
   * `bye`: Exits the app.
   
1. Refer to the Features below for details of each command. 


## Features 

#### Notes about the command format: 
Words in `UPPER_CASE` are the parameters to be supplied by the user.
Eg, in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo cook`.

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`

### Adding a ToDo: `todo`

Adds a ToDo to the task list.

Format: `todo DESCRIPTION`

Example: `todo cook`

Expected Outcome: 

`Done! One new task: `

`[T][] cook`

`Now you have 1 task in the list`

* This outcome is based on the situation where the list is initially empty.

### Adding a Deadline: `deadline`

Adds a Deadline to the task list. 

Format: `deadline DESCRIPTION /by DATE_AND_TIME`
* `DATE_AND_TIME` should be in the form yyyy-mm-dd HHmm

Example: `deadline project /by 2021-02-19 2359`

Expected Outcome:

`Done! One new task: `

`[D][] project (by: Feb 19 2021 11:59 PM)`

`Now you have 2 tasks in the list`


### Adding a Event: `event`

Adds an Event to the task list.

Format: `event DESCRIPTION /at DATE_AND_TIME`
* `DATE_AND_TIME` should be in the form yyyy-mm-dd HHmm

Example: `event meeting /at 2021-02-18 0900`

Expected Outcome:

`Done! One new task: `

`[E][] meeting (at: Feb 18 2021 9:00 AM)`

`Now you have 3 tasks in the list`


### Listing all tasks: `list`

Shows a list of all tasks in the application.

Format: `list`

Expected Outcome: 

`Here are the tasks in your list:` 


`1.[T][] cook`

`2.[D][] project (by: Feb 19 2021 11:59 PM)`

`3.[E][] meeting (at: Feb 18 2021 9:00 AM)`

### Searching for a task using keywords: `find`
Find tasks with descriptions containing the keywords specified.

Format: `find KEYWORD`
* The search is case-insensitive. Eg, `COOK` will match `cook`
* Order of keywords matter. Eg, `finish project` will not match `project finish`
* Only tasks matching all the keywords will be returned.

Examples: 

* `find project` returns `project` and `project meeting`
* `find cook` returns `cook`

Expected Outcome:

`Here are the matching tasks in your list:`

`[T][] cook`

### Tagging a task: `tag`

Tags a task based on the tag given as input.

Format: `tag INDEX /DESCRIPTION`

* Tags the task at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer.

Examples:

* `tag 1 /daily` tags the 1st task in the list as 'daily'

Expected Outcome:

`cook is now tagged as #daily`

### Marking a task as completed: `done`

Marks a specified task from the task list as done. 

Format: `done INDEX`
* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer.

Example: 

* `done 2` marks the 2nd task in the task list as done.

Expected Outcome:

`Nice! I've marked this task as done:`

`[D][X] project (by: Feb 19 2021 11:59 PM)`

### Deleting a task: `delete`

Deletes a specified task from the task list.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer.

Example:

* `list` followed by `delete 2` deletes the 2nd task in the task list.

Expected Outcome:

`Noted. I've removed this task:`

`[D][X] project (by: Feb 19 2021 11:59 PM)`

`Now you have 2 tasks in the list`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Editing the data file

Duke data are saved as a TXT file `[JAR file location]/data/tasks.txt`. Advanced users are welcome
to update data directly by editing that data file.

Caution: If your changes to the data file makes its format invalid, Duke will not be able to run.
If that happens, please delete the data file.

## FAQ

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file 
that contains the data of your previous Duke home folder.

## Command Summary

Action | Format, Examples
-------| ----------------
help | `help`
todo   | `todo DESCRIPTION` eg,`todo cook`
deadline | `deadline DESCRIPTION /by DATE_AND_TIME` eg, `deadline project /by 2021-02-19 2359`
event | `event DESCRIPTION /at DATE_AND_TIME` eg, `event meeting /at 2021-02-18 0900`
list | `list`
find | `find KEYWORD` eg, `find cook`
tag | `tag INDEX /DESCRIPTION` eg, `tag 1/daily`
done | `done` eg, `done 2`
delete | `delete INDEX` eg, `delete 2`
exit | `bye`





