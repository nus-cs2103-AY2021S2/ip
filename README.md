# User Guide
BearBear is a desktop task manager application done as part of the CS2103T module in NUS.

![start.png](docs/images/start.png)

## Content Page
* [Add Todo Task](#add-todo-task)
* [Add Deadline Task](#add-deadline-task)
* [Add Event Task](#add-event-task)
* [Mark Task as Complete](#mark-task-as-complete)
* [Delete Task](#delete-task)
* [Find Task](#find-task)
* [List Tasks](#list-tasks)
* [View Help guide](#view-help-guide)
* [Exit Application](#exit-application)

## Features
### Add Todo Task
* Description: Creates and adds a Todo task to the task list.
* Format: `todo <task_description>`
* Example: `todo borrow book`

![todo_example.png](docs/images/todo_example.png)


### Add Deadline Task
* Description: Creates and adds a Deadline task to the task list.
* Format: `deadline <task_description> <deadline>`
* Examples:
   1. `deadline submit project /by Sunday 2359`
   2. `deadline submit project /by 2021-2-23`

![deadline_example.png](docs/images/deadline_example.png)


### Add Event Task
* Description: Creates and adds an Event task to the task list.
* Format: `event <task_description> <event_time>`
* Examples:
   1. `event school /at Monday 1400`
   2. `event school /at 2021-2-23`

![event_example.png](docs/images/event_example.png)


### Mark Task as Complete
* Description: Marks a task corresponding to a task index in task list as complete.
* Format: done <task_index>
* Example: `done 2`

![done_example.png](docs/images/done_example.png)


### Delete Task
* Description: Deletes a task corresponding to a task index in task list.
* Format: delete <task_index>
* Example: `delete 2`

![delete_example.png](docs/images/delete_example.png)


### Find Task
* Description: Finds a task with task description that matches a keyword.
* Format: `find <keyword>`
* Example: `find book`

![find_example.png](docs/images/find_example.png)


### List Tasks
* Description: Shows a list of all tasks.
* Format: `list`
* Example: `list`

![list_example.png](docs/images/list_example.png)


### View Help guide
* Description: Shows a list of supported commands and their descriptions.
* Format: `help`
* Example: `help`

![help_example.png](docs/images/help_example.png)


### Exit Application
* Description: Terminates the application.
* Format: `bye`
* Example: `bye`

