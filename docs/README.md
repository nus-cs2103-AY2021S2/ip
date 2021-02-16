# User Guide

## Keeping track of tasks

### Add/delete tasks

Events can be of 3 types, todo, event and deadline.

### Set task as done

Tasks can be set as done.

### Search for tasks

The user can search for a string in the task.

### Tag tasks

Tasks can be tagged to categorize them.

## Usage

### `todo` - Add a todo task

This adds a todo task.

Example of usage:

`todo (task name)`

Expected outcome:

`added: [T][ ] task`

### `event` - Add an event task

This adds an event task.

Example of usage:

`event (event name) /at (event time)`

Expected outcome:

`added: [E][ ] event (at: 2021-02-16)`

### `deadline` - Add a deadline task

This adds a deadline task.

Example of usage:

`deadline (deadline name) /by (deadline time)`

Expected outcome:

`added: [D][ ] deadline (by: 2021-02-16)`

### `done` - Set a task as done

This marks the task as done.

Example of usage:

`done (task id)`

Expected outcome:

`[T][X] task`

### `find` - Search for a string in task

This prints any task that contains the specified string.

Example of usage:

`search (search string)`

### `list` - List tasks

Lists all the tasks that have been added so far.

Example of usage:

`list`

### `delete` - Delete a task

This deletes the task.

Example of usage:

`delete (task id)`

### `tag` - Tags a task

This either attaches or removes a tag for a task.

Example of usage:

`task add (task id) (tag name)`
`task delete (task id) (tag name)`
