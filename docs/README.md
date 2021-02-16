# User Guide

![Image of Ui](Ui.png)

## Features 
Create Todo
Create Deadline
Create Event
Mark a task as done
List all tasks
Delete a task
Find tasks matching a substring
Find tasks to do on a certain day
Clear all tasks

## Usage

### `todo` - Create Todo

A simple todo task will be created

Example of usage: 

`todo read a book`

Expected outcome:

`____________________________________________________________
	  Got it. I've added this task:
      [T][ ]  read a book\n"
      now you have 1 tasks in the list.
    ____________________________________________________________`

### `deadline` - Create Deadline

A simple deadline task will be created

Example of usage: 

`deadline read a book /by 2021-01-31`

Expected outcome:

`____________________________________________________________
	  Got it. I've added this task:
      [D][ ]  read a book (by: 31 Jan 2021)
      now you have 1 tasks in the list.
    ____________________________________________________________`

### `event` - Create Event

A simple event task will be created

Example of usage: 

`event read a book /at 2021-01-31`

Expected outcome:

`____________________________________________________________
      Got it. I've added this task:
      [E][ ]  read a book (at: 31 Jan 2021)
      now you have 1 tasks in the list.
    ____________________________________________________________`


### `done - Mark a task as done`

The task will be marked as done and will no longer show up when using taskson

Example of usage: 

`done 1`

Expected outcome:

`____________________________________________________________
	  Nice! I've marked this task as done:
      [T][X]  read a book
    ____________________________________________________________`

### `list - List all tasks`

All the tasks will show up as a list

Example of usage: 

`list`

Expected outcome:

`____________________________________________________________
	  Here are the tasks in your list:
      1.  [T][ ]  read a book
      2.  [D][ ]  read a book (by: 31 Jan 2021)
      3.  [E][ ]  read a book (at: 31 Jan 2021)
    ____________________________________________________________`

### `delete - Delete a task`

The task will be permanently removed

Example of usage: 

`delete 2`

Expected outcome:

`____________________________________________________________
	  Noted. I've removed this task:
      [D][ ]  read a book (by: 31 Jan 2021)
      now you have 2 tasks in the list.
    ____________________________________________________________`

### `find - Find tasks matching a substring`

Tasks matching the given substring will be shown as a list

Example of usage: 

`find read`

Expected outcome:

`____________________________________________________________
	  Here are the matching tasks in your list:
      1.  [T][ ]  read a book
      2.  [D][ ]  read a book (by: 31 Jan 2021)
      3.  [E][ ]  read a book (at: 31 Jan 2021)
    ____________________________________________________________`

### `taskson - Find tasks to do on a certain day`

Tasks that are not done matching the given day in YYYY-MM-DD format will be shown as a list

Example of usage: 

`taskson 2021-01-31`

Expected outcome:

`____________________________________________________________
	  Here are the tasks on 2021-01-31:
      1.  [T][ ]  read a book
      2.  [D][ ]  read a book (by: 31 Jan 2021)
      3.  [E][ ]  read a book (at: 31 Jan 2021)
    ____________________________________________________________`

### `clear - Clear all tasks`

Permanently remove all tasks

Example of usage: 

`clear`

Expected outcome:

`____________________________________________________________
	  The list has been cleared
    ____________________________________________________________`
