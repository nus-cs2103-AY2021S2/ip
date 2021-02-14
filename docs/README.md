# User Guide

Rem is a **desktop app for managing tasks using via a Command Line Interface(CLI)** while using Graphical User Interface(GUI) to display.

## Features 
  
  
### Add a Task:
Add the task to the memory of Rem. There are 4 kinds of tasks including:  
**todo**, **deadline**, **event**, **period**  

  #### `todo` - Add a todo to the task list

  Todo is a kind of task without exact date. The format should be:

  `todo {the description of the task}`

  Example of usage: 

  `todo Fight the White Whale`

  If added successfully, Rem will reply that:

  `Got it. I've added this task:`  
  `[T][ ] Fight the White Whale`   
  `Now you have {number of existing tasks} tasks in the list.`

  #### `deadline` - Add a deadline to the task list

  Deadline is a kind of task which need to be finished by the exact date. The format should be:

  `deadline {the description of the task} /by YYYY-MM-DD`

  Example of usage: 

  `deadline Fight the White Whale /by 2021-02-16`

  Expected outcome:

  `Got it. I've added this task:`  
  `[D][ ] Fight the White Whale (by: 2021-02-16)`  
  `Now you have {number of existing tasks} tasks in the list.`

  #### `event` - Add an event to the task list

  Event is a kind of task which need to be finished at the exact date. The format should be:

  `deadline {the description of the task} /at YYYY-MM-DD`

  Example of usage: 

  `deadline Fight the White Whale /at 2021-02-16`

  Expected outcome:

  `Got it. I've added this task:`  
  `[E][ ] Fight the White Whale (at: 2021-02-16)`  
  `Now you have {number of existing tasks} tasks in the list.`

  #### `period` - Add a period to the task list

  Period is a kind of task between two exact dates. The format should be:

  `period {the description of the task} /from YYYY-MM-DD /to YYYY-MM-DD`

  Example of usage: 

  `period Fight the White Whale /from 2021-04-26 /to 2021-05-24`

  If added successfully, Rem will reply that:

  `Got it. I've added this task:`  
  `[P][ ] Fight the White Whale (from: 2021-04-26 to: 2021-05-24)`   
  `Now you have {number of existing tasks} tasks in the list.`
  
  
### Mark down a completed task:
Once the user completes the task, he or she can tell Rem to mark down corresponding task.

  #### `done` - Mark down a completed task. The format should be:

  `done {index of task that is done}`

  Example of usage: 

  Assume a todo task has been added:

  `todo Fight the White Whale`

  `done 1`

  If marked successfully, Rem will reply that:

  `Nice! I've marked this duke.task as done:`  
  `[T][X] Fight the White Whale`
  
  
### Delete a task:
Delete the task located by index in the task list.

  #### `delete` - Delete a task in the task list. The format should be:

  `delete {index of task that is done}`

  Example of usage: 

  Assume a todo task has been added:

  `todo Fight the White Whale`

  `delete 1`

  If deleted successfully, Rem will reply that:

  `Got it. I've removed this duke.task:`  
  `[T][ ] Fight the White Whale`  
  `Now you have {number of tasks in the list} tasks in the list.`
  
  
### List the tasks:
list all the tasks stored in the task list.

  #### `list` - List all the task added. The format should be:

  `list`

  Example 1 of usage:  

  Assume a todo task and a deadline task have been added:

  `todo Fight the White Whale`  
  `deadline Meet Emilia /by 2021-02-14`

  Rem will reply that:

  `"Here are the tasks in your list:`  
  `[T][ ] Fight the White Whale`  
  `[D][ ] Meet Emilia (by: 2021-02-14)`

  Example 2 of usage:

  Assume no task has been added

  Rem will reply that:

  `There is no task.`
  
  
### Find a task:
Search through the task list by the keyword, and Rem will show all the tasks that contain the keyword.

  #### `find` - Find the tasks containing the keyword. The format should be:

  `find {keyword}`

  Example of usage: 

  Assume three tasks has been added:

  `todo Fight the White Whale`  
  `deadline Meet Emilia /by 2021-02-14`
  `event White Whale Hunter Meeting /at 2021-02-16`

  If `find White Whale`, Rem will reply that:

  `Here are the matching tasks in your list:`   
  `[T][ ] Fight the White Whale`  
  `[E][ ] White Whale Hunter Meeting (at: 2021-02-16)`

  If `find Lion`, Rem will reply that:

  `There is no matching task.`





