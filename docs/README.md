# User Guide

Welcome to Duke's user guide. This is a comprehensive user guide to teach you how to use Duke.

Duke is a command-line based Task Manager app. To instruct Duke, a user will type a series of commands.


## Summary 

Here is an overview of the commands that a user can use in Duke. 

`list` - Shows the full list of tasks. 

`delete` - Deletes a task from the task list.

`done` - Marks a task as done. 

`todo` - Creates a todo task. 

`event` - Creates an event task. 

`deadline` - Creates a deadline task.

`find` - Find all tasks in the list that contains the specified keyword. 

`sort` - Sorts the tasks in the list by lexicographic ordering.

`bye` - Says goodbye to Duke. 


# Features 

Here are the list of features (aka commands) in Duke.

<hr/>

### Feature 1: List 

Shows the full list of tasks.

### Usage

Example of usage: 

`list` 

Displays the full list of tasks. `list` expects `zero` parameters. 

Expected outcome:

```
Here are the tasks on your list:
1.[T][X] walk cat
2.[T][X] walk dog
Now you have 2 tasks in the list.
```

<hr/>

### Feature 2: Delete 

Deletes a task from the task list.

### Usage

Example of usage:

`delete 1` 

Deletes the first task in the list. `delete` expects `1 number parameter` after the command to tell it 
which task to delete. 

Expected outcome:

``` 
Noted. I've removed this task. 
[T][X] walk cat 
```
<hr/>

### Feature 3: Done

Marks a task as done.

### Usage

Example of usage:

`done 1` 

Marks the first task as done. `done` expects `1 number parameter` after the command to tell it which 
task to mark as done. 

Expected outcome:

``` 
Nice! I've marked this task as done. 
[T][✓] walk dog 
```
<hr/>

### Feature 4: Todo 

Creates a todo task and adds it to your task list. 

### Usage

Example of usage:

`todo walk dog` 

Creates a task called `walk dog`. `todo` expects `a string parameter` after the command that describes
the task.

Expected outcome:

``` 
Got it. I've added this task. 
[T][X] walk dog 
```
<hr/>

### Feature 5: Event

Creates an event task and adds it to your task list.

### Usage

Example of usage:

`event Attend wedding /at Sunday ` 

Creates a task called `Attend wedding` that will be happening on Sunday. `event ` 
expects 2 parameters - an `event description` and an `event time` separated by a `/at` keyword. 


Expected outcome:

``` 
Got it. I've added this task. 
[E][X] Attend wedding (at:Sunday) 
```
<hr/>

### Feature 6: Deadline

Creates an deadline task and adds it to your task list.

### Usage

Example of usage:

`deadline Return book /by 2020-01-21 ` 

Creates a deadline called `Return book` that will be happening on `2020-01-21`. 
`deadline `expects 2 parameters - a `deadline description` and an `deadline date` separated by a `/by` keyword.

Note: `deadline date` has to be in the `yyyy-mm-dd` format or else an exception will be thrown. 

Expected outcome:

``` 
Got it. I've added this task. 
[D][X] Return book (by:21 Jan 2020) 
```

<hr/> 

### Feature 7: Find

Finds all task containing the keyword and displays it to the user. 

### Usage

Example of usage:

`find book` 

Finds all the tasks containing keyword `book`. `find` expects `1 string parameter` that will serve as the search keyword.

Expected outcome:

``` 
Here are the matching tasks in your list. 
5.[D][X]Return book (by:21 Jan 2020) 
```

<hr/> 

### Feature 8: Sort

Sorts all the task in the task list by lexicographical ordering. 

### Usage

Example of usage:

`sort`

Sorts the current list by lexicographical ordering. `sort` expects `0` parameters.

Expected outcome:

``` 
Sorted! Here is your new list: 
1.[E][X]Attend Wedding(at:Sunday)
2.[D][X]Return book(by 01 Jan 2020)
3.[T][X]Walk dog
```
<hr/> 

### Feature 9: Bye

Says goodbye to Duke and tells user to close the program. 

### Usage

Example of usage:

`bye`

Says goodbye to the Duke program. `bye` expects `0` parameters.

Expected outcome:

``` 
Bye. Hope to see you again soon! 
To exit, click[X] at the top left. 
```
