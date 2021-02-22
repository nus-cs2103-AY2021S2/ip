# User Guide

Jeff is a desktop app for **managing tasks**.
## Features 

### Add tasks (ToDos, Deadlines, Events)
You can add 3 types of tasks to Jeff:
* **ToDo** - General tasks with no specific deadline
* **Deadline** - Tasks with a specific do-by date and time
* **Event** - Matters to attend to at a specific date and time

### List all tasks
You can list all the tasks currently stored in Jeff.

### Mark tasks as done
Once you finish a task, you can mark it as completed.

### Delete tasks
You can remove tasks from Jeff.

### Search for  tasks
You can search for tasks by keyword, and Jeff will display all tasks containing that keyword.

### Reschedule tasks
Tasks with an associated date and time (i.e. deadlines and events) can be rescheduled without deleting them.

### Save tasks
Before exiting the app, you can save tasks locally to your computer. The tasks will be automatically loaded when Jeff is launched the next time.


## Usage

### `todo` - Add a new ToDo

Adds a new ToDo to Jeff.

Example of usage:

`todo read book`

Expected outcome:

`Got it. I've added this task:`\
`[T][  ] read book`\
`Now you have 1 tasks in the list.`

### `deadline` - Add a new Deadline

Adds a new Deadline to Jeff.

Example of usage:

`deadline return book, 2021-01-01 18:00`

Expected outcome:

`Got it. I've added this task:`\
`[D][  ] return book (by: 1 JANUARY 2021 18:00)`\
`Now you have 2 tasks in the list.`

### `event` - Add a new Event

Adds a new Event to Jeff.

Example of usage:

`event book club meeting, 2021-01-10 11:30`

Expected outcome:

`Got it. I've added this task:`\
`[E][  ] book club meeting (at: 10 JANUARY 2021 11:30)`\
`Now you have 3 tasks in the list.`

### `list` - List all tasks

Lists all the tasks currently stored in Jeff.

Example of usage:

`list`

Expected outcome:

`1. [T][  ] read book`\
`2. [D][  ] return book (by: 1 JANUARY 2021 18:00)`\
`3. [E][  ] book club meeting (at: 10 JANUARY 2021 11:30)`

### `done` - Mark task as completed

Marks the task at the specified **index** as done. 

Example of usage:

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`\
`[D][X] return book (by: 1 JANUARY 2021 18:00)`

### `delete` - Delete a task

Removes the task at the specified **index**.

Example of usage:

`delete 3`

Expected outcome:

`Noted. I've removed this task:`\
`[E][  ] book club meeting (at: 10 JANUARY 2021 11:30)`\
`Now you have 2 tasks in the list.`

### `find` - Search for task

Searches for tasks with descriptions containing the specified **keyword**(s).

Example of usage:

`find book`

Expected outcome:

`Here are the tasks I found matching "book":`\
`[T][  ] read book`\
`[D][X] return book (by: 1 JANUARY 2021 18:00)`

### `reschedule` - Reschedule a task

Reschedules task at the specified index. Only applicable to **deadlines** and **events**.

Example of usage:

`reschedule 2, 2021-01-08 17:00`

Expected outcome:

`I've reschduled this task:`\
`[D][X] return book (by: 8 JANUARY 2021 17:00)`

### `bye` - Saves tasks to storage.

Saves all tasks in Jeff to local storage. **You may now close the app.**

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again!`