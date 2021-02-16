# User Guide

## Features 

### todo
Add a todo to the list with the given information

### event
Add an event to the list with the given information

### deadline
Add a deadline to the list with the given information

### done
Mark a task in the list as done by the given index

### list
Show the list of tasks.

### show
Show the statistic of the list with the given keyword.

### date
Search tasks which occur on the given date.

### find
Find tasks with names contain the given keyword.

### delete
Deletes the specified task from the list by the given index.

### bye
Save the current list and exit Duke.

### save
Duke data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

## Usage

### `todo` - Adding a todo

Add a todo to the list with the given information

Example of usage:

`todo INFO` <br /> 
`todo borrow book`

Expected outcome:

`Got it. I've added this task:` <br />
`[T][ ] borrow book` <br />
`Now you have 1 tasks in the list.`

### `event` - Adding an event

Add an event to the list with the given information

Example of usage:

`event INFO /at TIME` <br />
`event project meeting /at Oct 14 2021`

Expected outcome:

`Got it. I've added this task:` <br />
`[E][ ] project meeting (at: Oct 14 2021)` <br />
`Now you have 2 tasks in the list.`

### `deadline` - Adding a deadline

Add a deadline to the list with the given information

Example of usage:

`deadline INFO /by TIME` <br />
`deadline read book /by May 2 2021` 

Expected outcome:

`Got it. I've added this task:` <br />
`[D][ ] read book (by: May 2 2021)` <br />
`Now you have 3 tasks in the list.`

### `done` - Marking task as done

Mark a task in the list as done by the given index

Example of usage:

`done INDEX` <br />
`done 3`

Expected outcome:

`Nice! I've marked this task as done:` <br />
`[D][X] read book (by: May 2 2021)`

### `list` - Listing the tasks

Show the list of tasks.

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:` <br />
`1.[T][ ] borrow book` <br />
`2.[E][ ] project meeting (at: Oct 14 2021)` <br />
`3.[D][X] read book (by: May 2 2021)`

### `show` - Showing the statistic

Show the statistic of the list with the given keyword.

`todo` `event` `deadline` `done` `undone` `delete` `error`

Example of usage: 

`show KEYWORD` <br />
`show done`

Expected outcome:

`You have done 1 tasks in the list.`

### `date` - Searching tasks by date

Search tasks which occur on the given date.

Example of usage:

`date DATE` <br />
`date Oct 14 2021`

Expected outcome:

`Here are the tasks occurring on Oct 14 2021 in your list:` <br />
`2.[E][ ] project meeting (at: Oct 14 2021)`

### `find` - Finding tasks by name

Find tasks with names contain the given keyword.

Example of usage:

`find KEYWORD` <br />
`find book`

Expected outcome:

`Here are the matching tasks in your list:` <br />
`1.[T][ ] borrow book`

### `delete` - Deleting a task

Deletes the specified task from the list by the given index.

Example of usage:

`delete INDEX` <br />
`delete 3`

Expected outcome:

`Noted. I've removed this task:` <br />
`[D][X] read book (by: May 2 2021)` <br />
`Now you have 2 tasks in the list.`

### `bye` - Exiting Duke

Exit the program Duke.

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon`
