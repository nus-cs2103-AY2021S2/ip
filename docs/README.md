# duke - User Guide
duke is a personal task tracker that stores and records your to-dos, deadlines and events.This user guide demonstrates how to set up duke and how use the basic features of duke.

## Setting Up
1. Extract the jar file
2. Open and enter in your terminal `java -jar duke.jar`


### Features 
+ [ToDo](#todo)
+ [Deadline](#deadline)
+ [Event](#event)
+ [Done](#done)
+ [Delete](#delete)
+ [Find](#find)
+ [List](#list) 
+ [Bye](#bye)

### 1. Add a task
Add a task that is either a Todo, Deadline or Event with a given description.
For Deadline or Event task, it is created with the given due date and 
time or event date and time respectively.

### 2. Mark a task as done
Mark an existing task as completed. The cross mark (x) for the task indicates the task is done.

### 4. Delete a task
Delete an existing task from the task list.

### 5. Find tasks using keywords
Find a task or certain tasks using a keyword.

### 6. Display the task list
Display the full list of tasks.

## Usage

### 'todo' - Adds a Todo task

Adds a Todo task with a description of the task to the task list.

Format: `todo <description>`

Example of usage:

`todo math tutorial`

Expected outcome:

```
Got it. I've added this task:
Now you have 1 tasks in the list.
```

### 'deadline' - Adds a Deadline task

Adds a Deadline task with a description of the task and the due date and time to the tasks list. 


Date and time
has to be in this format: "d/m/yyyy HHmm" for to be valid. Time is in 24-hour format.

Format: `deadline <description1 description2> /by <date and time>`

Example of usage:

`deadline return book /by 20/02/2021 1800

Expected outcome:

```
Got it. I've added this task:
[D][] return book (by: Feb 20 2021)
Now you have 1 tasks in the list.
```

### 'event' - Adds an Event task
Adds an Event task with a description of the task and the event time to the tasks list. 

time
has to be in the following format: "event time"

Format: `event <description> /at <even time>`

Example of usage:

`event project meeting /at Mon 2-4pm

Expected outcome:
```
Got it. I've added this task:
[E][] project meeting (at:on 2-4pm)
  Now you have 1 tasks in the list.
```

### 'done' - Marks a task as done
Marks an existing task as done, i.e. adds a cross mark (x) to the tasks.

Format: `done <task number>`

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][x] math tutorial
```

### 'delete' - Deletes a task from the tasks list
Deletes an existing task from the tasks list.

Format: `delete <task number>`

Example of usage:

`delete 1`

Expected outcome:

```
Noted! I've removed this task:
    [T][x] math tutorial
Now u have 0 tasks in the list
```

### 'find' - Finds a task or certain tasks from the tasks list
Finds a task or certain tasks from the task list.

Format: `find <keyword(s)>`

Example of usage:

`find math`

Expected outcome:

```
Here are the tasks in your matching list:
     1. [T][x] math tutorial
```


### 'list' - Displays the full list of tasks
Displays the full list of tasks.

Format: `list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in ur list: 
      1. [T][x] math tutorial
      2. [D][] return book (by: Feb 20 2021)
      3. [E][] project meeting (at:on 2-4pm)

```
### 'bye' - ends the application
Format: `bye`

Example of usage:

`bye`

Expected outcome:
   Application will end and exit

