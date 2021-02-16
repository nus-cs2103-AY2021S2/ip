# User Guide

Duke is a **desktop application for managing tasks, optimized to be used with Command Line Interface** (CLI). It showcased your inputs and the outputs using Graphical User Interface (GUI).

#### Table Of Contents

- Features
    - Adding a task: `todo` / `event` / `deadline`
    - Listing all persons: `list`
    - Locating tasks by description: `find`
    - Marking a task as complete: `done`
    - Deleting a task: `delete`
    - Saving the data
    - Editing the save file
- Command summary

## Features
> Notes about the command format:
 >- Words in UPPER_CASE are the parameters supplied by you. e.g. in `DESCRIPTION`, `DESCRIPTION` is a parameter which which can be used as `todo read book`.

### Adding a task: todo / event / deadline
Adds a task into Duke. There are three types of tasks: todo, deadline, event.
> Note: DATE is in format: YYYY-MM-DD
1. Add todo: `todo`
	* Format: `todo DESCRIPTION`
	* Examples:
		* `todo read book`
2. Add deadline: `deadline`
	* Format: `deadline DESCRIPTION /by DATE`
	* Example:
		* `deadline assignment /by 2020-01-01`
3. Add event: `event`
	* Format: `event DESCRIPTION /at DATE`
	* Example:
		* `event funfair /at 2020-02-02`

### Listing all tasks: `list`
Shows a list of all tasks in Duke.
Format: `list`

### Locating tasks by description: `find`
Finds the tasks whose description contains the keywords you give.
Format: `find KEYWORDS`
 * The search is case-sensitive, and the order matters.
 * Only the description is searched.
	* Example: Fun fair does not match with fair fun.
 * Partial words will be matched.
	* Example: funfair will match with fun.
Examples: 
 * `find CS2101` returns `CS2101 HW`, `CS2101 assgn`, and `CS2101 exam`.
![Image of find method example.](https://github.com/markuz5116/Marcus-Ong-iP/blob/master/docs/Find_method_example.png)

### Marking a task as complete: `done`
Marks a task as done.
Format: `done INDEX`
 * Marks the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...
Eamples:
 - `done 1` Marks your first task as completed.
![Image of done example.](https://github.com/markuz5116/Marcus-Ong-iP/blob/master/docs/Done_method_example.png)

### Deleting a task: `delete`
Deletes a specified task from your task list.
Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in your task list.
* The index **must be a positive integer** 1, 2, 3, ...
Examples:
 * `delete 6` deletes the 6th task in your Duke.
 * ![Image of delete method](https://github.com/markuz5116/Marcus-Ong-iP/blob/master/docs/Delete_method_example.png)

### Saving the data
Duke saves your data in the hard disk automatically after each of your inputs. There is no need to save manually :)

### Editing the save file
Duke saves your data in `[file location]/data/save.txt]`. You are welcomed to update your data directly by editing the save file.
> Caution: Make sure the save file is of the right format, else all your data will be deleted.
> Format: 
	>1.  T | 0/1 | DESCRIPTION
	>2. E/D | 0/1 | DESCRIPTION | YYYY-MM-DD
	
## Command Summary
|Action|Format, Examples|
|--|--|
|Add|`todo DESCRIPTION`, `deadline DESCRIPTION /by YYYY-MM-DD`, `event DESCRIPTION /at YYYY-MM-DD`. Example: `todo task1`, `deadline task2 /by 2021-03-12`, `event task3 /at 2021-04-02`|
|List|`list`|
|Find|`find KEYWORDS`. Example: `find task`|
|Done|`done INDEX`. Example: `done 1`|
|Delete|`delete INDEX`. Example: `delete 6`

