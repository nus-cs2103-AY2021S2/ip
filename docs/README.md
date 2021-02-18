# User Guide

## Features 

### Feature 1 

## Usage

### `todo` - Adds a new task

Adds a new task to the agent.

Example of usage: 

`todo water the flowers`

The expected outcome:

The task is added to the agent.

### `deadline` - Adds a new deadline

Adds a new deadline to the agent.

Example of usage:

`deadline complete assignment /by 2020/11/28`

The expected outcome:

The task with deadline of 28th Nov 2020 is added to the agent.

### `event` - Adds a new event

Adds a new event to the agent.

Example of usage:

`deadline CS3243 midterms /at 2020/11/28`

The expected outcome:

The event happening at 28th Nov 28 is added to the agent.

### `delete` - Deletes a task.

Deletes a task at specified index in the agent.

Example of usage:

`delete 1`

The expected outcome:

The first task in the agent will be deleted.

### `done` - Marks a task as done.

Marks a task at specified index as done.

Example of usage:

`done 1`

The expected outcome:

The first task in the agent will be marked as done.

### `find` - Finds a task.

Finds a task with the given substring.

Example of usage:

`find abc`

The expected outcome:

Shows the list of task containing the substring `abc`.

### `list` - List tasks.

Shows all tasks stored in the agent sorted via index.

Example of usage:

`list`

The expected outcome:

Shows all tasks stored in the agent sorted via index.

### `undo` - Undo the previous action.

Undo the previous action (that modifies the content of the agent).

Example of usage:

`undo`

The expected outcome:

Undo the previous action (that modifies the content of the agent).