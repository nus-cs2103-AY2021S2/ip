# User Guide
The duke bot is a desktop task management app, optimised for Command Line Interface (CLI). It is suitable for people who are familiar with CLI and particularly benefits users who are able to type fast.

Thus, Duke bot can track and manage your tasks faster than traditional GUI apps.

## User Inteface
<p align="left"><img width="40%" src="Ui.png"/></p>

## Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
  * [Add a todo task: `todo`](#add-a-todo-task-todo)
  * [Add a deadline task: `deadline`](#add-a-deadline-task-deadline)
  * [Add an event task:`event`](#add-an-event-task-event)
  * [Mark a task as completed:`done`](#mark-a-task-as-completed-done)
  * [Delete a task:`delete`](#delete-a-task-delete)
  * [List all the tasks in the task list:`list`](#list-all-the-tasks-in-the-task-list-list)
  * [Finds tasks that matches a keyword:`find`](#finds-tasks-that-matches-a-keyword-find)  
  * [Viewing help:`help`](#viewing-help-help)
  * [Exit the application:`bye`](#exit-the-application-bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Features 
This section will be showcasing the list of commands currently available on Duke and their usages.

### Add a todo task: `todo` 
Adds a todo task which is a basic task to your task list.

Format: `todo <TASK_NAME>`

Example:
'todo groceries shopping'

Outcome:
```
As requested, I've added a new task for you, Master:
[T][ ]groceries shopping

You have 5 tasks in the list now, Master.
```

### Add a deadline task: `deadline`
Adds a deadline task to your task list.

Format: `deadline <TASK_NAME> /at <YYYY-MM-DD> <HH:MM>`

Example:
`deadline CS2100 written assignment /by 2021-03-12 23:59`

Outcome
```
As requested, I've added a new task for you, Master:
[D][ ]CS2100 written assignment (by: 12 Mar 2021 11:59pm)

You have 6 tasks in the list now, Master.
```

### Add an event task: `event`
Adds an event task to your task list.

Format: `event <TASK_NAME> /at <LOCATION> <YYYY-MM-DD> <HH:MM>`

Example:
`event lunch /at NUSS 2021-02-24 12:15`

Outcome
```
As requested, I've added a new task for you, Master:
[E][ ]lunch (at: NUSS 24 Feb 2021 12:15pm)

You have 7 tasks in the list now, Master.
```

### Mark a task as completed: `done`
Mark the task at an inputted number position as completed.

Format: `done <TASK_NUMBER>`

Example:
`done 5`


Outcome:
```
Well done. Master! I've marked this task as done:
[T][X]groceries shopping
```

### Delete a task: `delete`
Deletes the task at the inputted number position.

Format: `delete <TASK NUMBER>`

Example:
`delete 5`

Outcome:
```
Understood Master. I've removed this task from the list:
[T][X]groceries shopping

You have 6 tasks left in the list now, Master.
```
### List all the tasks in the task list: `list`
Displays a list of all the tasks in your task list.

Format:`list`

Example:`list`

Outcome:
```
Here is a list of your tasks, Master.
1. [D][X]CS2103 ip (by 19 Feb 2021 11:59pm)
2. [T][X]programming assignment
3. [D][ ]critique paper (by: 2021-03-05)
4. [E][ ]Mitchell's birthday party (at: Carousel 17 May 2021 06:30pm)
5. [D][ ]CS2100 written assignment (by: 12 Mar 2021 11:59pm)
6. [E][ ]lunch (at: NUSS 24 Feb 2021 12:15pm)
```

### Finds tasks that matches a keyword: `find`
Finds and shows a list tasks which contains the inputted keyword.

Format: `find <KEYWORD>`

Example:
`find assignment`

Outcome:
```
Master, I have found 2 such tasks:
1.[T][X]programming assignment
2.[D][ ]CS2100 written assignment (by: 12 Mar 2021 11:59pm)
```

### Viewing help: `help`
Format: `help` or `help <COMMAND>`

Example:
`help` or `help event`

Outcome:

If only `help` is used:
```
Master, here is a list of all the commands:
bye - Saves the Task List into a .txt file and closes the program.

deadline - Adds a deadline task into the Task List.
Enter "deadline TASK_NAME /by YYYY-MM-DD HH:MM"
Example: deadline English Essay /by 2021-03-08 23:59

delete - Deletes the task at the specified position.
Enter "delete TASK_NUMBER"
Example: delete 1

done - Marks the task at the specified position as completed
Enter "done TASK_NUMBER"
Example: done 1

event - Adds an event task into the Task List.
Enter "event TASK_NAME /at LOCATION_NAME YYYY-MM-DD HH:MM"
Example: event John's birthday /at MBS 2021-04-15 19:00

find - Finds tasks in the Task List that contains the keyword.
Enter "find KEYWORD"
Example: find meeting

help - Returns a list of commands and their usage.
Enter "help" returns a full list of commands
Enter "help COMMAND" returns the specific instructions for the command
Example 1: help
Example 2: help event

list - Returns the list of tasks in the Task List
Enter "list"

todo - Adds a ToDo task into the Task List
Enter "todo TASK_NAME"
Example: todo read finish LOTR
```

If `help <COMMAND>` is used:
```
Here is the information on the command, Master.
event - Adds an event task into the Task List.
Enter "event TASK_NAME /at LOCATION_NAME YYYY-MM-DD HH:MM"
Example: event John's birthday /at MBS 2021-04-15 19:00
```

### Exit the application: `bye`
Exits duke bot application and saves the task list.

Format: `bye`

Example: `bye`

Outcome:
```
Have a good day, Master. Take care.
```

##FAQ
Q: How do I transfer my data to another computer?
A: Install the Duke app in the other computer and overwrite the empty data file it creates with the file containing your data from your previous duke home folder.

## Command Summary
Action | Format/example
-------------|-------------
todo |`todo <TASK_NAME>` <br> e.g. `todo groceries shopping`
deadline | `deadline <TASK_NAME> /at <YYYY-MM-DD> <HH:MM>` <br> e.g. `deadline CS2100 written assignment /by 2021-03-12 23:59`
event | `event <TASK_NAME> /at <LOCATION> <YYYY-MM-DD> <HH:MM>` <br> e.g. `event lunch /at NUSS 2021-02-24 12:15`
done | `done <TASK_NUMBER>` <br> e.g. `done 5`
delete | `delete <TASK_NUMBER>` <br> e.g. `delete 5`
list | `list` <br>
find | `find <KEYWORD>` <br> e.g. `find assignment`
help | `help` or `help <COMMAND>` <br> e.g.`help` or `help event`
bye | `bye`