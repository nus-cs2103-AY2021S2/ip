# User Guide

This is a User Guide for the chatbox 'Duke!'.

## Features

### Feature 1: Load task list from the hard drive.

###### Task list is initialised from loading the data from the hard drive (if available) upon starting the programme, using pre-set file path.

### Feature 2: Add a task given by the user to a list and display upon requested.

###### Task type can be todos with no date/time associated, event within a specific timing and tasks with a deadline

### Feature 3: Mark a task as done

###### Task can be marked as done using the position of the task in the list displayed. Task is displayed after marked done for user reference

### Feature 4: Delete a task from the list

###### Task can be deleted using the position of the task in the list displayed. The deleted task is displayed after deletion for user reference

### Feature 5: Find tasks which match the keyword given

###### Given keyword from user, return all tasks with matching task description

### Feature 6: Store list on hard disk

###### Upon user input "bye", the updated list is stored on the hard disk using the pre-set file path.

## Usage

### todo

Example:
`todo return book`
Expected outcome:
A todo task with the description of 'return book' is added to the task list

### deadline

Example:
`deadline return book /by 2020-02-02`
Expected outcome:
A deadline task with the description of 'return book' and deadline of 2020-02-02 is added to the task list

### event

Example:
`event birthday party /at Sunday 4pm to 6pm`
Expected outcome:
An event task with the description of 'birthday party' and time of Sunday 4pm to 6pm is added to the task list

### delete

Example:
`delete 2`
Expected outcome:
The second task in the task list is deleted and the information of the task is displayed afterwards

### done

Example:
`done 2`
Expected outcome:
The second task in the task list is marked as done and the updated information of the task is displayed afterwards

### find

Example:
`find book`
Expected outcome:
All tasks containing the word book in their description are displayed on the screen

Describe action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

`outcome`
