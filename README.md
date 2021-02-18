# Duke User Guide

## Features 

### 1. List all the tasks 
User can view all the existing tasks. 

### 2. Create a ToDO task
User can create a ToDo task and store it in a file.

### 3. Create a Deadline task
User can create a Deadline task and store it in a file.

### 4. Create an Event task
User can create an Event task and store it in a file.

### 5. Mark a task as done
User can mark tasks as done.

### 6. Delete a task
User can delete tasks from the list.

### 7. Find a task
User can find a task by searching for a keyword


## Usage

### `list` - List all the tasks

By entering 'list', user can view all the existing tasks.

Example of usage: 

`list`

Expected outcome:

`Here are the matching tasks in your list`

`1.[T][X] join sports club`


### `todo` - Create a ToDO task

User can create a ToDo task and store it in a file by entering 'todo' followed by the task name.

Example of usage:

`todo CS2103 ip`

Expected outcome:

`Got it. I've added this task:`

`[T][] CS2103 ip`

`Now you have 2 tasks in the list.`

### `deadline` - Create a Deadline task

User can create a Deadline task and store it in a file by entering 'deadline' followed by the task name and due date.

Example of usage:

`deadline CS2103 Quiz /by 2021-02-19`

Expected outcome:

`Got it. I've added this task:`

`[D][] CS2103 Quiz (by: FEBRUARY 19 2021)`

`Now you have 3 tasks in the list.`

### `event` - Create an Event task

User can create an Event task and store it in a file by entering 'event' followed by the task name and the date of the event.

Example of usage:

`event CS2103 Lecture /at 2021-02-19`

Expected outcome:

`Got it. I've added this task:`

`[E][] CS2103 Lecture (at: FEBRUARY 19 2021)`

`Now you have 4 tasks in the list.`


### `done` - Mark a task as done

User can mark a task as done by entering 'done' followed by its index number.

Example of usage:

`done 3`

Expected outcome:

`Nice! I've marked this task as done:`

`[D][X] CS2103 Quiz (by: FEBRUARY 19 2021)`


### `delete` - Delete a task

User can delete tasks from the list by entering 'delete' followed by its index number.

Example of usage:

`delete 3`

Expected outcome:

`Noted. I've removed this task:`

`[D][X] CS2103 Quiz (by: FEBRUARY 19 2021)`

`Now you have 3 tasks in the list.`


### `find` - Find a task

User can find a task by searching for a keyword.

Example of usage:

`find CS2103`

Expected outcome:

`Here are the matching tasks in your list:`

`1.[T][] CS2103 ip`

`2.[E][] CS2103 Lecture (at: FEBRUARY 19 2021)`
