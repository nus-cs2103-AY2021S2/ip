# User Guide

## Features 

### Add tasks
You can add a task to the task list.
Duke recognises 3 types of tasks.
* `Todo` - A task with no time requirement
* `Deadline` - A task with a deadline
* `Event` - A task with a specified time

### List tasks
You can display the task list. Tasks will be shown
in the order they were added.

### Mark tasks as done
You can mark a task as done. Note that done tasks will
not be automatically deleted. Additionally, you will
not be able to undo this action.

### Delete task
You can delete a task from the task list.

### Find task
You can search for a task containing a given keyword.

### Exit
You can exit the program.

### Auto save
The task list will be automatically saved after every change
and will persist through subsequent sessions.

## Usage

### `todo` - Add a Todo task

Adds a task with name as provided.

Example of usage: 

`todo (name)`\
`t (name)`

Expected outcome:

`Task added:`\
`[T][ ] (name)`\
`Total tasks in list: (number of tasks)`

### `deadline` - Add a Deadline task

Adds a task with name and date as provided.
The date should be in `yyyy-MM-dd` format.

Example of usage:

`deadline (name) /by (date)`\
`d (name) /by (date)`

Expected outcome:

`Task added:`\
`[D][ ] (name) (by: (date))`\
`Total tasks in list: (number of tasks)`

### `event` - Add an Event task

Adds a task with name and date as provided.
The date should be in `yyyy-MM-dd` format.

Example of usage:

`event (name) /at (date)`\
`e (name) /at (date)`

Expected outcome:

`Task added:`\
`[E][ ] (name) (at: (date))`\
`Total tasks in list: (number of tasks)`

### `list` - Show the task list

Shows the saved list of tasks.

Example of usage:

`list`\
`l`

Expected outcome:

`Tasks in list:`\
`1.(task details)`\
`...`

### `done` - Mark a task as done

Marks as done the task at the provided numerical index of the list.
This number corresponds to the one shown by `list`.

Example of usage:

`done (index)`\
`do (index)`

Expected outcome:

`Successfully marked as done:`\
`[(task type)][X] (task description)`

### `delete` - Delete a task

Deletes the task at the provided numerical index of the list.
This number corresponds to the one shown by `list`.

Example of usage:

`delete (index)`\
`del (index)`

Expected outcome:

`Successfully removed:`\
`(task details)`\
`Total tasks in list: (number of tasks)`

### `find` - Find a task

Searches for all tasks containing the provided keyword.
This will only consider the first provided keyword.

Example of usage:

`find (keyword)`\
`f (keyword)`

Expected outcome:

`Matching tasks in list:`\
`1.(task details)`\
`...`

### `delete` - Delete a task

Deletes the task at the provided numerical index of the list.
This number corresponds to the one shown by `list`.

Example of usage:

`delete (index)`\
`del (index)`

Expected outcome:

`Successfully removed:`\
`(task details)`\
`Total tasks in list: (number of tasks)`

### `bye` - Exit the program

Closes the program immediately.

Example of usage:

`bye`\
`b`

Expected outcome:

>The program closes.