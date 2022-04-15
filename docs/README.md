# Meet Don, The Little Adventurer — User Guide

Don is a hardworking and dedicated explorer, 
and his mission is to help you manage your tasks! 
(And hopefully make it a little more fun for you to do so.)

If you're still new here, 
this guide will help you get to know Don a little better.

## Features 
Here is an overview of what Don can do:

Feature | Command(s)
--------|--------
Add task: todo | `todo DESCRIPTION`
Add task: deadline| `deadline DESCRIPTION /by YYYY-MM-DD`
Add task: event| `event DESCRIPTION /at YYYY-MM-DD`
List all tasks | `list`
Mark task as done | `done INDEX`
Edit a task | `edit INDEX [/desc DESCRIPTION] [/date YYYY-MM-DD]`
Delete a task | `delete INDEX`
Find tasks containing a keyword | `find KEYWORD`
Exit | `bye`

---
## Add tasks
Add tasks to your task list. \
You can add a todo, deadline or event.

### `todo` — Add a todo

**Format:** `todo DESCRIPTION`

Example of usage: 

```
todo buy plants
```

Don will reply:

```
Got it. I've added this task:
[T][] buy plants
```

### `deadline` — Add a deadline

**Format:** `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage:

```
deadline project /by 2021-02-19
```

Don will reply:

```
Got it. I've added this task:
[D][] project (by: Feb 19 2021)
```

### `event` — Add an event

**Format:** `event DESCRIPTION /at YYYY-MM-DD`

Example of usage:

```
event cny celebration /at 2021-02-12
```

Don will reply:

```
Got it. I've added this task:
[E][] cny celebration (at: Feb 12 2021)
```
---

## List tasks
List out all the tasks in your task list.

### `list` — List all your tasks

**Format:** `list`

Example of usage:

```
list
```

Don will reply:

```
Here are the tasks in your list:
1. 
```
---

## Mark tasks as done

### `done` — Marks a task as done
Mark the task at the specified index as done. \
**Format:** `done INDEX`

Example of usage:

```
done 3 
```

Don will reply:

```
Nice! I've marked this task as done:
[E][] cny celebration (at: Feb 12 2021)
```
---

## Edit tasks

### `edit` — Edit a task
Edit the task at the specified index.\
**Format:** `edit INDEX [/desc DESCRIPTION] [/date DATE]`

For todos, you can edit the description. \
For deadlines & events, you can edit the description, date or both.
Order of command (`/desc` or `/date`) does not matter.

Example #1 of usage

```
edit 2 /desc group project
```

Don will reply:

```
Okay! I've edited this task:
[D][] group project (by: Feb 19 2021)
```
***
Example #2 of usage

```
edit 2 /date 2021-01-20
```

Don will reply:

```
Okay! I've edited this task:
[D][] group project (by: Feb 20 2021)
```
***
Example #3 of usage

```
edit 2 /desc indiv project /date 2021-01-21
```

Don will reply:

```
Okay! I've edited this task:
[D][] indiv project (by: Feb 21 2021)
```
***
Example #4 of usage

```
edit 2 /date 2021-01-22 /desc another project
```

Don will reply:

```
Okay! I've edited this task:
[D][] another project (by: Feb 22 2021)
```
---

## Delete tasks

### `delete` — Delete a task
Delete the task at the specified index. \
**Format:** `delete INDEX`

Example of usage:

```
delete 2 
```

Don will reply:

```
Noted. I've removed this task:
2. [D][] another project (by: Feb 22 2021)
```
---

## Find tasks

### `find` — Find a task
Find tasks containing a specified keyword. \
**Format:** `find KEYWORD`

Example of usage:

```
find book
```

Don will reply:

```
Here are the matching tasks in your list:
4. [T][X] read book
7. [E][] book event (at: Mar 26 2021)
```
---

## Exit

### `bye` — Exit
**Format:** `bye`

Example of usage:

```
bye
```

Don will reply:

```
Goodbye, can't wait till our next adventure together!
```