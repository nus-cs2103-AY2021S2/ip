# User Guide

## Introduction
Introducing... a custom chatbot based on Meme Man and the Surreal Memes.
Not only will Meme Man entertain you every step of the way, Meme Man will also help you keep track of your tasks.
If your task has a tick, it's done. If your task has a cross, it's not done.

Meme Man will also catch exceptions for you, because Meme Man thinks exceptions are not stonks...

Based on the Duke chatbot.

## Features

### Add tasks of different types
You can add 3 types of tasks:
1. ToDo tasks (Fuss-free, own time own target)
2. Deadline tasks (Be sure not to miss these deadlines!)
3. Event tasks (Used to reserve certain timings like a calendar app)

### Delete tasks
If you feel that keeping them on the list is not necessary, Meme Man will delete them for you.

### List tasks
You can key in "list" and Meme Man will display the tasks in the order that you have keyed them in.

### Mark tasks as done
You can key in "done" along with a task number and Meme Man will tick that task for you.

### Mark tasks as undone
You can key in "done" along with a task number and Meme Man will cross that task for you.

### Surreal Memes easter eggs
Keep scrolling to find them.

## Commands related to tasks

### `todo (insert task description)` - Add ToDo task command

Key this command in along with task description to cause Meme Man to add a ToDo task.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

`todo Watch CS2103T lecture`

Expected outcome:

```
Meme Man is now adding to-do task: [T][✘] Watch CS2103T lecture
Total number of tasks: 1
```

### `deadline (insert task description) /by (insert deadline)` - Add Deadline task command

Key this command in along with task description and deadline to cause Meme Man to add a Deadline task.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

```
deadline Do CS2103T lecture quiz /by this Saturday
deadline Do CS2103T ip /by next Monday
```

Expected outcome:

```
Meme Man is now adding deadline task: [D][✘] Do CS2103T lecture quiz (by: this Saturday)
Total number of tasks: 2
Meme Man is now adding deadline task: [D][✘] Do CS2103T ip (by: next Monday)
Total number of tasks: 3
```

Outcome if "/by" is missing:
```
Wrong formatting. Did you forget to put '/by'? Not stonks!
```

### `event (insert task description) /at (insert time)` - Add Event task command

Key this command in along with task description and event time to cause Meme Man to add a Event task.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

`event Attend CS2101 class /at next Tuesday`

Expected outcome:

```
Meme Man is now adding event task: [E][✘] Attend CS2101 class (at: next Tuesday)
Total number of tasks: 4
```
Outcome if "/at" is missing:
```
Wrong formatting. Did you forget to put '/at'? Not stonks!
```


### What happens if task description is empty?
This applies to all 3 types of tasks:
```
Empty (insert task type) task description. Not stonks!
```

### `done (insert number)` - Do task command

Key this command in along with task number to put a tick for that task.

Example of usage:

`done 1`

Expected outcome:
```
Stonks! You've done this task:
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

Outcome if task was already done:
```
This task is already done.
I would have wanted to say Stonks...
but your usage of an illegal operation is Not Stonks!
```

### `undone (insert number)` - Undo task command

Key this command in along with task number to put a cross for that task.

Example of usage:

`undone 1`

Expected outcome:
```
Not stonks! This task has been marked as undone: 
Do CS2103T lecture quiz
```

Outcome if number field is empty:
```
Did you forget to put a number for the command you just typed in? Not stonks!
```
Outcome if number field has anything other than number or improper number (e.g. `undone meme` or `undone 1b`):
```
Did you put something other than a number or did you put a number incorrectly? Not stonks!
```

Outcome if task number is invalid:
```
Invalid task number. Not stonks!
```

Outcome if task was not done in the first place:
```
This task is already not done. Not stonks anyway!
```

### `delete (insert number)` - Delete task command

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

## Other commands

### `bye` - Exit command

Key this command in to cause Meme Man to EJECT you... er... I mean leave...

### `list` - List command

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
2. [D][✓] Do CS2103T lecture quiz (by: this Saturday)
3. [D][✘] Do CS2103T ip (by: next Monday)
4. [E][✘] Attend CS2101 class (at: next Tuesday)
Hmmst've... Stonks
```

Outcome if there is nothing in task list:
```
I have nothing to print. Not stonks!
```

### `orang` - Easter egg command
The orang here refers to the Surreal Memes orange (spelled "Orang"), not Orang Utan orang.
Key this command in and you'd be pleasantly surprised at how punny Orang can be (AND have a realisation about the nature of CS2103/T).

### `vegetal` - Easter egg command
Did someone said... NO VEGETALS?

### Invalid commands
If you key in an unrecognised command, this is what Meme Man has to say:
```
Command not recognised. Not stonks!
```