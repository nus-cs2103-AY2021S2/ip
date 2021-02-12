# SurrealChat User Guide

## Introduction
Introducing... a custom chatbot based on Meme Man and the Surreal Memes.
Not only will Meme Man entertain you every step of the way, Meme Man will also help you keep track of your tasks.
If your task has a tick, it's done. If your task has a cross, it's not done.

Meme Man will also catch exceptions for you, because Meme Man thinks exceptions are not stonks...

Based on the Duke chatbot.

## How to interpret this guide
Commands usually come in the following format:
`command (ARGUMENTS IF ANY)`

For date and time inputs, they would be given as such:

`{YYYY-MM-DD}T{HH:MM:SS}`

Any capital letter between braces is to be replaced with the relevant date/time component.
The T in the middle is used to indicate time.

Outcomes are usually indicated in the following format:
```
Possible outcomes
```

For this guide, we will assume that the task list is empty and these 4 tasks are going to be keyed in and marked as such:
```
1. Watch CS2103T lecture (ToDo, Done) Low priority
2. Do CS2103T lecture quiz (Deadline of 2021-01-12, 10:10:10, Done) High priority
3. Do CS2103T ip (Deadline of 2021-01-13, 10:10:10, Not done) High priority
4. Attend CS2101 class (Event happens on 2021-01-14, 10:10:10, Not done) Medium priority
```

## Features

### GUI Now Available!
Now comes with a GUI, so you get to see who you are chatting to. Very Stonks!

### Add tasks of different types
You can add 3 types of tasks:
1. ToDo tasks (Fuss-free, own time own target)
2. Deadline tasks (Be sure not to miss these deadlines!)
3. Event tasks (Used to reserve certain timings like a calendar app)

### Prioritise tasks
You can prioritise tasks with numbers 1, 2 and 3 in increasing order of priority.

### Delete tasks
If you feel that keeping them on the list is not necessary, Meme Man will delete them for you.
Even better, you can delete them all in one go!

### Edit tasks
You can edit any details for any task. Use `-keep-` to preserve details from original task.

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
Format: `todo DESCRIPTION ; PRIORITY`

Key this command in along with task description and priority number to cause Meme Man to add a ToDo task.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

`todo Watch CS2103T lecture ; 1`

Expected Outcome:

```
Meme Man is now adding to-do task: [T][✘] Watch CS2103T lecture | Priority: 1
Total number of tasks: 1
```

### Add Deadline task - `deadline`
Format: `deadline DESCRIPTION ; PRIORITY; DATETIME`

Key this command in along with task description, priority number and deadline datetime to cause Meme Man to add a Deadline task.
Deadline datetime must be in this format: {YYYY-MM-DD}T{HH:MM:SS} in 24 hour clock.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

```
deadline Do CS2103T lecture quiz ; 3 ; 2021-01-12T10:10:10
deadline Do CS2103T ip ; 3 ; 2021-01-13T10:10:10
```

Expected Outcome:

```
Meme Man is now adding deadline task: [D][✘] Do CS2103T lecture quiz | Priority: 3 (by: 2021-01-12, 10:10:10)
Total number of tasks: 2
Meme Man is now adding deadline task: [D][✘] Do CS2103T ip | Priority: 3 (by: 2021-01-13, 10:10:10)
Total number of tasks: 3
```

### Add Event task - `event`
Format: `event DESCRIPTION ; PRIORITY ; DATETIME`

Key this command in along with task description, priority number and event datetime to cause Meme Man to add an Event task.
Event date must be in this format: {YYYY-MM-DD}T{HH:MM:SS} in 24 hour clock.
Everytime a task is added, the new total number of tasks is displayed.

Example of usage:

`event Attend CS2101 class ; 2 ; 2021-01-14T10:10:10`

Expected Outcome:

```
Meme Man is now adding event task: [E][✘] Attend CS2101 class | Priority: 2 (at: 2021-01-14, 10:10:10)
Total number of tasks: 4
```

### What happens if task description is empty?
This applies to all 3 types of tasks:
```
Empty (insert task type) task description. Not stonks!
```

### Edit task description - `edit`
Format for ToDo tasks: 

`edit TASK_NUMBER /edit NEW_DESCRIPTION ; NEW_PRIORITY`

Format for Deadline/Event tasks: 

`edit TASK_NUMBER /edit NEW_DESCRIPTION ; NEW_PRIORITY ; NEW_DATETIME`

