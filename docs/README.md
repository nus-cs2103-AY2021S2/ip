# User Guide

Duke is a desktop app for **managing tasks**, and it is optimized for use via a **Command Line
Interface (CLI)**
while still having the benefits of a **Graphical User Interface (GUI)**.

Duke's GUI is in the form of a chatbot.

![Ui](./Ui.png)

## Quick Start

1. Ensure that you have Java 11 or above installed in your computer
2. Download the
   latest `duke.jar` [here](https://github.com/yaowei-soc/ip/releases/download/release/duke.jar)

## Features

### Feature Overview

- [deadline](#adding-a-new-deadline-deadline)
- [delete](#deleting-a-task-delete)
- [done](#marking-a-task-as-completed-done)
- [event](#adding-a-new-event-event)
- [exit](#exiting-the-application-exit)
- [find](#locating-a-task-by-description-find)
- [help](#viewing-help-help)
- [list](#list-all-tasks-list)
- [todo](#adding-a-new-todo-todo)

### Adding a new deadline: `deadline`

Adds a new deadline task to the task list.

Format: `deadline DESCRIPTION /by DATE`

Examples:

- `deadline Read books /by Monday`
- `deadline Read books /by 12/12/2021`

### Deleting a task: `delete`

Deletes a specified task from the task list.

Format: `delete INDEX`

- The index refers to the index number shown in the displayed task list
- The index **must be a positive integer** (e.g. 1, 2, 3, ...)

Examples:

- `delete 2`
  Deletes the second item in the task list
- `delete 10`
  Deletes the tenth item in the task list

### Marking a task as completed: `done`

Marks a task as completed.

Format: `done INDEX`

- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** (e.g. 1, 2, 3, ...)

Examples:

- `done 1`
  Marks the first item in the task list as completed
- `done 10`
  Marks the tenth item in the task list as completed

### Adding a new event: `event`

Adds a new event task to the task list.

Format: `event DESCRIPTION /at DATE`

Examples:

- `event CS2103 Tutorial /at Monday`
- `event CS2103 Tutorial /at 12/12/2021`

### Exiting the application: `exit`

Exits the Duke chatbot.

Format: `exit`

### Locating a task by description: `find`

Finds task where description contains keyword.

Format: `find KEYWORD`

- The search is case-insensitive (e.g. hans will match Hans)
- Only the task description is searched, not the date
- Partial words will match full words (e.g. han will match hans)

Examples:

- `find 2103`
  Will return tasks which has the keyword `2103` in the description (e.g. CS2103, cs2103, 2103
  rocks)
- `find make`
  Will return tasks which has the keyword `make` in the description (e.g. maker, make cake,
  troublemaker)

### Viewing help: `help`

Displays the help message which contains all the information on each command available.

Format: `help`

### List all tasks: `list`

Lists out all the tasks in the task list.

Format: `list`

### Adding a new todo: `todo`

Adds a new todo task to the task list.

Format: `todo DESCRIPTION`

Examples:

- `todo read books`
- `todo buy eggs`
