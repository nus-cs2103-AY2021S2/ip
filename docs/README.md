# Duke User Guide

## Introduction

Duke is a chat bot app that helps you to organise your tasks in a CLI manner with graphical feedback.

## Features
- [Adding a todo task - `todo`](#todo)
- [Adding an event task - `event`](#event)
- [Adding a deadline task - `deadline`](#deadline)
- [Listing all tasks - `list`](#list)
- [Tagging a task - `tag`](#tag)
- [Finding a task - `find`](#find)
- [Deleting a task - `delete`](#delete)
- [Marking a task as done - `done`](#done)
- [Exiting the program - `bye`](#bye) 

## Quickstart
1. Ensure you have Java 11 or above installed in your Computer.

1. Download the latest dukechatbot.jar from here.

1. Copy the file to the folder you want to use as the home folder for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

1. Type the command into the textbox to interact with the chat bot

**Notes about the command format:**

Commands are presented in the format: `command ARGS`

- Words in `[brackets]` are optional and may not be required
- Words with a bar|seperating indicate one of the option should be used and only one is needed
- Words in UPPERCASE are arguments to be supplied by the user

## Usage

### <a name="todo">`todo` - Adding a todo task</a>

Adds a todo task with no due date included

Format: `todo TASK_NAME`

###  <a name="deadline">`deadline` - Adding a deadline task</a>

Adds a deadline task that needs to be done before the due date.

Format: `deadline TASK_NAME /by DATE`

###  <a name="event">`event` - Adding an event task</a>

Adds an event task that occurs at a specific time.

Format: `event TASK_NAME /at DATE`

### <a name="list">`list` - Listing all the tasks</a>
Shows a list of tasks recorded with Duke

Format: `list`

### <a name="done">`done` - Marking a task as done</a>

Marks a specific task as done using the index of the task.

Format: `done INDEX`

### <a name="find">`find` - Finding a task by name or tag</a>
Finds a task which contains the given word in the name or associate with the tag

Format: `find KEYWORD|TAG`

### <a name="delete">`delete` - Tagging a task</a>
Deletes the specific task using its index

Format: `delete INDEX`

### <a name="tag">`tag` - Tagging a task</a>
Creates a tag and associate the tag with the task

Format: `tag INDEX TAG`

### <a name="bye">`bye` - Exiting the program </a>

Exiting the program

Format: `bye`