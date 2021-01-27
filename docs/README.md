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

`deadline complete assignment /by 2021-01-05`

Expected outcome:

`Got it. I've added this task: 
       [D][ ] complete assignment (05 Jan 2021)
     Now you have 6 tasks in the list.`
	 
Example of usage: 

`event attend meeting /from 2021-01-05 to 2021-01-06`

Expected outcome:

`Got it. I've added this task: 
       [E][ ] attend meeting (05 Jan 2021 to 06 Jan 2021)
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
	   
### Feature 4 - Delete Task from list
Enables the user to remove a task from the task list

## Usage

### `delete` - Use the delete keyword

The specified task on the list will be deleted.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task: 
       [T][X] Second Task
     Now you have 4 tasks in the list.`
	 
### Feature 5 - Find Task from list
Enables the user to find for tasks from the task list

## Usage

### `find` - Use the find keyword

The tasks matching the word(s) to find for will be displayed.

Example of usage: 

`find meeting`

Expected outcome:

`Here are the matching tasks in your list:
	 2. [D][X] prepare for meeting (05 Jan 2021)
	 5. [E][ ] attend meeting (05 Jan 2021 to 08 Jan 2021)`