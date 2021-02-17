# Antonio User Guide
Antonio is a chat bot that is able to keep track of three types of tasks: todos, deadlines and events.

## Quick Start
1. Ensure you have Java 11 or above installed on your Computer.
2. Download the latest Antonio.jar from [here](http://google.com).
3. Double-click on the file to run the application. Upon starting the application, you should encounter the screen below:
```
Ciao, buongiorno! My name Antonio
Come posso aiutarla?
What can I do you for?
```
4. Type in a command in the chat box and press Enter or send to execute it. 

    Here are some example commands you could try:
    
    `list`
   
    `todo [DESCRIPTION]`
   
    `delete 2`
    
    `help`
    
   `bye`
5. Refer to [Features](#features) below for more information and details about each command.

---

## Features
### Display all tasks in the list

Format: `list`

Examples:

```
> list

Capito! Here are the tasks in your list:
1. [T][] buy pasta
2. [T][X] cook pizza
3. [T][] eat lasagna
```

### Add todo task
Adds a new todo task to the list with a description.

Format: `todo [DESCRIPTION]`

Examples: 
```
> todo make pizza

Tuto chiaro! I've added this task:
[T][] make pizza
```

### Add deadline task
Adds a new deadline task to the list with a description, as well as by a specified date and time. 
Note that the time specified is in the 24 hour format.

Format: `deadline [DESCRIPTION] /by YYYY-MM-DD HHMM`

Examples: 
```
> deadline visit uncle mario /by 2021-04-03 1430

Tuto chiaro! I've added this task:
[D][] visit uncle mario
(by: 3 APRIL 2021 1430HRS)
```

### Add event task
Adds a new event task to the list with a description. Event tasks starts from a specific date
and time, and ends at a specific date and time.
Note that the time specified is in the 24 hour format.

Format: `event [DESCRIPTION] /at YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM `

Examples:
```
> event Carnevale di Venezia /at 2021-02-06 1030 /to 2021-02-16 2300

Tuto chiaro! I've added this task:
[E][] Carnevale di Venezia
(by: 6 FEBRUARY 2021 1030HRS to 16
FEBRUARY 2021 2300HRS)
```

### Delete a task
Delete a specified task that exists in the list.

Format: `delete [task ID]`

Examples:
```
> delete 3

Cosa certa! I've removed this task:
[T][] eat lasagna
Now you have 5 tasks in the list.
Prego
```

### Mark a task as done
Mark a specified task in the list as done.

Format: `done [task ID]`

Examples:
```
> done 2

Completato! I've marked this task as done:
[T][X] cook pizza
```

### Find a task
Find a task that exists in the list by its description.

Format: `find [DESCRIPTION]`

Examples:
```
> find pas

L'ho trovata! Here are the matching tasks in
your list:...

1. [T][] buy pasta
2. [T][] eat pasta
```

### Get help
Get a list of commands that is can be run on the Antonio chat bot.

Format: `help`

### Exit the program
Exit the chat bot.

Format: `bye`

---

## FAQ
Q: How do I transfer the data of my list of tasks to another computer?

A: At the same directory which Antonio.jar exists, create a folder called data. Transfer 
TaskListData.txt from the source computer to the newly created folder, and run Antonio.jar.

---

##Command Summary

| Action       | Format and Examples          |
| ------------- |:-------------|
| list| `list`| 
| add todo|`todo [DESCRIPTION]` <br/>e.g `todo make pasta`|
| add deadline|`deadline [DESCRIPTION] /by YYYY-MM-DD HHMM` <br/>eg. `deadline visit uncle mario /by 2021-04-03 1430`|
| add event|`event [DESCRIPTION] /at YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM` <br/>e.g `event Carnevale di Venezia /at 2021-02-06 1030 /to 2021-02-16 2300`|
|delete|`delete 2`|
|done|`done 3`|
|find|`find [DESCRIPTION]` <br/>e.g `find pasta`|
|help|`help`|
|exit|`bye`|




