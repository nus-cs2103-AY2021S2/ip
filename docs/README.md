# User Guide - Features 

## UI Sample
![image](Ui.png)
<br>

## `ToDo`

### Usage
`todo` *Description of Task*

### Action
Adds a new task of type "ToDo" to the task list.

### Example of usage: 
`todo` *buy groceries*

### Expected outcome:
Added Task!

## `Deadline`

### Usage
`deadline` *Description of Task*/by *DD.MM.YYYY HH:MM*

### Action
Adds a new task of type "Deadline" to the task list.

### Example of usage: 
`deadline` *return library book*/by *14.02.2021 14:00*

### Expected outcome:
Added Task!

## `Event`

### Usage
`event` *Description of Task*/from *DD.MM.YYYY HH:MM* to *DD.MM.YYYY HH:MM*

### Action
Adds a new task of type "Event" to the task list.

### Example of usage: 
`event` *Marcus's birthday party*/from *14.02.2021 18:00* to *14.02.2021 23:30*

### Expected outcome:
Added Task!

## `List`

### Usage
`list`

### Action
Lists all tasks currently in the task list.

### Example of usage: 
`list`

### Expected outcome:
All Tasks:   
[T][] buy groceries  
[D][] deadline return library book/by Sun Feb 14 14:00:00 SGT 2021  
[E][] Marcus's birthday party/from Sun Feb 14 18:00:00 SGT 2021 to Sun Feb 14 23:30:00 SGT 2021  

## `Complete`

### Usage
`complete` *Task Type* *Task Description*

### Action
Finds and marks the desired task complete.

### Example of usage: 
`complete` *todo* *buy groceries*

### Expected outcome:
Task marked complete!

## `Remove`

### Usage
`remove` *Task Type* *Task Description*

### Action
Finds and removes the desired task from the task list.

### Example of usage: 
`remove` *todo buy groceries*

### Expected outcome:
Task removed.

## `Find`

### Usage
`find` *Key Word*

### Action
Finds and lists all tasks from the task list that contain the entered key word.

### Example of usage: 
`find` *book*

### Expected outcome:
There were 1 tasks containing your keyword:   
1: [D][] deadline return library book/by Sun Feb 14 14:00:00 SGT 2021

## `Help`

### Usage
`help`

### Action
Lists all commandss accepted by Duke

### Example of usage: 
`help`

### Expected outcome:
Enter one of the following commands:  
[1] Hello  
[2] Todo <Description of Task>  
[3] Deadline <Description of Task>/by <DD.MM.YYYY HH:MM>  
[4] Event <Description of Task>/from <DD.MM.YYYY HH:MM> to <DD.MM.YYYY HH:MM>  
[5] List  
[6] Find <Key Word>  
[7] Complete <Task Type> <Description>  
[8] Remove <Task Type> <Description>  
[9] Help  
[10] Bye  

## `Hello`

### Usage
`hello`

### Action
If entered upon launch, prompts a greeting and an instruction from Duke. Subsequently prompts an instruction from Duke only.

### Example of usage: 
`hello`

### Expected outcome:
Please enter a command.

## `Bye`

### Usage
`bye`

### Action
Exits the application.

### Example of usage: 
`bye`

### Expected outcome:
Closure of application.
