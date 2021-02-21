# Vergil User Guide

Welcome! This is a user guide for the Vergil chatbot application.

![UI screenshot](Ui.png)

## Table of Contents
[1. Adding tasks.](#1-Adding-Tasks)\
&nbsp;&nbsp;&nbsp;&nbsp;[1.1. To-do tasks.](#11-To-do-tasks-todo)\
&nbsp;&nbsp;&nbsp;&nbsp;[1.2. Deadline tasks.](#12-Deadline-tasks-deadline)\
&nbsp;&nbsp;&nbsp;&nbsp;[1.3. Event tasks.](#13-Event-tasks-event)\
[2. Displaying tasks.](#2-Displaying-Tasks-list)\
[3. Finding tasks.](#3-Finding-Tasks-find)\
[4. Completing tasks.](#4-Completing-Tasks-done)\
[5. Deleting tasks.](#5-Deleting-Tasks-delete)\
[6. Exiting the program.](#6-Exiting-Vergil-bye)\
[7. Command summary.](#7-Command-Summary)\

## 1. Adding Tasks
### 1.1. To-do tasks: `todo`

To add a new to-do task, enter the following command:

`todo <task-description>`

Where `<task-description>` is the description of the to-do task.

Entering this command properly should result in the following message:
> Success! '<task-description>' has been added as a todo task.

### 1.2. Deadline tasks: `deadline`
To add a new deadline task, enter the following command:

`deadline <task-description> /by <date> <time>`

Where...
* `<task-description>` is the description of the deadline task;
* `<date>` is the date of the deadline in the following format: d/m/yyyy; and
* `<time>` is the time of the deadline, in 24-hours time, in the following format: hhmm.

Entering this command properly should result in the following message:
> Success! '<task-description>' has been added as a deadline task.

### 1.3. Event tasks: `event`
To add a new event task, enter the following command:

`event <task-description> /at <date> <time>`

Where...
* `<task-description>` is the description of the deadline task;
* `<date>` is the date of the event in the following format: d/m/yyyy; and
* `<time>` is the time of the event, in 24-hours time, in the following format: hhmm.

Entering this command properly should result in the following message:
> Success! '<task-description>' has been added as an event task.

## 2. Displaying Tasks: `list`
To display the list of current tasks, enter the following command:

`list`

This should display a list of the currently existing tasks, with each task prefixed by its list's serial number (with respect to the other tasks).

## 3. Finding Tasks: `find`
To search for tasks whose descriptions match a keyword, enter the following command:

`find <keywords>`

Where `<keywords>` is a word or phrase to be used for searching.

This should display a list of the currently existing tasks whose descritptions match the search query. If no such task exists, the following message would be displayed instead:
> There are no tasks matching with the given keywords.

## 4. Completing Tasks: `done`
To mark an existing task as completed, enter the following command:

`done <task-list-number>`

Where `<task-list-number>` is the serial number of task with respect to the other tasks in the list.

Entering this command properly should result in the following message:
> Success! The following task has been completed:
> <task-information>

Where `<task-information>` is the information stored in the task.

## 5. Deleting Tasks: `delete`
To delete an existing task from the list, enter the following command:

`delete <task-list-number>`

Where `<task-list-number>` is the serial number of task with respect to the other tasks in the list.

Entering this command properly should result in the following message:
> Acknowledged. The following task has been deleted:
> <task-information>

Where `<task-information>` is the information stored in the task.

## 6. Exiting Vergil: `bye`
To exit the program, simply click the 'Close' button in the top right of the window or type in the following command:

`bye`

## 7. Command Summary

Function            | Command format
--------------------|---------------
Add a to-do task    | `todo <task-description>`
Add a deadline task | `deadline <task-description> /by <date> <time>`
Add an event task   | `event <task-description> /at <date> <time>`
List tasks          | `list`
Find tasks          | `find`
Complete a task     | `done <task-list-number>`
Delete a task       | `delete <task-list-number>`
Exit                | `bye`
