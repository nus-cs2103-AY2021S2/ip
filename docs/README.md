# User Guide for Duke
![Duke SS](https://raw.githubusercontent.com/NightRaven49/ip/master/docs/Ui.png)
## Features 

### What is Duke?
Duke is a simple-to-use task manager for you life! Able to record your todos, deadlines and events,
you can also mark these tasks as done, search through them and snooze them as needed.
## Usage
Duke supports a number of commands:
<br>
`todo` `deadline` `event` `list` `done` `delete` `find` `snooze` `bye`
<br>
Duke also supports automatic saving and loading of tasks between sessions.
### To create a new task
* #### `todo` - creates a new todo task

  The todo command creates a todo with a description of the task.
  
  Example of usage:
  
  `todo (description)`
  
  Expected outcome:
  
  `Got it. I've added this task:`
  <br>
  `[T][ ] (description).`
  <br>
  `Now you have 1 task in the list.`
  <hr>
  
  Exceptions
  - Duke will prompt for a description if none was passed.
<br>
<br>
* #### `deadline` - creates a new deadline
  The deadline command creates a deadline task with both a description and date due.
  Example of usage:
  
  `deadline (description) /by (due date in YYYY-MM-DD format)`
  
  Expected outcome:

  `Got it`
  <br>
  `[D][ ] (description) (by: (due date)).`
  <br>
  `Now you have 1 task in the list.`
  <hr>
  
  Exceptions
    - Duke will prompt for description or due date if either was not passed.
<br>
<br>
* #### `event` - creates a new event task
  The event command creates an event task with both a description and associated time.
  Example of usage:

  `event (description) /at (time in HH:MM format)`

  Expected outcome:

  `Got it. I've added this task:`
  <br>
  `[E][ ] (description) (at: (time)).`
  <br>
  `Now you have 1 task in the list.`
  <hr>
  
  Exceptions
    - Duke will prompt for description or time if either was not passed.     

### For manipulation of current tasks
* #### `list` - lists all tasks
  Lists all tasks saved by Duke thus far. To be used without other parameters.
<br>
<br>
* #### `done` - Marks a specified task as done
  With input task index, Duke will mark the corresponding task as done. Outputs a confirmation.
  <br>
  Example of usage:
  
  `done (task index)`
  
  Expected outcome (assuming todo task of buying groceries):
  
  `Nice! I've marked this task as done:`
  <br>
  `[T][X] Buy groceries`
  <hr>
  
  Exceptions:
    * If no input index detected, Duke will prompt for one. 
    * If input index is negative or larger than total number of tasks, Duke will inform user that
      the input index does not exist.
    * If input index is not an integer, Duke will remind user to format it as such.
<br>
<br>
* #### `delete` - Deletes specified task
  With input task index, Duke will delete the corresponding task. Outputs a confirmation.
  <br>
  Example of usage:

  `delete (task index)`

  Expected outcome (assuming todo task of buying groceries):

  `Noted. I've removed this task:`
  <br>
  `[T][ ] Buy groceries`
  <br>
  `Now you have 0 tasks in the list.`
  <hr>

  Exceptions:
    * If no input index detected, Duke will prompt for one.
    * If input index is negative or larger than total number of tasks, Duke will inform user that
      the input index does not exist.
    * If input index is not an integer, Duke will remind user to format it as such.
<br>
<br>
* #### `find` - Finds tasks related to query
  With input query, displays tasks that contain the search query.

  Example of usage:
  
  `find (query)`
  
  Expected outcome:
  * If there are no relevant tasks: `Looks like no tasks were found :-(`
  * Otherwise:
    
  `Here are the matching tasks in your list:`
  
  `1. [T][ ] (Found task 1)`

  `2. [D][ ] (Found task 2)`
  <hr>

  Exceptions
    * Duke will prompt for a query if none was passed.
<br>
<br>
* #### `snooze` - Snoozes a specified task
  With input index, allows snoozing (postponing) of deadlines and events. For deadlines, input
  integer is in days from original due date, while for events it is in minutes from original time.
  This command has no effect on todos. Outputs a confirmation.
  
  Example of usage:
  
  `snooze (index of deadline) (time to snooze in days)` for deadlines

  or

  `snooze (index of event) (time to snooze in minutes)` for events

  Expected outcome (assuming event at 3pm snoozed for 120 minutes):

  `Got it, this is the updated task: (event) (at: 5pm)`
  <hr>

  Exceptions:
    * If not enough parameters detected, Duke will remind of proper syntax.
    * If input index is negative or larger than total number of tasks, Duke will inform user that
      the input index does not exist.
    * If input index is not an integer, Duke will remind user to format it as such.
  
### Misc
* #### `bye` - Terminates the program
  Duke will close upon entering this command. Will output farewell message and also automatically
  save all tasks stored in the session.
<br>
<br>
* #### Other keywords not yet specified
  Duke will inform user that input is not recognised.
