# User Guide
## Duke Task Manager
Duke Task Manager is a chatbot that can help you manage your tasks.

<p align="center">
  <img src="https://github.com/JulietTeoh/ip/blob/master/docs/Ui.png?raw=true">
</p>

## Features
* add todo task
* add event task
* add deadline task
* list all tasks
* delete task from list
* mark task as done
* find tasks related to given search string
* update description of specific task
* end current chatbot session and save tasks to memory

### Create and Add a todo task to Duke's task list : `todo`
### Usage
* Description: adds a todo task to duke's list
* Format: `todo GENERAL_DESCRIPTION_OF_TASK`
* Example: `todo take an afternoon nap`

### Create and Add an event task to Duke's task list : `event`
Add a ToDo tasks to list : `todo`
### Usage
* Description: adds a event task to duke's list
* Format: `todo GENERAL_DESCRIPTION_OF_TASK /at LOCATION_OF_EVENT`
* Example: `event party /at John's house`

### Create and Add an deadline task to Duke's task list : `deadline`
Add a ToDo tasks to list : `deadline`
### Usage
* Description: adds a deadline task to duke's list
* Format: `todo GENERAL_DESCRIPTION_OF_TASK /by YYYY-MM-DD` or `todo GENERAL_DESCRIPTION_OF_TASK /by DD-MM-YYYY`
* Example: `deadline CS2106 tutorial 5 /by 2021-02-16`

### View all tasks in Duke's task list : `list`
### Usage
* Description: view all the tasks the user has added to duke's task list
* Format: `list`
* Example: `list`

### Delete task : `delete`
### Usage
* Description: delete a task from duke's task list
* Format: `delete INDEX_OF_TASK`
* Example: `delete 3`

### Mark task as done : `done`
### Usage
* Description: done a task from duke's task list as done
* Format: `done INDEX_OF_TASK`
* Example: `done 3`

### Find task : `find`
### Usage
* Description: Finds all the tasks whose general description matches the given string
* Format: `find SEARCH_STRING`
* Example: `find book`
  
### Update descriptions of task : `update`
### Usage
* Description: Update the description of a specific task
* Format: `update INDEX_OF_TASK [/de GENERAL_DESCRIPTION_OF_TASK] [/by YYYY-MM-DD or DD-MM-YYYY] [/at LOCATION_OF_EVENT]`
* Example:
  * `update 1 /de buy groceries /at NTUC`
  * `update 3 /de return biology book /by 20-02-2021`

### End duke session : `bye`
### Usage
* Description: Ends current duke session and save the current tasks to disk
* Format: `bye`
* Example: `bye`