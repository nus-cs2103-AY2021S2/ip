# User Guide

## Features

### Archive

- Archive information of tasks locally in resource file

### User interaction

- Key in command and send to the application
- See application response in the app
- See tips when the input command is invalid
- Exit the app using command

### View

- View the entire list of all tasks
- View tasks happening on a designated time

### Task management

- Create
- Mark as done
- Delete

## Usage

### `list` - List Command

List out all the tasks with their index, type, status (done or not),
description, and time.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:

1.[T][✘] borrow books
2.[D][ ] cs2102 tutorial 1 (by: Sep 4 2020)
3.[T][ ] return books
4.[D][✘] cs2101 email (by: Sep 14 2020)
5.[E][✘] cs2103 project meeting (at: 2020-09-30 12:00/2020-10-01 12:00)
6.[E][ ] cs2102 project meeting (Oct 1 2020)
```

### `bye` - Bye Command

Exit the app.

Example of usage:

`bye`

### `todo` - Add todo Command

Add a todo task with given description.

Format:

`todo <description>`

Example of usage:

`todo sample task`

Expected outcome:

```
Got it. I've added this task:
[T][✘] sample task
Now your have 7 tasks in the list.
```

### `deadline` - Add deadline Command

Add a deadline task with given description and date.

Format:

`deadline <description> /by <yyyy-MM-dd>`

Example of usage:

`deadline sample deadline /by 2020-09-30`

Expected outcome:

```
Got it. I've added this task:
[D][✘] sample deadline (by: Sep 30 2020)
Now your have 8 tasks in the list.
```

### `event` - Add event Command

Add an event task with given description and date time. Multiple tentative slots
can be added to the event.

Format:

`event <description> /at <yyyy-MM-dd>`

Example of usage 1:

`event sample event /at 2020-10-01`

Expected outcome 1:

```
Got it. I've added this task:
[E][✘] sample event (at: 12:00 PM   Oct 1 2020)
Now your have 9 tasks in the list.
```

### `delete` - Delete Command

Delete the task of the given index.

Format:

`delete <index>`

Example of usage:

`delete 7`

Expected outcome:

```
Noted. I've removed this task:
[T][✘] sample task
Now your have 9 tasks.
```

### `done` - Done Command

Mark the task of the given index as done.

Format:

`done <index>`

Example of usage:

`done 7`

Expected outcome:

```
Nice! I've marked this task as done:
[D][✘] sample deadline (by Sep 30 2020)
```

### `find` - Find Filter Command

Filter tasks that contains the given substring. The end date should not occur
before the start date.

Format:

`find <substring>`

Example of usage:

`find cs`

Expected outcome:

```
Here are the tasks containing 'cs' in your list:

2.[D][✓] cs2102 tutorial 1 (by: Sep 4 2020 repeat every 1 days)
4.[D][✘] cs2101 email (by: Sep 14 2020)
5.[E][✘] cs2103 project meeting (at: 2020-09-30 12:00/2020-10-01 12:00)
6.[E][✓] cs2102 project meeting (at: 07:00 PM   Oct 1 2020 repeat every 3 days)
```

### `Archive` - Done Command

Saves the current list to a file of your choice.

Format:

`archive <filename.txt>`

Example of usage:

`archive test.txt`

Expected outcome:

```
Noted. The following tasks have been archived to this file test.txt:

1.[T][✘] borrow books
2.[D][ ] cs2102 tutorial 1 (by: Sep 4 2020)
3.[T][ ] return books
4.[D][✘] cs2101 email (by: Sep 14 2020)
5.[E][✘] cs2103 project meeting (at: 2020-09-30 12:00/2020-10-01 12:00)
6.[E][ ] cs2102 project meeting (Oct 1 2020)
```

## Trouble shooting

If you are sure that you have some tasks in your list but they don't show up,
please check the resource file located at src/main/resources/tasks.txt to see if
the input format of tasks are correct.

## Acknowledgements

This user guide was adapted from https://github.com/lll-jy's user guide.
