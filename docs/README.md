# Duke User Guide

Welcome to Duke Application. This program acts as a companion to remind you of the upcoming tasks that you have. It uses a command line interface which you can operate it by typing commands into the Command Box.

## Command Features 

### list

### todo

### deadline

### event

### delete

### done

### find

### sort

## Command Usage

### `list` - Lists down all the upcoming tasks that you have.

Example of usage: 

`list`

Expected outcome:

`Attached List screenshot`

### `todo` - Adds a Todo task followed by its description.
Its description cannot be left empty.

Example of usage: 

`todo {Description}`

Expected outcome:

`Attached toDo screenshot`

### `deadline` - Adds a Deadline task followed by its description.
Its description cannot be left empty. /by <DateTime> format has to be adhered for command to work.

Example of usage: 

`deadline {Description} /by {DateTime format in YYYY-MM-DD HH:MM}`

Expected outcome:

`Attached deadline screenshot`

### `event` - Adds an Event task followed by its description and date time. 
Its description cannot be left empty. /at <DateTime> format has to be adhered for command to work.

Example of usage: 

`event {Description} /at {DateTime format in YYYY-MM-DD HH:MM}`

Expected outcome:

`Attached event screenshot`

### `delete` - Deletes the task item at the respective index. 
Index has to be within the number of tasks in order for it to function.

Example of usage: 

`delete {index}`

Expected outcome:

`Attached delete screenshot`

### `done` - Marks task as completed at the respective index. 
Index has to be within the number of tasks in order for it to function.

Example of usage: 

`done {index}`

Expected outcome:

`Attached done screenshot`

### `find` - Searches for any tasks that contains the defined text.
Index has to be within the number of tasks in order for it to function.

Example of usage: 

`find {text}`

Expected outcome:

`Attached find screenshot`

### `sort` - Sorts tasks according to the earliest task that is due at the top.

Example of usage: 

`sort`

Expected outcome:

`Attached sort screenshot`



