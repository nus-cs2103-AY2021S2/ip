# Duke User Guide

Duke is a CLI (Command Line Interface) application to help its users to record various kinds of tasks, with deadlines and timeframes. 


![](Ui.png)

## Features

### Add task

Adds a task to the Duke storage. There are three types of tasks supported by Duke:
* `todo` : A task with only description
* `deadline` : A task with a description and one end time.
* `event` : A task with a description, one start time and one end time.

### Mark task as done

You can mark a finished task to signify its completion.

### List every tasks

You can see every tasks currently stored in Duke.

### Find tasks

You can find tasks that matches the keyword given.

### Delete tasks

You can remove unneeded tasks.

### Sort tasks

You can sort tasks alphabetically.

### Save tasks

Tasks are saved automatically in the hard drive for your convinience. It is loaded automatically every time.

## Usage

### `todo` - Add a **todo** task

Adds a **todo** task to the task list.

Format: `todo [Description]`

Example: `todo Buy book`

Expected outcome
```
Got it. I've added this task:
[T][_] Buy book
Now you have 1 task in the list
```
### `deadline` - Add a **deadline** task

Adds a **deadline** task to the task list.

Format: `deadline [Description] /by [MM/dd/yyyy] [HH:mm]`

Example: `deadline Do ST2334 HW /by 02/22/2021 10:00`

Expected outcome
```
Got it. I've added this task:
[D][_] Do ST2334 HW | 2021-02-22 10:00
Now you have 2 tasks in the list
```
### `event` - Add a **event** task

Adds a **event** task to the task list.

Format: `event [Description] /at [MM/dd/yyyy] [HH:mm] [MM/dd/yyyy] [HH:mm]`

Example: `event CCA party /at 02/22/2021 10:00 02/22/2021 13:00`

Expected outcome
```
Got it. I've added this task:
[E][_] CCA party | 2021-02-22 10:00 ~ 2021-02-22 13:00
Now you have 3 tasks in the list
```
### `list` - List all tasks

Prints all events in the list.

Format: `list`

Example: `list`

Expected outcome
```
Here are the tasks in your list:
1. [T][_] Buy book
2. [D][_] Do ST2334 HW | 2021-02-22 10:00
3. [E][_] CCA party | 2021-02-22 10:00 ~ 2021-02-22 13:00
```
### `find` - Find tasks with a keyword

Find events that matched the given keyword.

Format: `find [keyword]`

Example: `find book`

Expected outcome
```
Here are the tasks in your list:
1. [T][_] Buy book
```
### `done` - Mark task as done

Set a task as done.

Format: `done [index]`

Example: `done 2`

Expected outcome
```
Nice! I've marked this task as done:
[D][X] Do ST2334 HW | 2021-02-22 10:00
```

### `delete` - Delete a task

Removes one task in the list

Format: `delete [index]`

Example: `delete 3`

Expected outcome
```
Noted. I've removed this task:
[E][_] CCA party | 2021-02-22 10:00 ~ 2021-02-22 13:00
Now you have 2 tasks in the list
```
### `sort` - Sort the task list

Reorder the list so that it is listed alphabetically

Format: `sort`

Example: `sort`

Expected outcome
```
List Sorted
```
### `bye` - Exit the program

Close duke application

Format: `bye`



