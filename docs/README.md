# Diamant Chatbox User Guide

# Introduction
Diamant is a custom chatbox made for the CS2103T module individual project. 
It is inspired by the diamond hand memes during the production of the application (January 2021).
Jozu (the bot) will help you (diamond hand retard) keep track of tasks that you have to do.
Tasks that are complete have a `✓`, while tasks that are incomplete have a `✘`.

# Features 
## GUI
A graphical user interface to provide stronger interaction between the user and the chatbox.

## Add different types of task
You can add 3 different types of tasks for Jozu to consolidate.
1. ToDo 
2. Event
3. Deadline

## Delete tasks
If you do not need a specific task anymore, Jozu will help you delete it.

## Clear tasks
If you give up and want out, Jozu can clear all your outstanding tasks and give you a need fresh start.

## Find tasks
By entering a keyword, you can get a list of tasks that are contain the keyword.

## List tasks
Lists all the tasks in the sequential order you have keyed in by.

## Done tasks
Marks the task stored by Jozu as done, as depicted by a `✓`
## Bye
Closes the program and saves all your tasks that you have input.

## In-depth commands and expected output
### Add ToDo task - `todo` 

Format: `todo DESCRIPTION_OF_TASK`

Key in the command `todo` and the description of the task to add a ToDo task. 

Example of usage: 

`todo HODL GME`

Expected outcome:
```
COMMAND RECEIVED! 
ALRIGHT. I HAVE ALREADY ADDED THE TASK!!! 
[T][✘] HODL GME 
Now you have 1 tasks in the list.
```
### Add Event task - `event` 

Format: `event DESCRIPTION_OF_TASK /at yyyy-mm-dd`

Key in the command `event`, the description of the event and the date of the event to add an event task. 

Example of usage: 

`event project meeting /at 2019-10-19`

Expected outcome:
```
COMMAND RECEIVED! 
ALRIGHT. I HAVE ALREADY ADDED THE TASK!!! 
[E][✘] project meeting (at:Oct 19 2019)
Now you have 2 tasks in the list.
```

### Add Deadline task - `deadline` 

Format: `deadline DESCRIPTION_OF_TASK /by yyyy-mm-dd`

Key in the command `deadline`, the description of the deadline and the date of the deadline to add a deadline task. 

Example of usage: 

`deadline return book /by 2019-10-15`

Expected outcome:
```
COMMAND RECEIVED! 
ALRIGHT. I HAVE ALREADY ADDED THE TASK!!! 
[D][✘] return book (by:Oct 15 2019)
Now you have 3 tasks in the list.
```
### Delete tasks - `delete` 

Format: `delete TASK_NUMBER_TO_DELETE`

Key in the command `delete` and the number of the task from the list ordering to delete it.

Example of usage: 

`delete 3`

Expected outcome:
```
COMMAND RECEIVED! 
OK. TASK REMOVED.
[D][✘] return book (by:Oct 15 2019)
Now you have 2 tasks in the list.
```
### Delete all tasks - `clear'

Format: `clear`

Key in the command `clear` to delete all tasks currently stored by Jozu.

Example of usage: 

`clear`

Expected outcome:
```
COMMAND RECEIVED! 
List has been cleared.
```
### Find tasks - `find` 

Format: `find DESCRIPTION_OF_LIST_ITEM`

Key in the command `find` and a description of the task you are looking for to find all tasks related to the description.

Example of usage: 

`find GME`

Expected outcome:
```
COMMAND RECEIVED! 
-----------------
Here are the matching tasks in your list.
1. [T][✘] HODL GME 

```


### List tasks - `list` 

Format: `delete TASK_NUMBER_TO_DELETE`

Key in the command `list` to display all tasks currently stored by Jozu.

Example of usage: 

`list`

Expected outcome:
```
COMMAND RECEIVED! 
Here are the tasks.
1. [T][✘] HODL GME 
2. [E][✘] project meeting (at:Oct 19 2019)
```
### Done tasks - `done` 

Format: `done TASK_NUMBER_TO_MARK_AS_DONE`

Key in the command `done` and a number from the task list to mark the task as complete.

Example of usage: 

`done 1`

Expected outcome:
```
COMMAND RECEIVED! 
ALRIGHT. THIS TASKS HAS BEEN MARKED AS COMPLETE.
1. [T][✓] HODL GME 
```
### Exiting the program and save - `bye` 

Format: `bye`

Key in the command `bye` to exit and save all changes.

Example of usage: 

`bye`

Expected outcome:
```
Program exits.
```



