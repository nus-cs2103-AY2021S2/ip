# User Guide

## Features 

### 1. Add a Todo: `todo`
Add a Todo which has a description and isDone state.

#### Format: `todo DESCRIPTION`

Example of usage: 

`todo CS2103 assignment`

### 2. Add an Event: `event`
Add an Event which has a description, date/time and isDone state.

#### Format: `event DESCRIPTION /at DATE`

Example of usage: 

`event party /at 20-02-2021`

### 3. Add a Deadline: `deadline`
Add a Deadline which has a description, date/time and isDone state.

#### Format: `deadline DESCRIPTION /by DATE`

Example of usage:

`deadline assignment 2 /by tomorrow`

### 4. Add Expenses: `expense`
Add an Expense which has a description, amount and isDone state.

The isDone state indicates if the expense already been paid or not.

#### Format: `deadline DESCRIPTION /by DATE`

Example of usage:

`deadline assignment 2 /by tomorrow`

### 5. Delete a Task: `delete`
Todo, Event and Deadline and Expense are all tasks.
They can be deleted by calling the index of the task in the list.

Note that the index of the list starts from 1 and corresponds to the index displayed on the list.

#### Format: `delete INDEX`

Example of usage:

`delete 1`

### 6. Mark as done: `done`
Mark any task as done.

#### Format: `done INDEX`

Example of usage:

`done 1`

### 7. View all tasks: `list`
View all Todo, Event, Deadline, Expense.

#### Format: `list`

Example of usage:

`list`

### 8. Find task by keyword: `find`
Find any task which contains a specified keyword.
Returns nothing if no task contains the keyword.

#### Format: `find KEYWORD`

Example of usage:

`find assignment`

### 9. Save
The save feature is automated after each user input.
All data will be saved locally and automatically loaded upon the relaunch of the application.