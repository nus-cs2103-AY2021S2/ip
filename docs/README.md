# Spongebob Duke User Guide

##Introduction
Spongebob duke is a personal assistant chatbot that can
also help you keep track of various tasks.

## Features 
The commands offered by Spongebob duke are :

1. Add
2. List 
3. Done
4. Delete
5. Find
6. Sort

### Add
Add a new task to the list. Spongebob duke supports 3 types of tasks :
1. ToDo, which are tasks that are not tied to a specific date.
2. Deadline, which are tasks that must be completed before a certain date.
3. Event, which are tasks that occur on a certain date.

When a new task is added, the corresponding text file dukeText.txt is also updated.

### List

Shows every task sorted in the list.

### Done

Mark the task at a particular index as completed.

### Delete

Remove the task at a particular index.

### Find

Search for tasks containing a specific keyword.

### Sort
Sort the tasks in the list according to one of the following criteria :
1. By name alphabetically.
2. By showing completed tasks first followed
by incomplete tasks. Within completed tasks and incomplete
tasks, sorting is done by ascending alphabetical order.
3. By showing incomplete tasks first followed
   by completed tasks. Within completed tasks and incomplete
   tasks, sorting is done by ascending alphabetical order.

## Usage

### `todo` - Adds a todo task to the list

Example of usage:

`todo Email professor`

Expected outcome:

![Todo](./images/Todo.png)

### `deadline` - Adds a deadline task to the list

Example of usage:

`deadline CS2103 IP /by 2021-02-19`

Expected outcome:

![Deadline](./images/Deadline.png)

### `event` - Adds an event task to the list

Example of usage:

`event Midterm test /at 2021-02-18`

Expected outcome:

![Event](./images/Event.png)

### `list` - Display all tasks in the list

Example of usage:

`list`

Expected outcome:

![List](./images/List.png)

### `done` - Mark task as completed

Example of usage:

`done 4`

Expected outcome:

![Done](./images/Done.png)

### `delete` - Delete a task from the list

Example of usage:

`delete 3`

Expected outcome:

![Delete](./images/Delete.png)

### `find` - Search for particular tasks in the list

Example of usage:

`find CS`

Expected outcome:

![Find](./images/Find.png)

### `sort name` - Sort by alphabetical order

Example of usage:

`sort name`

Expected outcome:

![Sortname](./images/Sortname.png)

### `sort done` - Sort by completed tasks first

Example of usage:

`sort done`

Expected outcome:

![Sortdone](./images/Sortdone.png)

### `sort notdone` - Sort by incomplete tasks first

Example of usage:

`sort notdone`

Expected outcome:

![Sortnotdone](./images/Sortnotdone.png)




