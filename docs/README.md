# User Guide for DUKE

## Features 

### Keep track of To-Do tasks
Duke keeps track of your To-Do list.

### Keep track of Deadline tasks
Duke keeps track of your deadlines and the date they are supposed to be completed by.

### Keep track of Event tasks
Duke keeps track of your events and the date they are being held on.

### Snooze your tasks
Duke allows you to snooze a task.

### Find the tasks with specific keyword(s)
Duke allows you to find tasks that contain keyword(s) that you input.

## Usage

### `todo` - Adds a todo task

Duke parses the input and adds the todo task into its memory.

Example of usage: 

`todo borrow book`

Expected outcome:

```
Got it. I've added this task:
[T][✘] borrow book
Now you have 1 tasks in the list.
```

### `deadline` - Adds a deadline task

Duke parses the input and adds the deadline task into its memory.

Example of usage:

`deadline return book /by 2021-03-01`

Expected outcome:

```
Got it. I've added this task:
[D][✘] return book (by: Mar 1 2021)
Now you have 2 tasks in the list.
```

### `event` - Adds a deadline task

Duke parses the input and adds the event task into its memory.

Example of usage:

`event GME mooning /at 2021-02-22`

Expected outcome:

```
Got it. I've added this task:
[E][✘] GME mooning (by: Feb 22 2021)
Now you have 3 tasks in the list.
```
### `list` - Lists all tasks

Duke lists all tasks that it is keeping track of.

Example of usage:

`list`

Expected outcome:

```
1. [T][✘] borrow book
2. [D][✘] return book (by: Mar 1 2021)
3. [E][✘] GME mooning (by: Feb 22 2021)

Snoozed:
```

### `done` - Marks a task as done

Duke marks a task as done.

Example usage:

`done 1`

Expected outcome:

```
This task is marked as done:
1. [T][✓] borrow book
```

### `delete` - Delete a task

Duke deletes the task from its memory.

Example usage:

`delete 1`

Expected outcome:

```
This task has been removed:
[T][✓] borrow book
Now you have 2 tasks in the list.
```

### `find` - Find related tasks

Duke finds the tasks that contain keyword(s) input from user.

Example usage:

`find GME book`

Expected outcome:

```
1. [D][✘] return book (by: Mar 1 2021)
2. [E][✘] GME mooning (by: Feb 22 2021)
```

### `snooze` - Snooze a task

Duke snooze an active task.

Example usage:

`snooze 1`

Expected outcome:

```
This task has been snoozed:
[D][✘] return book (by: Mar 1 2021)
```