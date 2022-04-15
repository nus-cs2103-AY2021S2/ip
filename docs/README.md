# User Guide

## Features 

### Add Task
Users can add Task under 3 different categories.

1. ToDos: tasks without any date/time attached to it

2. Deadlines: tasks that need to be done before a specific date

3. Events: tasks that start at a specific date

### List 
Users can view their entered task in a list format.

### Mark as Done
User has the ability to mark task as done within the Task List.

### Delete
Users can delete their entered task in the list of Task List.

### Find
Users can find task from the list with certain keywords.

### Undo
Users undo their latest add command.

## Usage

### `todo` - Adds Todo Task

Adds a Todo Task to the Task List.

Example of usage: 

`Todo (arguments)`

Expected outcome:

`Got it. I've added this task:`

`[T][] (arguments)`

`Now you have 1 tasks in the list`

### `deadline` - Adds Deadline Task

Adds a Deadline Task to the Task List.

Example of usage:

`deadline (arguments) /by YYYY-MM-DD`

Expected outcome:

`Got it. I've added this task:`

`[D][] (arguments) (by: Month Day Year)`

`Now you have 2 tasks in the list`

### `event` - Adds Event Task

Adds a Event Task to the Task List.

Example of usage:

`event (arguments) /at YYYY-MM-DD`

Expected outcome:

`Got it. I've added this task:`

`[E][] (arguments) (by: Month Day Year)`

`Now you have 3 tasks in the list`

### `list` - Displays Task List

Allows user to view the current Task List.

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`

`1. [T][] (arguments)`

`2. [D][] (arguments) (by: Month Day Year)`

`3. [E][] (arguments) (by: Month Day Year)`

### `done` - Marks a task as done

Allows user to mark task as done within the Task List.

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`

`[T][x] (arguments)`

### `delete` - deletes a task

Allows user to remove a task within the Task List.

Example of usage:

`delete 1`

Expected outcome:

`Noted! I've removed this task:`

`[T][x] (arguments)`

`Now you have 2 tasks in the list`

### `find` - find a task with certain keywords

Allows user to find a task within the TaskList with a keyword.

Example of usage:

`find (argument)`

Expected outcome:

`Noted! I've removed this task:`

`[T][x] (arguments)`

`Now you have 2 tasks in the list`

### `Undo` - Undo an add command

Allows user to undo their latest add command.

Example of usage:

`undo`

Expected outcome:

`Undo-ed the latest AddCommand`
