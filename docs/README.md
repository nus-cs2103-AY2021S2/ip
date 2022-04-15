# User Guide

## Features 

### Add Task
Tasks are categorised as:
* Events
* Deadlines
* Todos

### Check Tasks
Mark task as completed

### Delete Task
Delete specified task from list

### Find Task
Find any task that matches keywords

### Request for reminder
Recieve a reminder for the tasks due in that day or week

## Usage

### `todo` - Add a new Todo

Adds a new todo without a date to task list

Example of usage: 

`todo Buy bananas`

Expected outcome:  
`Duchess: Great! I have added:`   
`1.[T][ ] Buy bananas`  
`U have 1 tasks in the list now :)`

### `deadline` - Add a new Deadline

Adds a new deadline to task list with a specified deadline in the format 'YYYY-MM-DD'

Example of usage:

`deadline Homework/by 2021-03-05`

Expected outcome:  
`Duchess: Great! I have added:`   
`2. [D][ ] Homework(by: Mar 5 2021)`  
`U have 2 tasks in the list now :)`

### `event` - Add a new Event

Adds a new event to task list with a specified date of event in the format 'YYYY-MM-DD'

Example of usage:

`event Party/at 2021-03-10`

Expected outcome:  
`Duchess: Great! I have added:`   
`3. [E][ ] Party(at: Mar 10 2021)`  
`U have 3 tasks in the list now :)`

### `list` - Request for list of tasks

Shows list of tasks in task list with their corresponding dates and completed status

Example of usage:

`list`
Expected outcome:  
`Duchess: Here are the tasks in your list:`  
`1. [T][ ] Buy bananas`  
`2. [D][ ] Homework(by: Mar 5 2021)`  
`3. [E][ ] Party(at: Mar 10 2021)`


### `find` - Finds for matching task names

Finds for tasks with names that matches the keyword

Example of usage:

`find Party`

Expected outcome:  
`Duchess: Here are the matching tasks in your list:`  
`3. [E][ ] Party(at: Mar 10 2021)`

### `done` - Check task off the list

Marks task with corresponding index as completed with an [X]

Example of usage:

`done 3`

Expected outcome:  
`Duchess: Woohoo I've checked off this task:`  
`3. [E][X] Party(at: Mar 10 2021)`
### `delete` - Deletes task

Deletes task with corresponding index from task list

Example of usage:
`delete 2`

Expected outcome:  
`Duchess: As requested, i have removed this task:`  
`2. [D][ ] Homework(by: Mar 5 2021)`

### `today` - Shows tasks due on that day

Shows list of uncompleted tasks due on that day if any

Example of usage:
`today`

Expected outcome:  
`Duchess: These are your tasks for today:`  
`[D][ ] Homework(by: Feb 13 2021)`

### `weekly` - Shows tasks due in that week

Shows list of uncompleted tasks due in that week if any

Example of usage:
`weekly`

Expected outcome:  
`Duchess: These are your tasks for this week:`  
`[E][ ] Dinner(at: Feb 16 2021)`  
`[D][ ] Homework(by: Feb 13 2021)`



