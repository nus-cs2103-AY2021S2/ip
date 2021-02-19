# User Guide

Duke is the go-to chat-bot that helps to manage and organize your task efficiently.
<br> Implemented with different avatars and a simple user interface, you will be able to navigate through it without fuss.

## Table Of Content

* [Quick Start](#quick-start)
* [Features](#features)
	1. Add a task 
		* [Todo](#adding-a-todo-task-todo)
		* [Deadline](#adding-a-deadline-task-deadline)
		* [Event](#adding-an-event-task-event)
	2. [List all tasks](#list-all-tasks-list)
	3. [Delete a task](#delete-a-task-delete)
	4. [Mark a task as done](#marks-a-task-as-done-done)
	5. [Find a task](#find-a-specific-task-find)
	6. [Exit Program](#exit-bye)
* [Command Summary](#command-summary)


# Quick start

1. Ensure you have Java `11` or above installed on your computer.

2. Download the latest `duke.jar` from [here](https://github.com/SiTingST/ip/releases/tag/A-Release).

3. Double-click on the downloaded file to launch Duke. 
	<br> Note: A data folder will be created in the folder containing the duke.jar file. Duke saves your tasks in the data file for efficient retrieval of tasks. 
	
4. After starting the application, you should see the GUI as shown below:
![Ui](Ui.png)


## Features

Notes about the command format:**<br>

* Words in `UPPER_CASE` are the *compulsory* parameters to be supplied by the user.<br>

### Add a task:  `todo`, `deadline`, `event`

There are three different tasks, namely, todo, event, and deadline. 
<br> Differents commands are used to facilitate the adding of different tasks to the program as seen below.

Notes:
* Commands are case-insensitive and the program only accepts commands typed in lower alphabets. 
* Users should add **ONE** task at a time. 
* There should **no duplication of tasks** with the same task description. 

#### Adding a todo task: `todo`
The todo command adds a todo task that requires a task description and no other field as user input.

Format: `todo` DESCRIPTION

Examples:
* `todo CNY visiting`
* `todo ST2334 tutorial`

![Ui](./Images/addTodoTask.png)

#### Adding a deadline task: `deadline`
The deadline command adds a deadline task that requires a task description, date and time to specify the due date and time of the task. 

Format: `deadline` DESCRIPTION /BY DATE TIME 
* DATE should be by DD/MM/YYYY (DATE that has already passed will not be accepted as a valid date)
* TIME should be in HHMM

Examples:
* `deadline submit proposal /by 03/05/2022 1800`

![Ui](./Images/addDeadlineTask.png)

#### Adding an event task: `event`
The event command adds an event task that requires a task description, date as well as start and end time of the task. 

Format: `event` description /AT DATE START_TIME-END_TIME 

Format: `deadline` DESCRIPTION /BY DATE TIME 
* DATE is should be by DD/MM/YYYY (DATE that has already passed will not be accepted as a valid date)
* START_TIME and END_IME should be in HHMM (END_TIME earlier than START_TIME will *not be accepted as valid time)

Examples:
* `event attend internship meeting /at 17/02/2022 1000-1100`

![Ui](./Images/addEventTask.png)


### List all tasks: `list`

Shows a list of all the tasks in duke.

Format: `list`

![Ui](./Images/listTask.png)

### Deletes a task: `delete`

Deletes a task using its task index in the list.

Note: TASK_INDEX starts from 1.

Format: `delete TASK_INDEX`

Assume that we still have 4 tasks, 
<br>`delete3`: will remove the third task on the list

![Ui](./Images/deleteTask.png)

### Marks a task as done: `done`

Marks the given task as completed which adds a tick beside the task. 

Note: All tasks added are uncompleted by default and TASK_INDEX starts from 1. 

Format: `done TASK_INDEX`

Example:

Assume that we still have 3 tasks, 
<br> `done 1`: will mark the first task on the list as completed 

![Ui](./Images/markAsDone.png)

### Finds a specific task: `find`

Show all tasks that match or contain keywords. 

Format: `find KEYWORDS`

Example:
  `find cs2103`

![Ui](./Images/findTask.png)

### Exit: `bye`

The program will terminate a few seconds after returning a "Bye. Hope to see you again!" message. 


## Command Summary

**Action**  | **Format, Examples**
--------|-----------------
**Add todo task** | `todo TASK_DESCRIPTION` e.g, `add todo watch assignments`
**Add deadline task** | `deadline TASK_DESCRIPTION /by DATE` e.g., `deadline finish cs2103 quiz /by 18/02/2021`
**Add event task** | `event TASK_DESCRIPTION /at DATE START_TIME END_TIME` e.g., `event attend internship meeting /at 17/02/2021 1000-1100`
**List** | `list`
**Mark as Done** |`delete TASK_INDEX` e.g., `delete 3`
**Delete** | `delete TASK_NUMBER` e.g., `delete 1`
**Find** | `find KEYWORD` e.g., `find cs2103`
**Exit** | `bye`


