# Duke User Guide

Welcome to Duke Application. This program acts as a companion to remind you of the upcoming tasks that you have. It uses a command line interface which you can operate it by typing commands into the Command Box.

## Feature Page (Highlight this segment to link features to their corresponding functions.

### [Create a ToDo task](#create-a-todo-task)

### [Create a Deadline task](#create-a-deadline-task)

### [Create an Event task](#create-an-event-task)

### [List all tasks](#list-all-tasks)

### [Delete a task](#delete-a-task)

### [Mark a task as done](#mark-a-task-as-done)

### [Find keywords](#find-keywords)

### [Sort tasks by DateTime](#sort-tasks-by-datetime)

## Command Usage

### Create a ToDo task
* Description: Adds a Todo task followed by its description. 
* Notes: Its description cannot be left empty.
* Command: `todo`
* Command Format: `todo <Description>`
* Command Example: `todo Read books`
<p align="center">
  <img height="640" width="480" src="/docs/images/create_todo_task.PNG">
</p>

### Create a Deadline task
* Description: Adds a Deadline task followed by its description.
* Notes: Its description cannot be left empty. /by <DateTime> format has to be adhered for command to work.
* Command: `deadline`
* Command Format: `deadline <Description> /by <DateTime format in YYYY-MM-DD HH:MM>`
* Command Example: `deadline Submit work /by 2021-02-28 10:00`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/create_deadline_task.PNG">
</p>
  
### Create an Event task
* Description: Adds an Event task followed by its description and date time.
* Notes: Its description cannot be left empty. /at <DateTime> format has to be adhered for command to work.
* Command: `event`
* Command Format: `event <Description> /at <DateTime format in YYYY-MM-DD HH:MM>`
* Command Example: `event Summer festival /at 2021-02-27 17:00`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/create_event_task.PNG">
</p>

### List all tasks
* Description: Lists down all the upcoming tasks that you have.
* Command: `list`
* Command Format: `list`
* Command Example: `list`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/list_task.PNG">
</p>

### Delete task
* Description: Deletes the task item at the respective index.
* Note: Index has to be within the number of tasks in order for it to function.
* Command: `delete`
* Command Format: `delete <index>`
* Command Example: `delete 1`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/delete_task.PNG">
</p>

### Mark a task as done
* Description: Marks task as completed at the respective index.
* Note: Index has to be within the number of tasks in order for it to function.
* Command: `done`
* Command Format: `done <index>`
* Command Example: `done 2`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/mark_task_done.PNG">
</p>

### Find keywords
* Description: Searches for any tasks that contains the defined text.
* Command: `find`
* Command Format: `find <text>`
* Command Example: `find festival`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/find_task.PNG">
</p>

### Sort tasks by DateTime
* Description: Sorts tasks according to the earliest task that is due at the top.
* Command: `sort`
* Command Format: `sort`
* Command Example: `sort`
<p align="center">
  <img height="640" width="480" src="https://github.com/ssoonwee/ip/blob/master/docs/images/sort_task.PNG">
</p>


### Exit Application
* Description: Ends the application.
* Command: `exit`
* Command Format: `exit`
* Command Example: `exit`
