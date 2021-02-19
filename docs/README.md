# **DUKE** - User Guide

## Features 
1. todo
2. deadline
3. event
4. list
5. done
6. delete
7. check
8. find
9. help
10. exit

`<>` - required inputs
`{}` - optional inputs
### 1. `todo` - Add todo task
Adds a todo task to the list

Format: `todo {task name}`

#### Usage
Example of usage: `todo read book`

Expected response:
````
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list
````

### 2. `deadline` - Add deadline task
Adds a deadline task to the list

Format: `deadline {task name} /by {date in YYYY-MM-DD format}`

#### Usage
Example of usage: `deadline submit essay /by 2021-05-03`

Expected response:
````
Got it. I've added this task:
  [D][ ] submit essay (by: May 3 2021)
Now you have 2 tasks in the list
````

### 3. `event` - Add event task
Adds a event task to the list

Format: `event {task name} /at {date in YYYY-MM-DD format}`

#### Usage
Example of usage: `event workshop /at 2021-03-05`

Expected response:
````
Got it. I've added this task:
  [E][ ] workshop (at: Mar 5 2021)
Now you have 3 tasks in the list
````

### 4. `list` - lists tasks
Lists all current tasks

Format: `list`

#### Usage
Example of usage: list

Expected response:
````
1. [T][ ] read book
2. [D][ ] submit essay (by: May 3 2021)
3. [E][ ] workshop (at: Mar 5 2021)
````

### 5. `done` - Marks task as done
Marks the given task as done

Format: `done {task number}`

#### Usage
Example of usage: `done 2`

Expected response:
````
Nice! I've marked this taks as done:
  [D][X] submit essay (by: May 3 2021)
````

### 6. `delete` - Deletes a task
Deletes the given task from the list

Format: `delete {task number}`

#### Usage
Example of usage: `delete 2`

Expected response:
````
Noted. I've removed this task:
  [D][X] submit essay (by: May 3 2021)
  Now you have 2 tasks in the list.
````

### 7. `check` - Checks tasks on date
Looks for all tasks on a given date

Format: `check {date in YYYY-MM-DD format}`

#### Usage
Example of usage: `check 2021-03-05`

Expected response:
````
1. [E][ ] workshop (at: Mar 5 2021)
````

### 8. `find` - Finds tasks by name
Looks for all tasks whose name contains a given key word

Format: `find {key work}`

#### Usage
Example of usage: `find read`

Expected response:
````
1. [T][ ] read book
````

### 9. `help` - Gives help for a command
Gets help for the list of commands or a specific command

Format: `help (command)`

#### Usage
Example of usage: `help`

Expected response:
````
List of commands:
1. todo
2. deadline
3. event
4. list
5. done
6. delete
7. find
8. check
9. exit
Type 'help <command>' in the chat to see the details!
````

Example of usage: `help done`

Expected response:
````
Marks the task as done

Format: done <task number>
````

### 10. `exit` - Exits duke
Prompts duke to say goodbye and closes the programme

Format: `exit`

#### Usage
Example of usage: `exit`

Expected response:
````
Bye. Hope to see you again soon!
````