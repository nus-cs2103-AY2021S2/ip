<p align="center">
  <img src="https://raw.githubusercontent.com/tanboonji/ip/master/docs/images/ApplicationIcon.png" alt="Application Icon" height="240"/>
</p>

# Jhin User Guide

Jhin is a **desktop chatbot app for managing your tasks** so that you can focus on doing than remembering. It is
optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface
(GUI).

* [Quick Start](#quick-start)
* [Features](#features)
  * [Listing all tasks: `list`, `ls`](#listing-all-tasks-list-ls)
  * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
  * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
  * [Adding an event task: `event`](#adding-an-event-task-event)
  * [Marking a task as done: `done`](#marking-a-task-as-done-done)
  * [Deleting a task: `delete`, `rm`](#deleting-a-task-delete-rm)
  * [Finding a task by description: `find`, `search`](#finding-a-task-by-description-find-search)
  * [Listing all command aliases: `listalias`, `lsalias`](#listing-all-command-aliases-listalias-lsalias)
  * [Adding a command alias: `alias`](#adding-a-command-alias-alias)
  * [Deleting a command alias: `deletealias`, `rmalias`](#deleting-a-command-alias-deletealias-rmalias)
  * [Viewing help: `help`](#viewing-help-help)
  * [Exiting the application: `bye`, `exit`](#exiting-the-application-bye-exit)
* [Command Summary](#command-summary)
* [Miscellaneous](#miscellaneous)
  * [Auto Save](#auto-save)
  * [Transferring Your Data](#transferring-your-data)
  * [Date Time Support](#date-time-format)
    * [Date Format](#date-format)
    * [Time Format](#time-format)

## Quick Start

1. Ensure that you have [**Java 11**](https://www.oracle.com/java/) or above installed in your computer.
2. Download the latest `jhin.jar` from [here](https://github.com/tanboonji/ip/releases).
3. Copy the jar file to the folder you want to use as the **home folder** for your Jhin.
4. Double-click the jar file to start Jhin. The GUI similar to the below should appear in a few seconds.
    * If the app does not start, you can use the following command in your command prompt to start the app.
      ```shell
      java -jar jhin.jar
      ```
<img src="https://raw.githubusercontent.com/tanboonji/ip/master/docs/images/Startup.png" alt="Startup Screenshot" height="700" align="center"/><br>
5. Start using Jhin!

## Features

* `[COMMAND1, COMMAND2]`: The command can be executed with either `COMMAND1` or `COMMAND2`.
* `<VALUE>`: This command arguments is compulsory.
* `{VALUE}`: This command arguments is optional.

### Listing all tasks: `list`, `ls`

Shows a list of all tasks in Jhin.

Format: `[list, ls]`

Example:

* `list`
* `ls`

### Adding a todo task: `todo`

Adds a todo task to Jhin.

Format: `todo <description>`

Example:

* `todo read book`
* `todo do iP`

### Adding a deadline task: `deadline`

Adds a deadline task to Jhin.

Format: `deadline <description> /by {date} {time}`

* The list of accepted date time format can be found [here](#date-time-format).
* If no date is entered, the date will be automatically set to the current date today.
* If no time is entered, the time will be automatically set to 11:59PM.

Example:

* `deadline return book /by 01012021 0000`
* `deadline finish iP /by 020221`

### Adding an event task: `event`

Adds an event task to Jhin.

Format: `event <description> /at {date} {time}`

* The list of accepted date time format can be found [here](#date-time-format).
* If no date is entered, the date will be automatically set to the current date today.
* If no time is entered, the time will be automatically set to 11:59PM.

Example:

* `event book release /at 15022021 14`
* `event midterms /at`

### Marking a task as done: `done`

Marks the specified task as completed.

Format: `done <task index>`

* The task index is shown in the [`list`, `ls` command](#listing-all-tasks-list-ls).
* The task index must be a positive integer.

Example:

* `done 1`
* `done 10`

### Deleting a task: `delete`, `rm`

Deletes the specified task from Jhin.

Format: `[delete, rm] <task index>`

* The task index is shown in the [`list`, `ls`](#listing-all-tasks-list-ls) command.
* The task index must be a positive integer.

Example:

* `delete 1`
* `delete 10`

### Finding a task by description: `find`, `search`

Finds all the tasks whose description contains the given keyword.

Format: `[find, search] {keyword}`

* The search is not case-sensitive, therefore *books* will match *Books*.
* Only the task description will be searched.
* Partial words will be matched, therefore *bo* will match *books*.
* If no keyword is entered, all tasks in Jhin will be shown.

Example:

* `find`
* `search book`
* `find iP`

### Listing all command aliases: `listalias`, `lsalias`

Shows a list of all aliases in Jhin.

Format: `[listalias, lsalias]`

Example:

* `listalias`
* `ls`

### Adding a command alias: `alias`

Adds a command alias to Jhin.

Format: `alias <alias> <command>`

Example:

* `alias l list`
* `alias da deletealias`

### Deleting a command alias: `deletealias`, `rmalias`

Deletes the specified alias from Jhin.

Format: `[deletealias, rmalias] <alias>`

Example:
* `deletealias l`
* `rmalias da`

### Viewing help: `help`

Shows a list of all commands and their format in Jhin.

Format: `help`

### Exiting the application: `bye`, `exit`

Exits the program.

Format: `[bye, exit]`

Example:

* `bye`
* `exit`

## Command Summary

Action | Command | Format, Examples
---|---|---
List task | `list`, `ls` | `[list, ls]` <br> e.g. `list`, `ls`
Add todo | `todo` | `todo <description>` <br> e.g.`todo read book`, `todo do iP`
Add deadline | `deadline` | `deadline <description> /by {date} {time}` <br> e.g. `deadline return book /by 01012021 0000`, <br> `deadline finish iP /by 020221`
Add event | `event` | `event <description> /at {date} {time}` <br> e.g. `event book release /at 15022021 14`, <br> `event midterms /at`
Mark task as done| `done` | `done <task index>` <br> e.g. `done 1`, `done 10`
Delete task | `delete`, `rm` | `[delete, rm] <task index>` <br> e.g. `delete 1`, `rm 10`
Find task | `find`, `search` | `[find, search] {keyword}` <br> e.g. `find`, `search book`, `find iP`
List alias | `listalias`, `lsalias` | `[listalias, lsalias]` <br> e.g. `listalias`, `lsalias`
Add alias | `alias` | `alias <alias> <command>` <br> e.g. `alias l list`, `alias da deletealias`
Delete alias | `deletealias`, `rmalias` | `[deletealias, rmalias]` <br> e.g. `deletealias l`, `rmalias da`
Help | `help` | `help`
Exit application | `bye`, `exit` | `[bye, exit]` <br> e.g. `bye`, `exit`

## Miscellaneous

### Auto Save

Jhin automatically saves all the data onto your local computer whenever any data is updated. You do not need to worry
about losing your data.

### Transferring Your Data

You can transfer all your saved data to another computer by copying the saved data files on your current computer to
your new computer. The saved data files are located in the same directory as your .jar file.

There are two saved data files:

* task.data
* alias.data

### Date Time Format

Jhin supports a variety of date time formats so that you do not need to enter the full date time every time.

* Missing date will be automatically set to the current date today.
* Missing time will be automatically set to 11:59PM.
* Missing date time will be automatically set to the current date today, 11:59PM.
* Date time in Jhin will be shown in the 12-hours clock.

#### Date Format

List of supported date formats:

Date Format | Example
---|---
ddMMyyyy | 01012021
ddMMyy | 010121
dd/MM/yyyy | 01/01/2021
dd/MM/yy | 01/01/21
dd-MM-yyyy | 01-01-2021
dd-MM-yy | 01-01-21
dd.MM.yyyy | 01.01.2021
dd.MM.yy | 01.01.21

#### Time Format

List of support time formats:

Time Format | Example
---|---
hmma | 1100PM
h:mma | 11:00PM
ha | 11PM
HHmm | 2300
HH:mm| 23:00
HH | 23
