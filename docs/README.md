# Simple Bot - User Guide

## Introduction

Enjoy last minute task completions? No? Well, regardless, here's an application written for you!

SimpleBot is a chat-operated task reminder system. Contains the bare minimum to get you through the day.

## Installation Guide

- Install [Java 11](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) on your machine.
- Download the latest [release](https://github.com/WeiLiangLOL/ip/releases/latest).
- Run the jar executable.

## Features

![Ui](Ui.png)

### Commands Overview

Here's an overview of all the functions!

- `hello` Greets you and establishes friendliness.
- `bye` Saves the given tasks.
- `list` Prints current list of tasks.
- `delete <index>` Deletes task at given index.
- `done <index>` Marks task at given index to be done.
- `todo <description>` Creates a generic to-do task.
- `deadline <description> /by <date>` Creates a task with a deadline.
- `event <description> /at <date>` Creates an event on given date.
- `find <description>` Locates a task given a portion of the description.
- `undo` Undo the most recent add or remove.

## Usage

### `hello` - Greets you

Greets you, to keep things friendly.

Example of usage:

`hello`

Sample reply:

```
Hello my name is SimpleBot!
```

---

### `bye` - Says goodbye and saves

Saves your task list as well as bid farewell.

Example of usage:

`bye`

Sample reply:

```
Bye. Hope to see you again soon!
```

---

### `list` - Lists all tasks

Prints the current list of tasks added to the system.

Example of usage: 

`list`

Sample outcome:

```
Printing List!
1.[T][ ] Feed the dog
2.[T][X] Kiss a cat
```

---

### `delete <index>` - Deletes task

Deletes a task at given index.

Example of usage:

`delete 1`

Sample outcome:

```
Noted, I have removed this task.
[T][ ] Eat apples
Now you have 5 tasks in the list.
```

---

### `done <index>` - Marks task done

Marks a task as completed.

Example of usage:

`done 1`

Sample outcome:

```
Nice, I've marked the task as done!
[T][X] Create a task
Now you have 1 tasks in the list.
```

---

### `todo <description>` - Creates todo

Creates a generic todo task with given description.

Example of usage:

`todo CS2103T iP`

Sample outcome:

```
Got it! I've added this task.
[T][ ] CS2103T iP
Now you have 103 tasks in the list.
```

---

### `deadline <description> /by <date>` - Creates deadline

Creates a task with a deadline, with the given description.  
(Note: Date needs to be in java date time format.)

Example of usage:

`deadline CS2103T iP /by 2021-02-19T23:59:59`

Sample outcome:

```
Got it! I've added this task.
[D][ ] CS2103T iP (by: 19 February 2021, 11:59PM)
Now you have 103 tasks in the list.
```

---

### `event <description> /at <date>` - Creates event

Creates a event with a date, with the given description.  
(Note: Date needs to be in java date time format.)

Example of usage:

`event Cry cause I think this is ugly /at 2021-02-20T00:00:00`

Sample outcome:

```
Got it! I've added this task.
[E][ ] Cry cause I think this is ugly (at: 20 February 2021, 12:00AM)
Now you have 104 tasks in the list.
```

---

### `find <description>` - Finds tasks

Locate tasks containing the description.

Example of usage:

`find Eat`

Sample outcome:

```
Printing matches!
1.[T][ ] Eat peaches
2.[T][ ] Eat bananas
```

---

### `undo` - Undos last action

Undo up to the previous action.  
(Note: Undo only works up to the most recent creation/deletion of task.)

Example of usage:

`undo`

Sample outcome:

```
Successfully undone previous action!
```
