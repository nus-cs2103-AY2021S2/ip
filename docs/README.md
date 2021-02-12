# User Guide

![Screenshot](/docs/Ui.PNG)

## Features

### Add tasks to Duke!
You are able to add 3 types of tasks to Duke.
They are:
* Todo
  * Specify description.
* Deadline
  * Specify description & date the task must be completed before.
* Event
  * Specify description & date the event will take place on.

For each type of task, a certain syntax format
must be followed. See usage section.

### Delete tasks

Tasks can also be deleted from the list with the `delete` command
and by specifying the index number of the desired task.

### Display all tasks

The list of tasks can be displayed with the `list` command.

### Find a certain task

The list of tasks that matches your requirement can also be
searched for and displayed, by using the `find` command
along with the keyword you wish to search for.

## Usage

### `todo` - Describe action

Creates and adds a Todo task to the list.

Example of usage:

`todo borrow book`

Expected outcome:

`[T][] borrow book`

### `deadline` - Describe action


Creates and adds a Deadline task to the list.

Example of usage:

`deadline return book /by 2021-03-03`

You must use the `/by` keyword and follow the date format yyyy-MM-dd exactly!

Expected outcome:

`[D][] return book (by: 03/03/2021)`

### `event` - Describe action


Creates and adds an Event task to the list.

Example of usage:

`event project meeting /at 2021-04-04`

You must use the `/at` keyword and follow the date format yyyy-MM-dd exactly!

Expected outcome:

`[E][] project meeting (at: 03/03/2021)`

### `done` - Describe action


Marks a task as complete with a tick in the box.
The index of the item you wish to mark must be provided.

Example of usage:

`done 1`

Expected outcome:

`[T][✔] borrow book`

### `delete` - Describe action


Deletes the desired from the list. The index of the item
you wish to delete must be provided.

Example of usage:

`delete 2`

Expected outcome:

`Noted. I've removed this task:`

`[D][] return book (by: 03/03/2021)`

In addition, when the `list` command is used again,
the deleted task will no longer appear.

### `list` - Describe action

Displays all the tasks in the list.

Example of usage:

`list`

Expected outcome:

`[T][✔] borrow book`

`[E][] project meeting (at: 03/03/2021)`

### `find` - Describe action


Searches the list for tasks that match with the keyword
provided.

Example of usage:

`find book`

Expected outcome:

`[T][✔] borrow book`

