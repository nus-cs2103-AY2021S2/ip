# MODOC_TM User Guide

MODOC_TM stands for Mechanized Organism Designed Only for Computing_Task Management. 

This is a chat-bot style task manager that saves to your local storage after exit.

-----
## Features


### 1. [list](#1-list)
### 2. [todo](#2-todo)
### 3. [event](#3-event)
### 4. [deadline](#4-deadline)
### 5. [snooze](#5-snooze)
### 6. [done](#6-done)
### 7. [delete](#7-delete)
### 8. [find](#8-find)
### 9. [help](#9-help)
### 10. [bye](#-10-bye)
---
## Usage

### `list` - Shows all the existing tasks

Example of usage: 

    list

Expected outcome:

    Here is the list of tasks.

### `todo` - Adds a todo to the task list.
Example of usage:

    todo homework

Expected outcome:

    Got it. I've added this task:
    [T][X] homework
    Now you have 2 tasks in the list
    

### `event` - Adds an event to the task list.
Example of usage:
    
    event study /at school

Expected outcome:

    Got it. I've added this task:
    [E][X] study (at: school)
    Now you have 3 tasks in the list    


### `deadline` - Adds a deadline to the task list.
Example of usage:

    deadline cs2103 /by 2021-02-19

Expected outcome:
    
    Got it. I've added this task:
    [D][X] cs2103 (by: Feb 19 2021)
    Now you have 4 tasks in the list

### `snooze` - Postpones a deadline to a specified date or automatically snoozes for one day.

Example of usage:

    snooze 3

Postpones the deadline by `1` day

    snooze 3 /to 2021-09-17 

Postpones the deadline to specified date.

Expected outcome:
    
    `Deadline has been snoozed to 2021-09-17`

### `done` - Marks a task at given index as completed.

Example of usage:

    done 2

Expected outcome:

    Nice! I've marked this task as done:
    [✔] completed task

### `delete` - Deletes a task at given index from the task list.
Example of usage:

    delete 2

Expected outcome:
    
    Noted, I've removed this task: 
    [T][✔] homework
    Now you have 2 tasks in the list

### `find` - Finds a task with the provided keywords/characters/details.
Example of usage:

    find work

Expected outcome:
    
    Here are the matching tasks in your list: 
    [T][✔] homework
    [E][✔] do work (at: school)

### `help` - Shows a list of possible commands for the user.
Example of usage:

    help

### `bye` - Saves the current task list onto local storage and closes the program.
Example of usage:
    
    bye

