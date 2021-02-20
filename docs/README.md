# User Guide

## Features 

### Adding a todo task: `todo`

Add a todo task.

Example of usage:
`todo homework`

Expected outcome:
```
Got it. I've added this task:
  [T][ ] homework
Now you have 1 task in the list.
````


### Adding a deadline task: `deadline`

Add a deadline task.

Example of usage: 
`deadline project /by 2020-01-01 23:39`

Expected outcome:
```
Got it. I've added this task:
  [D][ ] project (by: Jan 1 2020, 11:59PM)
Now you have 2 tasks in the list.
````


### Adding an event task: `event`

Add an event task.

Example of usage: 
`event party /at 2020-01-01 18:00`

Expected outcome:
```
Got it. I've added this task:
  [E][ ] party (at: Jan 01 2020, 6:00PM)
Now you have 3 tasks in the list.
````


### Listing all tasks: `list`

List all existing tasks.

Example of usage: 
`list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][ ] homework
2.[D][ ] project (by: Jan 1 2020, 11:59PM)
3.[E][ ] party (at: Jan 01 2020, 6:00PM)
```


### Deleting a task: `delete`

Delete a specified task.

Example of usage: `delete 2`

Expected outcome:
```
Noted. I've removed this task:
  [D][ ] project (by: Jan 1 2020, 11:59PM)
Now you have 2 tasks in the list.
```


### Marking a task as done: `done`

Mark a specified task as done.

Example of usage: 
`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] homework
```


### Finding tasks with a specified keyword: `find`

Find tasks that contain a specified keyword.

Example of usage: 
`find party`

Expected outcome:
```
Here are the matching tasks in your list:
1.[E][ ] party (at: Jan 01 2020, 6:00PM)
```


### Listing tasks with a specified date: `taskdate`

Listings tasks that have a specified date.

Example of usage: 
`taskdate 2020-01-01`

Expected outcome:
```
Here are the tasks on Jan 1 2020 in your list:
1.[D][ ] project (by: Jan 1 2020, 11:59PM)
2.[E][ ] party (at: Jan 01 2020, 6:00PM)
```


### Exiting the program: `bye`

Exit the program.

Example of usage: 
`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```




## Command Summary

Command Format | Examples
------------- | -------------
`todo` | e.g. `todo homework`
`deadline` | e.g. `deadline project /by 2020-01-01 23:59`
`event` | e.g. `event party /at 2020-01-01 18:00`
`list` | e.g. `list`
`delete` | e.g. `delete 2` will delete the 2nd task in the list
`done` | e.g. `done 3` will mark the 3rd task in the list as done
`find` | e.g. `find book` will list all existing tasks with the word `book`
`taskdate` | e.g. `taskdate 2020-01-01` will list all existing tasks with the date `2020-01-01`
`bye` | e.g. `bye`