# User Guide

## Features
1. [Add tasks](#todo---adds-a-todo-task)
1. [View all tasks](#list---view-all-tasks)
1. [Mark tasks as done](#done---marks-a-task-as-done)
1. [Delete tasks](#delete---removes-a-task)
1. [Search for tasks using keyword](#find---search-for-tasks)
1. [Sort all tasks by date](#sort---search-for-tasks)

## Usage

### `todo` - Adds a todo task
Adds a todo task with a name.

Example of usage: 

`todo mark a task`

Expected outcome:

```
Got it. I've added this task:
 [T][] mark a task
Now you have 1 tasks in the list.
```
### `event` - Adds a event task 
Adds a event task with a name and a date.

Example of usage: 

`event eat a cow /at 2021-09-16`

Expected outcome:

```
Got it. I've added this task:
 [E][] eat a cow (at: Sep 16 2021)
Now you have 2 tasks in the list.
```
### `deadline` - Adds a deadline task
Adds a deadline task with a name and a date.

Example of usage: 

`deadline ip submission /by 2017-01-12`

Expected outcome:

```
Got it. I've added this task:
 [D][] ip submission (by: Jan 12 2017)
Now you have 3 tasks in the list.
```
### `list` - View all tasks
View all the tasks.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][] mark a task
2. [E][] eat a cow (at: Sep 16 2021)
3. [D][] ip submission (by: Jan 12 2017)
```
### `done` - Marks a task as done 
Mark a task in the specified position as done.

Example of usage: 

`done 1`

Expected outcome:

```
Got it. I've marked this task as done:
 [T][X] mark a task
```
### `delete` - Removes a task 
Removes a task in the specified position.

Example of usage: 

`delete 1`

Expected outcome:

```
Got it. I've removed this task:
[T][X] mark a task
Now you have 2 tasks in the list.
```
### `find` - Search for tasks
Search for tasks that matches the specified keyword.

Example of usage: 

`find submission`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][] ip submission (by: Jan 12 2017)
```
### `sort` - Search for tasks
Sort all tasks by date

Example of usage: 

`sort`

Expected outcome:

```
Here are the tasks in your list:
1.[D][] ip submission (by: Jan 12 2017)
2.[E][] eat a cow (at: Sep 16 2021)
```
