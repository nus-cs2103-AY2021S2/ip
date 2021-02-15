# User Guide

# Introduction
**Duke (banchiang's version)** is a desktop (jar) application to track user's daily tasks. <br>
It functions as a GUI for users to type down tasks they want to record. <br>
Duke requires the download of [Java SE 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html);

# Features 

## General features:

## Feature `help`
View the commands for Duke.

###Usage - Enter in `help`

Example of usage:

`help`

Expected outcome:

`These are the formats for Duke commands:` <br>
`- help` <br>
`- bye` <br>
`- list` <br>
`- todo (taskName)` <br>
`- deadline (taskName) /by (YYYY-M-D TIME)` <br>
`- event (taskName) /at (YYYY-M-D TIME-TIME)` <br>
`- find (relevantName)` <br>
`- delete (taskNumber from list)` <br>
`- done (taskNumber from list)` <br>

## Feature `bye`
Exit the program.

### Usage - Enter in `bye`

Example of usage:

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`<br>
Program terminates.

## Feature `list` 
View the list of tasks.

### Usage - Enter in `list` 

Example of usage: 

`list`

Expected outcome:

`These are the tasks in your list:`<br>
`1. [T][] read book`

##Features to add a task in your list: 

## Feature `todo`
Add a to-do task in your list.

### Usage - Enter in `todo <task name>`

Example of usage:

`todo read book`

Expected outcome:

`Got it. I've added this task:`<br>
`[T][] read book` <br>
`You have 1 task(s) in your list.` 

## Feature `deadline`
Add a deadline task in your list.

### Usage - Enter in `deadline <task name> /by <YYYY-M-D TIME>`

Example of usage:

`deadline math homework /by 2021-12-31 2359`

Expected outcome:

`Got it. I've added this task:`<br>
`[D][] math homework (by: Dec 31 2021 2359)` <br>
`You have 1 task(s) in your list.`

## Feature `event`
Add an event task in your list.

### Usage - Enter in `event <task name> /at <YYYY-M-D TIME-TIME>`

Example of usage:

`event team meeting /at 2021-12-31 1200-1400`

Expected outcome:

`Got it. I've added this task:`<br>
`[E][] team meeting (at: Dec 31 2021 1200 - Dec 31 2021 1400)` <br>
`You have 1 task(s) in your list.`

##Features regarding tasks:

## Feature `delete`
Delete a task from your task list.

### Usage - Enter in `delete <task number>`

Example of usage:

`delete 1`

Expected outcome:

`Noted. I've removed this task:`<br>
`[T][] read book` <br>
`You have 0 task(s) in your list.`

## Feature `find`
Find a task with relevant keyword.

### Usage - Enter in `find <keyword>`

Example of usage:

`find book`

Expected outcome:

`Here are the matching tasks in your list:`<br>
`1. [T][] read book` <br>
`2. [T][] return book`

## Feature `done`
Mark a task in your task list as done.

### Usage - Enter in `done <task number>`

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`<br>
`[T][X] read book` <br>
`You have 1 task(s) in your list.` <br>

Notice that 'X' appears at the second box of the task. <br>
That 'X' signifies that the task is done.

#Summary of Duke Commands

Action | Command
------ | --------
See commands available | `help`
Exit program | `bye`
See task list | `list`
Add todo task | `todo <task name>`
Add deadline task | `deadline <task name> /by <YYYY-M-D TIME*>`
Add event task | `event <task name> /at <YYYY-M-D TIME*-TIME*>`
Find task with keyword | `find <keyword>`
Delete task | `delete <task number**>`
Mark task as done | `done <task number**>`

*TIME = format in 24hours. (eg: 2359, 0030) <br>
**task number = number for task in task list. (use `list` to check number) 
