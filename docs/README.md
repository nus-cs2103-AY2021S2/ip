# User Guide for Mantaro (Chatbot)

## Features

### Manages your tasks
You can add, retrieve and delete tasks of different types:
    
1. To-do - A basic task that only has description
2. Deadline - A task that has a description and a due date time
3. Event - A task that has a description, a start date time and a end date time

### List your tasks
List out all tracked tasks in an orderly manner and indicates which have been completed.

### Search through your tasks
Generate a list of tracked tasks that matches the query keyword and/or the date time that it is happening on.

## Usage

### `todo` - Add a to-do task
Add a task that only has description.

Example of usage: 
```
todo complete form
```

Expected outcome:
```
Got it meow, I've added this task:
  [T][] complete form
Now you have 1 task in the list
```

### `deadline` - Add a deadline task
Add a task that has a description and due date time.

Example of usage:
```
deadline hand in form /by 11-02-2021 11PM
```

Expected outcome:
```
Got it meow, I've added this task:
  [D][] hand in form (By: 11 Feb, Thu 11PM)
Now you have 2 tasks in the list
```

### `event` - Add an event task
Add a task that has a description, start date time, and end date time.

Example of usage:
```
event prom night /start 11-02-2021 6PM /end 11-02-2021 9PM
```
Expected outcome:
```
Got it meow, I've added this task:
  [E][] prom night (Start: 11 Feb, Thu 6PM | End: 11 Feb, Thu 9PM)
Now you have 3 tasks in the list
```

### `done` - Complete a task
Mark a task as completed with an X.

Example of usage:
```
done 2
```
Expected outcome:
```
Good job meow, I've marked this task as done:
2.[D][X] hand in form (By: 11 Feb, Thu, 11PM)
```
### `delete` - Remove a task
Remove a task from being tracked by the chatbot.

Example of usage:
```
delete 3
```
Expected outcome:
```
Noted meow, I've removed this task:
  [E][] hand in form (Start: 11 Feb, Thu 6PM | End: 11 Feb, Thu 9PM)
Now you have 6 tasks in the list
```

### `list` - List out all tracked tasks
Print out all tracked tasks.

Example of usage: 
```
list
```
Expected outcome:
```
Meow, here are the tasks in your list:
1.[D][] appointment (By: 01 Dec, Wed 10AM)
```

### `find` - Searches through tracked tasks

Go through all tracked tasks and print any that matches the given keyword and/or the date time that it is happening.

Example of usage: 

*Keyword only*
```
find appointment
```
*Date time only*
```
find /on 01-12-2021 10AM
```
*Keyword and date time*
```
find appointment /on 01-12-2021 10AM
```
Expected outcome:
```
Meow, here are your matching tasks in your list:
1.[D][] appointment (By: 01 Dec, Wed 10AM)`
```

### `exit` - Close the application

Shutdown the chatbot and close the application.

Example of usage:
```
bye
```