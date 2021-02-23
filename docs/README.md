# User Guide

## Features 

### Add tasks (ToDos, Deadlines, Events)
You can add 3 types of tasks to MyDuke:

**ToDo** - General tasks with no specific deadline

**Deadline** - Tasks with a specific do-by date and time

**Event** - Matters to attend to at a specific date and time

Number of tasks will be updated and shown upon each addition
of tasks.

### Show task list
You can list all the tasks stored in MyDuke.

### Mark tasks as done
You can mark a task as done upon completion.

### Search for tasks
You can search (a) task(s) using keywords, MyDuke will show
a list of all tasks matching the keyword.

### Sort tasks by alphabetical order
You can sort and reorder the tasks in alphabetical order.

### Save task
You can save the current tasks on MyDuke, which will be saved
as a txt file that can be read the next time MyDuke is launched.

## Usage

### `todo` - Add a new ToDo Task

Adds a new ToDo Task to MyDuke.

_input: todo [description]_

Example of usage: 

`todo play football`

Expected outcome:

`ok i just help u added this todo -- [T][ ] play football. now u got 1 item(s) in your list ah`

### `Deadline` - Add a new Deadline Task
Adds a new Deadline Task to MyDuke.

_input: deadline [description] /by [YYYY-MM-DD]_

Example of usage:

`deadline CS1010S submission /by 2020-06-06`


Expected outcome:

`ok i just help u added this event -- [D][ ] CS1010S submission (JUN 06 2020)
. now u got 2 item(s) in your list ah`

### `Event` - Add a new Event Task
Adds a new Event Task to MyDuke.

_input: event [description] /by [YYYY-MM-DD]_

Example of usage:

`event Staraptor Birthday /by 2020-02-03`


Expected outcome:

`ok i just help u added this event -- [E][ ] Staraptor Birthday (FEB 03 2020)
. now u got 3 item(s) in your list ah`

### `list` - list all Tasks
Adds a new Event Task to MyDuke.

_input: event [description] /by [YYYY-MM-DD]_

Example of usage:

`list`

Expected outcome:

`1. [T][ ] play football`

`2. [D][ ] CS1010S submission (JUN 06 2020)`

`3. [E][ ] Staraptor Birthday (FEB 03 2020)`

### `done` - mark a Task as done
Marks the task at the specified index as done.

_input: done [index]_

Example of usage:

`done 2`

Expected outcome:

`ok i just help u checked this task as done -- [D][X] CS1010S submission (JUN 06 2020)`

### `delete` - delete a Task
Deletes the task at the specified index as done.

_input: delete [index]_

Example of usage:

`delete 1`

Expected outcome:

`ok i just help u deleted this task -- [T][ ] play football.`

### `find` - find a Task using a keyword
Finds and displays the task that matches the keyword (case-sensitive)

_input: find [keyword]_

Example of usage:

`find Staraptor`

Expected outcome:

`dis is your search result for 'Staraptor'`

`1. [E][ ] Staraptor Birthday (FEB 03 2020)`

### `sort` - sort Task list by alphabetical order
Sorts the list in alphabetical order. (Input list to view list after sorting)

_input: sort_

Example of usage:

`sort`

Expected outcome:

`Ok can limpeh help u sort your list according to alphabetical order liao`

### `bye` - Saves tasks to storage
Saves all tasks in MyDuke to local storage. You may now close the app.

_input: bye_

Example of usage:

`bye`

Expected outcome:

`Leave so soon ah? Ok can limpeh help u save your list in [root]/data as saveFile.txt, i go sleep first`