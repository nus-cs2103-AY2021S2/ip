# User Guide

Need a productivity task manager application and love stonks at the same time?
Duke is perfect for you.

![Screenshot](./Ui.png)

## Recommended for:
* Users who can type fast
* Users who love productivity
* Users who hate wall street

## Installation
* Ensure that you have Java 11 installed in your computer
* Download the jar file from the [latest release](https://github.com/JonahhGohh/ip/releases)
* Double click the jar file to run the program

## Features 
- [Add tasks](#add-tasks)
- [Delete tasks](#delete-tasks)
- [Mark tasks as done](#mark-tasks-as-done)
- [List tasks](#list-tasks)
- [Find tasks](#find-tasks)


### Add Tasks
Duke can help you to manage 3 different types of tasks:
* Todos
* Deadlines
* Events

#### Todos
Todos are the simplest form of tasks, they only contain the description of the task

How to use: `todo [description]`

Example: `todo Browse wallstreetbets subreddit` - Creates a 'Browse wallstreetbets subreddit' task

Expected outcome: 
```
Got it. I've added this task:
[T][] Browse wallstreetbets subreddit
Now you have 1 task in the list.
```

#### Deadlines
Deadlines contains the description of the task and the date of the deadline of the task

How to use: `deadline [description] /by YYYY-MM-DD`

Example: `deadline Read GME latest 10Q filing /by 2021-02-20` - Creates a 'Read $GME latest 10Q filing' task with a
deadline on 20 February 2021

Expected outcome:
```
Got it. I've added this task:
[D][] Read GME latest 10Q filing (by: Sat Feb 20 2021)
Now you have 2 tasks in the list.
```

#### Events
Events contain the description of the task and the date of the event

How to use: `event [description] /at YYYY-MM-DD`

Example: `event Meet with Elon Musk /at 2021-02-21` - Creates an 'event Meet with Elon Musk' event on 20 February 2021

Expected outcome:
```
Got it. I've added this task:
[E][] Meet with Elon Musk (at: Sat Feb 21 2021)
Now you have 3 tasks in the list.
```

### Delete Tasks
Duke can delete tasks you specify individually

How to use: `delete [task number]`

Example: `delete 1` - deletes your first task

Expected outcome:
```
The following task has been removed:
[T][] Browse wallstreetbets subreddit
Now you have 2 tasks in the list
```

### Mark tasks as done
Duke allows you to mark tasks that you have completed

How to use: `done [task number]`

Example: `done 1` - marks your first task as completed

Expected outcome:
```
You have completed the following task:
[D][X] Read GME latest 10Q filing (by: Sat Feb 20 2021)
Keep up the good work!
```


### List tasks
Duke allows you to list all tasks that you have entered

How to use: `list`

Example: `list` - list all tasks

Expected outcome:
```
Here are the tasks in your list:
1. [D][X] Read GME latest 10Q filing (by: Sat Feb 20 2021)
2. [E][] Meet with Elon Musk (at: Sat Feb 21 2021)
```

### Find tasks
Duke allows you to find tasks that matches with your input

How to use: `find [keyword]`

Example: `find Elon` - displays the tasks with 'Elon' string in the description if any

Expected outcome:
```
Here are the matching results on your list:
1. [E][] Meet with Elon Musk (at: Sun Feb 21 2021)
```

## Accepted Date and Time Formats
* `YYYY-MM-DD`

## Uninstall Duke
To uninstall Duke, simply delete the `duke.jar` file at its current location