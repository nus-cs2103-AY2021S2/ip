# Duke User Guide

## Features

* `List` down all the tasks from the list.

* Add `todo`, `deadline` and `event` tasks to the list.
   
* Mark any task in the list as `done`.

* `Find` all tasks in the list whose description contains the keyword.

* `Delete` any task in the list.

* `Delete all` tasks in the list.

## Usage

### *`List`*

#### What it does:

List down all the tasks from the list.

#### Usage example: 

`list`

#### Expected outcome:

```
Here are the tasks in your list:
1. [T] [] read book
2. [D] [] return book (by: 28 Feb 2021, 5:00pm)
3. [E] [] formal dinner (at: 2 Apr 2021, 6:00pm-9:00pm)
```

### *`Todo`*

#### What it does:

Add a todo task to the list.

#### Usage format:

todo *discription of task*

#### Usage example:

`todo do homework`

#### Expected outcome:

```
Got it. I've added this task:
[T] [] do homework
Now you have 4 tasks in the list.
```

### *`Deadline`*

#### What it does:

Add a deadline task to the list.

#### Usage format:

deadline *discription of task* /by *DD/MM/YYYY HHMM*

#### Usage example:

`deadline submit homework /by 05/04/2021 1800`

#### Expected outcome:

```
Got it. I've added this task:
[D] [] submit homework (by: 5 Apr 2021, 6:00pm)
Now you have 5 tasks in the list.
```

### *`Event`*

#### What it does:

Add an event task to the list.

#### Usage format:

event *discription of task* /at *DD/MM/YYYY HHMM-HHMM*

#### Usage example:

`event team meeting /at 06/04/2021 2000-2200`

#### Expected outcome:

```
Got it. I've added this task:
[E] [] team meeting (by: 6 Apr 2021, 8:00pm-10:00pm)
Now you have 6 tasks in the list.
```

### *`Done`*

#### What it does:

Mark a task as done.

#### Usage format:

done *line number*

#### Usage example:

`done 1`

#### Expected outcome:

```
Nice! I've marked this task as done:
[T] [/] read book
```

### *`Find`*

#### What it does:

Find all tasks with descriptions containing the keyword.

#### Usage format:

find *keyword*

#### Usage example:

`find book`

#### Expected outcome:

```
Here are the matching tasks in your list:
1. [T] [/] read book
2. [D] [] return book (by: 28 Feb 2021, 5:00pm)
```

### *`Delete`*

#### What it does:

Delete a task **or** all tasks.

#### Usage format (delete a task):

delete *line number*

#### Usage example: (delete a task):

`delete 1`

#### Expected outcome (delete a task):

```
Noted. I've removed this task:
[T] [/] read book
Now you have 5 tasks in the list.
```

#### Usage example: (delete all tasks):

`delete all`

#### Expected outcome (delete all tasks):

```
Noted. I've removed all the tasks from the list.
```

### *`Bye`*

#### What it does:

Exit the Duke application.

#### Usage example:

`bye`

#### Expected outcome:

The Duke application closes.
