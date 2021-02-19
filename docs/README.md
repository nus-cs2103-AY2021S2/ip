# User Guide

Duke is a lightweight todo list app that helps to keep track of your todos,
deadlines and events.

<p align="middle">
  <img src="https://aaronsms.github.io/ip/userGuide.png" width="200" /> 
  <img src="https://aaronsms.github.io/ip/Ui.png" width="200" />
  <img src="https://aaronsms.github.io/ip/userGuide3.png" width="200" />
</p>

## Features 

### Adding tasks

There are three types of tasks, a todo item, a deadline and an event. Both
the deadline and event are associated with a date time.

### Deleting tasks

One task can be deleted at each time, specified by its index on the list.
The deleted task will not be recoverable.

### Marking tasks as complete

Every task can be marked as complete. This action is however not reversible. 
Additional information such as the number of tasks completed the past week 
is provided.

### Listing the current tasks

All tasks available including those marked as done will be listed in the order
that they are added. Information such as whether the task is complete will be
displayed.

### Searching for tasks

All tasks that matches with specified keyword will be listed in the order that
they are added. Information such as whether the task is complete will be
displayed.

### Exiting the program

This will be the proper way to exit the program. An encoded file will be saved
in the directory "/data/duke.txt". If the file is present, the program will
load previous state when it is run.

## Usage

### `todo` - Add todos

Create a new todo with the given name and adds it to the list of tasks.

Format:

`todo TASK_NAME`

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
Task added: [T][X] read book
Now you have 1 task in the list.
```

### `deadline` - Add deadlines

Create a new deadline with the given name and date time and adds it to the list
of tasks.

Format:

`deadline TASK_NAME /by DATETIME`

DATETIME is in the format `yyyy-MM-dd HH:mm` or `yyyy-MM-dd`.

Example of usage: 

`deadline A-UserGuide /by 2020-02-19`

Expected outcome:

```
Got it. I've added this task:
Task added: [D][X] A-UserGuide (by: 2020-02-19)
Now you have 2 tasks in the list.
```

### `event` - Add events

Create a new event with the given name and date time and adds it to the list
of tasks.

Format:

`event TASK_NAME /at DATETIME`

DATETIME is in the format `yyyy-MM-dd HH:mm` or `yyyy-MM-dd`.

Example of usage: 

`event UNICON /at 2020-02-20`

Expected outcome:

```
Got it. I've added this task:
Task added: [E][X] UNICON (at: 2020-02-20)
Now you have 3 tasks in the list.
```

### `delete` - Delete tasks

Removes the task with the given index from the list of tasks.

Format:

`delete INDEX`

INDEX should be in the range of the number of tasks available in the list.

Example of usage:

`delete 2`

Expected outcome:

```
Nice! I've removed this task:
    [D][X] A-UserGuide (by: 2020-02-19)
Now you have 2 task(s) in the list.
```

### `done` - Mark tasks as done

Mark the task with the given index from the task list as done.

Format:

`done INDEX`

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][1] read book
```

### `list` - List all available tasks

List all available tasks including those marked as complete.

Format:

`list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
    [T][1] read book
```

### `bye` - Exit the program

Save the current state and exit the program.

Format:

`bye`

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
