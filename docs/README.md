# User Guide

## Features 

### Task Management

- Add/Delete a task.

- Mark a task as completed.

### Different Types of Tasks

- To-Do. A task that only has a description.
- Deadline. A task to be completed by a given Date and Time.
- Event. A task that is conducted at a given Date and Time.

### View Tasks

- Display the entire list of tasks.

### Search for Tasks

- Find tasks using a keyword.

### Undo Action

- Undo a Task Management operation.


## Usage

### `todo` - Add To-Do Task

Add a task that only has a description.

Example of usage: 

`todo do laundry`

Expected Outcome:

```
Got it. I've added this task:
[T][X] do laundry
You have 1 task in your list
```

### `deadline` - Add a Deadline

Add a task that has to be completed by a given Date and Time.

Example of usage:

`deadline homework /by 2021-02-28 2359`

Expected Outcome:

```
Got it. I've added this task:
[D][X] homework (by: FEBRUARY 28 2021 11:59 PM)
You have 2 task in your list
```

### `event` - Add an Event

Add a task that is conducted at a given Date and Time.

Example of usage:

`event concert /by 2021-05-11 1900`

Expected Outcome:

```
Got it. I've added this task: 
[E][X] concert (at: MAY 11 2021 7:00 PM)
You have 3 task in your list
```

### `list` - List All Tasks

Display all user's tasks.

Example of usage:

`list`

Expected Outcome:

```
Your tasks:
1.[T][X] do laundry
2.[D][X] homework (by: FEBRUARY 28 2021 11:59 PM)
3.[E][X] concert (at: MAY 11 2021 7:00 PM)
```

###  `delete` - Delete Task

Delete task at a given list index.

Example of Usage:

`delete 2`

Expected Outcome:

```
I've removed this task:
[D][X] homework (by: FEBRUARY 28 2021 11:59 PM)
You have 2 tasks in your list
```

### `done` - Complete Task

Set task at given list index as done.

Example of Usage:

`done 1`

Expected Outcome:

```
Nice! I have marked this task as done:
[T][✓] do laundry
```

### `find` - Search for Tasks

Search for tasks that match a given keyword.

Example of Usage:

`find laundry`

Expected Outcome:

```
I have found tasks with the given keyword:
[T][✓] do laundry
```

### `undo` - Undo Action

Reverse the previous Task Management operation.

Example of Usage:

`undo` is called after `homework` deadline is deleted.

Expected Outcome:

```
DELETE undone, the following task is added:
[D][X] homework (by: FEBRUARY 28 2021 11:59 PM)
You have 3 tasks in your list
```

### `bye` - Exit the application