# User Guide

## About

Duke is a desktop app to track and manage tasks easily. Built
for [CS2103T: Software Engineering Module](https://nus-cs2103-ay2021s2.github.io/website/admin/index.html):

## Features

<img align="right" src="assets/gifs/Explore Frames.gif" height="400">

### 1. Add Tasks

Add different types of tasks to a task list to easily track them. Task types:

- Todos - Tasks with no completion date
- Events - Tasks to be completed **on** a certain dare
- Deadline - Tasks to be completed **by** a cetain date

### 2. View Tasks

View all the tasks in the task list.

### 3. Mark as Completed

Mark a task as completed based on the task number.

### 4. Delete Tasks

Remove tasks from the task list once tracking is not required.

### 5. Sort Tasks

Sort the task list alphabetically based on the task description.

### 6. Find Tasks

Search for tasks from the task list based on a keyword.

## Usage

|      Feature      |              Command Format               |               Example                | Expected Outcome             |
|:-----------------:|:-----------------------------------------:|:------------------------------------:|:----------------------------:|
|   Add Todo Task   |            `todo <description>`           |           `todo read book`           |![](./outcome_images/todo.png)|
|   Add Event Task  |   `event <description> /at <YYYY-MM-DD>`  |    `event seminar /at 2021-03-04`    |![](./outcome_images/event.png)|
| Add Deadline Task | `deadline <description> /at <YYYY-MM-DD>` | `deadline assignment /by 2021-02-28` |![](./outcome_images/deadline.png)|
|     View Tasks    |                   `list`                  |                `list`                |![](./outcome_images/list.png)|
| Mark as Completed |            `done <task_number>`           |               `done 1`               |![](./outcome_images/done.png)|
|    Delete Tasks   |           `delete <task_number>`          |              `delete 2`              |![](./outcome_images/delete.png)|
|     Sort Tasks    |                   `sort`                  |                `sort`                |![](./outcome_images/sort.png)|
|     Find Tasks    |              `find <keyword>`             |              `find book`             |![](./outcome_images/find.png)|