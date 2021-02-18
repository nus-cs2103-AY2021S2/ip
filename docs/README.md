# Meet Don, The Little Adventurer — User Guide

Don is a hardworking and dedicated explorer, 
and his mission is to help you manage your tasks! 
(And hopefully make it a little more fun for you to do so.)

If you're still new here, 
this guide will help you get to know Don a little better.

## Features 
Here is an overview of what Don can do:
1. Add: Todo, Deadline or Event
2. List
3. Done
4. Edit
5. Delete
6. Find
7. Bye

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

```

### `deadline` — Add a deadline

**Format:** `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage:

```
deadline project /by 2021-02-19
```

Don will reply:

```

```

### `event` — Add an event

**Format:** `event DESCRIPTION /at YYYY-MM-DD`

Example of usage:

```
event cny celebration /at 2021-02-12
```

Don will reply:

```

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

```
---

## Edit tasks

### `edit` — Edit a task
Edit the task at the specified index.\
**Format:** `edit INDEX [/desc DESCRIPTION] [/date DATE]`

For todos, you can edit the description. \
For deadlines & events, you can edit the description, date or both.

Example of usage

```
edit 2 /desc 
```

Don will reply:

```

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