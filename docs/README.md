# Cartman User Guide

Cartman is a task management application made for my CS2103T Software Engineering class.

It is a chatbox that handles interactions using a CLI (Command Line Interface)

## Quick Start
1. Ensure that you have `Java 11` installed in your computer.
2. Download the latest release and double click `duke.jar`
3. Type the commands available and start adding your tasks.

## Feature List
1. [**`todo`**](#todo): adds a todo task
   [**`deadline`**](#deadline): adds a deadline task
   [**`event`**](#event): adds an event task
2. [**`list`**](#list): prints a list of all tasks
3. [**`done`**](#done): marks a task as done
4. [**`delete`**](#delete): deletes a task from list
5. [**`find`**](#find): search for tasks with the input keyword
6. [**`bye`**](#bye): say bye to Cartman

## Features

### <a name="todo"></a>Adds a todo task: `todo`

Adds a todo task to the application.

Format:
`todo <task-description>`

Example of usage: 

`todo finish up iP`

Expected outcome:

```
Alrighty! I've added this task:
📅 TODO » finish up iP ✘
Now you have 1 tasks in the list.
```
### <a name="deadline"></a>Adds a deadline task: `deadline`

Adds a deadline task to the application specifying a date to complete by.

Format:
`deadline <task-description> /by <date>`  
Date format: `DD/MM/YYYY, hh:mm<AM/PM>`

Example of usage:

`deadline submit iP /by 19/02/2021, 11:59PM`  


Expected outcome:

```
Alrighty! I've added this task:
📅 DEADLINE » submit iP (by: 19 Feb 2021, 11:59PM) ✘
Now you have 2 tasks in the list.
```
### <a name="event"></a>Adds a event task: `event`

Adds a event task to the application specifying a date the event will happen.

Format:
`event <task-description> /at <date>`  
Date format: `DD/MM/YYYY, hh:mm<AM/PM>`

Example of usage:

`event Y2S2 ends /at 01/05/2021, 11:59PM`


Expected outcome:

```
Alrighty! I've added this task:
📅 EVENT » Y2S2 ends (at: 01 May 2021, 11:59PM) ✘
Now you have 3 tasks in the list.
```
### <a name="list"></a>Lists all available tasks: `list`

Lists all tasks currently stored.

Format:
`list`

Expected outcome:

```
Hey Butters! You've got to do these tasks:
1 » TODO » finish up iP ✘
2 » DEADLINE » submit iP (by: 19 Feb 2021, 11:59PM) ✘
3 » EVENT » Y2S2 ends (at: 01 May 2021, 11:59PM) ✘
```
### <a name="done"></a>Marks a task as done: `done`

Checks the task indicating completion.

Format:
`done <index>`  
`<index>` indicates the index of the completed task.

Example of usage:

`done 3`: marks task at index `3` as completed

Expected outcome:
```
Sweet! I've marked this task as done:
EVENT » Y2S2 ends (at: 01 May 2021, 11:59PM) ✓
```
### <a name="delete"></a>Deletes a task: `delete`

Removes a task forever.

Format:
`delete <index>`  
`<index>` indicates the index of the completed task.

Example of usage:

`delete 1`: deletes task at index `1`.

Expected outcome:
```
Gotcha! I've removed this task:
🚮 TODO » finish up iP ✘
Now you have 2 tasks in the list.
```
### <a name="delete"></a>Finds task using specific keywords: `find`

Lists tasks that matches the given keywords.

Format:
`find <keyword>`

Example of usage:

`find  submit`: finds tasks that include the keywords `submit`.

Expected outcome:
```
Oh oh! I've found something:
1 » DEADLINE » submit iP (by: 19 Feb 2021, 11:59PM) ✘
```
### <a name="bye"></a>Says bye to Cartman and ends the application: `bye`

Ends the application and sends Cartman home.

Format:
`bye`

Expected outcome:
```
Bye bye Butters! See you tomorrow!
```