# User Guide

## Features

* Adding a todoTask: `todo`
* Adding a deadlineTask: `deadline`
* Adding an eventTask: `event`
* Listing all tasks: `list`
* Completing a task: `done`
* Deleting a task: `delete`
* Finding a task: `find`
* Viewing schedule of the day: `schedule`
* Ending the program: `bye`

### Adding a todoTask: `todo`
Adds a todoTask to the task list.

Format: `todo TASKNAME`

Examples:
* `todo borrow book`
* `todo CS2103 Assignment`

### Adding a deadlineTask: `deadline`
Adds a deadlineTask to the task list.

Format: `deadline TASKNAME /by DATE`
* DATE: date is in the format of "dd mm yyyy"

Examples:
* `deadline return book /by 23 12 2021`
* `deadline cs2106 Tutorial /by 04 12 2021`

### Adding an eventTask: `event`
Adds an eventTask to the task list.

Format: `event TASKNAME /at TIMEDURATION`
* TIMEDURATION: time duration is in the format of "dd mm yyyy hh:mm a - hh:mm a"

Examples:
* `event project meeting /at 23 12 2021 03:00 pm - 05:00 pm`
* `devent Shopee interview /at 14 12 2021 03:00 pm - 04:00 pm`

### Listing all tasks : `list`
Shows a list of all tasks in the task list.

Format: `list`

### Completing a task: `done`
Marks the task done.

Format: `done INDEX`

Examples:
* `done 2` marks the second task in the task list done.

### Deleting a task: `delete`
Deletes a task.

Format: `delete INDEX`

Examples:
* `delete 2` deletes the second task in the task list.

### Finding a task: `find`
Finds task with the given keyword.

Format: `find KEYWORD`

Examples:
* `find meeting` displays all tasks with the word "meeting" in the task list.

### Viewing schedule of the day: `schedule`
Lists all tasks on the given date.

Format: `schedule DATE`
* DATE: date is in the format of "dd MM yyyy"

Examples:
* `schedule 23 Dec 2021`

### Ending the program: `bye`
Ends the program.

Format: `Bye`
