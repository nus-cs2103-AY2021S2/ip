# Duke User Guide
Duke is a customized chatbot that is always ready 
to use according to your need and easy to understand.
You can add, mark as done, and delete tasks from the
GUI as explained below.

## Requirements
- Duke can run in Java 11 or later version.
- The '.jar' file of Duke can be found [here] (https://github.com/godjuansan/ip/releases)

## Features 

### Interactive GUI
You can type in tasks that you would like Duke to 
help remember and since Duke has a GUI, it feels 
like chatting with friends and this helps you to
develop feelings towards Duke and create the best
version of yourself ready to tackle those tasks.

<img src="Ui.png" width="200">

### Tasks
Duke helps to organize your tasks and these tasks 
are categorized into 3 types:
- Todo
- Deadline
- Event

You can add, delete, and mark as done these tasks in 
your list.

### Notice duplicates
Duke can help to remind you in case you have already
put in the event inside Duke. This will help you to
organize and not over putting your tasks inside Duke

### Close just with 1 word
You can close Duke by using the keyword 'bye'. Very
simple and self-explanatory.

## Usage

### List Tasks

List all the tasks that you already put inside Duke. <br>
`list`<br>

Example of usage:
`list`<br> will show you all the tasks inside Duke.

Expected outcome:

`1.[E][] Valentine date with Emrata (at:Sunday, 
February 14, 2021 at 7:00:00 PM Singapore Standard Time)`

### Add Task

You can add task so that Duke can help to memorize it 
for you. For each of the category, here is how you do it:
- `todo [description]`<br> Adds a todo task to your list.
- `deadline [description] /by [due-date]`<br>Adds a 
  deadline-typed task that dues on the `due-date` to 
  the list. Your `due-date` must be in `YYYY-MM-DD HH:mm` 
  format.
- `event [description] /at [event-date]`<br>Adds an
  event-typed task that happens on the `event-date` to the 
  list. Your `event-date` must be in `YYYY-MM-DD HH:mm` 
  format.
  
Example of usage:
- `todo Swimming in Liquor`<br>
    Will add a new todo task with the description Swimming
  in Liquor. Duke will let you know if it has already been
  added beforehand or if any error is encountered.

- `deadline MA3111S /by 19-02-2021 23:59`<br>
  Adds a new deadline task with description 
  `MA3111S` which due at `19-02-2021 23:59`. Duke will 
  let you know if it has already been added beforehand 
  or if any error is encountered.
  
- `event Valentine date with Emrata /at 14-02-2021 19:00`<br>
  Adds a new event task with description 
  `Valentine date with Emrata` at `14-02-2021 19:00`.
  Duke will let you know if it has already been added 
  beforehand or if any error is encountered.

  
Expected outcome:

`Got it. I've added this task:` <br />
`[T][] Swimming in Liquor` <br />
`Now you have 2 tasks in the list.`

`Got it. I've added this task:` <br />
`[D][] MA3111S (by: Friday, February 19, 2021 at 
11:59:00 PM Singapore Standard Time)` <br />
`Now you have 2 tasks in the list.`

`Got it. I've added this task:` <br />
`[E][] Valentine date with Emrata (at: Sunday, 
February 14, 2021 at 07:00:00 PM Singapore Standard Time)` <br />
`Now you have 2 tasks in the list.`

### Deletes a Task

Removes a task from the list. <br>
`delete [task-index]`<br>

Example of usage:
`delete 2`<br> will remove the second task in your Duke 
list.

Expected outcome:

`Noted. I've removed this task:` <br />
`[T][] Swimming in Liquor` <br />
`Now you have 1 task in the list.`

### Mark as Done

Mark a task in the list as done. <br>
`done [task-index]`<br>

Example of usage:
`done 2`<br> will mark the second task in your Duke
list as done.

Expected outcome:

`Nice I have marked this task as done!` <br />
`[E][X] Valentine date with Emrata (at: Sunday,
February 14, 2021 at 07:00:00 PM Singapore Standard Time)`

### Find tasks

List all tasks with a specific keyword. <br>
`find [keyword]`<br>

Example of usage:
`find Emrata`<br> 
will display every task that contains `Emrata` in the
description.

Expected outcome:

`1.[E][] Valentine date with Emrata (at:Sunday,
February 14, 2021 at 7:00:00 PM Singapore Standard Time)`

### Find tasks

List all tasks with a specific keyword. <br>
`find [keyword]`<br>

Example of usage:
`find Emrata`<br>
will display every task that contains `Emrata` in the
description.

Expected outcome:

`1.[E][] Valentine date with Emrata (at:Sunday,
February 14, 2021 at 7:00:00 PM Singapore Standard Time)`
