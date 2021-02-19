# Duke User Guide

---

## What is Duke?

Duke is a simple desktop application that allows you to keep track your tasks in a task list through the use of a GUI.

---

## Before you begin...
1. Ensure you have Java `11` or above installed in your system.
2. Download the latest `duke.jar` from [here](https://github.com/lrj689/ip/releases/tag/1.0).
3. Place `duke.jar` in an empty folder of your choice.
4. Double click to start the app.

---

## Features

### Notes about formatting

* Commands given are case-sensitive.
* Square brackets `[ ]` are not needed when entering commands.

### Viewing help : `help`

Duke will provide you with a quick rundown on the commands available.

Format: `help`

### Adding a todo task : `todo`

Adds a task into your list.

Format: `todo [TASK_NAME]`

Example:
* `todo homework` Adds task `homework` to your list.

### Adding a deadline task : `deadline`

Adds a task with a deadline into your list.

Format: `deadline [TASK_NAME] /by [DATE]`

Example:
* `deadline project /by 2021-02-19` Adds a task `project` with a deadline on `19 Feb 2021` into your list.

### Adding an event task : `event`

Adds a task with a start date into your list.

Format: `event [TASK_NAME] /at [DATE]`

Example:
* `event exam /at 2021-03-02` Adds a task `exam` with a start date of `02 Mar 2021` into your list.

### Listing all tasks : `list`

Shows a list of all your tasks.

Format: `list`

### Deleting a task : `delete`

Deletes a specified task from your list.

**!!WARNING!!** This command cannot be undone!

Format: `delete [TASK_NUMBER]`
* `[TASK_NUMBER]` must be a **positive integer**.
* `delete` will not work if the task specified does not exist in your list.

Example:
*  `delete 1` Deletes the 1st task from your list

### Deleting all your tasks : `reset`

Deletes **all** tasks from your list.

**!!WARNING!!** This command cannot be undone!

Format `reset`

### Finding tasks that matches a keyword : `find`

Finds all tasks that matches a specified keyword.

Format: `find [KEYWORD]`
* `[KEYWORD]` can be more than 1 word i.e. `go to school`

Example: 
* `find school` Shows all tasks with names containing `school` keyword i.e. `go to school`

### Showing tasks that matches a given date : `show`

Shows all tasks have deadlines or start dates on a given date.

Format: `show [DATE]`
* `[DATE]` follows `YYYY-MM-DD` format.
* Given `DATE` must be **valid** e.g. `2021-02-31` `222222-999-11` are not valid dates.

Example:
*  `show 2021-03-03` Shows all tasks that have deadlines or start dates on `03 Mar 2021`

### Marking a task as done : `done`

Marks a specified task as done.

Format: `done [TASK_NUMBER]`
* `[TASK_NUMBER]` must be a **positive integer**.
* `done` will not work if the task specified does not exist in your list.

Example:
* `done 1` Marks the 1st task in your list as done.

### Saving and exiting : `bye`

Saves your list and exits Duke automatically.

**!!WARNING!!**
Closing Duke without entering `bye` will **not save any changes** made to your list for that session.

Format: `bye`

---

## FAQ

Q: How do I transfer my data to another system?

A: You can transfer your data by moving the data file located at `your_home_folder/data` to another home folder for Duke.

---

## Command summary


| Command       |        Format                     |
|:-------------:|:---------------------------------:|
| Todo          | `todo [TASK_NAME]`                |
| Deadline      | `deadline [TASK_NAME] /by [DATE]` | 
| Event         | `event [TASK_NAME] /at [DATE]`    |
| List          | `list`                            |
| Delete        | `delete [TASK_NUMBER]`            | 
| Reset         | `reset`                           |
| Find          | `find [KEYWORD]`                  |
| Show          | `show [DATE]`                     | 
| Done          | `done [TASK NUMBER]`              |
| Bye           | `bye`                             |
