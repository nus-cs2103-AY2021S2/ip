# User Guide

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest mike.jar from [here](https://github.com/ZechariahTan/ip/releases/tag/A-Jar).

3. Double-click the file to start the app.

## Features 

### View current task list: `List`
Shows current list of tasks

Format:`list`

Expected outcome:
```
Here are the tasks in your list:
1. [T] [X] read a book
2. [D] [ ] cs2103 Assignment (by: 12 Dec 2021 09:00)
3. [E] [ ] House party (at: 12 Dec 2021 09:00)
```
### Adding a task to list: `Todo`
Adds a task to be done to the list 

Format:`todo [TASK_DESCRIPTION]`

Example input:`todo clean room`

Expected outcome:
```
Got it. I've added this task:
1. [T] [X] clean room
Now you have 1 tasks in the list.
```

### Adding a task with a deadline to list: `Deadline`
Adds a task with a specified deadline to the list

Format:`deadline [TASK_DESCRIPTION] /by DD-MM-YYYY HH:MM`

Example input: `deadline cs2103 Assignment /by 12-12-2021 09:00`

Expected outcome:
```
Got it. I've added this task:
 [D] [ ] cs2103 Assignment (by: 12 Dec 2021 09:00)
Now you have 2 tasks in the list.
```

### Adding a task to list occurring at a specific time: `Event`
Adds an event with a specified time of occurrence to the list

Format:`event [TASK_DESCRIPTION] /at DD-MM-YYYY HH:MM`

Example input: `event house party /at 12-12-2021 09:00`

Expected outcome:
```
Got it. I've added this task:
 [E] [ ] house party (at: 12 Dec 2021 09:00)
Now you have 3 tasks in the list.
```

### Adding a task to list occurring at a specific time: `Find`
Finds a task with the provided keyword in the description

Format:`find [KEYWORD]`

Example input: `find party`

Expected outcome:
```
Here are the matching tasks in your list:
 1. [E] [ ] house party (at: 12 Dec 2021 09:00)
```

### Marking a task as done: `Done`
Marks a task as done in the list

Format:`done [TASK_INDEX]`

Example input: `done 3`

Expected outcome:
```
Nice! I've marked this task as done:
  [E] [X] house party
```

### Deleting a task from the list: `Delete`
Removes a task from the list

Format:`delete [TASK_INDEX]`

Example input: `delete 3`

Expected outcome:
```
Noted. I've removed this task:
  [E] [X] house party
Now you have 7 tasks in the list.
```
