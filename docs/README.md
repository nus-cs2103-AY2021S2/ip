# User Guide
Duke is a personal assistant that helps you keep track of your tasks! It supports the tracking of 3 kinds of tasks(todos, deadlines and events). You can use it to manage your daily tasks and check them off once done! Look below for the list of features as well as the command usage! Going through the usage guide below also serves as a quickstart guide on how to use Duke!

## Features

### Keep track of various tasks 
* Add todo tasks
* Add deadline tasks
* Add event tasks

### Manage your tasks
* List out your tasks
* Mark tasks as done
* Delete tasks
* Find tasks
* Add and look up additional information regarding tasks

## Usage

### `todo` - Adds a todo task

Adds a task that you are planning to do into your list of tasks. This task should not have any restrictions on the deadline or location, else use *'deadline'* or *'event'* instead!

**Format:**

`todo (task)`

**Example of usage:** 

`todo wash clothes`

**Expected outcome:**

`Got it! I have added the following task:`

`[T][][] wash clothes`

`Now you have 1 tasks!`

### `deadline` - Adds a deadline task

Adds a task with a specified deadline into your list of tasks.

**Format:**

`deadline (task) /by (YYYY-MM-DD)`

**Example of usage: **

`deadline Homework /by 2021-03-03`

**Expected outcome:**

`Got it! I have added the following task:`

`[D][][] Homework (by: March 3 2021)`

`Now you have 2 tasks!`

### `event` - Adds an event task

Adds a task with a specified location into your list of tasks.

**Format:**

`event (task) /at (location)`

**Example of usage: **

`event dinner /at orchard road`

**Expected outcome:**

`Got it! I have added the following task:`

`[E][][] dinner (at: orchard road)`

`Now you have 3 tasks!`

### `list` - Lists out all your tasks

Shows a list of all your completed and uncompleted tasks, indexed from 1-n, where n is the total number of tasks you currently have.

**Format:**

`list`

**Example of usage: **

`list`

**Expected outcome:**

`Here are your tasks!`

`1.[T][][] wash clothes`

`2.[D][][] Homework (by: March 3 2021)`

`3.[E][][] dinner (at: orchard road)`

### `done` - Marks a task as completed

Marks the specified task as completed. Specify the task using its index, which can be found by doing *'list'*.

**Format:**

`done (task index)`

**Example of usage: **

`done 2`

**Expected outcome:**

`Good job, I've marked the task as done!`

`[D][X][] Homework (by: March 3 2021)`

### `delete` - Deletes a task

Deletes the specified task from your list. Specify the task using its index, which can be found by doing *'list'*.

**Format:**

`delete (task index)`

**Example of usage: **

`delete 2`

**Expected outcome:**

`Okay I have removed this task!`

`[D][X][] Homework (by: March 3 2021)`

`Now you have 2 tasks in the list.`

### `find` - Finds and returns a task

Finds 1 or more tasks that contains the specified keyword.

**Format:**

`find (keyword)`

**Example of usage: **

`find wash`

**Expected outcome:**

`Here are the matching tasks in your list!`

`1.[T][][] wash clothes`

### `addinfo` - Adds additional information regard the specified task

Updates the specified task with additional information provided. Specify the task using its index, which can be found by doing *'list'*.

**Format:**

`addinfo (task index) (info)`

**Example of usage: **

`addinfo 1 gym clothes`

**Expected outcome:**

`I have added the additional info regarding this task!`

### `info` - Retrieves additional information regarding the specified task

Retrieves the additional information about the specified task, if any. Specify the task using its index, which can be found by doing *'list'*.

A task that contains additional info is marked by [i] when *'list'* is called, as shown below:

`1.[T][][i] wash clothes`

`2.[E][][] dinner (at: orchard road)`

**Format:**

`info (task index)`

**Example of usage: **

`info 1`

**Expected outcome:**

`Here is the additonal info regarding this task!`

`gym clothes`
