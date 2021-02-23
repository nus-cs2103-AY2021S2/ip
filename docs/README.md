# User Guide

Duke is a desktop app for **managing tasks**, and it is optimized for use via a **Command Line
Interface (CLI)**
while still having the benefits of a **Graphical User Interface (GUI)**.

Duke's GUI is in the form of a chatbot.

<div align="center">
   <h1>Duke Chatbot</h1>
   <img src="Ui.png" />
</div>

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
  * [Feature Overview](#feature-overview)
  * [Adding a new deadline: `deadline`](#adding-a-new-deadline-deadline)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Marking a task as completed: `done`](#marking-a-task-as-completed-done)
  * [Adding a new event: `event`](#adding-a-new-event-event)
  * [Exiting the application: `exit`](#exiting-the-application-exit)
  * [Locating a task by description: `find`](#locating-a-task-by-description-find)
  * [Viewing help: `help`](#viewing-help-help)
  * [List all tasks: `list`](#list-all-tasks-list)
  * [Adding a new todo: `todo`](#adding-a-new-todo-todo)
- [FAQ](#faq)
  * [Where is the save data stored?](#where-is-the-save-data-stored)
- [Date/Time Formats](#datetime-formats)

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

See [Date/Time Formats](#datetime-formats) for the formatting.

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

See [Date/Time Formats](#datetime-formats) for the formatting.

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

## FAQ

### Where is the save data stored?

The save data for Duke is stored under the user's home folder.

Windows: `C:\Users\${current_user_name}`
Ubuntu/Linux: `/home/${current_user_name}`

## Date/Time Formats

The allowed date formats are:

- `dd-MM-yyyy` (19-02-2021)
- `dd/MM/yyyy` (19/02/2021)
- `ddMMyyyy` (19/02/2021)
- Mon or Monday

The allowed time formats are:

- 12 hour format
    - 11PM or 11 PM
    - 11:59PM or 11:59 PM
    - 1159PM or 1159 PM
- 24 hour format
    - 23:59 or 2359
    - 0300 or 300
    - 0000