# Duke User Guide 

<p align="center">
    <img src="./Ui.png" width="400" heigh = "600">
</p>

## Features 

Duke is a task tracking applications that helps you to keep track of task/events with or without deadline.

## Usage

### Build and Run

To use Duke, you have to compile and run Duke.java at `src/main/java/duke`.

An alternatives will be using gradle to build and run the application.

A packaged jar file has been attached in the latest release. Feel free to download it.
To run the jar file, simply type in the command `java -jar Duke.jar` 


### 1. `todo` - Add a todo task

Add a todo task with your given task content to Duke.

Format: `todo CONTENT`

Example:
```$xslt
todo a 2103 individual project
```

Expected Outcome:
```$xslt
Added liao: [T][ ] 1. a 2103 individual project
You have 1 task in the list!
```
### 2. `deadline` - Add a deadline task

Add a deadline task with your given task content and deadline(YYYY-MM-DD) to Duke.

Format: `deadline CONTENT/DEADLINE`

Example:
```$xslt
deadline team meeting/2021-01-22
```

Expected Outcome:
```$xslt
Added liao: [D][ ] 2. team meeting (Jan 22 2021)
You have 2 tasks in the list!
```

### 3. `event` - Add an event

Add an event with the event content and deadline(YYYY-MM-DD) to Duke.

Format: `event CONTENT/DEADLINE`

Example:
```$xslt
event Chinese New Year/2021-02-11
```

Expected Outcome:
```$xslt
Added liao: [E][ ] 3. Chinese New Year (Feb 11 2021)
You have 3 tasks in the list!
```

### 4. `list` - List all the current tasks

List all the tasks that you have added to Duke.

Format: `list`

Example:
```$xslt
list
```

Expected Outcome:
```$xslt
[T][ ] 1. a 2103 individual project
[D][ ] 2. team meeting (Jan 22 2021)
[E][ ] 3. Chinese New Year (Feb 11 2021)
```

### 5. `done` - Mark a current task as done.

Mark a current task with the given index as done in Duke.

Format: `done INDEX`

Example:
```$xslt
done 1

list
```

Expected Outcome:
```$xslt
Wah~ You done the task: [T][X] 1. a 2103 indivual project
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
[T][X] 1. a 2103 individual project
[D][ ] 2. team meeting (Jan 22 2021)
[E][ ] 3. Chinese New Year (Feb 11 2021)
```

### 6. `delete` - Delete a current task from Duke.

Delete a task with the given index in Duke.

Format: `delete INDEX`

Example:
```$xslt
delete 1
kust
```

Expected Outcome:
```$xslt
Awww~ You've deleted the task: [T][X] 1. a 2103 indiviual project
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
[D][ ] 1. team meeting (Jan 22 2021)
[E][ ] 2. Chinese New Year (Feb 11 2021)
```

### 7. `find` - Find all relevant tasks.

Find all relevent tasks with your given keyword in Duke.

Format: `find KEYWORD`

Example:
```$xslt
find meeting
```

Expected Outcome:
```$xslt
Here are the matches for your search:
[D][ ] 1. team meeting (Jan 22 2021)
```

### 8. `clear` - Clear statistics and history.

Delete user history file.

Format: `clear`

Example:
```$xslt
clear
```

Expected Outcome:
```$xslt
Successfully clear statistics
(Statistics.txt removed)
```

### 9. `bye` - exit Duke

Exit Duke and save your task history.

Format: `bye`

Example:
```$xslt
bye
```

Expected Outcome: Application Exits


## Author
Huang Zhenxin







