# User Guide

Duke is a Command Line Interface (CLI) style desktop task 
list application for managing your tasks, events and deadlines.

## Features 
+ Viewing help : `help`
+ Adding a to do task : `todo`
+ Adding a deadline : `deadline`
+ Adding an event : `event`
+ Listing all tasks : `list`
+ Completing a task : `done`
+ Deleting a task : `delete`
+ Sorting task list : `sort`
+ Locating a task : `find`
+ Exiting the program : `bye`

## Viewing help : `help`
Shows complete list of commands.

### Usage
Format: `help`


## Adding a to do task : `todo`
Adds a generic to do task to the task list.

### Usage
Format: `todo {TASK_NAME}` 
+ adds`TASK_NAME` to the task list as a to do task.

Example of usage: 

`todo watch lecture videos`

Expected outcome: 

`TODO[ ] watch lecture videos` will be added to the task list. 

## Adding a deadline : `deadline`
Adds a deadline to the task list.

### Usage
Format: `deadline {DEADLINE_NAME} /by {YYYY-MM-DD}`
+ adds`DEADLINE_NAME` to the task list as a deadline.

Example of usage:

`deadline assignment /by 2021-02-20`

Expected outcome:

`DDLN[ ] assignment (by: Feb 20 2021)` will be added to the task list. 

## Adding a event : `event`
Adds an event to the task list.

### Usage
Format: `event {EVENT_NAME} /at {YYYY-MM-DD}`
+ adds`EVENT_NAME` to the task list as an event.

Example of usage:

`event career fair /at 2021-04-02`

Expected outcome:

`EVNT[ ] career fair (at: Apr 2 2021)` will be added to the task list. 

## Listing all tasks : `list`
Displays all tasks in the task list.

### Usage
Format: `list`

Example of usage:

`list`

Expected outcome:

    1. TODO[ ] watch lecture videos
    2. DDLN[ ] assignment (by: Feb 20 2021)
    3. EVNT[ ] career fair (at: Apr 2 2021)

## Completing a task : `done`
Marks a task as done.

### Usage
Format: `done {INDEX}`
+ Marks the task corresponding to INDEX according to 
  the list as done. 
+ List can be accessed with `list` command.

Example of usage:

`list`

    1. TODO[ ] watch lecture videos
    2. DDLN[ ] assignment (by: Feb 20 2021)
    3. EVNT[ ] career fair (at: Apr 2 2021) 

`done 2`

Expected outcome:

`list`

    1. TODO[ ] watch lecture videos
    2. DDLN[X] assignment (by: Feb 20 2021)
    3. EVNT[ ] career fair (at: Apr 2 2021) 

The second task on the list `2. DDLN[X] assignment (by: Feb 20 2021)` 
will be marked as done.

## + Deleting a task : `delete`
Deletes a task from the list.

### Usage
Format: `delete {INDEX}`
+ Deletes the task corresponding to INDEX according to
  the list
+ List can be accessed with `list` command.

Example of usage:

`list`

    1. TODO[ ] watch lecture videos
    2. DDLN[X] assignment (by: Feb 20 2021)
    3. EVNT[ ] career fair (at: Apr 2 2021) 

`delete 2`

Expected outcome:

    1. TODO[ ] watch lecture videos
    2. EVNT[ ] career fair (at: Apr 2 2021) 

`DDLN[X] assignment (by: Feb 20 2021)` has been deleted 
permanently from the task list.
## Sorting task list : `sort`
Sorts the task list by date.

### Usage
Format: `sort`
+ Tasks are sorted by date.
+ Deadlines and events will be displayed in order of
  increasing due date.
+ To do tasks without dates will be arranged below 
  deadlines and events
+ Sorting is for viewing only; the new indices of the 
  tasks will not apply when using `done` and `delete` 
  commands

Example of usage:

`list`

    1. TODO[ ] watch lecture videos
    2. DDLN[X] assignment (by: Feb 20 2021)
    3. EVNT[ ] career fair (at: Apr 2 2021) 

`sort`

Expected outcome:

`list`

    1. DDLN[X] assignment (by: Feb 20 2021)
    2. EVNT[ ] career fair (at: Apr 2 2021) 
    3. TODO[ ] watch lecture videos

`1. DDLN[X] assignment (by: Feb 20 2021)` and 
`2. EVNT[ ] career fair (at: Apr 2 2021)`are sorted by date, while 
`3. TODO[ ] watch lecture videos` has no date, and is sorted to
the bottom by default.

## Locating a task : `find`
Searches and displays tasks.

### Usage
Format: `find {KEYWORD}`
+ Tasks in the list that contain words that match the 
  KEYWORD will be displayed. 
+ Task names or descriptions, types of task or dates can 
all be used as keywords.
+ Keywords are case-sensitive and need to match the way the 
  list displays tasks, or the right results may not be retrieved.
+ Indices displayed in the results list are for viewing only,
  and will not apply when using `done` and `delete`
  commands
Example of usage:

`list`

    1. TODO[ ] watch lecture videos
    2. DDLN[X] assignment (by: Feb 20 2021)
    3. EVNT[ ] career fair (at: Apr 2 2021) 
    4. DDLN[ ] study for quiz (by: Mar 15 2021)

`find DDLN`

Expected outcome:

    Here are the matching tasks in your list:

        1. DDLN[X] assignment (by: Feb 20 2021)
        2. DDLN[ ] study for quiz (by: Mar 15 2021)
    
    Found 2 result(s).

All tasks with `DDLN` in the list will be displayed.


## Exiting the program : `bye`
Exits program.

### Usage
Format: `bye`

## Acknowledgements
### External Libraries used:
+ javafx-sdk-11.0.2
