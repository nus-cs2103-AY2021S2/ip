# User Guide
![Screenshot of Duke](./Ui.png)

## Types of Tasks
1) Todo Tasks
2) Deadline Tasks
3) Event Tasks

## Features 
 Feature | Description
 ------------ | -------------
 bye    | Exits the application
 help   | Shows the list of Commands available
 list   | List the tasks in the TaskList
 add    | Add tasks into the TaskList
 delete | Delete tasks from the TaskList
 done   | Mark Tasks as done in the TaskList
 find   | Find specific tasks in the TaskList using a keyword

## Usage

### `help` - List the commands 
Displays the available commands and their description. 

Example of usage: 
```
> help

  help                  Show list of commands
  bye                   Exit duke
  list                  Display list of tasks
    [todo]              Display only todos
    [deadline]          Display only deadline
    [events]            Display only events
  delete <task id>      Delete task
  done <task id>        Set task as completed
  find <keyword>        Filter tasks by keyword (in description)

  todo <task>                                        Add a new task
  deadline <task> /by <yyyy-mm-dd>                   Add a new deadline
  event <name> /at <yyyy-mm-dd> <hh:mm> - <hh:mm>    Add a new event
  **REMEMBER to exclude the < > when entering keywords
```

### `list` - List the tasks in the TaskList
#### `Optional Keywords` - `todo` `events` `deadline`
Displays the current list of tasks. 

Example of usage: 
```
> list

Here are the tasks in your list:
1.[T][] Get Groceries
2.[D][] Math Assignment (by: 6 Jun 2020)
3.[T][X] Go the the Gym

> list todo

Here are the tasks in your list:
1.[T][] Get Groceries
2.[T][X] Go the the Gym
```

### `todo` - Adds a Todo Task 
#### format: `todo <description>`
Adds a Todo Task into the TaskList

Example of usage: 
```
> todo go to the beach!

Got it. I've added this task:
  [T][] go to the beach!
Now you have 4 tasks in the list.
```

### `deadline` - Adds a deadline Task 
#### format: `deadline <description> /by <yyyy-mm-dd> `
Adds a Deadline Task into the TaskList

Example of usage: 
```
> deadline Math Assignment /by 2021-02-20

Got it. I've added this task:
  [D][] Math Assignment (by: 20 Feb 2021)
Now you have 4 tasks in the list.
```

### `event` - Adds a event Task 
#### format: `event <description> /at <yyyy-mm-dd> <hh:mm> - <hh:mm>`
Adds a Event Task into the TaskList

Example of usage: 
```
> event Track Festival /at 2021-02-20 10:00 - 14:00

Got it. I've added this task:
  [E][] Track Festival (at: 2021-02-20 10:00 - 14:00)
Now you have 4 tasks in the list.
```

### `delete` - Deletes a Task
#### format: `delete <index of Task>`
Deletes a Task from the TaskList

Example of usage: 
```
> delete 1

Noted. I've removed this task:
  [T][] Get Groceries
Now you have 2 tasks in the list.
```

### `done` - Marks a task as done
#### format: `done <index of Task>`
Marks a Task as done from the TaskList

Example of usage: 
```
> done 1

Nice! I've marked this task as done:
  [T][X] Get Groceries
```

### `find` - Deletes a Task
#### format: `find <keyword>`
Finds a Task from the TaskList based on a keyword

Example of usage: 
```
> find beach

Here are the matching tasks in your list:
  [T][] Go the to beach!

```

### `bye` - Exits the application
#### format: `find <keyword>`
Displays a goodbye message and closes the Duke Application

Example of usage: 
```
> bye

Bye. Hope to see you again soon!
```

