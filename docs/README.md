# User Guide
Duke Chatbot is a chatbot app for managing tasks including todos, events, deadlines.
## Features 

### Feature 1 
Adding a todo task.

## Usage

### `todo` - Adds a todo task

Adds a todo task to the list of tasks in the computer.

Example of usage: 

`todo tutorial`

### Feature 2
Adding an event task.

## Usage

### `event` - Adds an event task

Adds an event task with date to the list of tasks in the computer. Date must be formatted in *YYYY-MM-DD*.

Example of usage:

`event meeting /at 2021-01-01`

### Feature 3
Adding a deadline task.

## Usage

### `deadline` - Adds a deadline task

Adds a deadline task with date to the list of tasks in the computer. Date must be formatted in *YYYY-MM-DD*.

Example of usage:

`deadline homework /by 2021-02-02`

### Feature 4
Displays the saved task list in the computer.

## Usage

### `list` - Displays the tasks saved

Displays all the tasks saved in the task list in the computer.

Example of usage:

`list`

### Feature 5
Marking a task in the saved task list in the computer as done.

## Usage

### `done` - Marks a task as done

Marks a task saved in the computer as done. The task number from the task list must be stated in the command.

Example of usage:

`done 1`

### Feature 6
Deleting a task in the saved task list in the computer.

## Usage

### `delete` - Deletes a task

Deletes a task from the task list saved in the computer. The task number from the task list must be stated in the command.

Example of usage:

`delete 1`

### Feature 7
Finding a task.

## Usage

### `find` - Find a task

Finds a task in the saved task list in the computer. 
The search is case-sensitive, and the order of keywords does not matter.

Example of usage:

`find meeting`

### Feature 8
Editing the date of an event/deadline task.

## Usage

### `update` - Edits the date of an event/ deadline task

Edits the date of an event/deadline task in the saved task list in the computer. Date must be formatted in *YYYY-MM-DD*. 
The task number from the task list must be stated in the command.

Example of usage:

`update 2 2021-03-03`