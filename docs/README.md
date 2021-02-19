# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface while still having the benefits
of a Graphical User Interface
## Features 

### Adding tasks
User can add tasks of different nature into the task list. User can also specify the deadline by which the task 
is to be done

### Updating tasks
User can update the task descriptions and task deadlines of existing tasks

### Deleting tasks
User can delete existing tasks

### Marking task as done
User can mark tasks as done.

### Listing tasks
User can get a list of all the tasks in the task list.

## Usage

### `list` - Listing of all tasks

Displays a list of all tasks in the task list.

Example of usage: 

`list`

Expected outcome:

``1. T | 0 | reading``

### `todo` - Adding a todo task

Adds a todo tasks with task description to the task list.

Example of usage:

`todo jogging`

Expected outcome:

``1. T | 0 | jogging``

### `deadline` - Adding a deadline task

Adding a deadline task with specified task description and task deadline to the task list.

Example of usage:

`deadline submission /by 2020-12-01 8pm`

Expected outcome:

``1. D | 0 | submission | Dec 1 2020 20:00``

### `event` - Adding an event task

Adding an event task with specified task description and task deadline to the task list.

Example of usage:

`event tutorial /by 2020-12-01 12:00`

Expected outcome:

``1. E | 0 | tutorial | Dec 1 2020 12:00``

### `done` - Marking done

Marking the specified task as done. 0 represents an undone task, 1 represents a done task.

Example of usage:

`done 1`

Expected outcome:

```1. D | 1 | submission | Dec 1 2020 20:00```

### `delete` - Deleting a task

Deleting a task with specified task index from the task list.

Example of usage:

`delete 2`

Expected outcome:

`Noted. I have removed the task: 1. D | 0 | submission | Dec 1 2020 20:00`

### `find` - Finding a task

From the task list, finds tasks which matches the keyword specified

Example of usage:

`find book`

Expected outcome:

`Here aer the matching tasks in your list: 1. D | 0 | submission | Dec 1 2020 20:00`

### `update` - Updating attribute of a task

Updating the specified attribute (i.e. time/description) of the specified task.
The format of input is `update <task index> <type> <detail>`.

For `type`, there are `dt` which represents datetime and `desc` which represents task description. `detail` is the 
new information to be used to update old information.

Example of usage:

`update 1 dt 2020-12-01 21:00`

`update 1 desc jogging`

Expected outcome:

`Updated: 1. D | 0 | jogging | Dec 1 2020 21:00`

### `bye` - Exiting the program

Terminates the program and closes the window

Example of usage:

`see you again!`
