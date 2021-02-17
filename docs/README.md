# **DUKE**

Duke is a chatbox that users can use to track their tasks.
## Features 

- Adds a new todo (a task with only name): `todo`
- Adds a new deadline (a task with name and date): `deadline`
- Adds a new event (a task with name and date): `event`
- Delete a task: `delete`
- Mark a task as done: `done`
- Find tasks containing some keyword(s): `find`
- Edit a task: `edit` 
- List a task: `list`
- Exit the program: `bye`



## Usage

#### `todo` - Add todo
`<>` - required fields
`[]` - optional fields

Adds a todo task to the task list.

Format: `todo <task name>`

Example of usage: `todo sleep`

Expected outcome:


````
-------------------------------------------->>>>
    Ayyyyy I've added the task: 
    [T][✗]sleep
    Now you have 3 task(s) in the list.
-------------------------------------------->>>>
````

#### `deadline` - Add deadline

Adds a todo deadline to the task list.

Format: `deadline <task name> /by <YYYY-MM-DD>`

Example of usage: `deadline homework 3 /by 2021-03-01`

Expected outcome:


````
-------------------------------------------->>>>
    Ayyyyy I've added the task: 
    [D][✗]homework 3 (by: Mar 01 2021)
    Now you have 4 task(s) in the list.
-------------------------------------------->>>>
````

#### `event` - Add event

Adds a todo event to the task list.

Format: `event <task name> /at <YYYY-MM-DD>`

Example of usage: `event party /at 2021-03-02`

Expected outcome:


````
-------------------------------------------->>>>
    Ayyyyy I've added the task: 
    [E][✗]party (at: Mar 02 2021)
    Now you have 5 task(s) in the list.
-------------------------------------------->>>>
````

#### `delete` - Delete a task

Deletes a task from the task list.

Format: `delete <task number>`

Example of usage: `delete 2`

Expected outcome:


````
-------------------------------------------->>>>
    Yayyyy! I've removed the task:
    [E][✗]party (at: Mar 02 2021)
    Now you have 4 task(s) in the list.
-------------------------------------------->>>>
````

#### `done` - Mark a task as done

Mark a task as done in the task list.

Format: `done <task number>`

Example of usage: `done 2`

Expected outcome:


````
-------------------------------------------->>>>
    Otsukare! I've marked this task as done:
    [E][✓]sleep
-------------------------------------------->>>>
````

#### `find` - find tasks

Find all task as done in the task list.

Format: `find [keyword]`

Example of usage: `find sleep`

Expected outcome:


````
-------------------------------------------->>>>
    Hai~ The search results are:
    1.[T][✓]sleep
-------------------------------------------->>>>
````
#### `edit` - edit tasks

Edit a task in the task list.

Format: `edit <task number> /name <new name>`

Example of usage: `edit 2 /name wake up`

Expected outcome:


````
-------------------------------------------->>>>
    Done! Name of task number 2 has been changed 
to wake up.
-------------------------------------------->>>>
````

Format: `edit <task number> /time <new time>`

Example of usage: `edit 3 /time 2021-03-21`

Expected outcome:


````
-------------------------------------------->>>>
    Done! Time of task number 3 has been changed 
to Mar 21 2021.
-------------------------------------------->>>>
````
#### `list` - shows all tasks

Shows all tasks in the task list.

Format: `list`

Example of usage: `list`

Expected outcome:


````
-------------------------------------------->>>>
    Hai~ Current tasks are:
    1.[T][✓]sleep
-------------------------------------------->>>>
````

#### `bye` - exit program

Exits the program.

Format: `bye`

Example of usage: `bye`

Expected outcome:


````
-------------------------------------------->>>>
    Sayonara, matta ne~
-------------------------------------------->>>>
````