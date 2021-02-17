# ToDoBeast

---
```
Welcome to the ToDo
 _                    _   
| |                  | |  
| |__   ___  __ _ ___| |_ 
| '_ \ / _ \/ _` / __| __|
| |_) |  __/ (_| \__ \ |_ 
|_.__/ \___|\__,_|___/\__| ,

your greatest productivity tool!
```
ToDoBeast is a task-tracking chatbot that allows you to keep track of upcoming to-dos, deadlines and events. 📚

## Features

---

### Tracking tasks 📑
Three types of tasks can be added: To-Dos, Deadlines, and Events. Once a task is added, ToDoBeast will keep track of the task and its completion progress.

Tasks can also be marked as done or deleted accordingly.

### Adding notes to tasks 📝
You can also add notes/remarks to their tasks, either to new tasks or pre-existing tasks.

### Listing tasks 📖
ToDoBeast can list all the tasks that you have at once, so that you can keep tabs on the things that you have to do.

### Finding tasks 🔎
You can search for a task by specific keywords, and ToDoBeast will return the tasks containing matching descriptions.
<br/>

## Usage

---
_** All command parameters are delimited by `, ` **_

### 1. `instructions` - Lists instructions

Provides a detailed description of the various commands that can be used for ToDobeast.
<br/>

### 2. `todo, [taskDescription], [taskNotes] (optional)` - Add new To-do task

Adds a new To-do type task to ToDoBeast. Including notes are optional.

Example of usage: 

`todo, get milk from the supermarket, farmhouse brand milk`

Expected outcome:

```
One more task added to the hustle:
    TODO |   | get milk from the supermarket | farmhouse brand milk
You now have 1 tasks in total.
```
<br/>

### 3. `deadline, [taskDescription], by YYYY-MM-DD HH:MM, [taskNotes] (optional)` - Add new Deadline task

Adds a new Deadline type task to ToDoBeast. the `by` keyword must be specified, and date and time inputs must be in `YYYY-MM-DD` and `HH:MM` formats respectively. Including notes are optional.

Example of usage: 

`deadline, do CS2103 assignment, by 2021-02-19 23:59`

Expected outcome:

```
One more task added to the hustle:
    DEADLINE |   | do CS2103 assignment | by 19 Feb 2021, 23:59
You now have 2 tasks in total.  
```
<br/>

### 4. `event, [taskDescription], at YYYY-MM-DD HH:MM, [taskNotes] (optional)` - Add new Event task

Adds a new Event type task to ToDoBeast. the `at` keyword must be specified, and date and time inputs must be in `YYYY-MM-DD` and `HH:MM` formats respectively. Including notes are optional.

Example of usage: 

`event, committee meeting, at 2021-03-20 18:00, remember to prepare agenda!`

Expected outcome:

```
One more task added to the hustle:
    EVENT |   | committee meeting | at 19 Feb 2021, 23:59 | remember to prepare agenda!
You now have 3 tasks in total.  
```
<br/>

### 5. `list` - Lists all tasks

Example outcome: 

```
Here are your tasks:

1. TODO |   | get milk from the supermarket | farmhouse brand milk
2. DEADLINE |   | do CS2103 assignment | by 19 Feb 2021, 23:59
3. EVENT |   | committee meeting | at 19 Feb 2021, 23:59 | remember to prepare agenda!
```
<br/>

### 6. `note/notes, [taskIndex], [taskNotes]` - Add notes to existing task

Adds notes to an existing task. `[taskIndex]` indicates the index of the task as it appears in ToDoBeast. If there were existing notes present, they will be overwritten by the new notes.

Note: if `[taskNotes]` is left empty, notes will be deleted from the task.

Example of usage: 

`notes, 2, merge PR for assignment`

Expected outcome:

```
I've added notes to this task:
    DEADLINE |   | do CS2103 assignment | by 19 Feb 2021, 23:59 | merge PR for assignment  
```
<br/>

### 7. `delete, [taskIndex]` - Delete task

Deletes the specified task with the respective `[taskIndex]`.

Example of usage: 

`delete, 1`

Expected outcome:

```
Got it! I've removed this task for you:
    TODO |   | get milk from the supermarket | farmhouse brand milk
You now have 2 tasks in total.
```
<br/>

### 8. `done, [taskIndex]` - Mark task as done

Marks task with `[taskIndex]` as done.

Example of usage: 

`done, 1`

Expected outcome:

```
Good job! You've just completed this task:
    DEADLINE | ✔ | do CS2103 assignment | by 19 Feb 2021, 23:59 | merge PR for assignment  
You now have 2 tasks in total.
```
<br/>

### 9. `find, [regex]` - Search for tasks

Returns a list of all tasks that have the specified keywords, `[regex]`, as defined by the user.

Example of usage:

`find, committee`

Expected outcome:

```
Here are the matching tasks in your list:
   
Task #2. EVENT |   | committee meeting | at 19 Feb 2021, 23:59 | remember to prepare agenda!
```
<br/>

### 10. `bye/exit` - Exit ToDoBeast

Quits the application.
