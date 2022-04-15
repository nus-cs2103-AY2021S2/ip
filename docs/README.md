# User Guide

Owen the Owl is a personal assistant chatbot and task manager.

## Features

- [Adding Tasks](###adding-tasks)
    - [Todo](####todo)
    - [Deadline](####deadline)
    - [Event](####event)
- [Managing Tasks](###managing-tasks)
- [Finding Tasks](###finding-tasks)
- [Reminders](###reminders)

### Adding Tasks
Add different types of tasks for Owen to keep track of.

#### Todo

Todo tasks with no specified timeframe.

#### Deadline

Deadline tasks with a due date and time.

#### Event

Event task with a start and end datetime.

### Managing Tasks
List, mark as done, and delete tasks.

### Finding Tasks
Find tasks matching a search string.

### Reminders
Get reminders of tasks due within specified days.

## Usage

- [todo](###todo---add-todo-task)
- [deadline](###deadline---add-deadline-task)
- [event](###event---add-event-task)
- [list](###list---list-all-tasks)
- [done](###done---mark-task-as-done)
- [delete](###delete---delete-task)
- [find](###find---find-matching-tasks)
- [reminders](###reminders---get-reminders-about-upcoming-tasks)
- [bye](###bye---exit-application)

### `todo` - Add Todo task
Add Todo task with a description

Example of usage: 

`todo buy milk`

### `deadline` - Add Deadline task
Add deadline task with description and due datetime. Datetime must be in DD/MM/YYYY HHMM format.

Example of usage: 

`deadline file taxes /by 20/2/2021 1400`

### `event` - Add Event task
Add event task with description and start and end datetime. Datetime must be in DD/MM/YYYY HHMM format.

Example of usage: 

`event meow fest 2021 /at 21/2/2021 0900 - 21/2/2021 2000`

### `list` - List all tasks
List all tasks.

Example of usage: 

`list`

Expected outcome:

```
1. [T][ ] buy milk
2. [D][ ] file taxes (by: February 20 2021 2:00 PM)
3. [E][ ] meow fest 2021 (at: February 21 2021 9:00 AM - February 21 2021 8:00 PM)
```

### `done` - Mark task as done
Mark task as done given its index.

Example of usage: 

`done 1`

Expected outcome:

```
Hoot! I've marked this task as done:
    1. [T][X] buy milk
```

### `delete` - Delete task
Delete a task given its index.

Example of usage: 

`delete 2`

Expected outcome:

```
Hoot. I've removed this task:
    2. [D][ ] file taxes (by: February 20 2021 2:00 PM)
Now you have 2 tasks in the list.
```

### `find` - Find matching tasks
Find tasks matching a search string.

Example of usage: 

`find milk`

Expected outcome:

```
Hoot hoot here are the matching tasks in your list:
1. [T][ ] buy milk
```

### `reminders` - Get reminders about upcoming tasks
Get list of tasks due within specified number of days

Example of usage: 

`reminders 5`

Expected outcome:

```
Hoot hoot here are the tasks due within 5 days:
2. [D][ ] file taxes (by: February 20 2021 2:00 PM)
3. [E][ ] meow fest 2021 (at: February 21 2021 9:00 AM - February 21 2021 8:00 PM)
```

### `bye` - Exit application
Exits the application.

Example of usage: 

`bye`
