# User Guide

## About
Duke is a CLI application to track your tasks easily. Built for [CS2103T: Software Engineering Module](https://nus-cs2103-ay2021s2.github.io/website/admin/index.html)

## Features 

### 1. Add Tasks 
 Add a Task to a list to track them. There are **3** types of Task:
 * Todo - the simplest Task with just a description
 * Deadline - a Task that has a description and a deadline
 * Event - a Task that has a description, as well as the date and time of the Task

### 2. Delete Tasks
 Remove a Task from the current list if you no longer want to track it.

### 3. View Tasks
 View all currently tracked Tasks in chronological order.

### 4. Tag Tasks
 Add or remove tags from existing Tasks.

### 5. Complete Tasks
 Mark an existing Task as completed (It will not be deleted).

### 6. Find Tasks
 Search for tasks from the task list based on a keyword.   



## Usage
Action | Command Format | Example 
-------|----------------|---------
Add Todo | `todo \<description>` | `todo read book` | 
Add Event | `event \<description> /at \<YYYY-MM-DD> \<time>`| ```event seminar /at 2020-04-13 2-4pm``` |
Add Deadline | ```deadline \<description> /by \<YYYY-MM-DD>``` | ```deadline task1 /by 2020-04-13``` |
View Tasks | ```list``` | ```list``` | 
Add Tag | `tag add "<task description>" <tag>` | `tag add "CS2103T iP wk5" shag`
Delete Tag | `tag delete "<task description>" <tag>` | `tag delete "CS2103T iP wk5" shag`
Mark Task as completed | ```done <task_index>``` | ```done 1``` |
Delete Tasks | `delete <task_number>`  |  `delete 2` | 
Find Tasks | `find <keyword>`| `find book` | 

