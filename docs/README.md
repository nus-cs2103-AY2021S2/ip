# Ekud User Guide

Ekud is your personal task-managing chatbot, leave them to Ekud and never worry about them again.

![Product screenshot](Ui.png)

## Features

### Manage various types of tasks

Ekud can differentiate between ToDos, Deadlines and Events, allowing them to be managed all in one place.

### Find a task by keyword

Got an overwhelming list of tasks? Find the right one by telling Ekud the keyword!

### See your schedule at a glance

Want to know what's on for any day? Let Ekud know the day and he will show you the tasks relevant to that day!

## Usage

### `todo` - Add a ToDo task

Add a ToDo type task.

Format: `todo DESCRIPTION`

Example of usage:

`todo write user guide`

Expected outcome:

```
Okay I remember for you liao:
  [T][✘] write user guide
Err you got 1 task in total leh
```

### `deadline` - Add a Deadline

Add a deadline with date and time information.

Format: `deadline DESCRIPTION /by d/M/yyyy HHmm`

Example of usage:

`deadline submit iP final version /by 19/2/2021 2359`

Expected outcome:

```
Okay I remember for you liao:
  [D][✘] submit iP final version (by: Feb 19 2021 11.59pm)
Err you got 1 task in total leh
```

### `event` - Add an Event

Add an event with date and time information.

Format: `event DESCRIPTION /at d/M/yyyy HHmm`

Example of usage:

`event tP team meeting /at 15/2/2021 2200`

Expected outcome:

```
Okay I remember for you liao:
  [E][✘] tP team meeting (at: Feb 15 2021 10.00pm)
Err you got 1 task in total leh
```

### `list` - View all stored tasks

See a list of all the tasks currently stored.

Example of usage:

`list`

Expected outcome:

```
Get working! You need finish these things:
  1. [T][✘] write user guide
  2. [D][✘] submit iP final version (by: Feb 19 2021 11.59pm)
  3. [E][✘] tP team meeting (at: Feb 15 2021 10.00pm)
```

### `done` - Mark a task as completed

Change the status of a task to *completed* by its index.

Format: `done INDEX`

- `INDEX` - this is based on the numbering given by `list` command

Example of usage:

`done 1`

Expected outcome:

```
Good job! The task below is marked done!
  [D][✓] submit iP final version (by: Feb 19 2021 11.59pm)
```

### `delete` - Remove a task from the list.

Delete a task by its index

Format: `delete INDEX`

- `INDEX` - this is based on the numbering given by `list` command

Example of usage:

`delete 1`

Expected outcome:

```
Poof! This task is gone:
  [D][✓] submit iP final version (by: Feb 19 2021 11.59pm)
Err you got 2 tasks in total leh
```

### `find` - Search for tasks by a keywords.

Find any tasks containing the keywords given.

Format: `find KEYWORDS`

- `KEYWORDS` - any number of words that will be matched exactly as a whole

Example of usage:

`find tP`

Expected outcome:

```
Here's what I found!
  [E][✘] tP team meeting (at: Feb 15 2021 10.00pm)
```

### `view` - See schedule for a particular day

See a list of events or deadlines happening on a given date.

Format: `view d/M/yyyy`

Example of usage:

`view 15/2/2021`

Expected outcome:

```
You have these deadlines/events
  [E][✘] tP team meeting (at: Feb 15 2021 10.00pm)
```

### `bye` - Say goodbye to Ekud

Bid Ekud farewell and exit the application after 3 seconds.

Example of usage:

`bye`

Expected outcome:

```
Bye bye! Anything call me ah!
```

The application window will automatically close after 3 seconds.