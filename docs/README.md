# User Guide

## Chatbox: Luna

### Luna is an app that uses the CLI to manage tasks.

## Features 

## *Adding a todo task: todo*

### Adds a new todo task.
Format: todo NAME_OF_TASK
Example of usage: todo work

## *Adding a deadline task: deadline*

### Adds a new deadline task.
Format: deadline NAME_OF_TASK /by yyyy-mm-dd hh:mm
Example of usage: deadline work /by 2021-02-28 19:00

## *Adding an event task: event*

### Adds a new event task.
Format: event NAME_OF_TASK /at yyyy-mm-dd hh:mm
Example of usage: event work /at 2021-02-28 19:00

## *Marking a task as done: done*

### Updates a task by marking it as done.
Format: done INDEX_OF_TASK_IN_LIST
Example of usage: done 5

## *Listing down all the tasks: list*

### Lists down all the previously entered tasks.
Format: list
Example of usage: list

## *Finding a task using specific keywords: find*

### Lists tasks which contain the keywords.
Format: find KEYWORDS
Example of usage: find work

## *Tagging an existing task: tag*

### Adds a tag to the intended task.
Format: tag INDEX_OF_TASK_IN_LIST
Example of usage: tag 5

## *Finding a task which has a tag of specific keywords: tagged*

### Lists tasks which contain the keywords in the tag.
Format: tagged KEYWORDS
Example of usage: tagged work

## *Deleting a task: delete*

### Removes a task from the task list.
Format: delete INDEX_OF_TASK_IN_LIST
Example of usage: delete 5

## *Exiting the app: bye*

### Leaves the app and saves the existing list.
Format: bye
Example of usage: bye