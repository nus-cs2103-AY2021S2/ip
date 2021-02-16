# User Guide

Hello user! Duke is your friendly chatbot to help you manage and organize your tasks efficiently. 
With a simple user interface and customizable avatar, it is an indispensable part of everyday life.

## Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest Duke.jar from this repository.
3. Copy the file to the directory you want to use as the home folder for Duke.
    * A data folder will also be created in the same directory to store Duke files. 
4. Double-click the file to start the app. The window below should appear.
   ![startingscreen](startpage.PNG)
5. Type the command in the chat box and press `Enter` or click Send to execute it.

### Add tasks to Duke!
You are able to add 3 types of tasks to Duke.
They are:
* Todo
  * Specify description.
* Deadline
  * Specify description & date the task must be completed before.
* Event
  * Specify description & date the event will take place on.

For each type of task, a certain syntax format
must be followed. See usage section.

### Delete tasks

Tasks can also be deleted from the list with the `delete` command
and by specifying the index number of the desired task.

### Display all tasks

The list of tasks can be displayed with the `list` command.

### Find a certain task

The list of tasks that matches your requirement can also be
searched for and displayed, by using the `find` command
along with the keyword you wish to search for.

## Usage

### `todo` - Adds a Todo task

Creates and adds a Todo task to the list. Also tells you the number of tasks you have in the list after adding.

Example of usage:

`todo borrow book`

Expected outcome:

![todo](./screenshots/todo.PNG)
### `deadline` - Adds a Deadline task


Creates and adds a Deadline task to the list. Also tells you the number of tasks you have in the list after adding. 

Example of usage:

`deadline return book /by 2021-03-03`

You must use the `/by` keyword and follow the date format yyyy-MM-dd exactly!

Expected outcome:

![deadline](./screenshots/deadline.PNG)

### `event` - Adds an Event task


Creates and adds an Event task to the list. Also tells you the number of tasks you have in the list after adding.

Example of usage:

`event project meeting /at 2021-04-04`

You must use the `/at` keyword and follow the date format yyyy-MM-dd exactly!

Expected outcome:

![event](./screenshots/event.PNG)

### `list` - Displays all tasks

Displays all the tasks in the list.

Example of usage:

`list`

Expected outcome:

![list](./screenshots/list.PNG)

### `done` - Mark a task as complete


Marks a task as complete with a tick in the box.
The index of the item you wish to mark must be provided.
**The tasks will appear in chronological order! All todo tasks will be placed at the top.**
* The index of the item is displayed when the list command is used. 

Example of usage:

`done 1`

Expected outcome:

![done](./screenshots/done.PNG)



### `delete` - Removes a task


Deletes the desired from the list. The index of the item
you wish to delete must be provided.
* The index of the item is displayed when the list command is used.

Example of usage:

`delete 2`

Expected outcome:

![delete](./screenshots/delete.PNG)


Wwhen the `list` command is used again,
the deleted task will no longer appear.



### `find` - Search for a task


Searches the list for tasks that match with the keyword
provided.

Example of usage:

`find book`

Expected outcome:

![find](./screenshots/find.PNG)

## Command Summary

**Action**  | **Format, Examples**
--------|-----------------
**Add todo task** | `todo description` <br /> e.g., `todo Laundry`
**Add deadline task** | `deadline description /by yyyy-MM-dd` <br /> e.g., `deadline return book /by 2021-03-03 `
**Add event task** | `event description /at yyyy-MM-dd` <br /> e.g., `event project meeting /at 2021-04-04`
**List** | `list`
**Done** | `done`
**Delete** | `delete index` <br /> e.g., `delete 2`
**Find** | `find keyword` <br /> e.g., `find project`
**Exit** | `bye`