# User Guide
**Duke** is a desktop app for managing personal tasks and schedule, 
optimised for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Duke can get your tasks management done faster than traditional GUI apps.

## Features 

### Notes about the Command Format
* Words in `UPPER_CASE` are the parameters to be supplied by the user. 
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read a book`.


* The datetime format for add commands that takes in a datetime parameter (such as`deadline DESCRIPTION /by yyyy-mm-dd hh:mm`, `event DESCRIPTION /at yyyy-mm-dd hh:mm`) must strictly follow `yyyy-mm-dd hh:mm`. 
e.g. Duke will not understand `deadline homework /by 2021/2/28 10:00`.


* Similarly, the date format for command `schedule yyyy-mm-dd` must strictly follow `yyyy-mm-dd`. 


* Extraneous parameters for commands that do not take in parameters (such as `list`, `bye`) will cause the program to misinterpret the command.
e.g. if the command specifies `list 123`, Duke will NOT understand it as `list` but will return `OOPS!!! I'm sorry, but I don't know what that means :-(`.
  

* `N` in duke response `Now you have N tasks in the list.` is arbitrary, and it represents the total number of tasks in the Task List immediately after the current command is executed.
  
### Adding ToDo
Adds a ToDo task to the task list.
### Adding Deadline
Adds a Deadline task to the task list.
### Adding Event
Adds an Event task to the task list.
### Marking Task as Done
Marks a task in the task list as done by its order.
### Deleting Task
Deletes a task in the task list by its order.
### Viewing All Tasks
Views all tasks in the task list.
### Finding Task
Finds all tasks that contains a specific keyword in their descriptions in the task list.
### Viewing Schedule
Views all the tasks of a specific date.
### Exiting the programme
Exits the programme.

## Usage
### `todo` - Adding ToDo
Adds a ToDo task to the task list.

Example of usage: `todo DESCRIPTION`

Expected outcome:

    Got it. I've added this task:
      T | ✘ | DESCRIPTION
    Now you have N tasks in the list.

### `deadline` - Adding Deadline
Adds a Deadline task to the task list.

Example of usage: `deadline DESCRIPTION /by yyyy-mm-dd hh:mm`

Expected outcome:

    Got it. I've added this task:
      D | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    Now you have N tasks in the list.

### `event` - Adding Event
Adds an event task to the task list.

Example of usage: `event DESCRIPTION /at yyyy-mm-dd hh:mm`

Expected outcome:

    Got it. I've added this task:
      E | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    Now you have N tasks in the list.

### `done` - Marking Task as Done
Marks a task in the task list as done by its order.

Example of usage: `done TASK_NUMBER`

Expected outcome:

    Nice! I've marked this task as done:
      T | ✘ | DESCRIPTION

### `delete` - Deleting Task
Deletes a task in the task list by its order.

Example of usage: `delete TASK_NUMBER`

Expected outcome:

    Noted. I've removed this task:
      T | ✘ | DESCRIPTION
    Now you have N tasks in the list.

### `list` - Viewing All Tasks
Views all tasks in the task list.

Example of usage: `list`

Expected outcome:

    Here are the tasks in your list:
    1. T | ✘ | DESCRIPTION
    2. D | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    ...

### `find` - Finding Task
Finds all tasks that contains a specific keyword in their descriptions in the task list.

Example of usage: `find KEYWORD`

Expected outcome:

    Here are the matching tasks in your list:
    1. T | ✘ | DESCRIPTION
    2. D | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    ...

### `schedule` - Viewing Schedule
Views all the tasks of a specific date.

Example of usage: `schedule yyyy-mm-dd`

Expected outcome:

    Here are the tasks due/on this date: 
    1. D | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    2. E | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    ...

#### or 

    Congrats! It's a FREE day.

### `bye` - Exiting the programme
Exits the programme.

Example of usage: `bye`

### Saving the data
TaskList data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

