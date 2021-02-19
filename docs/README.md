# User Guide
Duke is a thing
## Features
### Keep track of various tasks 
* Add a todo task
* Add a deadline task
* Add an event task
### Manage your tasks
* List out tasks
* Mark tasks as done
* Delete tasks
* Find tasks
* Add and look up additional information regarding tasks
## Usage

### `todo` - Adds a todo task

Adds a task that you are planning to do into your list of tasks

Example of usage: 

`todo wash clothes`

Expected outcome:

`Got it! I have added the following task:
[T][][] wash clothes`

### `deadline` - Adds a deadline task

Adds a task with a specified deadline into your list of tasks

Example of usage: 

`deadline Homework /by 2021-03-03`

Expected outcome:

`Got it! I have added the following task:
[D][][] Homework (by: March 3 2021)`

### `event` - Adds an event task

Adds a task with a specified location into your list of tasks

Example of usage: 

`event dinner /at orchard road`

Expected outcome:

`Got it! I have added the following task:
[E][][] dinner (at: orchard road)`

### `list` - Lists out all your tasks

Shows a list of all your completed and uncompleted tasks

Example of usage: 

`list`

Expected outcome:

`Here are your tasks!
1.[T][][] wash clothes
2.[D][][] Homework (by: March 3 2021)
3.[E][][] dinner (at: orchard road)`

### `done` - Marks a task as completed

Marks the specified task as completed

Example of usage: 

`done 2`

Expected outcome:

`Good job, I've marked the task as done!
[D][X][] Homework (by: March 3 2021)`

### `delete` - Deletes a task

Deletes the specified task from your list

Example of usage: 

`delete 2`

Expected outcome:

`Okay I have removed this task!
[D][X][] Homework (by: March 3 2021)`

### `find` - Finds and returns a task

Finds a task that contains the specified keyword

Example of usage: 

`find wash`

Expected outcome:

`Here are the matching tasks in your list!
1.[T][][] wash clothes`

### `addinfo` - Adds additional information regard the specified task

Updates the specified task with additional information provided

Example of usage: 

`addinfo 1 gym clothes`

Expected outcome:

`I have added the additional info regarding this task!`

### `info` - Retrieves additional information regarding the specified task

Retrieves the additional information about the specified task, if any
A task with additional info is marked by [i] when 'list' is called. 
1.[T][][i] wash clothes
2.[E][][] dinner (at: orchard road)`

Example of usage: 

`info 1`

Expected outcome:

'Here is the additonal info regarding this task!
gym clothes`
