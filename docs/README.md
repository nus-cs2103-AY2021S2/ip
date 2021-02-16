# User Guide

Kawaii Kat chat bot is a desktop app for managing daily task, categorized into todo, deadline and event tasks. It optimized for use via a Command Line Interface (CLI) while still having the benefits or a Graphical User Interface (GUI).

* [Quick Start](#quick-start)
* [Notes](#notes)
* Features 
   * [Add a todo task: `todo`](#todo---adds-a-todo-task)
   * [Add an event task: `event`](#event---adds-an-event-task)
   * [Add a deadline task: `deadline`](#deadline---adds-a-deadline-task)
   * [Mark a task as complete: `done`](#done---mark-a-task-as-complete)
   * [Delete a task from the task list: `delete`](#delete---delete-a-task)
   * [Exiting the programme and saving the current task list: `bye`](#bye---save-the-current-list-and-exits-the-program)
   * [Find for a task using a keyword: `find`](#find---locate-a-task-by-description)
   * [Display the current task in the list: `list`](#list---list-all-tasks)
 * [FAQ](#faq)
 * [Command summary](#command-summary)
---
## Quick Start
1. Ensure that you have Java `11` or above installed in your computer.

2. Download the latest `chatbot.jar` from here.

3. Copy the file to the folder you want to use as the *home folder* for your Chat Bot.

4. Double-click the file to start the app. The GUI should appear in a few seconds and it should be identical to the one below.
![Image of UI](/docs/Ui.png)

5. Typing the command in text box and press Enter to execute it. e.g. typing `list` and pressing Enter will show the list of tasks.
   Some example commands you can try:
   * `todo TASK_DESCRIPTION`: Adds a todo task with the specified task descripton into the task list.
   
   * `done 1`: Mark the first task in the list as completed with a tick.
   
   * `delete 1`: Deletes the first task in the task list.
   
   * `bye`:Exits the chat bot and saved the current list.
   
6. Refer to the Features below for details of each command.

---
## Notes

|**:writing_hand: Notes about the command format:**|
|---|
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used to describe the todo task.
  
* Items in square brackets are optional.
  e.g `event TASK_DESCRIPTION /at TASK_DATE [TASK_TIME]` can be used as `event TASK_DESCRIPTION /at TASK_DATE` OR `event TASK_DESCRIPTION /at TASK_DATE TASK_TIME`
 
 * Parameters order must be fixed.
 
 * If a parameter is expected only once in the command but you specified it multiple times, the chat bot will display an incorrect input.
   e.g `delete 1 2`
 
 * Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will result in chat bot returning a incorrect input message.
   e.g `list 123`
 ---  
## Features

### `list` - List all tasks

Shows a list of all tasks in the task list.

Format: `list`

### `todo` - Adds a todo task

Add a todo task with the specified task description into the task list.

Format: `todo TASK_DESCRIPTION`

Example of usage: 
* `todo Laundry`
* `todo Read a book`

### `event` - Adds an event task

Add a event task with the specified task description, date and time into the task list.

Format: `event /at TASK_DESCRIPTION [TASK_TIME]`
>**NOTE**: TASK_TIME is optional

Example of usage: 
* `event Concert /at 2020-02-10` add an Concert `event` that is happening at 10 Feb 2020.
* `event Audition /at 2020-02-11 10:10` add an Audition `event` that is happening at 11 Feb 2020 10.10am.

### `deadline` - Adds a deadline task

Add a deadline task with the specified task description, date and time into the task list.

Format: `deadline /by TASK_DESCRIPTION [TASK_TIME]`
>**NOTE**: TASK_TIME is optional

Example of usage: 
* `deadline Do Laundry /by 2020-02-10` add a "Do Laundry" `deadline` that is happening at 10 Feb 2020.
* `deadline Read a book /by 2020-02-11 10:10`add a "Read a book" `deadline` that is happening at 11 Feb 2020 10.10am.

### `delete` - Delete a task

Delete a specified task from the task list.

Format: `delete INDEX`
  * Deletes the task at the specified `INDEX`.
  * The index refers to the index number shown in the displayed task list.
  * The index **must be a positive integer** 1,2, 3, ...

Example of usage: 
* `list` followed by `delete 1` deletes the 1st task in the task list

### `done` - Mark a task as complete

Mark a specified task from the task list as complete.

Format: `done INDEX`
  * Mark the task at the specified `INDEX`as complete.
  * The index refers to the index number shown in the displayed task list.
  * The index **must be a positive integer** 1,2, 3, ...

Example of usage: 
* `list` followed by `done 1` marks the 1st task in the task list as complete

### `find` - Locate a task by description

Finds the task with the specified description containing any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORD]`
  * The search is case-insensitive. e.g `laundry` will match `Laundry`
  * Partial matching words will be shown. e.g `Laundry` will match `Laundrys`

Example of usage: 
* `find Concer` could returns task with description `Concerts` and `Concert`

### `bye` - Save the current list and exits the program

Save the current task list into the same directory as `data.txt` and exits.

Format:`bye`

---

## FAQ

**Q**: How do i transfer my data to another Computer?

**A**: Install the app in the other compuer and overwrite the empty data file it creates with the file that contains the data of your previous Chat Bot home folder.

---

## Command summary

**Action**|**Format, Examples**
----------|---------------------
**todo** | `todo TASK_DESCRIPTION.` e.g `todo Laundry`
**deadline** | `deadline TASK_DESCRIPTION /by TASK_DATE [TASK_TIME]` e.g `event do homework /by 2020-02-10 10:10` or `event do homework /at 2020-02-10`
**event** | `event TASK_DESCRIPTION /at TASK_DATE [TASK_TIME]` e.g `event concert /at 2020-02-10 10:10` or `event concert /at 2020-02-10`
**delete** | `delete INDEX` e.g `list` followed by `delete 1` deletes the 1st task in the task list
**done** | `done INDEX` e.g `list` followed by `done 1` marks the 1st task in the task list as complete
**find** | `find KEY_WORD [MORE_KEYWORD]` e.g `find Concert` could returns task with description `Concerts` and `Concert`
**list** | `list`
**exit and save** | `bye`
