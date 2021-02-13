# User Guide

## Features

### Add Task
Add three types of tasks to your task list
* Todo task
* Deadline task
* Event task

### Delete Task
Delete tasks from your task list

### Mark Task as Completed
Mark tasks in your task list "Completed"

### View Task List
View all the tasks in your task list

### Find Task
Input a keyword and all tasks that match that keyword will be listed

### Save and Load Tasks
Tasks will be automatically saved into the hard disk whenever the task
list changes. Likewise, tasks will be loaded from the hard disk (if there is any)
whenever a Duke session is initiated


## Usage

### `todo` - Adds a todo task to the task list

This creates a new todo task and adds it to your task list. <br />
Has to be followed by a task description. <br />
By default, it will be indicated as a task "INPROGRESS".

Example of usage:

`todo eat lunch`

Expected outcome:

`Added!` <br />
`[T][INPROGRESS] eat lunch` <br />
`You have 1 task(s) in your list!` <br />


### `event` - Adds an event task to the task list

This creates a new event task and adds it to your task list. <br />
Has to be followed by a task description, and the date of the event in the format given below <br />
By default, it will be indicated as a task "INPROGRESS".

Example of usage:

`event eat lunch /at 10pm`

Expected outcome:

`Added!` <br />
`[E][INPROGRESS] eat lunch (at: 10pm)` <br />
`You have 1 task(s) in your list!` <br />


### `deadline` - Adds a deadline task to the task list

This creates a new deadline task and adds it to your task list. <br />
Has to be followed by a task description, and the deadline of the task in an ISO format as given below <br />
By default, it will be indicated as a task "INPROGRESS".

Example of usage:

`deadline eat lunch /by 2020-12-30`

Expected outcome:

`Added!` <br />
`[D][INPROGRESS] eat lunch (by: Dec 30 2020)` <br />
`You have 1 task(s) in your list!` <br />


### `delete` - Deletes a task from the task list

This removes any type of task from your task list. <br />
Has to be followed by the index of the task in the task list. <br />

Example of usage:

`delete 1`

Expected outcome:

`Noted! I've removed this task: [T][INPROGRESS] eat lunch` <br />
`You have 1 task(s) in your list!`


### `done` - Marks a task in your task list as completed

This marks any type of task from your task list as "COMPLETED!". <br />
Has to be followed by the index of the task in the task list. <br />

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done: [T][COMPLETED!] eat lunch`


### `list` - Displays all the task(s) in your task list

This displays all the task(s) in your task list. <br />

Example of usage:

`list`

Expected outcome:

`Here are your tasks!` <br />
`1.[E][COMPLETED!] eat lunch (at: 10pm)` <br />
`2.[T][INPROGRESS] eat lunch` <br />



### `find` - Search for tasks in your task list

This displays any type of task from your task list that matches the keyword, even partially. <br />
Has to be followed by a keyword. <br />

Example of usage:

`find lunch`

Expected outcome:

`[T][INPROGRESS] eat lunch`