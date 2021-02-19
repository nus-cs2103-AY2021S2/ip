# User Guide
Henchman is your loyal Task Manager application built using Java 11.
Henchman can help track all your tasks and their deadlines, so feel free to order him around. Go on to the **Features** section to test out the commands! 
---
## Features 
Note: All commands are case insensitive.

### Create a ToDo task:
* Description: Adds new ToDo task to your list of tasks.
* Command: `todo`
* Format: `todo <description>`
* Note: `<description>` cannot be empty.
* Example:

### Create an Event task:
* Description: Adds new Event, with the specified event time, task to your list of tasks.
* Command: `event`
* Format: `event <description> /at <YYYY-MM-DD> <HH:mm>`
* Note: `<description>` and `<date>` cannot be empty; `<time>` is optional.
* Example:

### Create a Deadline task:
* Description: Adds new Deadline task, with the specified deadline, to your list of tasks.
* Command: `deadline`
* Format: `deadline <description> /by <YYYY-MM-DD> <HH:mm>`
* Note: `<description>` and `<date>` cannot be empty; `<time>` is optional.
* Example:

### Delete a task:
* Description: Deletes the task specified by its input index.
* Command: `delete`
* Format: `delete <task index>`
* Note: `<task index>` cannot be empty.
* Example:

### Delete a task:
* Description: Marks the task specified by its input index as done.
* Command: `done`
* Format: `done <task index>`
* Note: `<task index>` cannot be empty.
* Example:

### List all tasks:
* Description: Lists all tasks currently in the task list.
* Command: `list`
* Format: `list`
* Note: Just `list` is enough.
* Example: 

### Find tasks: 
* Description: Finds all tasks containing the specified search term.
* Command: `find`
* Format: `find <search term>`
* Note: `<search term>` can even be a phrase, and is case insensitive.
* Example:

### Sort tasks: 
* Description: Sort all tasks according to the sort key.
* Command: `sort`
* Format: `sort <sort key>`
* Key (case insensitive):
    *  `created`: sort by task created date
    *  `description`: sort by task description
    *  `done`: sort by task done status, with unfinished tasks first
    *  `end`: sort by task end date
    *  `type`: sort by task type
* Note: If no sort key is provided, sort by task created date by default. 
* Example:
