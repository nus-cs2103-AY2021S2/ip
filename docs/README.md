# User Guide for Trumden
I am sure that many of you missed the excitements from the recently concluded US election.

Following the US Election takes up a lot of my time and I need a way to organize task to catch up on my work.

Not to worry, Trumden is here to help!

Trumden is a desktop application for managing task and is optimized for use via the 
**Command Line Interface**

## Features of Trumden

### Add Tasks
You can add tasks that you want to keep track of. 
There are 3 type of tasks to cater to your need: ToDo, Deadline and Event.

### List Tasks
You can view the full list of task and it's current status.

### Delete Tasks
You can delete the tasks if you no longer wants to keep track of it.

### Find Tasks
You can search for tasks.

### Finish Tasks
You can mark a task as done.



## Usage

### `todo`  Add a ToDO Task 

Add a ToDo task. 
This is the simplest type of task that you can add. Trumden only requires a description to do so.

Example of usage: 

`todo` Hold a MAGA Rally

Expected outcome:

`Got it. I've added this task:[T][] Hold a MAGA Rally`

### `deadline` Add a Deadline Task

Tasks that need to be done before a specific date/time. Date and time need to input in this format 2/12/2019 1800

Example of usage:

`deadline` Leave the White House /by 20/01/2020 1200

Expected outcome:

`Got it. I've added this task:[D][] Leave the White House (by: Jan 20 2020 12:00 PM)`

### `event` Add an Event task

Tasks that start at a specific time and ends at a specific time.

Example of usage:

`event` Capitol Attack 6 Jan 2020 2-4pm

Expected outcome:

`Got it. I've added this task:[E][] Capitol Attack (at 6 Jan 2020 2-4pm)`


### `list` list tasks

List all the tasks.

Example of usage:

`list` 

Expected outcome:

`Here are the tasks in your list`

`1. [T][] Hold a MAGA Rally`

`2. [D][] Leave the White House (by: Jan 20 2020 12:00 PM)`

`3. [E][] Capitol Attack (at 6 Jan 2020 2-4pm)`



### `delete` delete tasks

Delete tasks at that particular index.

Example of usage:

`delete 1`

Expected outcome:

`Here are the tasks in your list`


`1. [D][] Leave the White House (by: Jan 20 2020 12:00 PM)`

`2. [E][] Capitol Attack (at 6 Jan 2020 2-4pm)`


### `done` finish task

Finish task at that particular index.

Example of usage:

`done 1`

Expected outcome:

`Here are the tasks in your list`

`1. [D][X] Leave the White House (by: Jan 20 2020 12:00 PM)`

`2. [E][] Capitol Attack (at 6 Jan 2020 2-4pm)`

### `bye` exit application

Exit the application
Example of usage:

`bye`

Expected outcome:

`I am a sore loser bye!`






