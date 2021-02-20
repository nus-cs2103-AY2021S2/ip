# User Guide

## Features 

Add Tasks:
* Todo
* Deadline
* Event


1)List Tasks


2)Set task as done


3)Delete Task


4)Find tasks with specific keyword


5)Sort Tasks chronologically (Deadline only)


6)Save Tasks


## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Todo - Adds a todo to list of task

Example of usage: 

1) todo return book

Expected outcome:

1) Got it. I've added this task:  
[T][ ] return book  
Now you have 1 tasks in the list  

Deadline - Adds a deadline to list of task

Example of usage:

1) Deadline read book /by 18/12/2020 1600

Expected outcome

1) Got it. I've added this task:  
   [D][ ] read book (by: Dec 18 2020 4:00 PM) 
   Now you have 1 tasks in the list
   
Event - Adds an event to the list of task

Example of usage:

1) Event buy book /at 20/12/2020 1700

Expected outcome:

1) Got it. I've added this task:  
   [E][ ] buy book (at: Dec 20 2020 5:00 PM)
   Now you have 1 tasks in the list


done - set a task as completed

Example of usage:

1) done 1

Expected outcome:

1) Nice! I've marked this task as done:
   1. [T][X] return book
    
list - list all the tasks in the list

Example of usage:

1) list

Expected outcome:

1) Here are the tasks in your list:  
   1.[T][ ] return book
   
delete - deletes a task in the list

Example of usage:

1) delete 1

Expected outcome:

1) Noted. I've removed this task:
   [T][] return book
   Now you have 0 tasks in the list
   

   

