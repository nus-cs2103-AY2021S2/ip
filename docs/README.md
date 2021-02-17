
# User Guide

## About PAson 
PAson is a to-do list chatbot that helps you manage your schedule in one place. Specially designed for greater productivity, managing your schedule is now just a few simple commands away.

## Table of Contents
Quick Start
Features
FAQs
Summary of Commands

# Quick Start
1. Ensure that you have [Java 11](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) installed on your computer
2. Download the [latest official PAson release](https://github.com/jasaaanlim/ip/releases)
3. Place the downloaded `PAson.jar` file in your desired directory
4. Double click the `PAson.jar` file to launch PAson
5. Use the chat box to interact with PAson (View the full list of commands here)

# Features
### Add a Todo task
Add a ToDo task to your list.

Command:
`todo [description]`

Example:
`todo Do the laundry`

Expected outcome:
>Done! I've added a new task:
    [T][✘] Do the laundry
    Now there are 1 tasks in your list.

### Add an Event
Add an Event to your list. Events must contain a mandatory date and can include an optional time.

Command:
`event [description] /at [date] [optional time]`

Example:
`event Jason's birthday /at 24/09/2021`

Expected outcome:
>Done! I've added a new task:
    [E][✘] Jason's birthday (at 24 Sep 2021)
    Now there are 2 tasks in your list.


### Add a Deadline
Add a Deadline to your list. Deadlines must contain a mandatory date and time.

Command:
`deadline [description] /by [date] [time]`

Example:
`deadline CS2106 Assignment 1 /by 05/03/2021 2359`

Expected outcome:
>Done! I've added a new task:
    [D][✘] CS2106 Assignment 1 (at 05 Mar 2021 11:59pm)
    Now there are 3 tasks in your list.

### List Tasks
View the full list of current tasks in PAson.

Command:
`list`

Expected outcome:
> Here are the tasks in your list:
> 1. [T][✘] Do the laundry
> 2. [E][✘] Jason's birthday (at 24 Sep 2021)
> 3. [D][✘] CS2106 Assignment 1 (at 05 Mar 2021 11:59pm)

### Mark Task as Done
Change the status of a given task to done.

Command:
`done [task number]`

Example:
`done 1`

Expected outcome:
> Good job! I've marked this task as done:
> [D][✓] CS2106 Assignment 1 (at 05 Mar 2021 11:59pm

### Delete a Task
Delete a task from PAson.

Command:
`delete [task number]`

Example:
`delete 1`

Expected outcome:
> Okay! I've removed this task:
> [T][✘] Do the laundry
> There are now 2 tasks in your list.

### Find a Task
Find all tasks in your list matching a specific keyword.

Command:
`find [keyword]`

Example:
`find birthday`

Expected outcome:
> Here are the matching tasks in your list:
> 2. [E][✘] Jason's birthday (at 24 Sep 2021)

### List Tasks On a Specific Date
Find all tasks in your list matching a specific date. Two shortcut commands `today` and `tomorrow` are also provided for your convenience - they will display results for the current date and tomorrow's date respectively.

Commands:

`listschedule [dd/mm/yyyy]`
`listschedule today`
`listschedule tomorrow`

Example:
`listschedule 24/09/2021`

Expected outcome:
> Here are the matching tasks in your list:
> 2. [E][✘] Jason's birthday (at 24 Sep 2021)

### Exit PAson
Exit the PAson chatbot. Your tasks will be stored locally and available to you when you start PAson again.

Command:
`bye`

Expected outcome:
> Bye! I shall go rest now. PAge me when you need me.

***Program closes shortly after***
# Handling Dates in PAson
All date and time formats are accepted in the following format: `dd/mm/yyyy hhmm`. For events where the timing is optional, you may simply omit the `hhmm`.
# Saving Tasks
Tasks are automatically saved to your computer and can be found at `/data/tasks.txt` in the same folder as your PAson.jar file. If no valid task file is found, PAson will create one on start up.

# Advanced Features
## Importing Tasks
Switching to PAson and want to transfer your existing tasks into PAson? Follow these steps:
1. Locate the `/data` folder in the folder where PAson.jar resides
2. Open the `tasks.txt` file
3. PAson will read each line as a task, if it follows the PAson task format outlined below
### PAson Task Format
PAson tasks must follow the PAson Task Format. PAson will ignore any tasks that does not follow the correct format.
`[Task type: T, D, E] | [Done status: 0, 1] | [Description] | [Date and time in ISO 8601 format, if applicable]`

Date and time in the PAson `tasks.txt` file follows the [ISO 8601 Date and Time format](https://www.w3.org/TR/NOTE-datetime).
### Example of Valid Task Formats
Undone ToDo task:
`T | 0 | Undone task`
Completed Deadline with date and time:
`D | 1 | Completed deadline on 16/02/2021 at 12:00 PM | 2021-02-16T12:00`
Undone Event without time:
`E | 0 | Undone event without time | 2021-02-16`

# FAQs
### Is PAson supported on MacOS?
> PAson is supported on all machines running Java 11 and above, including MacOS.
### Are commands case-sensitive?
> For your convenience, all commands case insensitive.
### Do my tasks remain after closing PAson?
> Your tasks are automatically saved locally in `/data/tasks.txt`. You may refer to the 'Saving Tasks' section for more information.
### Why are the responses from PAson in red?
> Red coloured chat replies signifies an error. We recommend checking your spelling and input format.

# Summary of Commands
|Action|Format, examples|
|--|--|
|Add ToDo| `todo [description]` e.g. `todo Do the laundry`|
|Add Event|`event [description] /at [date] [optional time]` e.g. `event Jason's birthday /at 24/09/2021`|
|Add Deadline|`deadline [description] /by [date] [time]` e.g. `deadline CS2106 Assignment 1 /by 05/03/2021 2359`|
|List tasks|`list`|
|Mark as Done|`done` e.g. `done 1`|
|Delete|`delete [task number]` e.g. `delete 1`|
|Find|`find [keyword]` e.g. `find Birthday`|
|List Schedule|`listschedule [dd/mm/yyyy]`, `listschedule today`, `listschedule tomorrow` e.g. `listschedule 24/09/2021`
|Exit|`exit`|
