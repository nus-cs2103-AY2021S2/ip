
# User Guide
Duke is an interactive chatbot that stores your task

## Features
`Below are the basic features of Duke.`

### Feature 1: todo
Adds a ToDo task to your list

## Usage
todo "description"

Example of usage:
eg todo Run

Adds a ToDo task with description "Run" to your list without any tag

eg todo Carnival fun

Adds a ToDo task with description "Carnival" to your list with a tag "fun"

Expected outcome:
```
Got it. I've added this task:
[T][Incompleted]Run #
Now you have 1 tasks in the list.

Got it. I've added this task:
[T][Incompleted]Carnival #fun
Now you have 2 tasks in the list.
```
### Feature 2: event
Adds a Event task to your list

## Usage
event "description" /at "datetime"

Example of usage:
eg event game release /at Mon 2-4pm

Adds an Event task with description "game release"
at "Mon 2-4pm" to your list

Expected outcome:
```
Got it. I've added this task:
[E][Incompleted] game release  (at:  Mon 2-4pm)
Now you have 3 tasks in the list.
```

### Feature 3: deadline
Adds a Deadline task to your list

## Usage
deadline "description" /by "date"

Example of usage:
eg deadline submit file /by 2020-02-21

Adds a Deadline task with description "submit file"
by "2020-02-21" to your list

Expected outcome:
```
Got it. I've added this task:
[D][Incompleted] submit file  (by: Feb 21 2020)
Now you have 4 tasks in the list.
```

### Feature 4: list
Shows you your tasks in the list

## Usage
list

Example of usage:
eg list

Displays your task in the sequence you have added to the list

Expected outcome:
```
Here are the tasks in your list:
1. [T][Incompleted]Run #
2. [E][Incompleted] game release  (at:  Mon 2-4pm)
3. [D][Incompleted] submit file  (by: Feb 21 2020)
```

### Feature 5: done
Mark a task as done

## Usage
done "index"

Example of usage:
eg done 1

Mark the first task in your list as done

Expected outcome:
```
Nice! I've marked this task as done:
[T][Done]Run #
```

### Feature 6: delete
Delete a task in your list

## Usage
delete "index"

Example of usage:
eg delete 1

Delete the first task in your list as done

Expected outcome:
```
Noted. I've removed this task:
[T][Done]Run #
Now you have 2 tasks in the list.
```

### Feature 7: find
Find tasks that contain the keyword in your list

## Usage
find "keyword"

Example of usage:
eg find game

Find tasks that contains the word "game" in your list

Expected outcome:
```
Here are the matching tasks in your list:
[E][Incompleted] game release  (at:  Mon 2-4pm)
```

### Feature 8: bye
Ends Duke operations

## Usage
bye

Example of usage:
bye

Expected outcome:
```
Thanks for using Duke.Hope to see you again!
```









    