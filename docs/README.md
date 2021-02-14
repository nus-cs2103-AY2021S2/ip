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
