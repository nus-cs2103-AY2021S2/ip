# User Guide
This is Duke's user guide. It is a basic scheduling app that is easy to use.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. From www.github.com/winniehyx/ip/releases, download the latest duke.jar .

2. For Windows, open the jar file to start the app. The GUI for Duke would appear. For Mac, type `java -jar duke.jar` into the terminal to run the file.

4. Refer to `Features` for the commands you can type in the command box. 
   
5.Press Enter on your keyboard to execute the commands.

Image of Ui:
https://github.com/Winniehyx/ip/blob/master/docs/Ui.png

## List of Features 
These are the following features of Duke:
### Add tasks
Add task into the tasklist.
### Delete tasks
Deletes the task from the tasklist.
### List tasks
List all the task in the tasklist.
### Mark task as done
Marks the task as done.
### Tag tasks
Tag the task added.
### Find tag/task
Display the list of task that has the tag/task in the tasklist.

## Categories of tasks
There are 3 categories of tasks:
1) Todo represented by
   [T][ ] find book
2) Deadline
   [D][ ] return book (by: 10/02/2021 1003)
3) Event
   [E][ ] project meeting (at: 10/02/2021 2000)

## Notes about commands
When there are extra parameters behind the function 'list' , the program is smart enough to know that the user wants the 'list' function so no error would be thrown and the tasklist would be displayed.

The date format should be written in the format "d/M/yyyy HHmm", else an error would be thrown.

Tag should be the last argument.
   
# Saving of commands
Commands would be saved into a file once the 'bye' command is typed into the app. Data will be saved in the directory where you saved the jar file, under /data/duke.txt . A new file would be formed if there are no existing file. If there exists an existing file, the file would be used. 

## Usage

### 'todo'
Adds a todo task into the tasklist.

Example of usage: 

`todo borrow book`

Expected outcome:

`[T][X] borrow book`

### 'event'
Adds an event task into the tasklist. It can work with only the description, the same way as todo.
The time input for /at can be a duration or a time itself.

Example of usage:

`event project meeting /at 10/02/2021 1030-1200`
`event birthday party /at 15/02/2021 1000`

Expected outcome:

`[T][X] event project meeting (at: 10/02/2021 1030-1200)`
`[T][X] birthday party (at: 15/02/2021 1000)`

### 'deadline'
Adds an deadline task into the tasklist. It can work with only the description, the same way as todo.

Example of usage:

`deadline submission /by 11/02/2021 1000`

Expected outcome:

`[D][X] submission (by: 11/02/2021 1000`

### 'delete'
Delete the task corresponding to that number in the tasklist.

Example of usage:
`delete 2`

Expected outcome:
The second task in the list (if present) would be deleted.

### 'done'
Marks the task corresponding to that number in the tasklist as done with a tick.
Status changes from [X] to [✔]

Example of usage:
`done 2`

Expected outcome:
The second task in the list (if present) would be marked as done.
`[T][✔] event project meeting (at: 10/02/2021 1030)`

### 'list'
Lists all the tasks in the tasklist.

Example of usage:
`list`

Expected outcome:
The list of task in the order that they are added would be displayed.

### 'tag'
Tags a specific task in the tasklist. Tag should come after at or by.

Example of usage:
`todo buy shoes /tag purchase`
`deadline join archery /by 10/02/2021 1000 /tag club`

Expected outcome:
`todo buy shoes (tag: purchase)`
`deadline join archery (by: 10/02/2021 1000 (tag: club)`

### 'find'
Finds the word in the description or the tag from the tasklist.

Example of usage:
`find purchase`

Expected outcome:
The tasks with the word `purchase` in it would be displayed.

### 'bye'
Saves the tasks keyed in into the /data/duke.txt . Returns bye and terminates the program.

Example of usage:
`bye`

Expected outcome:
`bye`
Program will terminate.









