# User Guide

## Features

Duke is a simplified to-do list app that is able to keep track of the completion of tasks. It also provides simple search functionality.   

## Usage

### `help`

Prints the help manual for Duke

Example: `help`

### `todo <content>` 

Add a todo to Duke

Example: `todo buy milk`

### `deadline <content> /by <datetime>`

Add a task with a specific deadline to Duke. Allowed datetime formats are
* `02/02/2021 1220`
* `02/01/2021`

Example: `deadline finish project /by 03/03/2021 2359`

### `event <content> /at <place>`

Add an event that happens at a specific place

Example: `event Jake's football match /at stadium`

### `list`

List all the tasks stored in Duke

Example: `list`

### `find <search string>`

List tasks with the specified search string found in their details

Example: `find math project`
This will search for tasks with `math` or `project` words in it.

### `done <list index>`

Mark task with specified index from list command as done.

Example: `done 2`
This will mark the task with index 2 as done.

### `delete <list index>`

Delete task with specified index from list command.

Example: `delete 3`
This will delete the task with index 3.

### `bye`

Quit the bot

Example: `quit`
