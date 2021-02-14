# User Guide

Rem is a **desktop app for managing tasks using via a Command Line Interface(CLI)** while using Graphical User Interface(GUI) to display.

## Features 

### Add Task:
Add a task to the memory of Rem. There are 4 kinds of tasks including:\n
**todo**\n
**deadline**\n
**event**\n
**period**\n

## Usage

### `todo` - Add a todo to the task list

Todo is a kind of task without exact date. The format should be:

`todo {the description of the task}`

Example of usage: 

`todo Fight the White Whale`

If added successfully, Rem will reply that:

`Got it. I've added this task:\n
[T][ ] Fight the White Whale\n
Now you have {number of existing tasks} tasks in the list.`
\n
### `deadline` - Add a deadline to the task list

Deadline is a kind of task which need to be finished by the exact date. The format should be:

`deadline {the description of the task} /by {YYYY-MM-DD}`

Example of usage: 

`deadline Fight the White Whale /by 2021-02-16`

Expected outcome:

`Got it. I've added this task:\n
[D][ ] Fight the White Whale (by: 2021-02-16)\n
Now you have {number of existing tasks} tasks in the list.`
\n
### `event` - Add an event to the task list

Event is a kind of task which need to be finished at the exact date. The format should be:

`deadline {the description of the task} /at {YYYY-MM-DD}`

Example of usage: 

`deadline Fight the White Whale /at 2021-02-16`

Expected outcome:

`Got it. I've added this task:\n
[E][ ] Fight the White Whale (at: 2021-02-16)\n
Now you have {number of existing tasks} tasks in the list.`
