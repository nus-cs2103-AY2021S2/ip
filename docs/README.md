# User Guide

## Description

### 
Kobe is a desktop chatbot app for managing tasks, optimised with both a Command Line Interface (CLI)
and packaged with a Graphical User Interface (GUI).

## Features 

### Add Task
Adds three kinds of task to the task list:
1. Todo
2. Deadline
3. Event


## Usage

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


