# User Guide

Olly Chat Bot (OCB) is a Command Line Interface (CLI) task management application which keeps track of your task and
their respective deadlines so you'll never miss them!

## Features 

### Listing all tasks: `list`
Shows a list of all tasks that have been added in OCB.

Format: `list`

### Adding a task: `todo, deadline, event`
Adds a task (todo, deadline, event) into OCB
#### Todo Task
Format: `todo TODO_NAME`

Examples:
- `todo CS2103T Quiz`
- `todo CS2101 Presentation Slides`

#### Deadline Task
Format: `todo DEADLINE_NAME /by DEADLINE_DATE [YYYY-MM-DD]`

Examples:
- `deadline CS2103T tp1 2021-03-12`
- `deadline CS2101 OP2 Presentation 2021-02-18`

#### Event Task
Format: `todo EVENT_NAME /at EVENT_DATE [YYYY-MM-DD]`

Examples:
- `event AGM Meeting /at 2021-04-04`
- `event GetGo launch /at 2021-03-01`

### Updating a task: `update`
Format: `update INDEX UPDATE_TYPE UPDATE_VALUE`

Examples:
- `update 1 name CS3230 Assignment`
- `update 1 date 2021-04-30`

### Deleting a task: `delete`
Format: `delete INDEX`

Examples:
- `delete 1`

### Find a task: `find`
Format: `find SEARCH_CRITERIA`

Examples:
- `find CS2103T`
- `find CS2101`

### Complete task: `done`
Format: `done INDEX`

Examples:
- `done 1`
- `done 2`

### Exit application: `bye`
Format: `bye`