# **Seashell** - User Guide
Seashell is a personal task manager in the form of a chatbot to help users manage tasks like deadlines and events.

## Quick Start
1. Ensure you have Java 11 or above installed on your Computer
2. Download the latest seashell.jar from [here](https://github.com/jay9645/ip.github.io/releases).
3. Copy seashell.jar to the folder you want to use as home folder for your Snom.
4. Double-click on seashell.jar to start the application. <br>

   ![Ui](Ui.png)
5. Type in the command in the text box and press Enter to execute it.
6. Refer to this guide below for details of each command.

## Features 
1. todo
2. deadline
3. event
4. list
5. done
6. delete
7. find
8. clear
9. help
10. bye

`<>` - required inputs
### 1. `todo` - Add todo task
Add a todo task to the task list

Format: `todo <task name>`

#### Usage
Example of usage: `todo read book`

Expected outcome:
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list
```

### 2. `deadline` - Add deadline task
Add a deadline task to the task list

Format: `deadline <task name> /by {date in YYYY-MM-DD format}`

#### Usage
Example of usage: `deadline return books /by 2021-02-27`

Expected outcome:
```
Added [D][✘] return books (by: Feb 27 2021) to the task list!
You now have 5 items in the task list
```

### 3. `event` - Add event task
Add a event task to the task list

Format: `event <task name> /at {date in YYYY-MM-DD format}`

#### Usage
Example of usage: `event Meeting /at 2021-03-27`

Expected outcome:
```
Added [E][✘] Meeting (at: Mar 27 2021) to the task list!
You now have 6 items in the task list
```

### 4. `list` - list tasks
List all tasks in the task list

Format: `list`

#### Usage
Example of usage: list

Expected outcome:
```
1. [T][✘] read book
2. [D][✘] return books (by: Feb 27 2021)
3. [E][✘] Meeting (at: Mar 27 2021)
```

### 5. `done` - Mark task as done
Mark the specified task at the specified index as done

Format: `done <task number>`

#### Usage
Example of usage: `done 1`

Expected outcome:
````
Nice! I've marked this task as done:
[T][✓] return books (by: Mar 27 2021)
````

### 6. `delete` - Delete a task
Delete the task at the specified index from the list

Format: `delete <task number>`

#### Usage
Example of usage: `delete 2`

Expected outcome:
````
Noted. I've removed [D][✘] return books (by: Feb 27 2021)
You now have 5 items in the task list.
````

### 7. `find` - Find tasks by name
Look for all tasks whose name contains a given key word

Format: `find <key word>`

#### Usage
Example of usage: `find book`

Expected outcome:
````
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][✘] return books (by: Feb 27 2021)
````

### 8. `clear` - Clear all tasks
Clear all tasks in the task list

Format: `clear`

#### Usage
Example of usage: `clear`

Expected outcome:
````
Task list has been cleared!
````

### 9. `help` - Show help on how to use the app
Show a list of commands and what they can do

Format: `help`

#### Usage
Example of usage: `help`

Expected outcome:
````
Welcome to Seashells! Here is a list of commands you can do and its descriptions:
todo <task name> - add a todo task
deadline <task name> /by <datetime> - add a deadline task
event <task name> /at <datetime> - add an event task
list - list out the tasks that are currently in the task list
done <task index> - mark the task at the specified index as done
delete <task index> - remove the task at the specified index from the list
clear - clears the task list
bye - exits the program
````

### 10. `bye` Exit Seashell
Close the program

Format: `bye`

#### Usage
Example of usage: `bye`

Expected outcome:
````
Bye. Hope to see you again soon!
````
