# User Guide

## Features

### Feature 1
Choose to see tasks or contacts.

## Usage

### `tasks/contacts` - Key in `tasks` or `contacts`

Example of usage:

`taks` `contacts`

An expected outcome:

If `tasks` is entered, the task list will be used.
If `contacts` is entered, the contact list will be used.

### Feature 2
Add a todo task.

## Usage

### `todo` - Key in `todo [task description] `

Example of usage:

`todo borrow book`

An expected outcome:

A todo task is added into the task list.

### Feature 3
Add a deadline task.

## Usage

### `deadline` - Key in `deadline [task description] /by [date]`
Take note that date should be in yyyy-mm-dd format.

Example of usage:

`deadline return book /by 2020-02-20`

An expected outcome:

A deadline task is added into the task list.

### Feature 4
Add an event task.

## Usage

### `event` - Key in `event [task description] /at [date]`
Take note that date should be in yyyy-mm-dd format.

Example of usage:

`event project meeting /at 2020-02-25`

An expected outcome:

An event task is added into the task list.

### Feature 5
See the list of tasks.

## Usage

### `list` - Key in `list`

An expected outcome:

The task list is displayed.

### Feature 6
Mark a task as done.

## Usage

### `done` - Key in `done [item number according to the task list]`

Example of usage:

`done 2`

An expected outcome:

The second item in the task list will be marked as done.

### Feature 7
delete a task from the task list.

## Usage

### `delete` - Key in `delete [item number according to the task list]`

Example of usage:

`delete 2`

An expected outcome:

The second item in the task list will be deleted.

### Feature 8
Find tasks with specific keyword

## Usage

### `find` - Key in `find [keyword]`

Example of usage:

`find book`

An expected outcome:

The tasks with the keyword book will be displayed.

### Feature 9
Add a contact into the contact list.

## Usage

### `add` - Key in `add [name] [number]`
Note that name must be one single word.

Example of usage:

`add JayChen 91060000`

An expected outcome:

The contact will be added to the contact list.

### Feature 10
List the contacts in the contact list.

## Usage

### `list` - Key in `list`

An expected outcome:

The contact list will be displayed.

### Feature 11
Delete a contact from the contact list.

## Usage

### `delete` - Key in `delete [item number in the contact list]`

Example of usage:

`delete 2`

An expected outcome:

The second contact in the contact list will be deleted.

### Feature 12
update a contact in the contact list.

## Usage

### `update` - Key in `update [item number in the contact list] [new name] [new number]`

Example of usage:

`update 2 Vivien 82990000`

An expected outcome:

The second contact in the contact list will be updated with the new information.

### Feature 13
Exit the program.

## Usage

### `exit` - Key in `exit`

An expected outcome:

The program will be closed.