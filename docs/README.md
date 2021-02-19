# User Guide

Hello! You are using Duckie, an interactive chatbot that helps you manage your daily tasks.
This user guide teaches you how to use Duckie.

## Summary 
These are the commands you can use on Duckie. Specific usage examples are given after the summary.

Note: words in < > are customizable user input.

Adding tasks:
1. `todo` <task> - adds a simple todo task.
2. `deadline` <task> /by <date> - adds a deadline task with a date to be completed by.
3. `event` <task> /at <date> - adds an event task with a date to be held at.
4. `doWithin` <task> /from <date> /to <date> - adds a doWithin task with a beginning date to an end date.

Managing tasks:
1. `list` - shows all tasks in the list in the added order.
2. `done` <task number> - marks the specified task as done.
3. `delete` <task number> - deletes the specified task.
4. `find` <task>  - finds tasks matching user input.

Ending session:
1. `bye` - shows end message.

## Features 

These are Duckie's features.

### Feature 1: todo
Adds a simple todo task.

## Usage

### `todo eat`

Adds a todo task with description "eat" into the list

Expected outcome:
```
ok! i've added this task:
[T][ ] eat
you have 1 task in your list! keep working!
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```
### Feature 2: deadline
Adds a deadline task with a date to be completed by.

## Usage

### `deadline project /by 12-12-2020 2356`

Adds a deadline task with description "project" and date to be completed "12-12-2020 2356" into the list.

Expected outcome:
```
ok! i've added this task:
[D][ ] deadline project  (by: Sat Dec 12 23:56:00 SGT 2020)
you have 2 tasks in your list! keep working!
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```
### Feature 3: event
Adds an event task with a date to be held at.

## Usage

### `event project /by 12-03-2021 1200`

Adds an event task with description "conference" and date to be held "12-03-2021 1200" into the list.

Expected outcome:
```
ok! i've added this task:
[E][ ] event conference  (at: Fri Mar 12 12:00:00 SGT 2021)
you have 3 tasks in your list! keep working!
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```

### Feature 4: doWithin
Adds a doWithin task with a beginning date to an end date.

## Usage

### `doWithin buy present /from 12-12-2020 2356 /to 12-03-2021 1200`

Adds a doWithin task with description "buy present" and date to be completed from "12-12-2020 2356" 
to "12-03-2021 1200" into the list.

Expected outcome:
```
ok! i've added this task:
[DW][ ] doWithin buy present  (from: Sat Dec 12 23:56:00 SGT 2020 to: Fri Mar 12 12:00:00 SGT 2021)
you have 4 tasks in your list! keep working!
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```
### Feature 5: list
Shows all tasks in the list in the added order.

## Usage

### `list`

Shows all items currently in the list.

Expected outcome:
```
get to work! these are the tasks in your list:
1.[T][ ] eat
2.[D][ ] deadline project  (by: Sat Dec 12 23:56:00 SGT 2020)
3.[E][ ] event conference  (at: Fri Mar 12 12:00:00 SGT 2021)
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```

### Feature 6: done
Marks the specified task as done.

## Usage

### `done 1`

Marks the task numbered 1 as done with a cross.

Expected outcome:
```
cool! this task is marked as done:
[T][✘] eat
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```

### Feature 7: delete
Deletes the specified task.

## Usage

### `delete 2`

Removes the task from the list.

Expected outcome:
```
ok! i've deleted this task:
[D][ ] deadline project  (by: Sat Dec 12 23:56:00 SGT 2020)
yay! you have 2 tasks left to do!
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```
### Feature 8: find
Finds tasks matching user input.

## Usage

### `find eat`

Returns tasks matching user input.

Expected outcome:
```
found it! here's your task(s):
[T][✘] eat
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```
### Feature 9: bye
Shows end message

## Usage

### `Bye`

Shows end message.

Expected outcome:
```
goodbye! c ya soon ;)
:) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)
```