# User Guide

## Description

Kobe is a desktop chatbot app for managing tasks, optimised with both a Command Line Interface (CLI)
and packaged with a Graphical User Interface (GUI).

## Features 


### View Task List
Views all current tasks in the task list.

### Add Task
Adds three kinds of task to the task list:
1. Todo
2. Deadline
3. Event

### Delete Task
Delete task from the task list.

### Find Task
Finds a task from the task list.

### Mark Task as Done
Marks a task as done from the task list.

### Exit and Save
Exits the program and saves the task list.


## Usage

### `list` - Views the current task list

Views the current task list.

Example of usage:

     list
    
Expected outcome:

     Here are the tasks in your list:
         1. [T][ ] Laundry Cycle 
         2. [D][ ] Movie Reservation (by: Apr 21 2021)
         3. [E][X] Nature Walk (at: Gardens by the Bay)


### `todo` - Adds a todo task

Adds a todo task into the task list.

Format:

     todo <TASK_NAME>

Example of usage:

     todo Laundry Cycle
    
Expected outcome:

     Got it! Kobe added this task:
             [T][ ] Laundry cycle 
     Kobe sees that you have 1 task(s) in the list.

### `deadline` - Adds a deadline task

Adds a deadline task, with an optional datem into the task list.

Format:

     deadline <TASK_NAME>
###
     deadline <TASK_NAME> /by <DATE_IN_YYYY-MM-DD>

Example of usage:

     
     deadline Movie Reservation
###    
     deadline Movie /by 2021-02-17
    
Expected outcome:

     Got it! Kobe added this task:
        [D][ ] Movie Reservation
     Kobe sees that you have 1 task(s) in the list.
###     
     Got it! Kobe added this task:
        [D][ ] Movie Reservation (by: Feb 17 2021)
     Kobe sees that you have 1 task(s) in the list.

### `event` - Adds an event task

Adds an event task into the task list.

Format:

    event <TASK_NAME>
###
    event <TASK_NAME> /at <LOCATION>

Example of usage:

    event Gardens By the Bay
###
    event Nature Walk /at Gardens By the Bay
    
Expected outcome:

    Got it! Kobe added this task:
        [E][ ] Gardens By The Bay
     Kobe sees that you have 1 task(s) in the list.
###
    Got it! Kobe added this task:
        [E][ ] Nature Walk (at: Gardens By The Bay)
     Kobe sees that you have 1 task(s) in the list.

### `delete` - Deletes a task

Deletes a task from the task list.

Format:

     delete <TASK_NUMBER>

Example of usage:

     delete 1
    
Expected outcome:

     Okay! Kobe will remove your task from the list:
        [T][ ] Laundry Cycle 
     Kobe sees that you now have 2 task(s) in the list.
     
### `done` - Mark a Task as Done

Marks a task as done from the task list.

Format:

     done <TASK_NUMBER>

Example of usage:

     done 1
    
Expected outcome:

     Nice work! Kobe will mark your task as done!
        [D][X] Movie Reservation (by: Apr 21 2021)

### `bye` - Exits and Saves

Exits the program and saves the current list.

Example of usage:

     bye
    
Expected outcome:

     Goodbye! Kobe has saved your list.
     Kobe hopes to see you again soon!
    
###