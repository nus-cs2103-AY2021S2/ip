# User Guide

## Features 

### Add a task
Add a task, including the ability to specify if it's an event, deadline, or a todo

Format: `TASK_TYPE DESC [DATE]`

Following is the format for the commands to add different kinds of tasks.
```
todo DESC
deadline DESC /by DATE
event DESC /at DATE
```

`DATE` should be in the format `YYYY-MM-DD`. By default, a newly created task is not done yet. 

### List all tasks
List all current tasks, displaying their descriptions and date (if any)

Format: `list`

### Delete a task
Delete a task by their displayed index

Format: `delete INDEX`

### Mark a task as done
Mark a task as done by their displayed index

Format: `done INDEX`

### Find a task
Find a task by a keyword in description

Format: `find KEYWORD`

### Undo an action
Restore the task list to the state prior to the last change.

Format: `undo`

### Redo an undo
Restore the task list to the state prior to an undo action.

Format: `redo`
