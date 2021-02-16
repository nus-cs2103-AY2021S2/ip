# User Guide

## Introduction
**Duke** is a jar application to track a user's tasks in a list format.
It is a CLI application that requires the download of [Java SE 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

## Features

### Feature `todo` 
Add a todo task to your list

#### Usage - `todo <priority> <name>`

Example of usage: 

`todo H run` 

Expected outcome:

`Ok! I've added this task:`
`[T][X][H] run`
`Currently, you have 1 task(s) in the list!`

### Feature `event` 
Add a event task to your list

#### Usage - `event <priority> <name> /at <yyyy-mm-dd> <hh:mm>`

Example of usage: 

`event M walk /at 2021-03-12 12:12` 

Expected outcome:

`Ok! I've added this task:`
`[E][X][M] walk (at: Mar 12 2021 12:12 PM)`
`Currently, you have 1 task(s) in the list!`

### Feature `deadline` 
Add a deadline task to your list

#### Usage - `deadline <priority> <name> /by <yyyy-mm-dd> <hh:mm>`

Example of usage: 

`deadline L walk /by 2021-03-12 12:12` 

Expected outcome:

`Ok! I've added this task:`
`[D][X][L] walk (by: Mar 12 2021 12:12 PM)`
`Currently, you have 1 task(s) in the list!`

### Feature `list` 
Views the list of tasks.

#### Usage - `list`

Example of usage:

`list` 

Expected outcome:

`Here are the tasks in your list:`
`1.[D][X][L] walk (by: Mar 12 2021 12:12 PM)`

### Feature `delete` 
Deletes a task from your task list.

#### Usage - `delete <index>`

Example of usage:

`delete 1` 

Expected outcome:

`Ok! I've removed this task:`
`[E][X][M] walk (by: Mar 12 2021 12:12 PM)`
`Currently, you have 0 task(s) in the list!`

### Feature `done` 
Marks a task as done in your task list.

#### Usage - `done <index>`

Example of usage:

`done 1` 

Expected outcome:

`Nice! I've marked this task as done:`
`[E][O][M] walk (by: Mar 12 2021 12:12 PM)`

### Feature `find` 
finds tasks in your tasklist with the relevant keyword.

#### Usage - `find <keyword>`

Example of usage:

`find walk` 

Expected outcome:

`Here are the tasks in your list:`
`1.[E][O][M] walk (by: Mar 12 2021 12:12 PM)`

##Command Summary
Action | Format
-------|-------
Add todo | todo <priority> <name>
Add event | event <priority> <name> /at <yyyy-mm-dd> <hh:mm>
Add deadline | deadline <priority> <name> /by <yyyy-mm-dd> <hh:mm>
List tasks | list
Delete task | delete <index>
Mark task as done | done <index>
Find task using keyword | find <keyword

<priority> - takes in H, M or L characters
<keyword>/<name> - takes in String
<index> - takes in Integer
<hh:mm> - takes in time in 24 hours (e.g 23:30)
<yyyy-mm-dd> - takes in date (e.g 2021-12-12)