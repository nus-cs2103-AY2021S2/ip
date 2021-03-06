# User Guide

Duke is a desktop app for managing tasks. There are 3 types of task: todo, deadlines & events. It can perform simple
tasks such as add, delete and find for a task by providing keywords and mark as done. It also checked for user mistakes
such as providing a repeated task. User can easily view the tasks added conveniently with Duke. It is optimized for use
via Graphical User Interface (GUI).

### WARNING!

Please be careful when providing inputs as the program will terminate once an invalid input is given. The program fails
if it throws an error message after you typed the input and click enter. You are required to restart the program once the program
fails!!

## FEATURES

1. List
2. Add
3. Delete
4. Find
5. Mark as done

## USAGE

### Feature 1 (**List**)

List all the tasks (saved and recently added tasks) by typing "list"

Example of usage:

`list`

Expected outcome:

`Here are the tasks in your list:`</br>
`1.[T][ ] eat breakfast later`</br>
`2.[D][ ] return book (by: 2019-12-02 23:59)`</br>

### Feature 2 (Add)

Task can be inserted in the following manners:

### TODO

Insert a todo by simply providing a **DESCRIPTION** after the keyword todo. The description can have spaces in between,
and it can be of any length

Example of usage:

`todo eat breakfast later`

Expected outcome:

`Got it. I've added this task:`</br>
`[T][ ] eat breakfast later`</br>
`Now you have 1 tasks in the list.`</br>

### DEADLINE

Insert a deadline by providing a **DESCRIPTION** after the keyword "deadline", followed by **"/by"** with a space after
the description. For deadlines, it is compulsory to add the **DUE DATE** and **TIME** of deadline. The date must be of
this form DD/MM/YYYY and it allows to have single digit for day and month
(eg. 1/1/2021) and the time must be in 24 hours time format (eg. 1800)

Example of usage:

`deadline return book /by 2/12/2019 2359`

Expected outcome:

`Got it. I've added this task:`</br>
`[D][ ] return book (by: 2019-12-02 23:59)`</br>
`Now you have 1 tasks in the list.`</br>

### EVENT

Insert an event by providing a description after the keyword "event", followed by **"/at"** with a space after the
description. For events, it is compulsory to add the **DUE DATE**, **EVENT START TIME** and **EVENT END TIME**. The date
must be of the form DD/MM/YYYY and it allows to have single digit for day and month (eg. 1/1/2021) and the time must be
in 24 hours time format (eg. 1800)

Example of usage:

`event project meeting /at 18/10/2015 1000-1200`

Expected outcome:

`Got it. I've added this task:`</br>
`[E][ ] project meeting (at: 2015-10-18 10:00-12:00)`</br>
`Now you have 1 tasks in the list.`</br>

### Feature 3 (Delete)

Task can be deleted by specifying the index of the task in the TaskList after the keyword "delete". It is recommended
for users to check the index of tasks using "list" before starting to delete task. The example below will delete the
first task of the TaskList.

Example of usage:

`delete 1`

Expected outcome:

`Noted. I've removed this task:`</br>
`[T][X] eat breakfast later`</br>
`Now you have 0 tasks in the list.`</br>

### Feature 4 (Find)

Find all the tasks related to the keyword provided by specifying the keyword after "find". The following example will
return output task related to the keyword "books".

Example of usage:

`find eat`

Expected outcome:

`Task(s) related to the keyword :`</br>
`1.[T][X] eat breakfast later`</br>

### Feature 5 (Done)

Mark a task of the specified index as done. It is recommended for users to check the index of tasks using "list" before
starting to mark task. The example below will mark the first task of the TaskList as done represented as [X].

Example of usage:

`done 1`

Expected outcome:

`Nice! I've marked this task as done:`</br>
`[T][X] eat breakfast later`</br>