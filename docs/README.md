# User Guide
**Duke** is a desktop app for managing personal tasks and schedule, 
optimised for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Duke can get your tasks management done faster than traditional GUI apps.

## Features 

### Notes about the Command Format

### Adding task
Description of feature.

## Usage
### `todo` - Adding ToDo
Adds a ToDo task to the task list.

Example of usage:

`todo DESCRIPTION`

Expected outcome:

    Got it. I've added this task:
      T | ✘ | DESCRIPTION
    Now you have N tasks in the list.

### `deadline` - Adding Deadline
Adds a Deadline task to the task list.

Example of usage:

`deadline DESCRIPTION /by DATETIME`

Expected outcome:

    Got it. I've added this task:
      D | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    Now you have N tasks in the list.

### `event` - Adding Event
Adds an event task to the task list.

Example of usage:

`event DESCRIPTION /at DATETIME`

Expected outcome:

    Got it. I've added this task:
      E | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    Now you have N tasks in the list.

### `done` - Marking Task as Done
Marks a task in the task list as done by its order.

Example of usage:

`done TASK_NUMBER`

Expected outcome:

    Nice! I've marked this task as done:
      T | ✘ | DESCRIPTION

### `delete` - Deleting Task
Deletes a task in the task list by its order.

Example of usage:

`delete TASK_NUMBER`

Expected outcome:

    Noted. I've removed this task:
      T | ✘ | DESCRIPTION
    Now you have N-1 tasks in the list.

### `list` - Viewing All Tasks
Views all tasks in the task list.

Example of usage:

`list`

Expected outcome:

    Here are the tasks in your list:
    1. T | ✘ | DESCRIPTION
    2. D | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    ...

### `find` - Finding Task
Finds all tasks that contains a specific keyword in their descriptions in the task list.

Example of usage:

`find KEYWORD`

Expected outcome:

    Here are the matching tasks in your list:
    1. T | ✘ | DESCRIPTION
    2. D | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    ...

### `schedule` - View Schedule
View all the tasks of a specific date.

Example of usage:

`schedule DATE`

Expected outcome:

    Here are the tasks for this date: 
    1. D | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    2. E | ✘ | DESCRIPTION MMM dd yyyy hh:mm
    ...
or

    Congrats! It's a FREE day.



### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
