# SurrealChat User Guide

## Introduction
Introducing... a custom chatbot based on Meme Man and the Surreal Memes.
Not only will Meme Man entertain you every step of the way, Meme Man will also help you keep track of your tasks.
If your task has a tick, it's done. If your task has a cross, it's not done.

Meme Man will also catch exceptions for you, because Meme Man thinks exceptions are not stonks...

Based on the Duke chatbot.

## How to interpret this guide
Commands usually come in the following format:
`command (arguments if any)`

For date and time inputs, they would be given as such:
`{YYYY-MM-DD}T{HH:MM:SS}`
Any capital letter between braces is to be replaced with the relevant date/time component.
The T in the middle is used to indicate time.

## Features

### GUI Now Available!
Now comes with a GUI, so you get to see who you are chatting to. Very Stonks!

### Add tasks of different types
You can add 3 types of tasks:
1. ToDo tasks (Fuss-free, own time own target)
2. Deadline tasks (Be sure not to miss these deadlines!)
3. Event tasks (Used to reserve certain timings like a calendar app)

### Delete tasks
If you feel that keeping them on the list is not necessary, Meme Man will delete them for you.

### List tasks
You can key in "list" and Meme Man will display the tasks in the order that you have keyed them in.

### Save and load tasks
You can now save the tasks on your computer and Meme Man will load them upon starting up. 
It's automatic. No user input required!

### Mark tasks as done
You can key in "done" along with a task number and Meme Man will tick that task for you.

### Mark tasks as undone
You can key in "done" along with a task number and Meme Man will cross that task for you.

### Find tasks
You can key in a single keyword and get a narrowed list of tasks. 

### Surreal Memes easter eggs
Keep scrolling to find them.

### Find help for commands
If you are not sure how a command works, Meme Man will show you the format if you call for his help.

## Commands related to tasks

###  Add ToDo task - `todo`
Format: `todo DESCRIPTION`

Key this command in along with task description to cause Meme Man to add a ToDo task.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

`todo Watch CS2103T lecture`

Expected outcome:

```
Meme Man is now adding to-do task: [T][✘] Watch CS2103T lecture
Total number of tasks: 1
```

### Add Deadline task - `deadline`
Format: `deadline DESCRIPTION /by DATE`

Key this command in along with task description and deadline date to cause Meme Man to add a Deadline task.
Deadline date must be in this format: {YYYY-MM-DD}T{HH:MM:SS} in 24 hour clock.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

```
deadline Do CS2103T lecture quiz /by 2021-01-12T10:10:10
deadline Do CS2103T ip /by 2021-01-13T10:10:10
```

Expected outcome:

```
Meme Man is now adding deadline task: [D][✘] Do CS2103T lecture quiz (by: 2021-01-12, 10:10:10)
Total number of tasks: 2
Meme Man is now adding deadline task: [D][✘] Do CS2103T ip (by: 2021-01-13, 10:10:10)
Total number of tasks: 3
```

Outcome if "/by" is missing:
```
Wrong formatting. Did you forget to put '/by'? Not stonks!
```

Outcome for incorrect date time format:
```
"Input date time format is incorrect. Not stonks!"
```

### Add Event task - `event`
Format: `event DESCRIPTION /at DATE`

Key this command in along with task description and event date to cause Meme Man to add a Event task.
Event date must be in this format: {YYYY-MM-DD}T{HH:MM:SS} in 24 hour clock.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

`event Attend CS2101 class /at 2021-01-14T10:10:10`

Expected outcome:

```
Meme Man is now adding event task: [E][✘] Attend CS2101 class (at: 2021-01-14, 10:10:10)
Total number of tasks: 4
```
Outcome if "/at" is missing:
```
Wrong formatting. Did you forget to put '/at'? Not stonks!
```

Outcome for incorrect date time format:
```
"Input date time format is incorrect. Not stonks!"
```

### What happens if task description is empty?
This applies to all 3 types of tasks:
```
Empty (insert task type) task description. Not stonks!
```

### Edit task description - `edit`
Format: `edit TASK_NUMBER /edit NEW_DESCRIPTION`

Key this command in along with task number, "/edit" and a new description to change that task's description.

Example of usage:

`edit 1 /edit Do CS2103T optional ip tasks`

Expected outcome:
```
You have edited a task description to this:
Do CS2103T optional ip tasks
```

Outcome if you only put edit command:
```
Did you forget to add the task number and new description? Not stonks!
```

Outcomes if you forget to put /edit:
```
Task number not parsed. Did you forget to put '/edit'? Or did you not put a number? Not stonks!

Wrong formatting. Did you forget to put '/edit' and/or the description? Not stonks! (Happens if you only put a number too!)
```

