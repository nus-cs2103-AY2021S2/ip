![Ui Image](Ui.png)

# User Guide

## Features 

### List Tasks
List all tasks created by the user

## Usage

### `list` - Lists all tasks

Displays all tasks in a numbered list

Example of usage: 

`list`

Expected outcome:

```
1.[T][✗] borrow book
2.[D][✗] return book (by: Sunday)
3.[E][✗] project meeting (at: Mon 2-4pm)
```

### Mark Task as Done
Marks a specified task as done

## Usage

### `done <task#>` - Marks task <task#> as done

Marks the task specified by <task#> as done

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
  [E][✓] project meeting (at: Mon 2-4pm)
```

### Delete a Task
Deletes the specified task

## Usage

### `delete <task#>` - Deletes task <task#>

Deletes the task specified by <task#>

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
  [T][✗] help you
Now you have 1 task in the list.
```

### Create a Todo Task
Creates a todo task with the info supplied

## Usage

### `todo <task info>` - Creates a Todo task with <task info>

Creates a todo task with <task info> as the task name

Example of usage: 

`todo Collect book from library`

Expected outcome:

```
Got it. I've added this task:
  [T][✗] Collect book from library
Now you have 1 tasks in the list.
```

### Create a Deadline Task
Creates a deadline task with the supplied info and deadline

## Usage

### `deadline <task info> /by <date> <time>` - Creates a deadline task with <task info> and the deadline of <date> and <time>

Creates a deadline task with <task info> as the task name and <date> and <time> as the deadline
- <date> should be in the format of DD/MM/YYYY
- <time> should be in the format of HHHH

Example of usage: 
`deadline return book /by 02/03/2020 1400`

Expected outcome:

```
Got it. I've added this task:
  [D][✗] return book (by: Mar 02 2020 1400)
Now you have 1 tasks in the list.
```

### Create a Event Task
Creates an event task with the supplied info and time

## Usage

### `event <task info> /at <date> <time>` - Creates a event task with <task info> and the time of <date> and <time>

Creates a event task with <task info> as the task name and <date> and <time> as the time
- <date> should be in the format of DD/MM/YYYY
- <time> should be in the format of HHHH

Example of usage: 

`event project meeting /at 01/03/2020 1400`

Expected outcome:

`outcome`

### Search for a task
Description of feature.

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

```
Got it. I've added this task:
  [E][✗] project meeting (at: Mar 01 2020 1400)
Now you have 1 tasks in the list.
```

### Archive a task
Archives the specified task

## Usage

### `archive <task#>` - Archive task <task#>

Archives the task specified by <task#>
Archived task will be exported to a file in the working directory called `archived_tasks`

Example of usage: 

`Archive 2`

Expected outcome:

```
Got it. I've archived this task:
  [E][✗] project meeting (at: Mar 01 2020 1400)
Now you have 1 tasks in the list.
```
