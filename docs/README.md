# User Guide

## Features 

### Feature 1 
Adding a todo task.

### Usage

### `todo` - Adds a todo task to the list.

Format: `todo {description}`

Example of usage: 

* `todo buy eggs`
* `todo study`

Expected outcome:

`Got it. I've added this task:`

`[T][ ] buy eggs. `

### Feature 2
Adding a deadline task.

### Usage

### `deadline` - Adds a deadline task to the list.

Format: `deadline {description} | by: {date}`

* The description and date must be separated by the character 
  "|".
* The date must be in format `yyyy-mm-dd`. 

Example of usage:

* `deadline project | by: 2021-02-17`
* `deadline assignment | by: 2021-01-19`

Expected outcome:

`Got it. I've added this task:`

`[D][ ] project (by: Feb 17 2021). `

### Feature 3
Adding an event task.

### Usage

### `event` - Adds an event task to the list.

Format: `event {description} | by: {date}`

* The description and date must be separated by the character
  "|".
* The date must be in format `yyyy-mm-dd`.

Example of usage:

* `event group meeting | on: 2021-02-19`
* `event CNY gatehring | on: 2021-02-15`

Expected outcome:

`Got it. I've added this task:`

`[E][ ] group meeting (on: Feb 19 2021). `

### Feature 4
Listing all tasks.

### Usage

### `list` - Shows a list of all tasks in the list.

Format: `list`

* Any arguement after `list` will be ignored.

* Returns `There is currently no task in the list.` if the list is currently
 empty.
  
* The tasks will be ordered by their priority. If priority are the same,
 the earlier task that is created will come first. 

Expected outcome:

`Here are the task in your list:`

`1.[T][ ] buy eggs. `

`2.[D][ ] project (by: Feb 17 2021). `

`3.[E][ ] group meeting (on: Feb 19 2021).  `

### Feature 5
Deleting a task.

### Usage

### `delete` - Deletes a specific task from the list.

Format: `delete INDEX`

* Deletes the task at the specific `INDEX`.

* The index refers to the index number shown in the 
  displayed list.
  
* The index **must be a positive integer** 1, 2, 3 ...

Example of usage:

* `list` followed by `delete 2` deletes the second
  task in the list.
  
Expected outcome:

`Noted, I've removed this task: `

`[D][ ] project (by: Feb 17 2021).`

### Feature 6
Marking a task as done.

### Usage

### `done` - Marks a specific task as done in the list.

Format: `done INDEX`

* Marks the task at the specific `INDEX` as done.

* The index refers to the index number shown in the
  displayed list.

* The index **must be a positive integer** 1, 2, 3 ...

Example of usage:

* `list` followed by `done 2` marks the second
  task in the list as done."

Expected outcome:

`Nice! I've mark this task as done`

`[D][(tick)] project (by: Feb 17 2021).`

### Feature 7
Locating task by description.

### Usage

### `find` - Finds tasks which the description contains the given keyword.

Format: `find {keyword}`

* The search is case-sensitive. e.g `buy` will not match `Buy` or `BUY`.

* Only the description of task is searched.

* Task that contain the keyword as substring will be returned.
 e.g `buy` will match `buys egg`.


Example of usage:

* `find project` returns
  
`Here are the matching tasks in your list:`
  
`1. [D][ ] project (by: Feb 17 2021).`

### Feature 8
Prioritises tasks.

### Usage

### `priority` - Sets the priority of a specific task.

Format: `priority INDEX PRIORITY`

* Marks the task at the specific `INDEX` as done.

* The index refers to the index number shown in the
  displayed list.

* The index **must be a positive integer** 1, 2, 3 ...

* There are only 3 types of priority: `high`, `medium`, `low`. 
  (in decreasing order of priority).
  
* priority is case-sensitive. e.g `priority 1 HIGH` will result in an error.


Example of usage:

* `priority 2 high` returns

`Noted! I have successfully set the task to high priority: `

`[D][ ] project (by: Feb 17 2021).`