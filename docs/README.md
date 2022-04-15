# Duke User Guide
## Introduction
**Duke** is a personal chatbot that allow users to manage their daily task easily.
## Features 
* Listing all tasks: `list` 
* Adding a new task: `todo`, `deadline`, `event` 
* Marking a task as done: `done` 
* Deleting a task: `delete` 
* Finding tasks by keywords: `find` 
* Showing the statistics of the task list: `stat` 
* Closing the app: `bye`

---------------------------------------

### Listing all tasks: `list`
Shows the list of all tasks in added order.

#### Usage
Format: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][✓]  read book
2. [E][✘]  project meeting (at: Feb 15 2021 1:00pm)
```

### Adding a task: `todo`
Adds a new todo task to the task list.

#### Usage
Format: `todo <description>` \
Example: `todo go to sleep` \
Expected outcome:
```
Got it. I've added this task: 
  [T][✘] go to sleep
Now you have 3 tasks in the list.
``` 
### Adding a task: `deadline`
Adds a new deadline task to the task list.
#### Usage
Format: `deadline <description> /by <date> [time]` \
Note: 
* The `date` format is `YYYY-MM-DD`.
* The `time` format is `HHMM`.
* `time` is optional.

Example 1: `deadline CS2102 assignment /by 2021-02-17 1800` \
Expected outcome:
```
Got it. I've added this task: 
  [D][✘] CS2102 assignment (by: Feb 17 2021 6:00pm)
Now you have 4 tasks in the list.
```

Example 2: `deadline IP week 5 /by 2020-02-11` \
Expected outcome:
```
Got it. I've added this task: 
  [D][✘] IP week 5 (by: Feb 11 2020)
Now you have 5 tasks in the list.
```

### Adding a task: `event`
Adds a new event task to the task list.
#### Usage
Format: `deadline <description> /at <date> [time]` \
Note: 
* The `date` format is `YYYY-MM-DD`.
* The `time` format is `HHMM`.
* `time` is optional.

Example: `event Lunar New Year /at 2020-02-12` \
Expected outcome:
```
Got it. I've added this task: 
  [E][✘] Lunar New Year (at: Feb 12 2020)
Now you have 6 tasks in the list.
```
### Marking a task as done: `done`
Marks a task at a specified index as done.
#### Usage
Format: `done <index>`

Example: `done 5` \
Expected outcome: 
```
Nice! I've marked this task as done:
  [D][✓] IP week 5 (by: Feb 11 2020)
```

### Deleting a task: `delete`
Deletes a task at a specified index.
#### Usage
Format: `delete <index>`

Example: `delete 1` \
Expected outcome: 
```
Noted. I've removed this task: 
  [T][✓] read book
Now you have 5 tasks in the list.
```

### Finding tasks by keywords `find`
Find tasks that description or date match given keywords.
#### Usage
Format: `find <keyword> [keyword]`

Note: There can be one or more `keyword`

Example: `find meeting assignment` \
Expected outcome:
```
Here are the matching tasks in your list:
1. [E][✘]  project meeting (at: Feb 15 2021 1:00pm)
2. [D][✘] CS2102 assignment (by: Feb 17 2021 6:00pm)
```
### Showing the statistics of the task list: `stat`
Shows the statistics of the task list.
### Usage
Format: `stat`
Expected outcome:
```
There is 1 todo task in the list
    1 not done todo task
There are 1 deadlines in the list
    1 not done deadline
    1 done deadline
There are 1 events in the list
    1 not done event
    1 done event
```
### Closing the app: `bye`
Closes the app

Format: `bye`