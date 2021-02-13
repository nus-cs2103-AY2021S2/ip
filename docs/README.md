# User Guide

## Features
Blarb is a Task Reminder. He keeps a list of events that you have to do.


## Usage

### `list` - Lists everything in the Tasklist.

Format: `list`

Example of usage:

`list`

Expected outcome:

````
Here are your tasks:

1. [T][✓] this
2. [T][✘] that
````
---
### `todo` - Creates a new ToDo Task.

Format: `todo TASK`

Example of usage:

`todo task`

Expected outcome:

````
Affirmative. I've added this task:
 [T][✘] task
Now you have 3 tasks in the list.
````
---
### `deadline` - Creates a new Deadline Task.

Format: `deadline TASK /by YYYY-MM-DD`

Example of usage:

`deadline homework /by 2021-03-06`

Expected outcome:

````
Affirmative. I've added this task:
[D][✘] homework (by: Mar 6 2021 AD)
Now you have 4 tasks in the list.
````
---
### `event` - Creates a new Event Task.

Format: `event TASK /at LOCATION`

Example of usage:

`event meeting /at NUS`

Expected outcome:

````
Affirmative. I've added this task:
 [E][✘] meeting (at: NUS)
Now you have 5 tasks in the list.
````
---
### `done` - Marks a task as done by index.

Format: `done INDEX`

Example of usage:

`done 1`

Expected outcome:

````
I've marked this task as done:
[T][✓] this
````
---
### `delete` - Deletes a task by index.

Format: `delete INDEX`

Example of usage:

`delete 2`

Expected outcome:

````
The task is terminated:
[T][✘] that
````

---
### `edit` - Edits a task by index.

Format: `edit INDEX TASK [/by YYYY-MM-DD or /at LOCATION]`

Example of usage:

`edit 1 explode`

Expected outcome:

````
The task is updated from
[T][✓] this
to
[T][✓] explode
````

---
### `bye` - Exits the program.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

````
Hasta la vida, baby.
````