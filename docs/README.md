# Duke User Guide

## Introduction
Duke is a chat-bot that can help you with organising your tasks of different formats. Duke is optimised for those who prefer to work with a CLI while being able to see the responses in a GUI.

## Table of Contents
{:toc}

## Features
**Notes about the command format:**
* Commands come in the format: `command [args]`
* Words in `[brackets]` are optional and may not be required
* Words with a `bar|seperating` indicate one, or the other may be used  
* Words in `UPPERCASE` are arguments to be supplied by the user.
* Date and time is expected in `dd-MM-yy HHmm` format
* Extra arguments supplied will be ignored

## Usage

### `bye` - Exit the program

Exits the program.

Format: `bye`


### `list` - Lists the tasks stored

Lists the tasks currently stored.

Format: `list`

Example:

```
>list
Here are the tasks in your list:
1. [T][✓] read book
2. [D][X] return book (by: 06 Jun 26 1159PM)
3. [E][✓] project meeting (from: 20 Apr 21 0420PM to 20 Apr 21 0520PM)
```

### `todo` - Add a todo task

Add a todo task. Todo tasks have no due date.

Format: `todo TASK_DESCRIPTION`

Example outcome:

```
>todo read book
Got it. I've added this task:
	[T][X] read book
Now you have 2 tasks.
```

### `deadline` - Add a deadline task

Add a deadline task. Deadline tasks are tasks that need to be done before a certain date.

Format: `deadline TASK_DESCRIPTION /by DD-MM-YY HHMM`

Example outcome:

```
>deadline return book /by 06-06-26 2359
Got it. I've added this task:
	[D][X] return book (by: 06 Jun 26 1159PM)
Now you have 3 tasks.
```

### `event` - Add an event task

Add an event task. Event tasks are tasks that start and end at specific times.

Format: `event TASK_DESCRIPTION /at DD-MM-YY HHMM /to DD-MM-YY HHMM`

Example outcome:

```
>event project meeting /at 20-04-21 1620 /to 20-04-21 1720
Got it. I've added this task:
	[E][X] project meeting (from: 20 Apr 21 0420PM to 20 Apr 21 0520PM)
Now you have 4 tasks.
```

### `done` - Mark a task as done

Mark the specified task as done. Takes in the index of the task.

Format: `done INDEX`

Example outcome:

```
>done 1
Nice! I've marked this as done: 
[T][✓] read book
```

### `snooze` - Snooze a task

Snooze/postpone a task. Follows a similar format to deadline/event tasks, takes in an index.

Format: `snooze INDEX /at|/by DD-MM-YY HHMM [/to DD-MM-YY HHMM]`

Example outcomes:

```
>snooze 2 /by 06-06-21 2359
Got it. I've snoozed this task:
	[D][X] return book (by: 06 Jun 21 1159PM)
	
>snooze 3 /at 04-02-21 1620 /to 04-02-21 1720
Got it. I've snoozed this task:
	[E][✓] project meeting (from: 04 Feb 21 0420PM to 04 Feb 21 0520PM)
```

### `delete` - Delete a task

Delete the specified task. Takes in the index of the task.

Format: `delete INDEX`

Example outcome:

```
>delete 1
Got it. I've removed this task:
	[T][✓] read book
Now you have 5 tasks.
```

### `find` - Find a task

Finds a task with a given keyword(s). Does complete matching (i.e. searches for the whole phrase)

Format: `find KEYWORD`

Example outcome:

```
>find book
Here are the matching tasks in your list:
1. [D][X] return book (by: 06 Jun 21 1159PM)
2. [T][X] read book
```
