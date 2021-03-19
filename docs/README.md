# User Guide

## Features 

### Feature 1 
Add a To-do Task

## Usage

### `todo TASK_NAME` - Add to-do task

Adds a to-do task into the task list. Task will be saved upon exit of program.

Example of usage: 

`todo Homework`

Expected outcome:

`Got it! I've added this task`<br/>
`[T][] Homework`<br/>
`Now you have 1 tasks in the list`

### Feature 2
Add an Event

## Usage

### `event EVENT_NAME /at DATE` - Add an Event

Adds an event task into the task list. Specify the date in `YYYY-MM-DD` format. Task will be saved upon exit of program.

Example of usage: 

`event My Birthday /at 2020-11-26`

Expected outcome:

`Got it! I've added this task`<br/>
`[E][] My Birthday (at: Nov 26 2020)`<br/>
`Now you have 1 tasks in the list`

### Feature 3
Add a deadline

## Usage

### `deadline DEADLINE_NAME /by DATE` - Add a deadline

Adds an deadline task into the task list. Specify the date in `YYYY-MM-DD` format. Task will be saved upon exit of program.

Example of usage: 

`deadline Project /by 2020-02-19`

Expected outcome:

`Got it! I've added this task`<br/>
`[D][] Project (at: Feb 19 2020)`<br/>
`Now you have 1 tasks in the list`

### Feature 3
Task removal

## Usage

### `delete TASK_NUMBER` - Removes task from task list

Deletes a task from the task list. The task is specified by the task number.

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task`<br/>
`[T][] Homework`<br/>
`Now you have 0 tasks in the list`


### Feature 3
View task list

## Usage

### `list` - Shows task list items

Displays the current items in the task list.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`<br/>
`1. [T][] Homework`<br/>
`2. [E][] My Birthday (at: Nov 26 2020)`<br/>
`3. [D][] Project (at: Feb 19 2020)`


### Feature 4
Find task

## Usage

### `find SEARCH_TERM` - Search for task

Searches for tasks with the search term in their contents and displays that tasks' information.

Example of usage: 

`find home`

Expected outcome:

`Here are the matching tasks in your list:`<br/>
`1. [T][] Homework`

### Feature 4
Close application

## Usage

### `bye` - Close application

Application will save the task list for the next time it is booted up and will terminate the program.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again!`<br/>
`Application exiting in 3`<br/>
`Application exiting in 2`<br/>
`Application exiting in 1`


















