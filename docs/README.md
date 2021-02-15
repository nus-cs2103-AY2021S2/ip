# User Guide
Duke is a chatbot that helps manage your to-do list.

## Features

### `todo [description]`
Creates a new todo with given description.

Example: `todo buy eggs`

### `deadline [description] /by [date]`
Creates a new task with given description and deadline. Dates should be formatted as
- `dd/mm/yyyy`
- `dd/mm/yyyy hhmm`

Example: `deadline submit assignment /by 21/02/2021`

### `event [description] /at [location]`
Creates a new task with given description and location.

Example: `event picnic /at Botanic Gardens`

### `list`
Lists all tasks currently stored in Duke.

Example: `list`

### `find [keyword]`
Search and lists all tasks containing the keyword.

Example: `find assignment`

### `done [index]`
Marks the task with the given index as done.

Example: `done 3`

### `delete [index]`
Deletes the task at the given index.

Example: `delete 3`

### `tag [index] [tag]`
Tags the task at the given index with the specified tag.

Example: `tag 3 school`

### `bye`
Terminates the chatbot session.

Example: `bye`