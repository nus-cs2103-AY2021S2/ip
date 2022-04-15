# User Guide

## Introduction

**Duke** is a desktop application that can help users track their daily tasks.
It functions in a GUI (Graphical User Interface) to help those who type fast manage their tasks quickly and efficiently.

## Features 

## Feature 1 
View list of tasks.

### Usage

### `list` - Key in `list`

Example of usage: 

`list`

Expected outcome:
<br>
<br>
Tasks in your list are displayed in order.


## Feature 2
Add a todo task.

### Usage

### `todo` - Key in `todo <task description>`

Example of usage:

`todo run`

Expected outcome:

`Got it.I've added this task:`
<br>
`T[] run`
<br>
`Now you have 1 tasks in the list.`

## Feature 3
Add a deadline task.

### Usage

### `deadline` - Key in `deadline <task description> /by <yyyy-mm-dd> <hh:mm>`

Example of usage:

`deadline run /by 2020-10-11 18:00`

Expected outcome:

`Got it.I've added this task:`
<br>
`[D][] run (by:Oct 11 2020 06:00PM)`
<br>
`Now you have 1 tasks in the list.`

## Feature 4
Add an event task.

### Usage

### `event` - Key in `deadline <task description> /at <yyyy-mm-dd> <time of event>`

Example of usage:

`event eat /at 2020-10-11 2pm-4pm`

Expected outcome:

`Got it.I've added this task:`
<br>
`[E][] eat (at:Oct 11 2020 2pm-4pm)`
<br>
`Now you have x tasks in the list.`

## Feature 5
Mark a specific task as done.

### Usage

### `done` - Key in `done <task number>`

Example of usage:

`done 1`

Expected outcome:

`Nice!I've marked this task as done:`
<br>
`[T][X] run`
<br>

## Feature 6
Delete a task from the list.

### Usage

### `delete` - Key in `delete <task number>`

Example of usage:

`delete 1`

Expected outcome:

`Noted!I've removed this task(s):`
<br>
`[T][X] run`
<br>
`Now you have x tasks in the list`

## Feature 7
Delete multiple tasks from the list.

### Usage

### `delete` - Key in `delete <task number> <task number> <task number>`

Example of usage:

`delete 1 2 3`

Expected outcome:

`Noted!I've removed this task(s):`
<br>
`[D][X] run (by: Oct 11 2020 06:00PM)`
<br>
`[E][X] eat (at: Oct 11 2020 2pm-4pm)`
<br>
`Now you have x tasks in the list` 
<br> 


## Feature 8
Search for tasks containing a keyword.

### Usage

### `find` - Key in `find <keyword>`

Example of usage:

`find run`

Expected outcome:

`Here are the matching tasks in your list:`
<br>
`[T][X] run`
<br>

## Feature 9
Search for tasks that are due on a specific date.

### Usage

### `due` - Key in `due <YYYY-MM-DD>`

Example of usage:

`due 2020-11-10`

Expected outcome:

`Here are the tasks due on 2020-11-10:`
<br>
1.`[D][X] run (by:Oct 10 2020 06:00PM)`
<br>

## Feature 10
Exit the program.

### Usage

### `bye` - Key in `bye`

Example of usage:

`bye`

Expected outcome:

`Bye.Hope to see you again soon!`





## Summary of Task Commands

Action by user | Command to input
------------ | -------------
Add Todo task | `todo <task description>`
Add Deadline task | `deadline <task description> /by <YYYY-MM-DD> <hh:mm>`
Add Event task | `event <task description> /at <YYYY-MM-DD> <time>`
Delete task | `delete <task number>`
Delete multiple tasks | `delete <task number> <task number>`
List all tasks | `list`
Mark task as done | `done <task number>`
Find tasks containing keyword | `find <keyword>`
Find tasks due on a specific date | `due <YYYY-MM-DD>`
Close application | `bye`

