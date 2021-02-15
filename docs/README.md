# User Guide

## Features 

* `bye` Exits the program
* `list` Displays the current list of tasks
* `done [num]` Marks task [num] to done
* `delete [num]` Deletes task [num] from the list
* `find [keyword]` Searches for tasks containing [keyword]
* `todo [description]` Adds a todo task with a description
* `deadline [description] /by [yyyy-mm-dd]` Adds a deadline
* `event [description] /at [yyyy-mm-dd]` Adds an event
* `help` Displays a list of commands and their syntax

### `bye` 
Exits the program

Example of usage: `bye`

Expected outcome: Exits Duke

### `list` 
Displays the current list of tasks

Example of usage: `list`

Expected outcome: List of tasks is displayed

### `done [num]` 
Marks task [num] to done

Example of usage: `done 2`

Expected outcome: Task 2 in the list is marked as done with a tick

### `delete [num]` 
Deletes task [num] from the list

Example of usage: `delete 3`

Expected outcome: Task 3 in the list is deleted from the list


### `find [keyword]` 
Searches for tasks containing [keyword] (case insensitive)

Example of usage: `find home`

Expected outcome: All tasks containing the keyword are displayed


### `todo [description]` 
Adds a todo task with a description

Example of usage: `todo Homework`

Expected outcome: A new task "Homework" is added to the list

### `deadline [description] /by [yyyy-mm-dd]` 
Adds a deadline 

Example of usage: `deadline Lab Report /by 2021-02-19`

Expected outcome: A new deadline is added to the list

### `event [description] /at [yyyy-mm-dd]` 
Adds an event

Example of usage: `event Birthday Party /at 2021-01-20`

Expected outcome: A new event is added to the list


### `help` 
Displays a list of commands and their syntax

Example of usage: `help`

Expected outcome: A list of commands and their syntax is displayed




