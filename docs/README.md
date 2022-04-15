# User Guide

## Introduction

Irene is an application to help users keep track of 
all their tasks. Using Command Line Interface (CLI), Irene is able to help users see, add 
and delete their tasks quickly and seamlessly with the benefits of GUI (Graphical User Interface).
## Features 

### Feature 1 
***
### `todo` - Adds a todo task to the list.

### Usage

#### Format - `todo <task description>`

Example of usage: 

`todo refill water bottle`

Expected outcome:

`Got it. I've added this task:`

 `[T][] refill water bottle`

 `Now you have 1 task in the list.`

### Feature 2
***
### `deadline` - Adds a deadline task to the list.

### Usage

#### Format - `deadline <task description> /by <yyyy-mm-dd> <time>` 

Example of usage:

`deadline read 2103T admin notes /by 2021-02-05 9pm`

Expected outcome:

`Got it. I've added this task:`

`[D][] read 2103T admin notes (by: Feb 5 2021 9pm)`

`Now you have 2 tasks in the list.`

### Feature 3
***
### `event` - Adds an Event task to the list.

### Usage

#### Format - `event <task description> /at <yyyy-mm-dd> <time>`


Example of usage:

`event party at Louis' house /at 2021-02-15 9pm`

Expected outcome:

`Got it. I've added this task:`

`[E][] party at Louis' house (at: Feb 15 2021 9pm)`

`Now you have 3 tasks in the list.`
### Feature 4
***
### `list` - Shows all the tasks in the list.

### Usage

### Format - `list`

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`

`1) [T][] refill water bottle`

`2) [D][] read 2103T admin notes (by: Feb 5 2021 9pm)`

`3) [E][] party at Louis' house (at: Feb 15 2021 9pm)`


### Feature 5
***
### `done` - Indicates a task has been completed.

### Usage

### Format - `done <task number>`

Example of usage:

`done 2`

Expected outcome:

`Nice! I've marked this task as done:`

`[D][X] read 2103T admin notes (by: Feb 5 2021 9pm)`

### Feature 6
***
### `delete` - Deletes a task from the list.

### Usage

### Format - `delete <task number>` 

Task number refers to the number it is in the list.

Example of usage:

`delete 1`

Expected outcome:

`Noted. I've removed this task:`

`[T][] refill water bottle`

`Now you have 2 tasks in the list`



### Feature 7
***
### `find` - Finds tasks that match the given keyword.

### Usage

### Format - `find <keyword>`


Example of usage:

`find read`

Expected outcome:

`Here are the matching tasks in your list:`

`1) [D][X] read 2103T admin notes (by: Feb 5 2021 9pm)`



### Feature 8
***
### `mark` - Set a priority to a certain task.

### Usage

### Format - `mark <task number> <priority level>`

Priority level can be either low, medium or high 
and is case-insensitive.

Task number refers to the number it is in the list.


Example of usage:

`mark 2 medium`

Expected outcome:

`Nice! I've marked this task's priority as MEDIUM`

`[E][] party at Louis' house (at: Feb 15 2021 9pm) [MEDIUM]`
### Feature 9
***
### `bye` - To indicate the user is done.

### Usage

### Format - `bye`


Example of usage:

`bye`

Expected outcome:

`Bye! Please press the red button on the top left corner to close the application.`

Summary of Commands
---

Feature     | Format , Example
------------|------------------
Add Todo task | `todo <task description>` , `todo refill water bottle`
Add Deadline task | `deadline <task description> /by <yyyy-mm-dd> <time>` ,`deadline read 2103T admin notes /by 2021-02-05 9pm`
Add Event task | `event <task description> /at <yyyy-mm-dd> <time>` , `event party at Louis' house /at 2021-02-15 9pm`
Delete task | `delete <task number>` , `delete 1`
List out tasks | `list` , `list`
Mark a task as done | `done <task number>`, `done 2`
Mark priority for a task | `mark <task number> <priority level>` , `mark 2 medium`
Find tasks using keyword | `find <keyword>` , `find read`
End application | `bye` , `bye`



