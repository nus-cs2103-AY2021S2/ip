# User Guide
Duke is a Personal Assistant Chatbot that helps users to manage their tasks.

## Features 

### Add new tasks
You can add 4 different types of tasks in Duke:
* **Todo** - *Tasks that can be done anytime*
* **Deadline** - *Tasks that must be completed before a specific date/time*
* **Event** - *Events that are occurring at a specific date/time*
* **Fixed Duration** - *Tasks that take a fixed amount of time to complete but do not have a fixed start/end time*

### Delete tasks
You can delete existing tasks in Duke.

### Mark tasks as completed
You can mark existing tasks as completed in Duke.

### List tasks
You can list all existing tasks in Duke.

### Find tasks
You can search through all tasks in Duke and find tasks matching the keyword(s).

### Save and load tasks
All tasks will be automatically saved into a local file when you exit Duke, and are loaded when you start
the application.

## Usage
**Note:**  
Words in `UPPER_CASE` are the parameters to be supplied by the user.  
*e.g. in `t DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `t Read book`.*

### `t` - Add a new Todo task 
Adds a new Todo task.

Format: `t DESCRIPTION`

Example of usage:  
`t Read book`

Expected outcome:
<pre>
Got it! I've added this task:
    [T][] Read book
Now you have 1 task in the list.
</pre>

### `d` - Add a new Deadline task
Adds a new Deadline task.  
Input date should be in **yyyy-mm-dd format**.

Format: `d DESCRIPTION /by DATE`

Example of usage:  
`d Return book /by 2021-03-05`

Expected outcome:
<pre>
Got it! I've added this task:
    [D][] Return book (by: Mar 05 2021)
Now you have 2 tasks in the list.
</pre>

### `e` - Add a new Event task
Adds a new Event task.  

Format: `e DESCRIPTION /at DATE`

Example of usage:  
`e Project meeting /at Mon 5pm`

Expected outcome:
<pre>
Got it! I've added this task:
    [E][] Project meeting (at: Mon 5pm)
Now you have 3 tasks in the list.
</pre>

### `fd` - Add a new Fixed Duration task
Adds a new Fixed Duration task.  

Format: `fd DESCRIPTION /dur DURATION`

Example of usage:  
`fd Fix bicycle /dur 1 hour`

Expected outcome:
<pre>
Got it! I've added this task:
    [FD][] Fix bicycle (time required: 1 hour)
Now you have 4 tasks in the list.
</pre>

### `delete` - Delete an existing task
Deletes the task with the specified **index**.

Format: `delete INDEX`

Example of usage:  
`delete 2`

Expected outcome:
<pre>
Noted! I've removed this task:
    [D][] Return book (by: Mar 05 2021)
Now you have 3 tasks in the list.
</pre>

### `done` - Mark an existing task as completed
Marks the task with the specified **index** as completed.

Format: `done INDEX`

Example of usage:  
`done 1`

Expected outcome:
<pre>
Nice! I've marked this task as done:
    [T][X] Read book
</pre>

### `list` - List all existing tasks
Lists all existing tasks.  
The type and status icon of the task will be shown.
The status icon will be `[X]` for completed tasks and `[]` otherwise.

Format: `list`

Example of usage:  
`list`

Expected outcome:
<pre>
Here are the tasks in your list:
    1. [T][X] Read book
    2. [E][] Project meeting (at: Mon 5pm)
    3. [FD][] Fix bicycle (time required: 1 hour)
</pre>

### `find` - Search tasks
Finds all existing tasks matching the **keyword(s)**.

Format: `find KEYWORDS`

Example of usage:  
`find book`

Expected outcome:
<pre>
Here are the matching tasks in your list:
    1. [T][X] Read book
</pre>

### `bye` - Save tasks
Saves all existing tasks to the hard disk.  
**The user may now close the app.**

Format: `bye`

Example of usage:  
`bye`

Expected outcome:
<pre>
Bye! Hope to see you again soon!
</pre>

