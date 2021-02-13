# FakeBot User Guide

--------------------------------------------------------------------------------------------------------------------
##What is Fakebot?
Fakebot is a fake bot application where it faked a bot that helps the user to set
notes for the upcoming event, deadlines and to-do list.
It uses CLI interfaces to ensure faster and more efficient experience.
--------------------------------------------------------------------------------------------------------------------
## Features

### View help
Shows a list of command explaining the command available in the application.


### Add a task
You can add a task to the list. There are 3 different categories of tasks: `todo`, `deadline` and `event`.
`todo` is a task that only contains description while `deadline` contains description and deadline date
and event contain `event`  contain description, start date, start time, end date and end time.

### Mark a task as done
You can mark a task as done.

### List all tasks
You can `list` all your tasks.

### Find tasks
You can `find` your tasks using a keyword.

### Deleting a task
You can delete a task from the list.

###Exiting the program
You can easily exit the program by `bye` command.
### Saving the data
Task data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Detecting duplicate tasks
Duplicate tasks are automatically detected and ignored.
There is no need to worry about entering the same task.

##


--------------------------------------------------------------------------------------------------------------------


## Usage

### `help` - Help the user

Show a list of command available to User

Example of usage:

`help`

Expected outcome:
```
List of Command Available: 
Deadline Command: deadline [Description] /by [yyyy-mm-dd] [hh:mm]]
Delete   Command: delete [index]
Done     Command: done [index]
Event    Command: event [Description] /at [yyyy-mm-dd] [hh:mm] [yyyy-mm-dd] [hh:mm]
Find     Command: find [Description]
List     Command: list
Todo     Command: todo [Description
Exit     Command: bye
```
### `todo` - Add a todo task

Adds a todo task to the task list.

Format : `todo [Description]`

Example of usage:

`todo Buy egg from supermarket`

Expected outcome:

```
Got it. I've added this task:   
  [T][ ] Buy egg from supermarket
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task to the task list.

Format : `deadline [Description] /by [yyyy-mm-dd] [hh:mm]`

Example of usage:

`deadline CS2103 Week 6 Submission /by 2021-02-16 23:59`

Expected outcome:

```
Got it. I've added this task:   
  [D][ ] CS2103 Week 6 Submission (by: Feb 16 2021 23:59)
Now you have 2 tasks in the list.
```

### `event` - Add a event task

Adds an event task to the task list.


Format : `event [Description] /at [yyyy-mm-dd] [hh:mm] [yyyy-mm-dd] [hh:mm]`

Example of usage:

`event CS2103T Team Meeting /at 2021-02-13 18:00 2021-02-13 20:00`

Expected outcome:

```
Got it. I've added this task:   
  [E][ ] CS2103T Team Meeting (from: Feb 13 2021 18:00 to Feb 13 2021 20:00)
Now you have 3 tasks in the list.
```
### `list` - List all tasks

Show a list of all tasks in the task list.


Format : `list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] Buy egg from supermarket
2.[D][ ] CS2103 Week 6 Submission (by: Feb 16 2021 23:59)
3.[E][ ] 2103T Team Meeting (from: Feb 13 2021 18:00 to Feb 13 2021 20:00)
```
### `find` - Describe action

Finds tasks whose description contain the given keyword.

Format : `find [Keyword]`

Example of usage:

`find egg`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] Buy egg from supermarket
```
### `done` - Mark task as done

Mark a task as done.


Format : `done [Index]`

Example of usage:

`done 3`

Expected outcome:

```
Nice! I've marked this task as done: 
  [E][X] CS2103T Team Meeting (from: Feb 13 2021 18:00 to Feb 13 2021 20:00)
```

### `delete` - Delete a task

Delete a task from the task list.


Format : `delete [Index]`

Example of usage:

`delete 3`

Expected outcome:

```
Noted. I've removed this task: 
  [E][X] CS2103T Team Meeting (from: Feb 13 2021 18:00 to Feb 13 2021 20:00)
Now you have 2 tasks in the list.
```

### `bye` - Exit the program

Exits the program.

Format : `bye`

--------------------------------------------------------------------------------------------------------------------
## Command summary

Command | Format, Examples
--------|------------------
**todo** | `todo [Description]`<br> e.g., `todo Buy egg from supermarket`
**deadline** | `deadline [Description] /by [yyyy-mm-dd] [hh:mm]`<br> e.g., `deadline CS2103 Week 6 Submission /by 2021-02-16 23:59`
**event** | `event [Description] /at [yyyy-mm-dd] [hh:mm] [yyyy-mm-dd] [hh:mm]`<br> e.g., `event CS2103T Team Meeting /at 2021-02-13 18:00 2021-02-13 20:00`
**done** | `done [Index]`<br> e.g., `done 3`
**delete** | `delete [Index]`<br> e.g., `delete 3`
**find** | `find [Keyword]`<br> e.g., `find egg`
**list** | `list`
**help** | `help`

--------------------------------------------------------------------------------------------------------------------
## Acknowledgment

Bot Image found at : https://icon-library.com/icon/robotics-icon-2.html

User image found at : https://www.shareicon.net/man-user-profile-avatar-social-829459
