# User Guide
SAGE desktop application for managing tasks that allows one to keep
track of one's tasks, deadlines, and events. It is designed to be 
used via a Command Line Interface (CLI) while benefiting from a 
Graphical User Interface (GUI).

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer. 
1. Download the iP.jar file from github.com/Yanneko/ip/releases
1. Move the file to the target *home folder* for SAGE. 
1. Double-click the file to start the app. The GUI similar to what
   is shown below should appear in a few seconds.
   
   ![SAGE Ui](/docs/Ui.png)
   
1. Refer to the Features below for details of each command.

## Features 

### Viewing help : `help`
Displays a list of commands.

Format: `help`

Alternatively, takes in one command word as an argument and 
displays the details for that specific command. 

Format: `help <command>`

### Listing all tasks : `list`
Lists all tasks in the task list. 

Format: `list`

### Add a ToDo task : `todo`
Adds a ToDo task to the task list.

Format: `todo <task name>`
* `<task name>` can be a String of any length except 0, i.e. it cannot
be an empty String. 
  
### Add a deadline task : `deadline`
Adds a deadline task to the task list. 

Format: `deadline <deadline title> /by <yyyy-mm-dd hhmm>`
* `<deadline title>` can be a String of any length except 0, i.e. it cannot
  be an empty String.
* Date format has to be maintained.
* Time entry `<hhmm>` for the deadline task is optional. If absent,
it will default to midnight. 
  
### Add an event task : `event`
Adds an event task to the task list. 

Format: `event <event title> /at <yyyy-mm-dd hhmm>`
* `<event title>` can be a String of any length except 0, i.e. it cannot
  be an empty String.
* Date format has to be maintained.
* Time entry `<hhmm>` for the deadline task is optional. If absent,
  it will default to midnight.
  
### Mark a task as done : `done`
Marks a task as done using its index in the task list. 

Format: `done <index>`
* `<index>` is the task index number as seen when `list` is called.

### Delete a task from the task list : `list`
Deletes a task from the task list using its index in the task list.

Format: `delete <index>`
* `<index>` is the task index number as seen when `list` is called.

### Find tasks based on a keyword : `find`
Finds all tasks that have the specified keyword in it.

Format: `find <keyword>`

### Closing the application : `bye`
Exits the program. 

Format: `bye`
