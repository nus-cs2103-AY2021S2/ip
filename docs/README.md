# User Guide

## Features 

### Feature 1 
List down all tasks that are currently in the task list.

## Usage

### `list` - Enter `list`

Example of usage: 

`list`

Expected outcome:

List of tasks will be displayed.

### Feature 2 
Clear all tasks that are currently in the task list.

## Usage

### `clear` - Enter `clear`

Example of usage: 

`clear`

Expected outcome:

Task list will be emptied.

### Feature 3 
Mark a task as completed.

## Usage

### `done` - Enter `done [task index]`

Example of usage: 

`done 1`

Expected outcome:

First item on the task list will be marked as completed.

### Feature 4 
Add a todo task.

## Usage

### `todo` - Enter `todo [task description]`

Example of usage: 

`todo cook pasta`

Expected outcome:

A todo task described as "cook pasta" will be added to the task list.

### Feature 5 
Add a deadline task.

## Usage

### `deadline` - Enter `deadline [task description] /by [yyyy-MM-DD]`

Example of usage: 

`deadline assignment /by 2021-01-01`

Expected outcome:

A deadline task described as "assignment" due on "Jan 01 2021" will be added to the task list.

### Feature 6 
Add an event task.

## Usage

### `event` - Enter `event [task description] /at [yyyy-MM-DD]`

Example of usage: 

`event party /at 2021-01-01`

Expected outcome:

A event task described as "party" on "2021-01-01" will be added to the task list.

### Feature 7 
Delete a task.

## Usage

### `delete` - Enter `delete [task index]`

Example of usage: 

`delete 1`

Expected outcome:

The first task in the task list will be removed.

### Feature 8 
Find a task containing the given keyword.

## Usage

### `find` - Enter `find [keyword]`

Example of usage: 

`find party`

Expected outcome:
Tasks containining "party" in their descriptions will be displayed.


### Feature 9 
Reschedule a deadline task.

## Usage

### `reschedule` - Enter `reschedule [task index] /to [yyyy-MM-DD]`

Example of usage: 

`reschedule 1 /to 2021-01-01`

Expected outcome:

The first task on the list will be rescheduled to "2021-01-01", provided that this is a deadline task. 
If the first task is not a deadline task, an error message will be displayed.

### Feature 10 
Record the finalised task list in the system for future use.

## Usage

### `save` - Enter `save`

Example of usage: 

`save`

Expected outcome:

The current tasks on the task list will be recorded in the system. The same list will be used when the user
reopens the application in the future.

### Feature 11
Exit the application.

## Usage

### `exit` - Enter `exit`

Example of usage: 

`exit`

Expected outcome:

The application closes.
