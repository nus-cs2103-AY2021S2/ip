# Duke User Guide

**Duke** is a desktop application to help users keep track of tasks, and make notes. It functions using a GUI (Graphical
User Interface) to help those potential users be at ease while using Duke.

### Basic Info

**Duke** allows the user to key in three types of tasks: **todo**, **deadlines** and **events**, and also be able to
manipulate them.

1. **Todo** tasks are those that you expect to finish soon, and so does not have an end date.
2. **Deadlines** are tasks that must have an end date, whereas it is optional to add time.
3. **Events** are tasks that must have an end date, and although the time here is optional, it is recommended that you
   key in the time.

**Note:** The time for both deadline and events must be entered in 24-hour format.

Duke also allows the user to keep track of **notes**, which are separately stored compared to tasks. Notes are flexible
in that you can key in anything, search for them using keywords, and be able to delete them if you know their index.

### Setting Up

1. Create a new folder in your PC.
2. Download the latest jar file version into that folder.
3. You need atleast Java 11 to run Duke.
4. Open and enter in your terminal the following command: `java -jar duke.jar`. Alternatively, you could simply double
   click the jar file.

### List of Features

1. Get list of all tasks
2. Save Entries
3. Add todo task
4. Add deadline task
5. Add event task
6. Mark a task as done.
7. Delete a certain task.
8. Find tasks matching keyword.
9. Get reminders.
10. Add notes.
11. Delete a certain note.
12. Find Notes.
13. Get List of Notes.
14. Exit Duke.

**Note:** Given the nature of the chatbot, the above features are divided into three categories:

1. Main Features: Which deal with the chatbot basic functions that are not directly related to tasks or notes.
2. Task Features: Which deal with the tasks.
3. Note Features: Which deal with the notes.

## Duke Features

### Main Features

#### Feature 1

Save entries

##### Usage

##### `save` - key in `save`

Saves all the data entered so far

Example of usage:

`save`

Expected outcome:

```
Your entries have been saved :)
```

#### Feature 2

Exit Duke

##### Usage

##### `bye` or `exit` - key in `bye` or `exit`

Saves all the data and exits duke

Example of usage:

`bye` or `exit`

Expected outcome:

```
Goodbye for now.
Hope to see you soon!
```

### Task Features

#### Feature 1

Get the list of tasks

##### Usage

##### `list` - key in `list`

Returns the list of tasks

Example of usage:

`list`

Expected outcome:

```
Contents:
1. [D][-] hw (by: Mar 18 2021) 
2. [E][-] meeting (at: Mar 22 2021 04:00PM)
3. [T][X] run
```

Returns the list of all the tasks and they are numbered starting from 1.

#### Feature 2

add todo task

##### Usage

##### `todo` - key in `todo <task description>`

Example of Usage:

`todo read book`

Expected outcome:

```
Got it! Added: 
[T][-] read book
Now you have 6 items in your list
```

#### Feature 3

add deadline task

##### Usage

##### `deadline` - key in `deadline <task description> /by <Date: Format(yyyy-mm-dd)> <Time: Format(hh:mm:ss)>`

Example of Usage:

`deadline finish lab /by 2021-03-15 18:00:00`

Expected outcome:

```
Got it! Added: 
[D][-] finish lab (by: Mar 15 2021 6:00PM)
Now you have 7 items in your list
```

##### Note:

1. You may not key in the time, and the task would still be added.

2. Time is in 24-hour format.

#### Feature 4

add event task

##### Usage

##### `event` - key in `event <task description> /at <Date: Format(yyyy-mm-dd)> <Time: Format(hh:mm:ss)>`

Example of Usage:

`event attend meeting /at 2021-03-15 18:00:00`

Expected outcome:

```
Got it! Added: 
[E][-] attend meeting (at: Mar 15 2021 6:00PM)
Now you have 8 items in your list
```

##### Note:

1. You may not key in the time, and the task would still be added.

2. Time is in 24-hour format.

#### Feature 5

Mark a task as done

##### Usage

##### `done` - key in `done <index>`

Index must be greater than 0

Example of Usage:

`done 6`

Expected outcome:

```
Task 6 is complete:
[T][X] read book
```

