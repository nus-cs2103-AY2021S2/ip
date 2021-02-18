# User Guide

Zee helps you to manage your tasks and keep track of deadlines and events.

## Features 

### Adding a todo task: `todo`
Adds a todo task to the list. 

Format: `todo DESCRIPTION`

Examples:

* `todo develop GUI`

___

### Adding a deadline task: `deadline`
Adds a deadline to the list.

Format: `deadline DESCRIPTION /by DD-MM-YYYY HHMM`

* Date must be provided using the following syntax: `DD-MM-YYYY`.
* Time must be provided in 24 hour format.
* Time must be provided using the following syntax: `HHMM` where `H` stands for hours and `M` for minutes.

Examples:

* `deadline assignment /by 12-03-2021 1430`

---

### Adding an event: `event`
Adds an event to the list.

Format: `event DESCRIPTION /at DD-MM-YYYY HHMM` 

* Date must be provided using the following syntax: `DD-MM-YYYY`.
* Time must be provided in 24 hour format.
* Time must be provided using the following syntax: `HHMM` where `H` stands for hours and `M` for minutes.

Examples:

* `event Midterm /at 05-03-2021 1500`

___

### List all tasks: `list`
Lists all tasks in the list.

Format: `list`

---

### Mark tasks as done: `done`
Marks the specified task as done.

Format: `done INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index of the task in the list of tasks.

Examples:

* `done 1` marks the first task in the list as done.

---

### Delete tasks: `delete`
Deletes the specified task from the list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index of the task in the list of tasks.

Examples:

* `delete 1` deletes the first task in the list.

---

### Search for tasks: `find`
Searches for tasks with the given keyword.

Format: `find KEYWORD`

Examples:

* `find assignment` returns `CS2103T assignment`.

---

### Undo previous command: `undo`
Performs undo operation.

Format: `undo`

Examples:

* `done 1` followed by `undo` marks the task as incomplete.
* `todo tutorial` followed by `undo` removes the task from the list of tasks.

---

### Exiting the program: `bye`
Exits the program.

Format: `bye`

---

