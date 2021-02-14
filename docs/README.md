# User Guide for The Duke Task Manager

## Features 

### Add and view ToDos, Deadlines and Events in a single list 
Duke helps to keep track of these 3 types of tasks!

### Mark tasks as done
Allows you to keep track of which tasks have been attended to!

### Delete tasks
Remove tasks that are done or no longer relevant!

### Find tasks
You can search for tasks matching a particular keyword!

## Usage

### `todo` - add a ToDo

Adds a ToDo and its description into the list.

Example of usage: 

`todo borrow book`

Expected outcome:

`Got it! I've added this task:`<br>
`[T][] borrow book`<br>
`Now you have 1 task in the list.`

### `deadline` - add a Deadline

Adds a Deadline into the list.

Example of usage:

`deadline return book /by 15-02-2021 1800`

Expected outcome:

`Got it! I've added this task:`<br>
`[D][] return book (by: 15 Feb 2021 06:00PM)`<br>
`Now you have 2 tasks in the list.`

### `event` - add an Event

Adds an Event into the list.

Example of usage:

`event team meeting /at 15-02-2021 1600`

Expected outcome:

`Got it! I've added this task:`<br>
`[E][] team meeting (at 15 Feb 2021 04:00PM)`<br>
`Now you have 3 tasks in the list.`

### `list` - show all tasks

Displays every task in the list in order.

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`<br>
`1.[T][] borrow book`<br>
`2.[D][] return book (by 15 Feb 2021 06:00PM)`<br>
`3.[E][] team meeting (at 15 Feb 2021 04:00PM)`

### `done` - mark task as done

Marks a specific task in the list as done.

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`<br>
`[T][X] borrow book`

### `delete` - remove task from list

Removes a specific task from the list.

Example of usage:

`delete 1`

Expected outcome:

`Noted! I've removed this task:`<br>
`[T][X] borrow book`<br>
`Now you have 2 tasks in the list.`

### `find` - find matching tasks

Searches the list for tasks matching a specific keyword.

Example of usage:

`find team`

Expected outcome:

`Here are the matching tasks in your list:`<br>
`1.[E][] team meeting (at 15 Feb 2021 04:00PM)`