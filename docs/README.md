# User Guide
CS2103 Duke Task Manager is a GUI task manager where the user can manage and keep track of tasks of
different types (ToDo, Event and Deadline) with various actions (list, sort, find, done, delete, exit) available.

![Image of Duke](Ui.png)

## Features

### 1. Adding a ToDo task
Adds an ToDo task to the task list in Duke Task Manager.

Format:
`todo {description}`

Usage example:
`todo finish CS2103 tutorial`

Expected outcome:
```
Got it. I've added this task: 
    [T][] finish CS2103 tutorial
    Now you have 1 task(s) in the list.
```

### 2. Adding an Event task
Adds an Event task to the task list in Duke Task Manager.

Format:
`event {description} /at {MMM-DD-YYYY}`

Usage example:
`event CS2103 Group meeting /at Feb 22 2021 `

Expected outcome:
```
Got it. I've added this task: 
    [E][] CS2103 Group meeting (at: 22-02-2021)
    Now you have 2 task(s) in the list. 
```

### 3. Adding a Deadline task
Adds a Deadline task to the task list in Duke Task Manager.

Format:
`deadline {description} /by {MMM-DD-YYYY}`

Usage example:
`deadline CS2103 ip submission /by Feb 19 2021 `

Expected outcome:
```
Got it. I've added this task: 
    [D][] CS2103 ip submission (by: 19-02-2021)
    Now you have 3 task(s) in the list. 
```

### 4. Listing all tasks
List all recorded tasks from the task list in Duke Task Manager.

Format:
`list`

Usage example:
`list`

Expected outcome:
```
Here are the task(s) in your list: 
    1. [T][] finish CS2103 tutorial
    2. [E][] CS2103 Group meeting (at: 22-02-2021) 
    3. [D][] CS2103 ip submission (by: 19-02-2021)
```

### 5. Deleting a task
Delete a task from the task list in the Duke Task Manager based on the index number given by user input.

Format:
`delete {task index number}`

Usage example:
`delete 1`

Expected outcome:
```
Noted. I've removed this task:
    [T][] finish CS2103 tutorial
    Now you have 2 task(s) in the list.
```

### 6. Marking a task as done 
Marks a task as done status from the task list in the Duke Task Manager.

Format:
`done {task index number}`

Usage example:
`done 2`

Expected outcome:
```
Nice! I've marked this task as done:
    [E][✘] CS2103 Group meeting (at: 22-02-2021) 
```

### 7. Finding a task
Find a task from the task list in Duke Task Manager based on the keyword from the user input.

Usage example:
`find submission`

Expected outcome:
```
Here are the matching task(s) in your list:
    1.[D][] CS2103 ip submission (by: 19-02-2021)
```

### 8. Sorting a task
Sort tasks (date in ascending order) which are of Event and Deadline type from the task list in Duke Task Manager.

Usage example:
`sort`

Expected outcome:
```
Here are the event and deadline task(s) (sorted by date) in your list:
    1.[D][] CS2103 ip submission (by: 19-02-2021)
    2.[E][✘] CS2103 Group meeting (at: 22-02-2021) 
```

### 9. Exiting the program
Bid farewell to Duke Task Manager.

Usage example:
`bye`

Expected outcome:`Bye. Hope to see you again!`

