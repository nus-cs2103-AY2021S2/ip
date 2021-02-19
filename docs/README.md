# User Guide for DukeLukeMuke
DukeLukeMuke is a **desktop app for managing tasks**, with a simple GUI and CLI-like commands.
## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
1. Download the latest DukeLukeMuke.jar from here.
1. Copy the file to the folder you want to use as the home folder for your DukeLukeMuke.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
1. Type the command in the command box and press Enter to execute it.
1. Refer to the Features below for details of each command.

## Feature Overview
1. Adding a single task:
    1. Todo
    1. Deadline
    1. Event
1. Adding recurring tasks:
    1. Schedule
1. Listing all tasks
1. Deleting a task
1. Marking a task as done
1. Finding tasks
1. Exiting program

## Features
Command format:
```
1. Words in UPPER_CASE are parameters supplied by the user
2. Parameters starting with DATETIME must be in the format:
   dd-MMM-yyyy HH:mm, where
   a. dd is the date as a valid integer (e.g. 28, 03, 1)
   b. MMM is the month as a 3-letter capitalised string (e.g. Jan, Feb)
   c. yyyy is the year as a valid integer (e.g. 2020)
   d. HH:mm is the time in 24 hour format (e.g. 15:00 for 3:00pm)
```

### Adding a single Todo task: `todo`
Adds a to-do task.

Format:
```
todo DESCRIPTION
```
Examples:
```
todo read
todo Buy broccoli
```
### Adding a single Deadline task: `deadline`
Adds a task, with a specification of when it is due.

Format:
```
deadline DESCRIPTION /by DATETIME
```
Examples:
```
deadline Submit /by 01.Jan.2021 23:59
deadline Reply John’s email /by 01.Jan.2021 13:00
```
### Adding a single Event task: `event`
Adds a task, with a specification of when it occurs.

Format:
```
event DESCRIPTION /at DATETIME
```
Examples:
```
event Interview /by 01.Mar.2021 12:00
event CS2103T make-up tutorial /at 01.Apr.2021 16:00
```
### Adding recurring tasks: `schedule`
Adds multiple tasks, at recurring intervals. For now, only weekly intervals are allowed.

There are two types of tasks that can recur: `Deadline` and `Event`.


Format:
```
//add recurring deadline tasks
scheudle NO_OF_WEEKS weekly deadline DESCRIPTION /by DATETIME

//adds recurring event tasks
scheudle NO_OF_WEEKS weekly event DESCRIPTION /at DATETIME
```
Examples:
```
// add deadlined task for 13 weeks from starting date
schedule 13 weekly deadline Lab submission /by 1.Feb.2021 23:59
// add event for 13 weeks from starting date.
schedule 13 weekly event CS2103T tutorial /at 25.Jan.2021 10:00
```
Response:
```
Got it. I've added this recurring task:
  [D][ ] Lab submission (by: 1.Feb.2021 23:59)
for 13 weeks.
Now you have 13 tasks in the list.
```
### Listing all tasks: `task`
Shows a list of all tasks.

Format: `list`
### Deleting a task: `delete`
Deletes the task at the specified INDEX.
The INDEX refers to the index number shown in the list of tasks,
which is retrievable with `list`.


Format: `delete INDEX`

Example:
```
// to delete the first task
delete 1
```
### Marking a task as done: `done`
Marks the task at the specified INDEX, as done. 
The INDEX refers to the index number shown in the list of tasks,
which is retrievable with `list`.

Format: `done INDEX`

Example:
```
// to mark the first task as done
done 1
```
Response:
```
Nice! I've marked this task as done:
  [T][X] read book
```

### Finding tasks: `find`
Find all tasks containing a particular keyword.

Format: `find KEYWORD`

Example:
```
find submission
```

Response:
```
 3. [D][✗] Lab submission (by: 12.Feb.2021  23:59)
 7. [T][ ] Resume submission (at: 01.Mar.2021 10:00)
```
### Exiting program: `bye`
Exits the program and closes the application window.

Format: `bye`