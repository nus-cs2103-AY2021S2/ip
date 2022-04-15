# User Guide

This is a custom task-tracking chatbot made for the module 
CS2103T's individual project component. It's named after the Java mascot _Duke_.
You may download the jar file from the release for your own use!

![UI](Ui.png)

# Features

### 1. Add tasks

Adds a task to the list, as well as stores it in your disk.

* `todo` : A task with a to-do description.
* `deadline` : A task with a description and deadline.
* `event` : A task with a description and duration.

### 2. Mark tasks as done

Marks individual tasks as done with an [x].

### 3. List the tasks

Retrieves and lists all tasks currently stored.

### 4. Delete tasks

Deletes individual tasks.

### 5. Find tasks using keywords

Retrieves all tasks that match given arguments.

### 6. Get reminders

Shows all deadline tasks.

# Usage

### 1. `todo` - Create a todo task

Format:
`todo {description}`

Example of usage:
`todo something`

Expected outcome:

```
Your task has been added: [T][] something
You currently have 1 task(s) in the list.
```

### 2. `deadline` - Create a deadline task

Format:
`deadline {description} /by {dateTimeFormat}`

Possible `{dateTimeFormat}`:

* dd/MM/yyyy
* dd-MM-yyyy
* yyyy-MM-dd hhmm
* dd/MM/yyyy hhmm
* dd-MM-yyyy hhmm

Example of usage:
`deadline workz /by 12/02/2021 1800`

Expected outcome:

```
Your task has been added: [D][] workz (by: 12 Feb 2021, 6PM)
You currently have 2 task(s) in the list.
```

### 3. `event` - Create an event task

Format:
`event {description} /at {duration}`

Example of usage:
`event party /at 12 to 2`

Expected outcome:

```
Your task has been added: [E][] party (at: 12 to 2)
You currently have 3 task(s) in the list.
```

### 4. `done` - Mark task as done

Format:
`done {task number}`

Example of usage:
`done 1`

Expected outcome:

```
Noice. It's done.
[T][x] something
```

### 5. `list` - Lists all tasks stored

Format:
`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][x] something
2.[D][] workz (by: 12 Feb 2021, 6PM)
3.[E][] party (at: 12 to 2)
```

### 6. `delete` - Deletes a task

Format:
`delete {task number}`

Example of usage:
`delete 3`

Expected outcome:

```
See la. It's deleted.
[E][] party (at: 12 to 2)
You currently have 2 task(s) in the list.
```

### 7. `find` - Finds matching tasks

Format:
`find {description}`

Example of usage:
`find thing`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][x] something
```

### 8. `reminders` - Gets deadline tasks as reminders

Format:
`reminders`

Expected outcome:

```
Here are the tasks with deadlines in your list:
2.[D][] workz (by: 12 Feb 2021, 6PM)
```

### 9. `bye` - Closes the app

Expected outcome:
Window closes without replying.

[docs/Ui.png]: docs/Ui.png