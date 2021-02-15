# User Guide to Mister Duke

## Introduction
Mister Duke is an interactive chatbot that will help you to keep track of your tasks.

## Features

### Adding a task 
Mister Duke can identify four different types of tasks: To-do, Deadlines, Events and Notes. Identify which type of task you are adding and Mister Duke will keep track of them for you.

### Marking a task as done
After you have complete a task, Mister Duke is able to tick its checkbox for you. This way, you are able to stay on top of things and have a quick overview of what has not been done.

### Deleting a task
Added a wrong task by mistake? Fret not as Mister Duke can delete the task from your list.

### Listing your task list
Mister Duke can display all the tasks in your list for an easy overview of what needs to be done.

### Finding a task
By specifying a keyword, Mister Duke is able to search through your task list for the task containing that keyword.
## Usage

### `todo (task)` - Adds a To-Do Task into your task list.

Successfully adding a To-Do Task will be confirmed by Mister Duke.

Example of usage: 

`todo buy bread`

Expected outcome:

`Got it. I've added this task: [T][ ] buy bread`

### `deadline (task) /by (date)` - Adds a Deadline Task into your task list.

Successfully adding a Deadline Task will be confirmed by Mister Duke. Do specify when the deadline is using /by.

Example of usage:

`deadline write essay /by 19-02-2021 2359`

Expected outcome:

`Got it. I've added this task: [D][ ] write essay (by: Fri Feb 19 23:59:00 SGT 2021)`

### `event (task) /at (timing)` - Adds an Event Task into your task list.

Successfully adding an Event Task will be confirmed by Mister Duke. Do specify the timing using /at.

Example of usage:

`event project meeting /at 2pm`

Expected outcome:

`Got it. I've added this task: [E][ ] project meeting (at: 2pm)`

### `note (text)` - Adds a Note Task into your task list.

Successfully adding a Note Task will be confirmed by Mister Duke.

Example of usage:

`note rmb to call mom`

Expected outcome:

`Got it. I've added this task: [N][ ] rmb to call mom`

### `list` - Lists all tasks in your task list.

Mister Duke will list your tasks in order of when they were added.

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list: 1. [T][ ] todo buy bread 2. [D][ ] write essay (by: Fri Feb 19 23:59:00 SGT 2021) 3. [E][ ] project meeting (at: 2pm) 4. [N][ ] rmb to call mom`

### `done (task number)` - Marks a task as done.

A tick will be indicated next to the specified task.

Example of usage:

`done 4`

Expected outcome:

`Nice! I've marked this task as done: [N][ ] rmb to call mom`

### `delete (task number)` - Deletes a task from the task list. 

The specified task will be removed from the task list.

Example of usage:

`delete 1`

Expected outcome:

`Noted. I've removed this task: [T][ ] buy bread`

### `find (keyword)` - Finds a task with the specified keyword

Returns all tasks in the task list with the matching keyword

Example of usage:

`find meeting`

Expected outcome:

`Here are the matching tasks in your list: 1. [E][ ] project meeting (at: 2pm)`
