# Chat the Cat: User Guide

Chat is a desktop app for managing your tasks. 
It is optimised for use via a Command Line Interface (CLI),
while having the benefits of a Graphical User Interface (GUI).

## Features

###1. Adding a new task

Adds a task to Chat's list of tasks. Type of task needs to be specified.

####To add todo task: `todo`
* Command:`todo [name of todo task]`
* Example: `todo read book`

####To add deadline task: `deadline`
* Command: `deadline [name of deadline task] /by [date]`
    * Date should be written as: **dd/MM/YYYY HH:MM**
* Example: `deadline submit paper /by 20/01/2021 23:59`

####To add event task: `event`
* Command: `event [name of event task] /at [start date] - [end date]`
    * Start and end date should be written as: **dd/MM/YYYY HH:MM**
* Example: `event camp /at 20/01/2021 11:00 - 22/01/2021 12:00`
  
Expected outcome: 
* Chat will respond with message that task has been added successfully.
* Task will also be reflected on Chat's list which can be viewed by `list`.

Error:
ChatException will be thrown if format of command is incorrect.

###2. Deleting a task: `delete`

Deletes a task from Chat's list of tasks.

Command: `delete [index of task]`
* **Index of task is as given in list** and list can be viewed by `list`.

Example: `delete 1`

Expected outcome:
* Chat will respond with message that task has been deleted successfully.
* Task will no longer be reflected on Chat's list which can be viewed by `list`.

Error: ChatException will be thrown if format of command is incorrect.
For example, if given index is a negative number.

###3. Marking a task as completed: `done`

Marks a task as completed.

Command: `done [index of task]`
* **Index of task is as given in list** and list can be viewed by `list`.

Example: `done 1`

Expected outcome:
* Chat will respond with message congratulating user on having completed task.
* Upon viewing list by `list`, task will now be marked as done.
    * Marked as not done: `1. T[] read book`
    * Marked as done: `1. T[X] read book`

Error:
ChatException will be thrown if format of command is incorrect.
For example, if given index is a negative number.

###4. Finding tasks: `find`

Finds task by given keywords.

Command: `find [keywords]`

Example: `find t/T n/read`
* This will display all found **todo tasks** with name that includes the word **"read"**.

####Keywords:
* `t/[task type]`
  * `[task type]` = _T_, _D_ or _E_
  * _T_ for todo tasks, _D_ for deadlines and _E_ for events.
* `n/[name]`
* `c/[is completed]`
    * `[is completed]` = _T_ or _F_
    * _T_ for completed, _F_ for not completed yet.
* `d/[dates]`
    * date should be written as dd MMM YYYY, dd MMM or MMM YYYY.
    * `[dates]` can include just one date (start/end) or both start and end dates.
        * dates should be separated with `,` if both start and end dates are entered.
    
####Additional details
    * Each type of keyword should be separated by a space bar.
    * Keywords can be arranged in any order.
    * There should only be one of each kind of keywords. 
        * If there are more than one keywords for one type, the latest keyword of that type will be used.
        * Example: t/E t/D will result in only deadline tasks being found.

Expected outcome:
* Chat will display a list of all tasks that match the keywords given by user.

Error:
ChatException will be thrown if format of command is incorrect.
For example, if incorrect type of keywords are being entered.

###5. Listing all tasks: `list`

List all tasks saved by Chat.

Command: `list`

Expected outcome:
* Chat will list all tasks.

###6. Exit Chat: `bye`

Exits Chat the Cat.

Command: `bye`

Expected outcome:
* Chat will say bye to user.

## Extra: Understanding Chat the Cat

Chat has many different expressions to help users understand the kind of responses given by Chat.

<img src="https://github.com/clarlzx/ip/blob/A-BetterGui/src/main/resources/images/greetingCat.png?raw=true" alt="Greeting Chat" width="80" height="80">
When Chat greets the user upon startup.<br/><br/>

<img src="https://github.com/clarlzx/ip/blob/A-BetterGui/src/main/resources/images/goCat.png?raw=true" alt="Go Chat" width="80" height="80">
When Chat has successfully executed a command given by user. <br/><br/>

<img src="https://github.com/clarlzx/ip/blob/A-BetterGui/src/main/resources/images/errorCat.png?raw=true" alt="Error Chat" width="80" height="80">
When Chat encounters an error. <br/><br/>

<img src="https://github.com/clarlzx/ip/blob/A-BetterGui/src/main/resources/images/gdJobCat.png?raw=true" alt="Good Job Chat" width="80" height="80">
When Chat congratulates user on completing a task. <br/><br/>

<img src="https://github.com/clarlzx/ip/blob/A-BetterGui/src/main/resources/images/goodByeCat.png?raw=true" alt="Goodbye Cat" width="80" height="80">
When Chat is saying goodbye sadly. <br/><br/>