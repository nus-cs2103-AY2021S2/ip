<p align="center">
  <img src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/steve.png">
  <h1 align="center">Steve User Guide</h1>
</p>

## Introduction
Steve is a simple task tracker done as part of the CS2103T module in NUS. The latest releases for download are available [here](https://github.com/tjtanjin/ip/releases/).

## Content Page
* [Features](#features)
    * [Create Todo Task](#create-todo-task)
    * [Create Deadline Task](#create-deadline-task)
    * [Create Event Task](#create-event-task)
    * [Delete Task](#delete-task)
    * [Find Task](#find-task)
    * [Mark Task As Complete](#mark-task-as-complete)
    * [Undo Task Modification](#undo-task-modification)
    * [View All Tasks](#view-all-tasks)
    * [View Command Help](#view-command-help)
    * [Exit Application](#exit-application)

## Features
### Create Todo Task
* Description: Creates a todo task.
* Format: `todo <task description>`
* Example: `todo finish ip project`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/create_todo_task.png">
</p>

### Create Deadline Task
* Description: Creates a deadline task with a due date.
* Format: `deadline <task description> /by YYYY-MM-DD`
* Example: `deadline finish ip project /by 2021-02-17`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/create_deadline_task.png">
</p>

### Create Event Task
* Description: Creates an event task with a start date and end date.
* Format: `event <task description> /from YYYY-MM-DD /to YYYY-MM-DD `
* Example: `event do assignment /from 2021-02-17 /to 2021-02-17`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/create_event_task.png">
</p>

### Delete Task
* Description: Deletes the task with the matching index given.
* Format: `delete <task index>`
* Example: `delete 1`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/delete_task.png">
</p>

### Find Task
* Description: Finds all task(s) with details matching the given search term(s).
* Format: `find <search term 1> <search term 2> ...`
* Example: `find ip assignment`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/find_task.png">
</p>

### Mark Task As Complete
* Description: Marks the task with the matching index given as complete.
* Format: `done <task index>`
* Example: `done 1`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/mark_task_as_complete.png">
</p>

### Undo Task Modification
* Description: Undo the last modification done to a task (create, delete or mark as complete)
* Format: `undo`
* Example: `undo`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/undo_task_modification.png">
</p>

### View All Tasks
* Description: Shows a list of all tasks.
* Format: `list`
* Example: `list`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/view_all_tasks.png">
</p>

### View Command Help
* Description: Shows a list of supported commands and their descriptions.
* Format: `help`
* Example: `help`
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/tjtanjin/ip/master/docs/images/view_command_help.png">
</p>

### Exit Application
* Description: Exits the application.
* Format: `bye`
* Example: `bye`