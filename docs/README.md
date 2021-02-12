# User Guide

## Features 
1. Add task to task list.
1. View all tasks in task list.
1. Mark completed tasks in task list.
1. Delete tasks from task list.
1. Exit the application

## Adding task into task list
To add todo, deadline or event tasks into task list.

### Usage

#### `todo` - To add todo task into task list.

Example of usage: 

`todo (task name)`

Expected outcome:

Message indicating the successful addition of todo task.

#### 'deadline' - To add deadline task into task list.

Example of usage: 

`deadline (task name) /by (date in dd/mm/yyyy) (optional time in 24 hour format)`

Expected outcome:

Message indicating the successful addition of deadline task.

#### 'event' - To add event task into task list.

Example of usage: 

`event (task name) /at (date in dd/mm/yyyy) (optional starting time in 24 hour format) (optional ending time in 24 hour format)`

Expected outcome:

Message indicating the successful addition of event task.

## Viewing all tasks in task list.

### Usage

#### 'list' - To list and view all tasks in task list.

Example of usage: 

`list`

Expected outcome:

All tasks with the completion status will be listed down in increasing order number.

## Marking completed task in task list.

### Usage

#### 'done' - To mark task as completed in task list.

Example of usage: 

`done (task number)`

Expected outcome:

Message indicating the successful marking of task.

## Deleting tasks from task list.

### Usage

#### 'delete' - To delete task from task list.

Example of usage: 

`delete (task number)`

Expected outcome:

Message indicating the successful deletion of task.

## Exiting from application.

### Usage

#### 'bye' - To exit from the application.

Example of usage: 

`bye`

Expected outcome:

Goodbye message.
