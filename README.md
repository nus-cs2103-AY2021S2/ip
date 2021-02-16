# User Guide

![PercyUi](https://github.com/colintkn/ip/blob/master/docs/Ui.png?raw=true)

## Features 
Percy chat bot is a task manager. It allows users to add, delete, find tasks. 
### 1. `help` - Exits the application 
Creates a help dialogue box. 

**Example of usage:**

```
help
```

**Expected output:**
```
Hello, I am Percy and I'm your personal assistant.
Here are some commands that I can understand:"
1. todo: e.g. "todo feed dog"
2. deadline: e.g. "deadline renew pet license /by 2015-08-31"
3. event: e.g. "event pet expo /at 2015-08-31"
4. list: e.g. "list"
5. delete: e.g. "delete 1"
6. done: e.g. "done 1"
7. find: e.g. "find pet"
8. bye: e.g. "bye"
For more command-specific help, type "help <COMMAND>"
```
### 2. `bye` - Exits the application 
Exits Percy Chat Bot application.

**Example of usage:**

```
bye
```

**Expected output:**
```
Bye. Hope to see you again soon!
```
### 3. `todo`
Creates a todo task

**Example of usage:**

```
todo feed dog
```

**Expected output:**
```
Got it. I've added this task:
[T][X] feed dog
Now you have 6 tasks in the list.
```

### 3. `todo`
Creates a todo task

**Example of usage:**

```
todo feed dog
```

**Expected output:**
```
Got it. I've added this task:
[T][X] feed dog
Now you have 6 tasks in the list.
```

### 4. `deadline`
Creates a deadline task

**Example of usage:**

```
deadline renew pet license /by 2015-08-31 0800
```

**Expected output:**
```
Got it. I've added this task:
[D][X] renew pet license (by: Aug 31 2015 08:00am)
Now you have 6 tasks in the list.
```

### 5. `event`
Creates an event

**Example of usage:**

```
event pet expo /at 2015-08-26 1000
```

**Expected output:**
```
Got it. I've added this task:
[E][X] pet expo (at: Aug 26 2015 10:00am)
Now you have 6 tasks in the list.
```

### 6. `Done`
Marks a task as done.

**Example of usage:**

```
done 2
```

**Expected output:**
```
Nice! I've marked this task as done:
[D][✓] renew pet license (by: Aug 31 2015 08:00am)
```

### 7. `find`
Searches tasks by keyword in the task list.

**Example of usage:**
```
find pet
```

**Expected outcome:**
````
Here are the matching tasks in your list:
1.[D][✓] renew pet license (by: Aug 31 2015 08:00am)
````
### 8. `list`
Lists all the tasks. 

**Example of usage:**
```
list
```

**Expected output:**
```
Here are the tasks in your list:
1. [T][X] feed dog
2. [E][X] pet expo (at: Aug 31 2015 10:00am)
```
## Acknowledgements 
@tjtanjin
@Jane-Ng 
@ zhixianggg
