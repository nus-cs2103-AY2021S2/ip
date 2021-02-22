# User Guide

## What is Oui? 

Oui is a desktop application that functions as a planner, allowing you to keep track of your tasks in a task list. 
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
Oui functions like a chatbot. If you can type fast, Oui can help you keep track of your tasks even more efficiently than
traditional GUI applications.

## Table of Contents 
* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a ToDo task: `todo`](#adding-a-todo-task)
  * [Adding a Deadline task: `deadline`](#adding-a-deadline-task)
  * [Adding an Event task: `event`](#adding-an-event-task)
  * [Deleting a task: `delete`](#deleting-a-task)
  * [Listing all tasks: `list`](#listing-all-tasks)
  * [Listing all tasks falling on a specific date: `date`](#listing-all-tasks-falling-on-a-specific-date)
  * [Marking a task as done: `done`](#marking-a-task-as-done)
  * [Finding a task with a keyword: `find`](#finding-a-task-with-a-keyword)
  * [Rescheduling a task: `reschedule`](#rescheduling-a-task)
  * [Saving the data](#saving-the-data)
  * [Exiting the program: `bye`](#exiting-the-program)
* [FAQ](#faq)
* [Command summary](#command-summary)


## Quick start 
1. Ensure you have Java `11` installed in your Computer. 
1. Download the latest `oui.jar` from [here](https://github.com/mesyeux/ip/releases/tag/0.2).
1. Copy the file to the folder you want to use as the *home folder* for your Oui application.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 

![Startup Screenshot](images/startup.jpg)

5. Type the command in the command box and press Enter to execute it. e.g. typing `list` and pressing 
Enter will list all the tasks currently in the task list. 
Some example commands you can try: 
  * `list` : Lists all tasks.
  * `event project meeting /at 2021-02-03 1400-1600`: Adds an event task 'project meeting' that falls on 3rd Feb 2021 from 2pm to 4pm.
  * `delete 1` : Deletes the 1st task shown in the current list.
  * `bye` : Exits the application.
6. Refer to the [Features](#features) below for details of each command.

## Features 


### Adding a ToDo task 
Adds a to do task into the task list. 

Format: `todo <TASK DESCRIPTION>`

Examples: 

* `todo drink water` 

### Adding a Deadline task
Adds a deadline (task to be done by a certain date) into the task list.

Format: `deadline <TASK DESCRIPTION> /by <DATE AND TIME>`

* `DATE AND TIME` is to be specified in YYYY-MM-DD HHMM format.
* Timings are to be specified in 24H format.

Examples: 

* `deadline english quiz /by 2021-02-03 1400`
* `deadline buy present /by 2021-03-14 1600`

### Adding an Event task
Adds an event (task happening on a certain date during a certain time period) into the task list. 

Format: `event <TASK DESCRIPTION> /at <DATE AND TIME>`

* `DATE AND TIME` is to be specified in YYYY-MM-DD HHMM-HHMM format.
* Timings are to be specified in 24H format.

Examples:

* `event birthday party /at 2021-09-21 2000-2300`
* `event bash /at 2021-03-05 1700-2000`

### Deleting a task
Deletes a task from the task list.

Format: `delete <INDEX>`

* `INDEX` is the must be an integer.

Examples:

* `delete 2` deletes the second task from the task list. 

### Listing all tasks
Lists all the tasks within the task list.

Format: `list`

Examples:

* `list` will list all tasks currently in the task list. 

### Listing all tasks falling on a specific date 
Lists all tasks that fall on a specific date.

Format: `date <DATE>`

* `DATE` must be in YYYY-MM-DD format.

Examples:

* `date 2021-02-03` will list all the tasks falling on 3rd Feb 2021.
* `date 2021-12-25` will list all the tasks falling on 25th Dec 2021.

### Marking a task as done 
Marks a task within the task list as done.

Format: `done <INDEX>`

* `INDEX` must be an integer.

Examples: 

* `done 5` will mark the 5th task within the task list as done.

### Finding a task with a keyword 
Finds a task within the task list with a given keyword.

Format: `find <KEYWORD>`

* `KEYWORD` must only be one word long.

Examples: 

* `find dog` will list all the tasks within the task list that have the word 'dog' in them.

### Rescheduling a task
Reschedules a Deadline or Event task to another date and time. 

Format: `reschedule <INDEX> <DATE AND TIME>

* `INDEX` must be an integer.
* For deadlines, `DATE AND TIME` must be specified in YYYY-MM-DD HHMM format.
* For events, `DATE AND TIME` must be specified in YYYY-MM-DD HHMM-HHMM format.

Examples: 

* `reschedule 2 2021-02-03 1400` will reschedule the 2nd task (must be a deadline) within the task list to 3rd Feb 2021 2PM.
* `reschedule 3 2021-02-15 1600-1800` will reschedule the 3rd task (must be a deadline) within the task list to 15th Feb 2021 4PM-6PM.

### Saving the data 
Oui's data is automatically saved in the hard disk upon exiting using the `bye` command. There is no need to save manually.

### Exiting the program 
Exits the program with all tasks in the task list saved.

Format: `bye`


## FAQ 

Q: How do I transfer my data to another Computer? 

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous Oui home folder.


## Command summary

**Action**      | **Format**
----------      | --------------------
**todo**        | `todo <TASK DESCRIPTION>`
**deadline**    | `deadline <TASK DESCRIPTION> /by <DATE AND TIME>`
**event**       | `event <TASK DESCRIPTION> /at <DATE AND TIME>`
**delete**      | `delete <INDEX>`
**list**        | `list`
**date**        | `date <DATE>`
**done** 	| `done <INDEX>`
**find** 	| `find <KEYWORD>`
**reschedule**  | `reschedule <INDEX> <DATE AND TIME>`
**bye** 	| `bye`
