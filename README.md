# Duke User Guide

Welcome to Duke Application. This program acts as a companion to remind you of the upcoming tasks that you have. It uses a command line interface which you can operate it by typing commands into the Command Box.

## Feature Page (Highlight this segment to link features to their corresponding functions.

### [Create a ToDo task](#create-a-todo-task-1)

### [Create a Deadline task](#create-a-deadline-task-1)

### [Create an Event task](#create-an-event-task-1)

### [List all tasks](#list-all-tasks-1)

### [Delete a task](#delete-a-task-1)

### [Mark a task as done](#mark-a-task-as-done-1)

### [Find keywords](#find-keywords-1)

### [Sort tasks by DateTime](#sort-tasks-by-datetime-1)

### [Exit Application](#exit-application-1)

## Command Usage

### Create a ToDo task
* Description: Adds a Todo task followed by its description. 
* Notes: Its description cannot be left empty.
* Command: `todo`
* Command Format: `todo <Description>`
* Command Example: `todo Read books`
<p align="center">
  <img height="640" width="480" src="/docs/images/create_todo_task.png">
</p>

### Create a Deadline task
* Description: Adds a Deadline task followed by its description.
* Notes: Its description cannot be left empty. /by <DateTime> format has to be adhered for command to work.
* Command: `deadline`
* Command Format: `deadline <Description> /by <DateTime format in YYYY-MM-DD HH:MM>`
* Command Example: `deadline Submit work /by 2021-02-28 10:00`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/create_deadline_task.png">
</p>
  
### Create an Event task
* Description: Adds an Event task followed by its description and date time.
* Notes: Its description cannot be left empty. /at <DateTime> format has to be adhered for command to work.
* Command: `event`
* Command Format: `event <Description> /at <DateTime format in YYYY-MM-DD HH:MM>`
* Command Example: `event Summer festival /at 2021-02-27 17:00`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/create_event_task.png">
</p>

### List all tasks
* Description: Lists down all the upcoming tasks that you have.
* Command: `list`
* Command Format: `list`
* Command Example: `list`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/list_task.png">
</p>

### Delete task
* Description: Deletes the task item at the respective index.
* Note: Index has to be within the number of tasks in order for it to function.
* Command: `delete`
* Command Format: `delete <index>`
* Command Example: `delete 1`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/delete_task.png">
</p>

### Mark a task as done
* Description: Marks task as completed at the respective index.
* Note: Index has to be within the number of tasks in order for it to function.
* Command: `done`
* Command Format: `done <index>`
* Command Example: `done 2`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/mark_task_done.png">
</p>

### Find keywords
* Description: Searches for any tasks that contains the defined text.
* Command: `find`
* Command Format: `find <text>`
* Command Example: `find festival`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/find_task.png">
</p>

### Sort tasks by DateTime
* Description: Sorts tasks according to the earliest task that is due at the top.
* Command: `sort`
* Command Format: `sort`
* Command Example: `sort`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/sort_task.png">
</p>


### Exit Application
* Description: Ends the application.
* Command: `bye`
* Command Format: `bye`
* Command Example: `bye`
