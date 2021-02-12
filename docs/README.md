# User Guide

Welcome to Duke, an easy to use Task Manger

## Features 

### Feature 1 
Adding of tasks. There are 3 kinds of tasks that can be added - todo tasks, event tasks and deadline tasks.

Deadline tasks are tasks are tasks with a certain deadline.
Event tasks are tasks which are happening on a certain date
Todo tasks are tasks which are to be completed

## Usage
You can add tasks to Duke to keep track of the tasks that you have to complete.

#### Command Syntax:
To add a todo task, type the command in the following format:
<br>
todo *task_description*

To add a deadline task, type the command in the following format:
<br>
deadline *task_description* /by *deadline*

To add a event task, type the command in the following format:
<br>
event *task_description* /at *date*

#### Examples of usage: 

todo dishes
<br>
deadline ip /by 2021-02-19
<br>
event dinner /at 2021-02-12

#### Expected outcome:

For Todo Task:
Got it. I've added this task:
 <br>
 [T][] dishes
 <br>
 Priority:Unassigned 
 <br>
Now you have 2 tasks in the list

For Deadline Task:

Got it. I've added this task:
 <br>
 [D][] ip (by: 19-02-2021) 
 <br>
 Priority:Unassigned
 <br>
Now you have 2 tasks in the list

For Event Task:

Got it. I've added this task:
 <br>
 [E][] dinner (at: 2021-02-12)
 <br>
 Priority:Unassigned
 <br>
Now you have 2 tasks in the list



### Feature 2 
List tasks in Duke

## Usage
To view what tasks need to be done

#### Command Syntax:
To list tasks, type the command in the following format:
list

list command will return a list of tasks stored in Duke

#### Example of usage: 
list

#### Expected outcome:
Here are the tasks in your list:
  1.[D][] ip (by: 19-02-2021) 
  Priority:Unassigned

### Feature 3
Checking of tasks that contain the date typed in 

## Usage
Used to check if there are any event tasks on a particular date or any deadline tasks with deadlines on a particular date

#### Command Syntax:
To check tasks with a certain date, type the command in the following format:
check *date*

It will return a list of tasks which contain that date

#### Example of usage: 
check 2020-02-19

#### Expected outcome:
Here are the tasks due on the date:
   [D][] ip (by: 19-02-2021) 
   Priority:Unassigned


### Feature 4
Delete a task

## Usage
Delete a task from duke

#### Command Syntax:
To delete a task, type the command in the following format:
delete *task_number*

This will delete the task from duke

#### Example of usage: 
delete 1

#### Expected outcome:
Noted. I've removed this task:
   [D][] ip (by: 19-02-2021) 
   Priority:Unassigned


### Feature 5
Mark a task as done

## Usage
To mark a task as done so that user can keep track of tasks easier

#### Command Syntax:
To mark a task as done, type the command in the following format:
done *task_number*

This will mark the task in duke with a cross to indicate it is done.

#### Example of usage: 
done 1

#### Expected outcome:
Nice! I've marked this task as done:
   [D][X] ip (by: 19-02-2021) 
   Priority:Unassigned


### Feature 6
Find a task with keyword

## Usage
To find a task that contains a particular keyword

#### Command Syntax:
To find a task, type the command in the following format:
find *word*

It will return a list of tasks which contain that date.

#### Example of usage: 
find ip

#### Expected outcome:
Here are the matching tasks in your list:
   1.[D][X] ip (by: 19-02-2021) 
   Priority:Unassigned


### Feature 7
Assign a priority to a task. There are 3 priority levels: high, medium, low

## Usage
User can assign priority to tasks to see what should be completed first

#### Command Syntax:
To assign a priority to a task, type the command in the following format:
priority *task number* *priority level*

It assigns the task with a priority

#### Example of usage: 
priority 2 high

#### Expected outcome:
The following task priority has been changed!
   [D][X] ip (by: 19-02-2021) 
   Priority: High


### Feature 8
Exit Duke

## Usage
Used to exit the Duke program

#### Command Syntax:
bye

Duke will reply with a goodbye message before closing

#### Example of usage: 
bye

#### Expected outcome:
Duke responds with "Bye. Hope to see you again soon!" before exiting


