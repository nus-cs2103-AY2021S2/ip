# User Guide

## Features 

### Adding a Todo: `todo`
Adds a todo to the task list.

Format: `todo DESCRIPTION`

Example:

`todo read book`

Expected outcome:

![Todo Example](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/todo.png)

### Adding a Deadline: `deadline`
Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DATE`

* `DATE` must be in the format `yyyy-mm-dd`.

Example:

`deadline return book /by 2021-05-10`

Expected outcome:

![Deadline Example](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/deadline.png)

### Adding an Event: `event`
Adds an event to the task list.

Format: `event DESCRIPTION /at DATE`

* `DATE` must be in the format `yyyy-mm-dd`.

Example:

`event meeting /at 2021-04-21`

Expected outcome:

![Event Example](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/event.png)

### Listing all tasks: `list`
Lists all tasks in the task list.

Format: `list`

Example:

`list`

Expected outcome:

![List Example](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/list.png)

### Completing a task: `done`
Completes a task in the task list.

Format: `done INDEX`
* `INDEX` must be a positive integer that is within the range of the task list.
* Use `list` to see the list of tasks.

Example:

`done 1`

Expected outcome:

![Done Example](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/done.png)

### Updating a task: `update`
Edits a task in the task list.

Format: `update INDEX /description DESCRIPTION` or `update INDEX /date DATE`
* `INDEX` must be a positive integer that is within the range of the task list.
* Use `list` to see the list of tasks.
* `DATE` must be in the format `yyyy-mm-dd`.

Example:

`update 1 /description finish homework`

`update 1 /date 2021-03-15`

Expected outcome:

![Update Example 1](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/update.png)

![Update Example 2](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/update2.png)


### Finding all tasks with keyword: `find`
Finds all tasks in the task list that contains the keyword.

Format: `find KEYWORD`

Example:

`find book`

Expected outcome:

![Find Example](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/find.png)

### Deleting a task: `delete`
Deletes a task from the task list.

Format: `delete INDEX`
* `INDEX` must be a positive integer that is within the range of the task list.
* Use `list` to see the list of tasks.

Example:

`delete 1`

Expected outcome:

![Delete Example](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/delete.png)

### Exiting the program: `bye`
Exits the program.

Format: `bye`

Example:

`bye`

Expected outcome:

![Bye Example](https://raw.githubusercontent.com/wongkokian/ip/master/src/main/resources/images/bye.png)

### Saving the data
Task list data are saved in the hard disk automatically after the program exits. 

There is no need to save manually.

### Loading the data
Task list data are loaded into the program from the hard disk automatically upon starting the program.

There is no need to load manually.