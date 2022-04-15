# User Guide

##Introduction
Duke is a interactive chatbot that helps the user keep track of the tasks he has to accomplish.

## Features 
* `help`
* `load`
* `list`
* `event [taskName] /at [yyyy-MM-dd] [startTime-endTime]`
* `deadline [taskName] /by [yyyy-MM-dd] [endTime]`
* `todo [taskName]`
* `done [taskNumber]`
* `delete [taskNumber]`
* `find [keyword]`
* `detect`
* `clean`
* `save`
* `bye`

## Usage

### `help`
* Provides the user with a list of all available commands and how to use them

### `load`
* Loads the text file of saved task lists into the current run of duke

### `list`
* List all available tasks currently in the task list to the user

### `event [taskName] /at [yyyy-MM-dd] [startTime-endTime]`
* Creates a event task and adds it to the task list
* Example of usage: event project meeting /at 2019-12-01 2-4pm

### `deadline [taskName] /by [yyyy-MM-dd] [endTime]`
* Creates a deadline task and adds it to the task list
* Example of usage: deadline project meeting /by 2019-12-01 4pm

### `todo [taskName]`
* Creates a to do task and adds it to the task list
* Example of usage: todo read a book

### `done [taskNumber]`
* Marks the task number as listed on the task list as done
* Example of usage: done 3
* Expected outcome: the 3rd task on the task list will be marked as done and shows a tick in the status box

### `delete [taskNumber]`
* Deletes the task number as listed on the task list
* Example of usage: delete 3
* Expected outcome: the 3rd task will be dele ted and no longer be in the task list

### `find [keyword]`
* Searches the task list which contains the keyword input
* Example of usage: find read
* Expected outcome: all tasks containing read in its description will be shown, ie read a book, eat bread

### `detect`
* Searches the task list for any duplicated task
* Expected outcome: a list of all duplicated task will be shown

### `clean`
* Cleans the task list and gets rid of all duplicated task
* Expected outcome: all tasks in the task lists will now be unique

### `save`
* saves the current task list into a textfile

### `bye`
* exits the program