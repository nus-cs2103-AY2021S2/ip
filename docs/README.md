# User Guide
Rick is a Personal Chatbot that records and handles all your tasks.
This application

---

## Overview:

Features | Syntax
-------------- | --------------
Show help menu | `help`
Show all tasks | `list`
Find tasks | `find <keyword/keyphrase>`
Add todo | `todo <description>`
Add deadline | `deadline <description> /by <date>`
Add event | `event <description> /on <date>`
Delete a task | `delete <task index>`
Mark task as done | `done <task index>`
Exit application | `bye`

---

## Show Help Menu

### Usage

#### `help` - Displays the help menu

Users can view the help menu for the list of available commands.

Example of usage: 

`help`

Expected outcome:

![help_image](images/help.png)

---

## Show List of All Tasks

### Usage

#### `list` - Displays the list of all tasks

Users can view the list of all tasks stored with Rick.

Example of usage:

`list`

Expected outcome:

![list_image](images/list.png)

---

## Find Tasks

### Usage

#### `find <keyword/keyphrase>` - Displays tasks with matching description

Users can search for tasks by keywords/keyphrase that match the task description.
Additionally, users can also search for tasks based on their type, such as `todo`, `deadline` and `event`.

Example of usage:

`find book`

Expected outcome:

![find_image](images/find.png)

---

## Add Todo Task

### Usage

#### `todo <description>` - Adds a todo task

Users can add a Todo task.

Example of usage:

`todo read book`

Expected outcome:

![todo_image](images/todo.png)

---

## Add Deadline Task

### Usage

#### `deadline <description> /by <date>` - Adds a deadline task

Users can add a Deadline task with a specified due date.

Example of usage:

`deadline CS2103 iP /by 2020-02-12`

Expected outcome:

![deadline_image](images/deadline.png)

---

## Add Event Task

### Usage

#### `event <description> /on <date>` - Adds an Event task

Users can add an Event task with a specified date of event.

Example of usage:

`event IPPT /on 2020-08-04`

Expected outcome:

![event_image](images/event.png)

---

## Delete A Task

### Usage

#### `delete <task index>` - Delete a task

Users can delete a task by specifying the task index.

Example of usage:

`delete 2`

Expected outcome:

![delete_image](images/delete.png)

---

## Mark Task As Done

### Usage

#### `done <task index>` - Mark specified task as done

Users can mark a task as done by specifying the task index.

Example of usage:

`done 2`

Expected outcome:

![done_image](images/done.png)

---

## Exit Application

### Usage

#### `bye` - Exit the application

Users can exit the application.

Example of usage:

`bye`

Expected outcome:

![bye_image](images/bye.png)