Similar output is expected for other type of tasks

#### Feature 6

Remove a task

##### Usage

##### `delete` or `remove` - key in `delete <index>` or `remove <index>`

Index must be greater than 0

Example of Usage:

`delete 6` or `remove 6`

Expected outcome:

```
Noted. Item removed:
[T][X] read book
Now you have 6 items in your list
```

Similar output is expected for other type of tasks

#### Feature 7

Find matching tasks based on keyword

##### Usage

##### `find` or `search` - key in `find <keyword>` or `search <keyword>`

Example of Usage:

`find meeting` or `search meeting`

Expected outcome:

```
We have found the following 2 item(s)
Contents:
1. [E][X] meeting (at: Mar 18 2021) 
2. [E][-] meeting (at: Mar 22 2021 04:00PM)
```

The output depends on the number of matching tasks found

#### Feature 8

Get tasks that are currently due

##### Usage

##### `dues` or `reminders` - key in `dues` or `reminders`

Example of Usage:

`dues` or `reminders`

Expected outcome:

```
These are the due tasks
Contents:
1. [D][-] hw (by: Mar 18 2021) 
2. [E][-] meeting (at: Mar 22 2021 04:00PM)
```

The output depends on the number of matching tasks found

### Note Features

#### Feature 1

Get the list of notes

##### Usage

##### `notes` - key in `notes`

Returns the list of notes

Example of usage:

`notes`

Expected outcome:

```
Contents:
1. watch movies
2. play video games
```

Returns the list of all the notes and they are numbered starting from 1.

#### Feature 2

add note

##### Usage

##### `add` - key in `add <note description>`

Example of Usage:

`add play video games`

Expected outcome:

```
Got it! Added: 
play video games
Now you have 6 items in your list
```

#### Feature 3

Remove a note

##### Usage

##### `delete-note` or `remove-note` - key in `delete-note <index>` or `remove-note <index>`

Index must be greater than 0

Example of Usage:

`delete-note 6` or `remove-note 6`

Expected outcome:

```
Noted. Item removed:
play video games
Now you have 5 items in your list
```

#### Feature 4

Find matching notes based on keyword

##### Usage

##### `find-note` or `search-note` - key in `find-note <keyword>` or `search-note <keyword>`

Example of Usage:

`find-note movies` or `search-note movies`

Expected outcome:

```
We have found the following 2 item(s)
Contents:
1. watch movies
2. go to the movie theatre
```

The size of the list depends on the number of matches

### Summary of features:

1. Save entries - `save`
2. Exit duke - `bye` or `exit`
3. Get list of all tasks - `list`
4. Add todo task - `todo <task description>`
5. Add deadline task - `deadline <task description> /by <Date: Format(yyyy-mm-dd)> <Time: Format(hh:mm:ss)>`
6. Add event task - `event <task description> /at <Date: Format(yyyy-mm-dd)> <Time: Format(hh:mm:ss)>`
7. Mark task as done - `done <index>`
8. Remove a task - `delete <index>` or `remove <index>`
9. Find tasks matching a keyword - `find <keyword>` or `search <keyword>`
10. Get tasks that are due - `dues` or `reminders`
11. Get list of notes - `notes`
12. Add notes - `add <note description>`
13. Delete a note - `delete-note <index>` or `remove-note <index>`
14. Find notes based on keyword - `find-note <keyword>` or `search-note <keyword>`

#### Reminder:

- Remember to key in the commands in the exact format provided. Especially for date and time.
- For commands where you need to key in index, make sure that index is always greater than 0.

#### The data is not saving:

Duke saves all your entries automatically, however in rare cases, there might be storage problems. This would most
likely happen if you enter the commands in the wrong format and somehow the task is added in the list of tasks but not
stored. Hence, Duke may not be able to save tasks afterwards. When this happens, remove all the contents from the
storage files namely DukeTasks.txt and DukeNotes.txt, and start Duke again. The storage files should be created in the
same folder in which Duke was downloaded. Then it should run properly again. It is also worth noting that exiting duke
using the `bye` or `exit` command is the safest way, in order to ensure that there are no discrepancies in storage.

## Hope you have a wonderful time using Duke!

