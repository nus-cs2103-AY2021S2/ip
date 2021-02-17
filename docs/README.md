# User Guide
The Duke program is a task management program that can help you schedule management such as **adding, deleting** events, deadlines and todos.
It is also optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, Duke can help you manage tasks faster than traditional GUI apps.
## Features 

### Viewing help: `help` 
Shows a command guide.

Type in: `help`

### Adding a task: `todo`, `event`, `deadline`
Adds a task to the address book.

Command part 1:`{taskType} {taskName}`

Command part 2:`/by {YYYY-MM-DD HH:MM}` or `/at {YYYY-MM-DD HH:MM}`

Command part 3:`-p {levelOfPriority}`

Type in: `{Command part 1} {Command part 2} {Command part 3}` in correct order.

For example:

* `todo go to school -p 3` 
* `deadline HW2 /at 2021-01-01 19:00 -p 5` 
* `todo watch movie`

Tips:

* `taskType` can only be `todo`, `event`, `deadline`.
* For `todo` type of task, there is no need to specify Command part 2.
* For `deadline`, use `/by`. For `event`, use `/at` when specifying Command part 2.
* Command part 3 can be ignored. If ignored, the default level of priority is 1.
* New added task will be regarded as undone.


### Listing all tasks : `list`
Shows a list of all tasks in Duke.

Type in: `list` or `list -p`

Tips:

* `list` will list all the tasks. 
* `list -p` will list all the tasks sorted by priority.

### Delete tasks : `delete`
Deletes a task in Duke.

Type in: `delete {taskIndex}`

For example:

* `delete 3`

### Mark task as done : `done`
Mark a task as done.

Type in: `done {taskIndex}`

For example:

* `done 2`

### Search tasks : `find` or `search time`
Search a task based on the information provided.

Type in: `find {taskName}` or `search time {YYYY-MM-DD HH:MM}`

Tips:

* The time format must follow YYYY-MM-DD HH:MM.

### Quit Duke : `bye`
Quit the program.

Type in: `bye`

### Save and read tasks :
Once launching Duke, the tasks will be read from local txt file. When user quit the program, the tasks will be saved to local. 

Tips: 
* If there is no such txt file, the program will automatically create a new txt file.

## Credit

@author caitlinjee: Reference CSS file and FXML file for JavaFx UI design.
