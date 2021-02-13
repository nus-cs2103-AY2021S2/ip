# User Guide
Duke is a task manager. Duke can record your tasks in your daliy life. With Duke, you will never forget important tasks. Duke can manage you task well!

## Features 
- Adds a todo task 
- Adds a deadline task 
- Adds an event task 
- Lists all the tasks
- Mark a task as done
- Delete a task
- Finds tasks
- Sorts tasks
- Exit Duke
---
### Feature 1 
Adds a todo task 

### Usage

### `todo` - Adds a todo task to Duke task list.

Format: `todo <task_name>`

Example of usage: 

`todo CS2103 Tutorial`

Expected outcome:

```
Got it. I've added this task:
[T][ ] CS2103 Tutorial
Now you have 2 tasks in the list.
```
---

### Feature 2 
Adds a deadline task 

### Usage

### `deadline` - Adds a deadline task to Duke task list.

Format: `deadline <task_name> /by <YYYY-MM-DD>`

Example of usage: 

`deadline CS2103 Project /by 2021-02-19`

Expected outcome:

```
Got it. I've added this task:
[D][ ] CS2103 Project (by: Feb 19 2021)
Now you have 3 tasks in the list.
```
---

### Feature 3 
Adds an event task 

### Usage

### `event` - Adds an event task to Duke task list.

Format: `event <task_name> /at <YYYY-MM-DD>`

Example of usage: 

`event CS2103 Presentation /at 2021-03-15`

Expected outcome:

```
Got it. I've added this task:
[E][ ] CS2103 Presentation (by: Mar 15 2021)
Now you have 4 tasks in the list.
```
---

### Feature 4 
Lists all the tasks

### Usage

### `list` - Lists all the tasks

Format: `list`

Example of usage: 

`list`

Expected outcome:

```
1.[T][ ] CS2103
2.[T][ ] CS2103 Tutorial
3.[D][ ] CS2103 Project (by: Feb 19 2021)
4.[E][ ] CS2103 Presentation (at: Mar 15 2021)
```
---

### Feature 5 
Mark a task as done

### Usage

### `done` - Mark a task as done.

Format: `done <order>`

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X]CS2103 Tutorial
```
---

### Feature 6 
Delete a task

### Usage

### `delete` - Delete a task from Duke task list.

Format: `delete <order>`

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
[T][X]CS2103 Tutorial
```
---

### Feature 7 
Finds tasks

### Usage

### `find` - Finds tasks which have specified keyword.

Format: `find <keyword>`

Example of usage: 

`find Tutorial`

Expected outcome:

```
1.[T][ ] CS2103 Tutorial
```
---

### Feature 8 
Sorts tasks

### Usage

### `sort` - Sorts tasks based on time.

Format: `sort`

Example of usage: 

`sort`

Expected outcome:

```
1.[T][ ] CS2103
2.[T][ ] CS2103 Tutorial
3.[D][ ] CS2103 Project (by: Feb 19 2021)
4.[E][ ] CS2103 Presentation (at: Mar 15 2021)
```
---

### Feature 9 
Exit Duke

### Usage

### `bye` - Exit Duke.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
