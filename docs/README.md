# User Guide

## Features 
1. Add tasks to task list.
1. View all tasks in task list.
1. Mark completed tasks in task list.
1. Delete tasks from task list.
1. Exit the application.

## Adding task into task list
To add todo, deadline or event tasks into task list.

### Usage

#### `todo (task name)` - To add todo task into task list.

Example of usage: 

```
todo task1
```

Expected outcome:

```
Got it. I've added this task:
[T][] task1
Now you have 1 tasks in the list.
```

#### `deadline (task name) /by (date in dd/mm/yyyy) (optional time in 24 hour format)` - To add deadline task into task list.

Example of usage: 

```
deadline task2 /by 20/02/2020 1400
```

Expected outcome:

```
Got it. I've added this task:
[D][] task2 (by: 20 Feb 2020 02:00 PM)
Now you have 2 tasks in the list.
```

#### `event (task name) /at (date in dd/mm/yyyy) (optional starting time in 24 hour format) (optional ending time in 24 hour format)` - To add event task into task list.

Example of usage: 

```
event task3 /at 20/02/2020 1400 1500
```

Expected outcome:

```
Got it. I've added this task:
[E][] task3 (at: 20 Feb 2020 02:00 PM-03:00 PM)
Now you have 3 tasks in the list.
```

## Viewing all tasks in task list.

### Usage

#### `list` - To list and view all tasks in task list.

Example of usage: 

```
list
```

Expected outcome:

```
Here are the tasks in your list:
1.[T][] task1
2.[D][] task2 (at: 20 Feb 2020 02:00 PM)
3.[E][] task3 (at: 20 Feb 2020 02:00 PM-03:00 PM)
```

## Marking completed task in task list.

### Usage

#### `done (task number)` - To mark task as completed in task list.

Example of usage: 

```
done 1
```

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] task1
```

## Deleting tasks from task list.

### Usage

#### `delete (task number)` - To delete task from task list.

Example of usage: 

```
delete 2
```

Expected outcome:

```
Noted. I've removed this task:
[D][] task2 (at: 20 Feb 2020 02:00 PM)
Now you have 2 tasks in the list.
```

## Exiting from application.

### Usage

#### `bye` - To exit from the application.

Example of usage: 

```
bye
```

Expected outcome:

```
Bye. Hope to see you again soon!
```
