# Hayate User Guide
***
Hayate is a **productivity app** to help you keep track of your
**daily tasks**. It is a Command Line Interface (CLI) based app
optimized for **fast typers**! Keeping track of your tasks will
now be a breeze (风) !

## Quick Start
1. Ensure you have `Java 11` or above installed on your computer.
2. Download the latest release of `Hayate.jar` from [here](https://github.com/khiaxeng/ip/releases/tag/v0.4).
3. Copy `Hayate.jar` to a directory of your choice.
4. Double-click `Hayate.jar` to run the app.

## Command List

**Commands** | **Usage**
------------ | ----------
`todo` | Adds a `todo` to the list
`deadline <deadlineDescription> /by dd/MM/yyyy HHmm` | Adds a `deadline` to the list
`event <eventDescription> /at dd/MM/yyyy HHmm` | Adds an `event` to the list
`list` | Display the list of all tasks
`done <taskIndex>` | Marks the task positioned at `<taskIndex>` in the list as `done`
`delete <taskIndex>` | Deletes the task positioned at `<taskIndex>` in the list
`update <taskIndex> <newDescription>` | Updates the description of the task positioned at `<taskIndex>`
`bye` | Exits the app

## Usage

### 1. `todo` - Adds a `todo` to the list

Example of usage:

`todo House chores`

Expected outcome:

```
Got it. I've added this task:
 [T][ ] House chores
You now have 4 task(s) in the list.
```

### 2. `deadline` - Adds a `deadline` to the list

Example of usage:

`deadline CS2103T Project /by 19/02/2021 2359`

Expected outcome:

```
Got it. I've added this task:
 [D][ ] CS2103T Project (by: Feb 19 2021 11:59 PM)
You now have 5 task(s) in the list.
```

### 3. `event` - Adds an `event` to the list

Example of usage:

`event Vivian's birthday /at 21/02/2021 1700`

Expected outcome:

```
Got it. I've added this task:
 [E][ ] Vivian's birthday (at: Feb 21 2021 05:00 PM)
You now have 6 task(s) in the list.
```

### 4. `list` - Display the list of all tasks

Example of usage:

`list`

Expected outcome:

```
1. [T][ ] Watch CS2103T lecture
2. [D][ ] iP Project (by: Feb 19 2021 11:59 PM)
3. [T][ ] House chores
4. [D][ ] CS2103T Project (by: Feb 19 2021 11:59 PM)
5. [E][ ] Vivian's birthday (at: Feb 21 2021 05:00 PM)
```

### 5. `done` - Marks the task positioned at `<taskIndex>` in the list as `done`

Example of usage:

`done 2`

Expected outcome:

```
Nice! I've marked this task as done.
[D][X] iP Project (by: Feb 19 2021 11:59 PM)
```

### 6. `delete` - Deletes the task positioned at `<taskIndex>` in the list

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task.
 [D][X] iP Project (by: Feb 19 2021 11:59 PM)
Now you have 4 task(s) in the list.
```

### 7. `update` - Updates the description of the task positioned at `<taskIndex>`

Example of usage:

`update 2 Wash dishes`

Expected outcome:

```
Noted. I've updated this task.
 [T][ ] Wash dishes
```

### 8. `bye` - Exits the app

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```