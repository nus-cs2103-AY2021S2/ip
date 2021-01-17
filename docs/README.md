# User Guide

## Introduction
Introducing... a custom chatbot based on Meme Man and the Surreal Memes.
Not only will Meme Man entertain you every step of the way, Meme Man will also help you keep track of your tasks.
If your task has a tick, it's done. If your task has a cross, it's not done. 

## Features

### Add tasks
You can key in "add" along with a task description and Meme Man will record it down for you.

### List tasks
You can key in "list" and Meme Man will display the tasks in the order that you have keyed them in.

### Mark tasks as done
You can key in "done" along with a task number and Meme Man will tick that task for you.

### Mark tasks as undone
You can key in "done" along with a task number and Meme Man will cross that task for you.

## Usage

### `bye` - Exit command

Key this command in to cause Meme Man to EJECT you... er... I mean leave...

### `add` - Add task command

Key this command in along with task description to cause Meme Man to add task.

Example of usage:

`add Do CS2103T lecture quiz`

Expected outcome:

`Meme Man is now adding task: Do CS2103T lecture quiz`

### `list` - List command

Key this command in to cause Meme Man to show you your tasks in order and whether you have completed the tasks.

Now suppose you have 2 tasks:
1. Do CS2103T lecture quiz (Done)
2. Do CS2103T ip (Not done)

How to get Meme Man to list out above tasks:
`list` 

Expected outcome:
```
I print the tasks:
1. [✓] Do CS2103T lecture quiz
2. [✘] Do CS2103T ip
Hmmst've... Stonks
```
Outcome if there is nothing in task list:
`I have nothing to print. Not stonks!`

### `done` - Do task command

Key this command in along with task number to put a tick for that task.

Example of usage:

`done 1`

Expected outcome:
```
Stonks! You've done this task:
 Do CS2103T lecture quiz
```
Outcome if task number is invalid:

`Invalid task number. Not stonks!`

### `undone` - Undo task command

Key this command in along with task number to put a cross for that task.

Example of usage:

`undone 1`

Expected outcome:
```
Not stonks! This task has been marked as undone: 
 Do CS2103T lecture quiz
```
Outcome if task number is invalid:

`Invalid task number. Not stonks!`

### Invalid commands
If you key in an unrecognised command, this is what Meme Man has to say:
`Command not recognised. Not stonks!`