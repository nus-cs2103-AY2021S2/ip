# User Guide

## Introduction
**Duke** is a jar application to track a user's tasks in a list format. <br> 
It is a CLI application that requires the download of [Java SE 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

## Features

### Feature `todo` 
Add a todo task to your list

#### Usage - `todo <priority> <name>`

Example of usage: 

`todo H run` 

Expected outcome:

`Ok! I've added this task:` <br>
`[T][X][H] run` <br>
`Currently, you have 1 task(s) in the list!` <br>


### Feature `event` 
Add a event task to your list

#### Usage - `event <priority> <name> /at <yyyy-mm-dd> <hh:mm>`

Example of usage: 

`event M walk /at 2021-03-12 12:12` 

Expected outcome:

`Ok! I've added this task:` <br>
`[E][X][M] walk (at: Mar 12 2021 12:12 PM)` <br>
`Currently, you have 1 task(s) in the list!` <br>


### Feature `deadline` 
Add a deadline task to your list

#### Usage - `deadline <priority> <name> /by <yyyy-mm-dd> <hh:mm>`

Example of usage: 

`deadline L walk /by 2021-03-12 12:12` 

Expected outcome:

`Ok! I've added this task:` <br>
`[D][X][L] walk (by: Mar 12 2021 12:12 PM)` <br>
`Currently, you have 1 task(s) in the list!` <br>


### Feature `list` 
Views the list of tasks.

#### Usage - `list`

Example of usage:

`list` 

Expected outcome:

`Here are the tasks in your list:` <br>
`1.[D][X][L] walk (by: Mar 12 2021 12:12 PM)` <br>


### Feature `delete` 
Deletes a task from your task list.

#### Usage - `delete <index>`

Example of usage:

`delete 1` 

Expected outcome:

`Ok! I've removed this task:` <br>
`[E][X][M] walk (by: Mar 12 2021 12:12 PM)` <br>
`Currently, you have 0 task(s) in the list!` <br>


### Feature `done` 
Marks a task as done in your task list.

#### Usage - `done <index>`

Example of usage:

`done 1` 

Expected outcome:

`Nice! I've marked this task as done:` <br>
`[E][O][M] walk (by: Mar 12 2021 12:12 PM)` <br>


### Feature `find` 
finds tasks in your tasklist with the relevant keyword.

#### Usage - `find <keyword>`

Example of usage:

`find walk` 

Expected outcome:

`Here are the tasks in your list:` <br>
`1.[E][O][M] walk (by: Mar 12 2021 12:12 PM)` <br>


## Command Summary
Action | Format
-------|-------
Add todo | todo <priority> <name>
Add event | event <priority> <name> /at <yyyy-mm-dd> <hh:mm>
Add deadline | deadline <priority> <name> /by <yyyy-mm-dd> <hh:mm>
List tasks | list
Delete task | delete <index>
Mark task as done | done <index>
Find task using keyword | find <keyword

priority - takes in H, M or L characters <br>

keyword/name - takes in String <br>

index - takes in Integer <br>

hh:mm - takes in time in 24 hours (e.g 23:30) <br>

yyyy-mm-dd - takes in date (e.g 2021-12-12) <br>