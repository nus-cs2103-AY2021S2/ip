# User Guide

Duke is a personal todo list which helps you keep track of your todos, deadlines and events.

## Features 

* Addition of tasks to task list

    > Adds todo, deadlines and events to the task list.

* Marking of tasks as done

    > Marks a task as done in the task list.

* Searching of tasks in task list

    > Search for a task by keyword in the task list.


## Usage

### `list` - List all tasks

Shows a list of all tasks in task list.

Example of usage: 

`list`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][ ] Task 1
2. [D][ ] Deadline 1 (by: Aug 5 2020 00:00)
```

---

### `find` - Find tasks by keyword

Shows a list of tasks related to the given keyword.
Search criteria can be based on partial match of task name and by task type. 

Format:

`find KEYWORD_1 [KEYWORD_2 KEYWORD_3 ...]`

Example of usage:

`find deadline key`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][ ] Keyword project (by: Aug 9 2020 00:00)
2. [D][ ] Keypress project (by: Sep 10 2020 00:00)
3. [D][ ] Keylog project (by: Mar 2 2020 00:00)
```

---

### `todo` - Add todos to list

Creates a new todo with the given name and adds it to the task list.

Format:

`todo TASK_NAME`

Example of usage:

`todo Task 1`

Expected outcome:

```
Got it. I've added this task:
    [T][ ] Task 1
Now you have 3 task(s) in the list.
```

---

### `deadline` - Add deadlines to list

Creates a new deadline with the given name and date and adds it to the task list.

Format:

`deadline TASK_NAME /by DEADLINE`

DEADLINE to be specified in the format `dd/MM/yyyy HHmm`, `yyyy-MM-dd HH:mm` or `MMM d yyyy`.
Examples of valid datetime formats are `06/12/2015 2025`, `2020-08-05 07:08` or `Jan 1 2021`.

Example of usage:

`deadline Assignment 1 /by 06/12/2015 2025`

Expected outcome:

```
Got it. I've added this task:
    [D][ ] Assignment 1 (by: Dec 6 2015 20:25)
Now you have 4 task(s) in the list.
```

---

### `event` - Add events to list

Creates a new event with the given name and date and adds it to the task list.

Format:

`event TASK_NAME /at DATETIME`

DATETIME to be specified in the format `dd/MM/yyyy HHmm`, `yyyy-MM-dd HH:mm` or `MMM d yyyy`.
Examples of valid datetime formats are `06/12/2015 2025`, `2020-08-05 07:08` or `Jan 1 2021`.

Example of usage:

`event Concert /at 2021-05-03 08:00`

Expected outcome:

```
Got it. I've added this task:
    [E][ ] Concert (at: May 3 2021 08:00)
Now you have 7 task(s) in the list.
```

---

### `delete` - Delete tasks from list

Removes a task from the task list.

Format:

`delete INDEX`

Example of usage:

`delete 3`

Expected outcome:

```
Nice! I've removed this task:
    [T][X] Task 2
Now you have 5 task(s) in the list.
```

---

### `done` - Mark tasks as done

Mark a particular task from the task list as done.

Format:

`done INDEX`

Example of usage:

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][X] Task 2
```

---

### `bye` - Exits the program

Exits the program.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
