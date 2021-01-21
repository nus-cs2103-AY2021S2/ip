# User Guide

## Features 

### Feature 1 - Add task to list
Enables the user to add tasks(ToDos, Deadlines, Events) into the list

## Usage

### `todo/deadline/event` - Use one of the three keywords

The appropriate task will be added to the list.

Example of usage: 

`todo buy bread`

Expected outcome:

`Got it. I've added this task: 
       [T][ ] buy bread
     Now you have 5 tasks in the list.`

Example of usage: 

`deadline complete assignment /By tmr 2359`

Expected outcome:

`Got it. I've added this task: 
       [D][ ] complete assignment (By tmr 2359)
     Now you have 6 tasks in the list.`
	 
Example of usage: 

`event attend meeting /Fri 2pm-3pm`

Expected outcome:

`Got it. I've added this task: 
       [E][ ] attend meeting (Fri 2pm-3pm)
     Now you have 7 tasks in the list.`

### Feature 2 - View list
Enables the user to view all the tasks in the list

## Usage

### `list` - Use the list keyword

The list will be displayed with all the current tasks.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:
     1.[T][X] read book`

### Feature 3 - Mark Task as Done
Enables the user to mark a task as done

## Usage

### `done` - Use the done keyword

The specified task on the list will be marked as done.

Example of usage: 

`done 2`

Expected outcome:

`Nice! I've marked this task as done: 
       [T][X] Second Task`