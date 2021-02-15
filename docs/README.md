# Duke User Guide
## What is Duke? 
Duke is a command line task manager that helps users to keep track of tasks.
With a Command Line Interface (CLI) along with a Graphical User Interface (GUI), 
users who can type fast will find it to be a better alternative than a GUI-only 
task manager.

## Features
  
No. | Features | Command Syntax 
:---: | :-------:| :------------: 
1. | todo | todo [task_description]
2. | deadline | deadline [task_description] /by [yyyy-MM-dd HHmm]
3. | event | event [task_description] /at [yyyy-MM-dd HHmm]
4. | list | list
5. | delete | delete [task_index] 
6. | done | done [task_index]
7. | find | find [keyword] 
8. | update |  update [task_index] description [new_description] <br> OR <br> update [task_index] date/time [new_dateTime, format: yyyy-MM-dd HHmm]
9. | bye | bye

### 1. **`todo`**
Add a todo task into the list.  

- **Command Syntax:**  
`todo [task_description]`
  
- **Example of Usage:**  
`todo math assignment`
  
- **Expected Outcome:**  
`Yes sir! I've added this task:`  
`[T][] math assignment`  
`Now you have 1 task in the list.`

### 2. **`deadline`**
Add a deadline task into the list.

- **Command Syntax:**  
`deadline [task_description] /by [yyyy-MM-dd HHmm]`

- **Example of Usage:**  
`deadline complete tutorial /by 2021-03-10 2359`

- **Expected Outcome:**  
`Yes sir! I've added this task:`  
`[D][] complete tutorial (by: Mar 10 2021 23:59)`  
`Now you have 2 task in the list.`

### 3. **`event`**
Add an event task into the list.

- **Command Syntax:**  
`event [task_description] /at [yyyy-MM-dd HHmm]`

- **Example of Usage:**  
`event birthday party /at 2021-03-08 1400`

- **Expected Outcome:**  
`Yes sir! I've added this task:`  
`[E][] birthday party (at: Mar 08 2021 14:00)`  
`Now you have 3 task in the list.`

### 4. **`list`**
Lists all the tasks.  

- **Command Syntax:**  
`list`
- **Example of Usage:**  
`list`

- **Expected Outcome:**  
`Here are the tasks in your list:`  
`1. [T][] math assignment`  
`2. [D][] complete tutorial (by: Mar 10 2021 23:59)`  
`3. [E][] birthday party (at: Mar 08 2021 14:00)`
  
### 5. **`delete`**
Deletes the specific task from the list. 

- **Command Syntax:**  
`delete [task_index]`

- **Example of Usage:**  
`delete 3`

- **Expected Outcome:**  
`Noted! I've removed this task:`  
`[E][] birthday party (at: Mar 08 2021 14:00)`  
`Now you have 2 tasks in the list`

### 6. **`done`**
Marks the specific task as 'done' by indicating on the list with a cross.

- **Command Syntax:**  
`done [task_index]`

- **Example of Usage:**  
`done 1`

- **Expected Outcome:**  
`Nice! I've marked this task as done:`  
`[T][X] math assignment`

### 7. **`find`**
Find tasks with the matching keyword specified.  
Keyword specified can be in the form of [MMM dd yyyy] or [HH:mm] 
to search for tasks with the specific date/time. 

- **Command Syntax:**  
`find [keyword]`

- **Example of Usage:**  
`find tutorial`

- **Expected Outcome:**  
`Here are the matching tasks in your list:`  
`1. [D][] complete tutorial (by: Mar 10 2021 23:59)`

### 8. **`update`**
Updates the specific task accordingly. 

- **Command Syntax:**  
 Description: `update [task_index] description [new_description]`  
 Date/Time: `update [task_index] date/time [new_dateTime, format: yyyy-MM-dd HHmm]`

- **Example of Usage:**  
 Description: `update 1 description chinese assignment`  
 Date/Time: `update 2 date/time 2021-03-15 1900`

- **Expected Outcome:**  
 Description:  
  `Noted. I've updated the following task to:`  
  `[T][X] chinese assignment`  
 Date/Time:   
  `Noted. I've updated the following task to:`  
  `[D][] complete tutorial (by: Mar 15 2021 19:00)`

### 9. **`bye`**
Duke application terminates upon calling bye. 

- **Command Syntax:**  
`bye`

- **Example of Usage:**  
`bye`

- **Expected Outcome:**  
`Bye, this was Eeyore serving you!`  
`Hope to see you again, take care!`
