# User Guide

## Features 

### Feature 1 
Description of feature.

## Usage

### `list` - List all the tasks in the current task list

Example of usage: 

`list`

Expected outcome:

`_______________________________________________________`

`1.[T][X]assignment`

`2.[T][]homework`

`_______________________________________________________`

### `todo` - Add a task without any date/time attached to it

Example of usage:

`todo borrow book`

Expected outcome:

`_______________________________________________________`

`Got it. I've added this task:`

`[T][ ] borrow book`

`Now you have 5 tasks in the list.`
`______________________________________________________`

### `deadline` - Add a task with deadline (e.g. 2019-10-15) attached to it

Example of usage:

`deadline return book /by 2019-10-15`

Expected outcome:

`____________________________________________`

`     Got it. I've added this task:`

`      [D][] return book (by: Oct 15 2019)Now you have 4 tasks in the list`

`____________________________________________`

### `event` - Add a task with date (e.g. 2019-10-15) attached to it

Example of usage:

`event project meeting /at 2019-10-15`

Expected outcome:

`____________________________________________`

`     Got it. I've added this task:`

`      [E][] project meeting (by: Oct 15 2019)Now you have 5 tasks in the list`

`____________________________________________`

### `delete` - Delete a task from the list

Example of usage:

`list`

`_______________________________________________________`

`     Here are the tasks in your list:`

`     1.[T][X] read book`

`     2.[D][X] return book (by: June 6th)`

`     3.[E][ ] project meeting (at: Aug 6th 2-4pm)`

`     4.[T][X] join sports club`

`     5.[T][ ] borrow book`

`    _______________________________________________________`

`delete 3`

Expected outcome:

`_______________________________________________________`

`     Noted. I've removed this task: `

`       [E][ ] project meeting (at: Aug 6th 2-4pm)`

`    Now you have 4 tasks in the list.`
`    ______________________________________________________`

### `find` - Find a task by searching for a keyword

Example of usage:

`find book`

Expected outcome:

`_______________________________________________________`

`     Here are the matching tasks in your list:`

`     1.[T][X] read book`

`     2.[D][X] return book (by: June 6th)`

`_______________________________________________________`


