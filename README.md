# User Guide
Welcome to MAGATRON!

MAGATRON is here to help you keep log important events and keep track of impending deadlines.

MAGATRON has a CLI (command-line interface). The valid commands are listed below in "Features".

## Features 

### Managing Tasks
MAGATRON helps you log tasks. Tasks are split into 3 categories. 
* todo
* deadline
* event

MAGATRON allows you to add, view, mark-as-done and delete these tasks whenever necessary.
Deadline tasks have an additional feature, reminder, that updates you how long you have left
until the deadline for the task.

## Commands

### Adding Tasks to the TaskList

* #### `todo` - Adds Todo task

  Adds a "todo" task to your task list.
  Todo tasks do not have specified due by date or other additional information
  
Example of usage:

    todo Clean the floor

Expected outcome:

    Got it! I've added this new Task!
    [T][x] Clean the floor
    Now you have 1 task in your TaskList.

* #### `deadline` - Adds Deadline task

  Adds a "Deadline" to your task list.
  Deadline tasks have a description and a due date. 
  They are split using a " /by "
  Dates have to follow convention of YYYY-MM-DD.
  
 
Example of usage:

    deadline homework /by 2021-03-03

Expected outcome:

    Got it! I've added this new Task!
    [D][x] homework by (by: 2021-03-03)
    Now you have 2 tasks in your TaskList.

* #### `event` - Adds Event

  Adds an "event" task to your task list.
  Event tasks have a description and location. 
  They are split using a " /at ". Locations do not have to 
  follow any convention.
  

Example of usage:

    event Party /at Clementi Mall

Expected outcome:

    Got it! I've added this new Task!
    [E][x] Party (at: Clementi Mall)
    Now you have 3 tasks in your TaskList.

### Managing the TaskList

* #### `list` - lists all the logged tasks

  "List" all tasks from your task list.

Example of usage:

    list

Expected outcome:

    Here are your remainding tasks!
    1. [T][x] Clean the floor
    2. [D][x] homework by (by: 2021-03-03)
    3. [E][x] Party (at: Clementi Mall)

* #### `delete` - Deletes Task

  **Delete** a task from your task list.

Example of usage:

    delete 3

Expected outcome:

    Noted! I have removed this task from the list.
    [E][x] Party (at: Clementi Mall)

* #### `find` - Finds Tasks

  "Finds" the tasks from your TaskList containing the given keyword.
  keywords are only compared to the description of each task.

Example of usage:

    find Clean the floor

Expected outcome:

    Here are the relevant tasks:
    1. [T][x] Clean the floor

* #### `reminder` - reminds you of impending deadline 
  "reminder" shows a list of all impending deadline tasks in order of
  days till the deadline.

Example of usage:

    reminder

Expected outcome:

    Here are your upcoming deadline Tasks!
    1. [D][x] homework by (by: 2021-03-03) x days left!!


## Acknowledgement

Reused @banchiang code for GUI implementation.