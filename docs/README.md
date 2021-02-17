# User Guide

## Features 

### Terminate Application
### List Tasks
### Add Tasks (To-do, Event, Deadline)
### Delete Tasks
### Complete Tasks
### Find Tasks

## Usage

### `bye`
Terminate the application


### `list`
List all the tasks in the task list.

Example of usage:
`list`

Expected outcome:

```
Sonia: Here are your tasks!
  1. [X] Todo: start doing iP
  2. [ ] Deadline: this iP by 19 Feb 2021
  3. [ ] Event: midterms at 06 Mar 2021
```

### `todo NAME`
Add a to-do into the task list.

Example of usage:
`todo do homework`

Expected outcome:

```
Sonia: I have added the task to the task list!
```

### `event NAME /at DATE`
Add an event into the task list.

Example of usage:
`event someone's wedding /at 2021-06-09`

Expected outcome:

```
Sonia: I have added the task to the task list!
```

### `deadline NAME /by DATE`
Add a deadline into the task list

Example of usage:
`deadline this iP /by 2021-02-19`

Expected outcome:

```
I have added the task to the task list!
```

### `delete INDEX`
Delete the task specified by the index.

Example of usage:
`delete 1`

Expected outcome:

```
I have deleted that task from the task list!
```

### `find QUERY`
Find the tasks that match the search query.

Example of usage:
`find iP`

Expected outcome:

```
Sonia: Here are your tasks!
  1. [X] Todo: start doing iP
  2. [ ] Deadline: this iP by 19 Feb 2021
```
