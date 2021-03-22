# User Guide for Duke

## Features 

### `list` - List all tasks 
To list all tasks. Shows the number, task type and done status of each task.

- `[T]` Task
- `[D]` Deadline
- `[E]` Event

Example outcome:
```
1. [D][X] drive to school (by: 2021-12-05)
2. [T][] buy tomato
3. [E][] meeting (at: 2021-02-03)
```

### `sort` - Sort all tasks
Sort tasks in the list by alphabetical order.

Example outcome:
```
1. [T][] buy tomato
2. [D][X] drive to school (by: 2021-12-05)
3. [E][] meeting (at: 2021-02-03)
```

### `todo [DESCRIPTION]` - Add todo task
Add a new todo task with description.

Example input: `todo buy tomato`

Example outcome:
```
I've added this task:
[T][] buy tomato
```

### `deadline [DESCRIPTION] /by [YYYY-MM-DD]` - Add deadline task
Add a new deadline task with description and deadline date in `YYYY-MM-DD` format.

Example input: `deadline do homework /by 2020-12-02`

Example outcome:
```
I've added this task:
[D][] do homework (by: 2 Dec 2020)
```

### `event [DESCRIPTION] /at [YYYY-MM-DD]` - Add event task
Add a new event task with description and date in `YYYY-MM-DD` format.

Example input: `event rock concert /at 2020-12-02`

Example outcome:
```
I've added this task:
[E][] rock concert (at: 2 Dec 2020)
```

### `done [INDEX]` - Mark task as done
Mark a task as done. Specified using 1-based index which can be seen from the list.

Example input: `done 2`

Example outcome:
```
Marked as done:
[D][X] do homework (by: 2 Dec 2020)
```

### `delete [INDEX]` - Delete a task
Delete a task. Specified using 1-based index which can be seen from the list.

Example input: `delete 2`

Example outcome:
```
I removed this task:
[D][X] do homework (by: 2 Dec 2020)
```

### `find [WORD]` - Find a task
Find a task which contains matching word in description.

Example input: `find rock`

Example outcome:
```
1. [E][] rock concert (at: 2 Dec 2020)
```

### `bye` - Exit
To quit duke.

Expected outcome: Program exits

