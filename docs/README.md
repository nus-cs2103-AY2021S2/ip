# User Guide

## Features 

### Add a Task:
Add a task to Mike's storage. 
There are 4 types of tasks:
1. todo
2. event
3. description
4. fixed duration task


#### todo - Add a todo task to Mike's storage

Todo is a task with no time restrictions. The format should be:
```
todo {description of the task}
```

Example of usage: 

`todo Fix this code`

Expected outcome:

`Got it. I've added this task`
`[T][X]Fix this code`
`Now you have {number of tasks} tasks in the list.`


#### event - Add an event task to Mike's storage

Event is a task that needs to be completed on the specified date. The format should be:
```
event {description of the task} /at YYYY-MM-DD
```

Example of usage: 

`event Fix this code /at 2021-02-01`

Expected outcome:

`Got it. I've added this task`
`[E][X]Fix this code (at: Feb 01 2021)`
`Now you have {number of tasks} tasks in the list.`

#### deadline - Add an deadline task to Mike's storage

Deadline is a task that needs to be completed before the specified date. The format should be:
```
deadline {description of the task} /by YYYY-MM-DD
```

Example of usage: 

`deadline Fix this code /by 2021-02-01`

Expected outcome:

`Got it. I've added this task`
`[D][X]Fix this code (by: Feb 01 2021)`
`Now you have {number of tasks} tasks in the list.`

#### fixed duration task - Add an fixed duration task to Mike's storage

Fixed duration task is a task with a fixed time duration. The format should be:
```
fixed {description of the task} /needs {amount of time}
```

Example of usage: 

`fixed Fix this code /needs 30 hours`

Expected outcome:

`Got it. I've added this task`
`[F][X]Fix this code (needs: 30 hours)`
`Now you have {number of tasks} tasks in the list.`

### Mark a completed task as done:
Once a task has been completed, the user can instruct Mike to mark the corresponding task.
#### done - Mark a completed task as done. The format should be:
```
done {index of completed task}
```

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`
`[T][X] Fix this code`

### Delete a task:
Delete the task at the index specified.
#### delte - Delete a task in Mike's storage. The format should be:
```
delete {index of completed task}
```

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:`
`[T][X] Fix this code`
`Now you have {number of tasks} tasks in the list.`

### List the tasks:
List all the tasks in Mike's storage.
#### list - List all the tasks in Mike's storage. The format should be:
```
list
```

Example of usage: 

`list 1`

Expected outcome:

`Here are the tasks in your list:`
`1.[T][X] Fix this code`

### Find tasks:
Find tasks in Mike's storage corresponding to the keyword.
#### find - Find tasks corresponding to the keyword. The format should be:
```
find {keyword}
```

Example of usage: 

`find code`

Expected outcome:

  `Here are the matching tasks in your list:`
`[T][X] Fix this code` 
`[E][X]Fix this code (at: Feb 01 2021)`
`[D][X]Fix this code (by: Feb 01 2021)`
`[F][X]Fix this code (needs: 30 hours)`
