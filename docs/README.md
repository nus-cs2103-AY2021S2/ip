# User Guide

## Contents 
1. [Features](#features)
2. [Usage](#Usage)
3. [Command Syntax Summary](#command-syntax-summary)
4. [Accepted date time formats](#accepted-date-time-formats) 

## Features 

### 1. Add tasks to your task list
You can add three types of tasks to your Kiwi Task List: todo, event and deadline tasks.

### 2. Modify your task list
View your tasks, mark them done and delete tasks you don't want.

### 3. Interact with Kiwi
Find tasks that contain a specified keyword.

### 4. Don't worry about losing your data
Kiwi helps you save data to your local storage.

### 5. Convenient and friendly syntax
Kiwi provides convenient alternative syntax so that you can save time and keystrokes.

## Usage

#### How to interpret this guide
Replace the words given in {curly braces} with appropriate inputs. For example, replace {DD/MM hh:mm} with a valid date format.

### Add tasks to your task list
____

### `todo` - Add a task to do

Adds a todo task to your tasklist.

Syntax:

`todo {description}`

Example of usage:

`todo sample task 1 `

Expected outcome:

`Success! I've added this task:`

`  [T][] sample task 1`

____

### `event` - Add an event to attend

Adds an event to your tasklist.

Syntax:

`event {description} /at {DD-MM hh:mm}`

Example of usage:

`event task 2 /at 20/4 6PM`

Expected outcome:

`Success! I've added this task:`

`  [E][] {description} (at: {date} {time})`

____

### `deadline` - Add a deadline to attend

Adds a deadline to your tasklist.

Syntax:

`deadline {description} /by {DD-MM hh:mm}`

Example of usage:

`deadline  task 3 /by 17/4 11:59PM`

Expected outcome:

`Success! I've added this task:`

`[D][] task 3 (by: 17/4 11:59PM)`

### View your tasks
____

### `list` - List tasks in your tasklist

Lists all your tasks. If the year details of a task is this year, the year is not printed out to prevent clutter.

Syntax:

`list`

Expected outcome:

`Your tasks:`

`1. [T][] sample task 1`

`2. [E][] task 2 (at: 20/4 6PM)`

`3. [D][] task 3 (by: 17/4 11:59PM)`

`You have 3 tasks.`
____
### `find` - find tasks in your tasklist

Finds tasks containing a certain keyword.

Syntax:

`find {keyword}`

Example of usage:

`find sample`

Expected outcome:

`Your tasks:`

`1. [T][] sample task 1`

`You have 1 tasks.`

### Modify tasks in your task list
____

### `done` - Mark tasks in your tasklist done

Mark a specified task as completed.

Example of usage:

`done {task number}`

Example of usage:

`done 1`

Expected outcome:

`Good work! I've marked this task done:`

`[T][/] sample task 1`


---
### `delete` - Delete tasks in your tasklist

Deletes a specified task.

Syntax:

`delete {task number}`

Example of usage:

`delete 1`

Expected outcome:

`Got you. I've deleted this task:`

` [T][/] sample task 1`

### Miscellaneous
____

### `help` - Get a list of possible inputs you can enter

Provides a list of commands and their syntax.

Syntax:

`help`

---


### `bye` - Exit the kiwi app

Closes the gui and exits the kiwi app.

Syntax:

`bye`

---

## Command Syntax Summary
Command keyword | Accepted alternative keywords* | Usage | Examples
----------|------|------|------
`todo`| `t` | `todo {description}`|`todo sample task 1 `
`event`|`e` | `event {description} /at {DD-MM hh:mm}` | `event task 2 /at 20/4 6PM`
`deadline` | `dl` | `deadline {description} /by {DD-MM hh:mm}` | `deadline  task 3 /by 17/4 11:59PM`
`list`|`ls` `l` | `list` | `list`
`find` | `f` | `find {keyword}` | `find sample`
`done` |- | `done {task number}` | `done 1`
`delete` | `del` | `delete {task number}` | `delete 1`
`help` |`h` | `help` | `help`
`bye` | - | `bye` | `bye`

*How to use alternative keywords: Replace the command keyword in the usage column with any accepted alternative keyword

## Accepted date time formats

For your convenience, kiwi provides multiple date and time formats you can use. 
You can use any combination of a date and time format from the tables below.

These formats can replace the {DD-MM hh:mm} placeholder given for the event and deadline commands above.

An example event command usage would be: `event use alternative syntax /at 30/6 2:40 pm`

### Date syntax 

syntax | example
---|---
dd-mm  | 30-04
dd/mm  | 30/04
dd-mm-yyyy  | 30-04-2023

### Time syntax

syntax | example(s)
---|---
hh:mm | 16:11
hh:mm pm | 11:58 pm, 11:58 am
hham | 11am, 2pm