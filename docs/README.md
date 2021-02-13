# User Guide

## Features 

### Adding a to-do task: `todo`

Adds a to-do task with a description to the to-do list. 

### Adding a deadline task: `deadline`

Adds a deadline task with a description and date to the to-do list.

### Adding an event task: `event`

Adds an event task with a description, date and time to the to-do list.

### Viewing all tasks in the to-do list: `list`

Displays all active tasks in the to-do list. 

### Marking a task as complete: `done`

Marks a task as completed in the to-do list. 

### Deleting a task: `delete` 

Deletes a task from the to-do list. 

### Finding a task with a keyword: `find` 

Finds a task from the to-do list which matches the keyword given. 

### Archiving a task: `archive` 

Removes the task from the to-do list and moves it to the archive list. 

### Viewing all tasks in the archive list: `archive` 

Displays all tasks in the archive list. 

### Unarchiving a task: `unarchive`

Removes the task from the archive list and moves it back to the to-do list.

### Saving data: `save`

Saves the data. 

### Exiting the program: `exit`

Saves the data and closes the window.

### 

## Usage

### `todo` - Adds a to-do task.

Adds a to-do task with a description to the to-do list.

Example of usage: 

`todo read book`

Expected outcome:

```
Okay, I've added this task:
[T][ ] read book
You now have a total of 1 tasks.
```

---

### `deadline` - Adds a deadline task.

Adds a deadline task with a description and date to the to-do list.

Example of usage: 

`deadline return book /by 2021-03-01`

Expected outcome:

```
Okay, I've added this task:
[D][ ] return book (by: Mar 1 2021)
You now have a total of 2 tasks.
```

---

### `event` - Adds an event task.

Adds an event task with a description, date and time to the to-do list.

Example of usage: 

`event project meeting /at 2021-02-15T14:30`

Expected outcome:

```
Okay, I've added this task:
[E][ ] project meeting (at: Feb 15 2021 14:30)
You now have a total of 3 tasks.
```

---

### `list` - Displays to-do list.

Displays all active tasks in the to-do list. 

Example of usage: 

`list`

Expected outcome:

```
Here is your to-do list:
1. [T][ ] read book
2. [D][ ] return book (by: Mar 1 2021)
3. [E][ ] project meeting (at: Feb 15 2021 14:30)
```

---

### `done` - Marks a task as completed.

Marks a task as completed in the to-do list. 

Example of usage: 

`done 1`

Expected outcome:

```
Okay, this task has been marked as done: 
1. [T][X] read book
```

---

### `delete` - Deletes a task.

Deletes a task from the to-do list. 

Example of usage: 

`delete 3`

Expected outcome:

```
Okay, I've deleted this task:
[E][ ] project meeting (at: Feb 15 2021 14:30)
You now have a total of 2 tasks.
```

---

### `find` - Finds task with given keyword. 

Finds task from the to-do list which matches the keyword given. 

Example of usage: 

`find book`

Expected outcome:

```
Bingo Flamingo! I've found these matches: 
1. [T][X] read book
2. [D][ ] return book (by: Mar 1 2021)
```

---

### `archive` - Archives task.

Removes the task from the to-do list and moves it to the archive list.

Example of usage: 

`archive 1`

Expected outcome:

```
Okay, I've archived this task: 
[T][X] read book
You have archived 1 tasks.
```

Example of usage: 

`archive all`

Expected outcome:

```
All tasks have been archived! 
You have archived 2 tasks.
```

---

### `archive` - Displays archive list.

Displays all tasks in the archive list. 

Example of usage: 

`archive`

Expected outcome:

```
Here is the archive:
1. [T][X] read book
2. [D][ ] return book (by: Mar 1 2021)
```

---

### `unarchive` - Unarchives task.

Removes the task from the archive list and moves it back to the to-do list. 

Example of usage: 

`unarchive 2`

Expected outcome:

```
Okay, I've unarchived this task:
[D][ ] return book (by: Mar 1 2021)
You now have 1 tasks in the archive.
```

---

### `save` - Saves the data.

Saves the data. 

Example of usage: 

`save`

Expected outcome:

```
Great! I've saved your data.
```

---

### `exit` - Exits the program.

Saves the data and closes the window.

Example of usage: 

`exit`

---