Key this command in along with task number, "/edit" and the relevant arguments to change corresponding details.
If you want to keep any original detail, put -keep- at the original position.

Example of usage:

`edit 1 /edit Do CS2103T optional ip tasks ; 3`

Expected Outcome:
```
You have edited a task to this:
[T][✓] Do CS2103T optional ip tasks | Priority: 3
```

Example of usage with `-keep-`:

`edit 1 /edit Do CS2103T optional ip tasks ; -keep-`

Expected Outcome with `-keep-`:
```
You have edited a task to this:
[T][✓] Do CS2103T optional ip tasks | Priority: 1
```

What if I choose to `-keep-` all the details?
```
There is no point editing this task! Not stonks!
```

### Mark task as done or undone - `done`
Format: `done TASK_NUMBER`

Key this command in along with task number. This toggles the done status

Example of usage:

`done 1`

Expected Outcome if undone task is marked as done:
```
Stonks! You've done this task:
[D][✓] Do CS2103T lecture quiz | Priority: 3 (by: 2021-01-12, 10:10:10)
```

Expected Outcome if done task is marked as undone:
```
Not stonks! This task has been marked as undone: 
[D][✘] Do CS2103T lecture quiz | Priority: 3 (by: 2021-01-12, 10:10:10)
```

### List - `list`
Format: `list`

Key this command in to cause Meme Man to show you your tasks in order and whether you have completed the tasks.

How to get Meme Man to list out above tasks:

`list`

Expected Outcome:
```
I print the tasks:
1. [T][✓] Watch CS2103T lecture | Priority: 1
2. [D][✓] Do CS2103T lecture quiz | Priority: 3 (by: 2021-01-12, 10:10:10)
3. [D][✘] Do CS2103T ip | Priority: 3 (by: 2021-01-13, 10:10:10)
4. [E][✘] Attend CS2101 class | Priority: 2 (at: 2021-01-14, 10:10:10)
Hmmst've... Stonks
```

Outcome if there is nothing in task list:
```
I have nothing to print. Not stonks!
```

### Delete task - `delete`
Format: `delete TASK_NUMBER`

Key this command in along with task number to delete the corresponding task.
Meme Man will display how many tasks you have left.

Example of usage:

`delete 1`

Expected Outcome:
```
This task has been deleted:
[T][✓] Watch CS2103T lecture | Priority: 1
Total number of tasks: 3
```

### Delete all tasks - `scronch`
Format: `scronch`

Key this command in to delete all tasks and empty task list.

Example usage:
`scronch`

Expected Outcome:
```
Scronching all tasks...
Total number of tasks: 0
```

Outcome if list is already empty:
```
List is already empty. Not stonks!
```

### Find tasks - `find`
Format: `find KEYWORD`

Key this command in along with a single keyword to find tasks that contain that keyword.
Case-insensitive.

Example usage:
`find CS2103T` 

Expected Outcome:
```
Here are my search results:
1. [T][✘] Watch CS2103T lecture | Priority: 1
2. [D][✘] Do CS2103T lecture quiz | Priority: 3 (by: 2021-01-12)
3. [D][✘] Do CS2103T ip | Priority: 3 (by: 2021-01-13)
Hmmst've... Stonks
```

Outcome if no tasks are returned:
```
My search returned nothing. Not stonks!
```

## Sort - `sort`
Format: `sort CRITERIA`

Key this command in along with sorting criteria to sort the tasks.
Currently supported criteria are:
1) Priority Level - `priority`
2) Task done status - `done`

Example usage:
`sort priority`

Expected Outcome:
```
I have sorted the tasks. Stonks!
```

Expected List:
```
I print the tasks:
1. [D][✓] Do CS2103T lecture quiz | Priority: 3 (by: 2021-01-12, 10:10:10)
2. [D][✘] Do CS2103T ip | Priority: 3 (by: 2021-01-13, 10:10:10)
3. [E][✘] Attend CS2101 class | Priority: 2 (at: 2021-01-14, 10:10:10)
4. [T][✓] Watch CS2103T lecture | Priority: 1
Hmmst've... Stonks
```

Outcome if list is empty:
```
I have nothing to sort. Not stonks!
```

Outcome if criteria is unsupported/invalid:
```
Sort type not supported or invalid. Not stonks!
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
Format of arguments: todo [description] ; [priority]
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
