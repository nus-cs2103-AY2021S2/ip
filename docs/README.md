# Duke User Guide

## Introduction
Welcome to *Duke*! A customized task manager with a light political twist.
To whom politics may concern, Duke will help you laugh while managing your tasks.

There are exceptions to Duke, just like politics!

## Reading this guide
Commands often come in the following format:
`command (ARGUMENTS IF ANY)`

Dates input only accepted in this form: `YYYY-MM-DD`

Outcomes will be formatted in ``Outcome``

Assume that the task list contains the following tasks:
```
[D][ ] comeback(by: Jul 04 2024)
[T][ ] imprison Joe
[E][ ] expose Hunter(at: Jun 22 2023)
[T][ ] MAKE AMERICA GREAT AGAIN
```
## Features 

### GUI available 
Now you get to see who's who!

## Usage

### `todo` - Add a todo task

Adds a todo task to the list of tasks.

Example of usage: 

`todo rally`

Expected outcome:

```
Got it. Now I have added this task:
  [T][ ] rally
Now you have 5 tasks in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task to the list of tasks.
Remember to follow specified input format given above.
The date must follow `/by`

Example of usage:

`deadline raise campaign funds /by 2024-11-03`

Expected outcome:

```
Got it. Now I have added this task:
  [D][ ] raise campaign funds(by: Nov 03 2024)
Now you have 6 tasks in the list.
```

### `event` - Add an event task

Adds an event task to the list of tasks.
Remember to follow specified input format given above.
The date must follow `/at`.

Example of usage:

`event victory party /at 2024-11-08`

Expected outcome:

```
Got it. Now I have added this task:
  [E][ ] victory party(by: Nov 08 2024)
Now you have 7 tasks in the list.
```

### `done` - Mark a task as done

Marks a task at the specified index as done.

Example of usage:

`done 4`

Expected outcome:

```
Nice! I've marked this task as done:
  [T][X] MAKE AMERICA GREAT AGAIN
```

### `delete` - Delete a task

Deletes a task at a specified index.

Example of usage:

`delete 6`

Expected outcome:

```
Noted. I've removed this task:
  [D][ ] raise campaign funds(by: Nov 03 2024)
Now you have 6 tasks in the list.
```

### `list` - List all the tasks

List all the tasks in added order. 
Note that when there are many tasks, parts are to be clipped
from display.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [D][ ] comeback(by: Jul 04 2024)
2. [T][ ] imprison Joe
3. [E][ ] expose Hunter(at: Jun 22 2023)
4. [T][ ] MAKE AMERICA GREAT AGAIN...
```

### `find` - Find tasks with keywords

Finds all tasks that matches input keywords.

Example of usage:

`find comeback`

Expected outcome:

```
Here are the tasks in your list:
1. [D][ ] comeback(by: Jul 04 2024)
```


### `empty` - Empty the list

Remove all tasks from the list of tasks.

Example of usage:

`empty`

Expected outcome:

```
Noted. I've emptied the list.
```

### `bye` - Close Duke

Quit Duke.

Example of usage:

`bye`

Expected outcome:

```
Bye! Hope to see you again soon!
```