Outcome if you did not put a proper task number but still have /edit:
```
Task number not parsed. Did you forget to put '/edit'? Or did you not put a number? Not stonks!
```

Outcome if your description is empty but you still put /edit:
```
Wrong formatting. Did you forget to put '/edit' and/or the description? Not stonks!
```

### Mark task as done or undone - `done`
Format: `done TASK_NUMBER`

Key this command in along with task number. This toggles the done status

Example of usage:

`done 1`

Expected outcome if undone task is marked as done:
```
Stonks! You've done this task:
Do CS2103T lecture quiz
```

Expected outcome if done task is marked as undone:
```
Not stonks! This task has been marked as undone: 
Do CS2103T lecture quiz
```

Outcome if number field is empty:
```
Did you forget to put a number for the command you just typed in? Not stonks!
```

Outcome if number field has anything other than number or improper number (e.g. `done meme` or `done 1b`):
```
Did you put something other than a number or did you put a number incorrectly? Not stonks!
```

A task number is invalid if any of the following conditions are met:
```
1. 0
2. Negative number
3. Number is greater than number of tasks Meme Man has stored
```

Outcome if task number is invalid:
```
Invalid task number. Not stonks!
```

### Delete task - `delete`
Format: `delete TASK_NUMBER`

Key this command in along with task number to delete the corresponding task.
Meme Man will display how many tasks you have left.

Example of usage:

`delete 1`

Expected outcome:
```
This task has been deleted:
[T][✓] Watch CS2103T lecture
Total number of tasks: 3
```

Outcome if number field is empty:
```
Did you forget to put a number for the command you just typed in? Not stonks!
```

Outcome if number field has anything other than number or improper number (e.g. `delete meme` or `delete 1b`):
```
Did you put something other than a number or did you put a number incorrectly? Not stonks!
```

Outcome if task number is invalid:
```
Invalid task number. Not stonks!
```
### Find tasks - `find`
Format: `find KEYWORD`

Key this command in along with a single keyword to find tasks that contain that keyword.
Case-insensitive.

Example usage:
`find CS2103T` 

Expected outcome:
```
Here are my search results:
1. [T][✘] Watch CS2103T lecture
2. [D][✘] Do CS2103T lecture quiz (by: 2021-01-12)
3. [D][✘] Do CS2103T ip (by: 2021-01-13)
Hmmst've... Stonks
```

Outcome if keyword field is empty:
```
No keyword given! Not stonks!
```

Outcome if no tasks are returned:
```
My search returned nothing. Not stonks!
```

## Other commands
### Help - `help`
Format: `help COMMAND`

Key this command in along with the name of another command to display how to use the command.

Example usage:
`help todo`

Expected Outcome:
```
Given a description, stores todo task.
Format of arguments: todo [description]
```
Outcome for invalid commands:
```
Helper does not recognise command. Not Stonks!
```

### List - `list`
Format: `list`

Key this command in to cause Meme Man to show you your tasks in order and whether you have completed the tasks.

Now suppose you have 4 tasks:
```
1. Watch CS2103T lecture (ToDo, Done)
2. Do CS2103T lecture quiz (Deadline of this Saturday, Done)
3. Do CS2103T ip (Deadline of next Monday, Not done)
4. Attend CS2101 class (Event happens next Tuesday, Not done)
```

How to get Meme Man to list out above tasks:

`list` 

Expected outcome:
```
I print the tasks:
1. [T][✓] Watch CS2103T lecture
2. [D][✓] Do CS2103T lecture quiz (by: 2021-01-12)
3. [D][✘] Do CS2103T ip (by: 2021-01-13)
4. [E][✘] Attend CS2101 class (at: 2021-01-14)
Hmmst've... Stonks
```

Outcome if there is nothing in task list:
```
I have nothing to print. Not stonks!
```

### Easter egg - `orang`
Format: `orang`

The orang here refers to the Surreal Memes orange (spelled "Orang"), not Orang Utan orang.
Key this command in and you'd be pleasantly surprised at how punny Orang can be (AND have a realisation about the nature of CS2103/T).

### Easter egg - `vegetal`
Format: `vegetal`

Did someone said... NO VEGETALS?

### Easter egg - `icandoit` or `aikendueet`
Format: `icandoit` or `aikendueet`

You can use either command to achieve transcendence. Try it out!

### Invalid commands
If you key in an unrecognised command, this is what Meme Man has to say:
```
Command not recognised. Not stonks!
```
### Excess inputs
If you key in additional inputs next to a no-input command such as `list`, this is what Meme Man has to say:
```
Excessive inputs for a no-input command. Not stonks!
```