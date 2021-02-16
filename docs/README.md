# Duke User Guide
Welcome to Duke, Task List Extraordinaire, below you will find a guide to the features of Duke and
how to use them.\
Fields denoted by curly brackets `{}` are optional.
## Features 

### Tracking of tasks of 3 types
Duke allows tracking of tasks of the todo, deadline and event types.
See `todo`, `deadline`, `event`
### Tagging
You can add customised tags to every task you add to Duke. See `todo`, `deadline`, `event`
### View your task list
Duke can display your task list for you. See `list`
### Mark tasks as done
Duke can mark your completed tasks as done. See `done`
### Delete tasks
Duke can delete tasks from your list. See `delete`
### Searching for tasks by name
You can filter for particular tasks by name.
See `find`
### Save and Load
Duke automatically saves and loads your task list as you use it.
## Usage

### `todo` - Adds a todo task to your task list.
Format :\
`todo [NAME] {#TAG}...` 

Any number of tags (including 0) can be added.

Example of usage:\
`todo Buy groceries #urgent`

Expected outcome:

`Added to to-do list:
[T][] Buy groceries #urgent`

`[T]` identifies this as a todo task, `[]`signifies the task is not yet done.

### `deadline` - Adds a deadline task to your task list.
Format :\
`deadline [NAME] /by [DATE] {TIME} {#TAG}...`

The date should be in the format `yyyy-mm-dd`, the time field is optional.

Example of usage:

`deadline Assignment /by 2021-02-21 1800`

Expected outcome:

`Added to to-do list:
[D][] Assignment (By:21 Feb 2021 1800)`
### `event` - Adds an event task to your task list
Format :\
`event [NAME]  /at [DATE/TIME] {#TAG}...`

The date and/or time can be in any format and will be saved as such.

Example of usage:

`event Concert /at Feb 19 1900 #esplanade`

Expected outcome:

`Added to to-do list:
[E][] Concert #esplanade (At:Feb 19 1900)`
### `list` - Lists all the tasks currently in your list
Format :\
`list`

Example of usage:

`list`

Expected outcome:

A list of all the current tasks in your task list will be displayed, 
along with the index for each task.

### `done` - Marks a task as done on your list
Format :\
`done [INDEX]`

Index should be provided as an integer. Use `list` to see which task is at which index.

Example of usage:

`done 1`

Expected outcome:

The task located at index 1 on the list will be marked as done.

### `delete` - Deletes a task from your list
Format :\
`delete [INDEX]`

Index should be provided as an integer. Use `list` to see which task is at which index.
Note that this process is irreversible.

Example of usage:

`delete 1`

Expected outcome:

The task located at index 1 on the list will be deleted.

### `find` - Finds tasks from the task list that matches the provided text
Format :\
`find [TEXT]`

The `[TEXT]` field is case-sensitive. 
It will return all tasks that contain the given text in any part of its name.

Example of usage:

`find gro`

Expected outcome:

Any tasks with a name that contains the text `gro` will be returned in a list. 
For example a task with a name `Buy groceries` will be displayed if it is in the task list.

### `bye` - Exits the program
Format :\
`bye`

Your task list is saved after each command and will be reloaded when you restart Duke.

Example of usage:

`bye`

Expected outcome:

A goodbye message will be displayed and the program will close after a short time.

