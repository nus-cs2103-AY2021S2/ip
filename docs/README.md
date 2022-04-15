# User Guide for Kelbot

## Features

### Add
Add Tasks to Task List

### Usage

#### `todo`/`deadline`/`event`/`trivia`

Example of usage: 

`todo update README.md`

Expected outcome:

```
Okay! I have added:
[T] [] update README.md
Now there is 1 task on the list 
```

Example of usage:

`deadline submit Kelbot /by 2021-02-16`

Expected outcome:

```
Okay! I have added:
[D] [] submit Kelbot by February 16, 2021
Now there are 2 tasks on the list 
```

Example of usage:

`event CS2103 tutorial /at 2021-02-17`

Expected outcome:

```
Okay! I have added:
[E] [] CS2103 tutorial at February 17, 2021
Now there are 3 tasks on the list 
```

Example of usage:

`trivia IP is almost done!`

Expected outcome:

```
Okay! I have added:
Trivia: IP is almost done!
Now there are 4 tasks on the list 
```

### List
List out all the tasks

### Usage

#### `list`

Example of usage:

`list`

Expected outcome:

```
1. [T] [] update README.md
```

### Done
Mark a task as done

### Usage

#### `done`

Example of usage:

`done 1`

Expected outcome:

```
Well done! You have completed this task!
[T] [X] update README.md
```

### Find
Find a task using keywords

### Usage

#### `find`

Example of usage:

`find update`

Expected outcome:

```
These are the tasks that have your keyword:
1. [T] [X] update README.md
```

### Snooze
Snooze a task and reschedule it

### Usage

#### `snooze`

Example of usage:

`snooze 2 2021-02-17`

Expected outcome:

```
You have rescheduled this task:
[D] [] submit Kelbot by February 17, 2021
```

### Tag
Tag a task

### Usage

#### `tag`

Example of usage:

`tag 4 #yay`

Expected outcome:

```
You have tagged this task:
Trivia: IP is almost done! #yay
```

### Delete
Delete a task

### Usage

#### `delete`

Example of usage:

`delete 3`

Expected outcome:

```
You have deleted this task:
[E] [] CS2103 tutorial at February 17, 2021
```

### Bye
Terminates Kelbot

### Usage

#### `bye`

Example of usage:

`bye`

Expected outcome:

```
Bye le bye! See you next time!
```