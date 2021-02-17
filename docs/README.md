# Tasker's User Guide

## Features 

### Tasker manages ALL your tasks, in one place.
Add/Delete/Find/Mark/List your tasks whenever you like. 


Tasker might know your tasks better than you.

## Usage

### `list` - Displays all your tasks

Shows a list of all your tasks 

Example of usage: 

`list`

Expected outcome:

![Image of outcome](https://github.com/figo2127/ip/blob/master/docs/Ui.png)

### `todo [description]` - Adds a todo task

Adds a todo task to your list of tasks.

Example of usage:

`todo wash my car`

Expected outcome:

`a TodoTask named 'wash my car' will be added.`

### `deadline [description] /by [YYYY-MM-DD] /time [HH:mm:ss]` - Adds a deadline task

Adds a deadline task to your list of tasks.

Example of usage:

`deadline wash my car /by 2020-03-21 /time 09:00:00`

Expected outcome:

`a DeadlineTask named 'wash my car' need to be done by 2020-03-21, 09:00:00 will be added.`

### `event [description] /at [YYYY-MM-DD] /time [HH:mm:ss]` - Adds an event task

Adds an event task to your list of tasks.

Example of usage:

`event wash my car /at 2020-03-21 /time 09:00:00`

Expected outcome:

`a EventTask named 'wash my car' need to be done at 2020-03-21, 09:00:00 will be added.`

### `find [keyword]` - Finds among  all your  tasks, those that matches the keyword provided

Returns a list of tasks related to the keyword you provided.

Example of usage:

`find wash`

Expected outcome:

`all tasks with description containing "wash" will be displayed.`

### `delete [index]` - Deletes the task at [index] provided

Deletes that task and shows you the number of tasks left. 

Recommended: use `list` to find out the index of the task you want to delete before deleting.

Example of usage:

`delete 2`

Expected outcome:

`any task at index 2 will be deleted.`

### `done [index]` - Marks the task at [index] as done

Marks that task as done. 

Recommended: use `list` to find out the index of the task you want to mark as done before marking.

Example of usage:

`done 2`

Expected outcome:

`any task at index 2 will be marked as done.`

## There's also a secret Expenses Tracker inside Tasker :)

Here's how to use it:

`list_e` instead of `list` to list out all expenses.

`spend [description] /amt [amount spent] /date YYYY-MM-DD` to add an expense to your expenses list.
