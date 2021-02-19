# User Guide
Welcome to the Duke User Guide! Duke is a task list manager that you
can use to add, view and delete your tasks, amongst other features.

## Features 

### Add a New Task
Duke allows you to add 3 different kinds of tasks: ToDos, Events and Deadlines.

##### Adding a ToDo Task
Use the following command format for adding a ToDo Task: `todo` (space) `<task description>`

Example: todo score A+ in CS2103

##### Adding an Event
Use the following command format for adding an Event with a specified time and date: 
`event` (space) `<task description>` (space) `/by` (space) `dd/mm/yyyy` (space) `HHmm`

Example: event score A+ in CS2103 /by 01/05/2021 2359

**Note:** None of your task description's words may start with "/", or Duke will not read your input correctly!

##### Adding a Deadline
Use the following command format for adding a Deadline with a specified time and date:
`deadline` (space) `<task description>` (space) `/by` (space) `dd/mm/yyyy` (space) `HHmm`

Example: deadline score A+ in CS2103 /by 01/05/2021 2359

**Note:** None of your task description's words may start with "/", or Duke will not read your input correctly!

### View your Task List
Use the `list` command to see your entire Task List.

Tasks will have the following format in the list:

`task type` `completion status` `task description` `day` `time`

1. Task Type: Indicates if task is a ToDo, Deadline or Event.
1. Completion Status: Indicates if task has been completed. All tasks will be uncompleted 
   (indicated by a "0") by default. A "1" indicates that the task is completed.
1. Task Description: Description of the task.
1. Day: Date of task (if applicable).
1. Time: Time of task (if applicable).

### Complete a Task
Use the `done` command to complete a task in your Task List.

Use the following command format for completing a task: `done` (space) `<list number of task to complete>`

Example: done 1

**Note:** You have to input a correct list number! If the number is larger than the current task list, Duke will
not be able to process your command.

### Delete a Task
Use the 'delete' command to delete a task from your Task List.

Use the following command format for deleting a task: `delete` (space) `<list number of task to delete>`

Example: delete 1

**Note:** You have to input a correct list number! If the number is larger than the current task list, Duke will
not be able to process your command.

### Search for Tasks
Use the `find` command to search for specific tasks in your Task List by their Task Description.

Use the following command format for searching for tasks: `find` (space) `<keyword>`

Example: find A+

**Note:** You may search for tasks with uncompleted words, e.g. "Scor" to search for "Score"

### Ask for Help
Use the `help` command to get a quick summary of the commands you can use in Duke.

### Close Duke
Use the `bye` command to close Duke. You may also use the close button at the top right to close Duke.

## Usage

### `todo` - Creates a ToDo Task

Creates a ToDo Task in the Task List.

Example of usage: 

`todo score A+ in CS2103`

### 'event' - Creates an Event

Creates an Event in the Task List.

Example of usage:

`event score A+ in CS2103 /by 01/05/2021 2359`

### `deadline` - Creates a Deadline

Creates a Deadline in the Task List.

Example of usage:

`deadline score A+ in CS2103 /by 01/05/2021 2359`

### `list` - Views the Entire Task List

Views your entire Task List.

Example of usage:

`list`

### `done` - Completes a Task

Completes a task in the Task List.

Example of usage:

`done 1`

**Note:** Task number is ordered by the Task List 
(first task in the list is 1, second is 2, etc.)

### `delete` - Deletes a Task

Deletes a task in the Task List.

Example of usage:

`delete 1`

**Note:** Task number is ordered by the Task List
(first task in the list is 1, second is 2, etc.)

### `find` - Find Tasks

Finds tasks in the Task List using a keyword.

Example of usage:

`find score`

### `help` - View Summary of Commands

Gets a quick summary of the commands you can use.

Example of usage:

`help`

### `bye` - Close Duke

Closes Duke.

Example of usage:

`bye`
