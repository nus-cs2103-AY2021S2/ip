# User Guide
Duke is a desktop app for managing daily tasks and contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your task and contact management done faster than traditional GUI apps.

## Features 

### Viewing help: help
### Adding a task: todo/deadline/event
### Listing all tasks: list
### Marking a task as done: done 
### Searching tasks by keywords: find 
### Deleting a task: delete 
### Adding a contact: contact
### Removing a contact: remove 
### Searching a contact by keywords: search
### Viewing all contacts: phone book
### Exiting the program: exit


## Usage

### Viewing help: help 

Shows a message explaining how to access the various features.
```
Format: help
```

### Adding a task: Todo
Adds a todo to the task list.
```
Format: Todo DESCRIPTION
```

Examples of usage: 

Todo workout with Dion

Todo breakfast with Tai

### Adding a task: Deadline 
Adds a deadline with specific due date to the task list.
```
Format: deadline DESCRIPTION /by yyyy_MM_dd
```
Examples of usage:

deadline english assignment /by 2021-02-02

deadline history quiz /by 2021-01-02

### Adding a task: Event
Adds an event with specific time to the task list.
```
Format: event DESCRIPTION /at yyyy_MM_ddTHH:mm
```
Examples of usage:

event class debate against Amber /at 2021-02-02T09:30

event PE assessment /at 2021-02-03T16:30

### Listing all tasks: list 
Shows all tasks in the task list.
```
Format: list
```

### Marking a task as done: done
Marks an existing task at the specified index of the task list as done. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3...
```
Format: done INDEX 
```

Examples of usage: 

done 1

done 11

Tips: _list_ followed by _done 2_ marks the 2nd task in the task list as done.

### Searching tasks by keywords: find 
Finds tasks whose descriptions contain any of the given keywords.
```
Format: find KEYWORDS
```
Examples of usage:

find Dion 

find class debate 

Tips: 

The search is case-insensitive. e.g. makeover will match MAKEOVER or MaKeOvEr.

Not just full words will be matched. e.g. Di will also match Dion.

### Deleting a task: delete 
Deletes an existing task at the specified index of the task list. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3...
```
Format: delete INDEX
```
Examples of usage:

delete 1

delete 11

Tips: _list_ followed by _delete 2_ deletes the 2nd task in the task list.

### Adding a contact: contact
Adds a contact to the phone book.
```
Format: contact NAME NUMBER
```
Examples of usage:

contact Dion 88888888

contact Tai 66666666

### Removing a contact: remove 
Removes an existing contact at the specified index of the phone book. The index refers to the index number shown in the displayed phone book. The index must be a positive integer 1, 2, 3...
```
Format: remove INDEX
```
Examples of usage:

remove 1

remove 11

Tips: _phone book_ followed by _remove 2_ removes the 2nd contact in the phone book.

### Searching a contact by keywords: search
Searches contacts whose descriptions contain any of the given keywords.
```
Format: search KEYWORDS
```
Examples of usage:

search Dion

search 8888

Tips:

Not just full names will be matched. e.g. Di will also match Dion.

Not just the complete number will be matched. e.g. 88 will also match 88888888.

### Viewing all contacts: phone book
Shows all contacts in the phone book.
```
Format: phone book
```
### Exiting the program: exit
Exits the program by clicking on the *exit* button at the top left corner of the GUI. 



















