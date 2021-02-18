# Duke User Guide

**Duke** is a desk application to help users keep track of tasks, and make notes. It functions using a GUI (Graphical
User Interface) to help those potential users be at ease while using Duke.

## Basic Info

Duke allows the user to key in three types of tasks: todo, deadlines and events. Todo tasks are those that you expect to
finish immediately, and so does not have an end date. Deadlines and Events are tasks that must have an end date, whereas
it is optional to add time.

Duke also allows the user to keep track of notes, which are separately stored compared to tasks. Notes are flexible in
that you can key in anything, search for them using keywords, and be able to delete them if you know their index.

## Basic Features

### Feature 1

Exit Duke

### Usage

### `bye` or `exit` - saves all the data and exits duke

Example of usage:

`bye` or `exit`

Expected outcome:

```
Goodbye for now.
Hope to see you soon!
```

### Reminder

Remember to always execute this command or else the task would not be saved!

## Task Features

### Feature 1

Get the list of tasks

### Usage

### `list` - Simply type in `list`

Returns the list of tasks

Example of usage:

`list`

Expected outcome:

Returns the list of all the tasks and they are numbered starting from 1.

### Feature 2

add todo task

### Usage

### `todo` - Key in `todo <task description>`

Example of Usage:

`todo read book`

Expected outcome:

```
Got it! Added: 
[T][-] read book
Now you have 6 items in your list
```

#### Note:

Task description must be two words. If your task description is one word, then type `-` in place of first word, and the
description in place of the second one. For example: `todo - run`.

### Feature 3

add deadline task

### Usage

### `deadline` - Key in `deadline <task description> /by <Date: Format(yyyy-mm-dd)> <Time: Format(hh:mm:ss)>`

Example of Usage:

`deadline finish lab /by 2021-03-15 18:00:00`

Expected outcome:

```
Got it! Added: 
[D][-] finish lab (by: Mar 15 2021 6:00PM)
Now you have 7 items in your list
```

#### Note:

Task description must be two words. If your task description is one word, then type `-` in place of first word, and the
description in place of the second one. For example: `deadline - lab /by 2021-03-15 18:00:00`.

Also you may not key in the time, and the task would still be added.

### Feature 4

add event task

### Usage

### `event` - Key in `event <task description> /at <Date: Format(yyyy-mm-dd)> <Time: Format(hh:mm:ss)>`

Example of Usage:

`event attend meeting /at 2021-03-15 18:00:00`

Expected outcome:

```
Got it! Added: 
[E][-] attend meeting (at: Mar 15 2021 6:00PM)
Now you have 8 items in your list
```

#### Note:

Task description must be two words. If your task description is one word, then type `-` in place of first word, and the
description in place of the second one. For example: `event - meeting /at 2021-03-15 18:00:00`.

Also you may not key in the time, and the task would still be added.

