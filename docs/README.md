# Duke User Guide

**Duke** is a desktop application to help users keep track of tasks, and make notes. It functions using a GUI (Graphical
User Interface) to help those potential users be at ease while using Duke.

## Basic Info

**Duke** allows the user to key in three types of tasks: **todo**, **deadlines** and **events**, and also be able to
manipulate them.

1. **Todo** tasks are those that you expect to finish soon, and so does not have an end date.
2. **Deadlines** are tasks that must have an end date, whereas it is optional to add time.
3. **Events** are tasks that must have an end date, and although the time here is optional, it is recommended that you
   key in the time.

**Note:** The time for both deadline and events are in 24-hour format.

Duke also allows the user to keep track of **notes**, which are separately stored compared to tasks. Notes are flexible
in that you can key in anything, search for them using keywords, and be able to delete them if you know their index.

## Setting Up

1. Download the latest jar file version
2. Open and enter in your terminal the following command: `java -jar duke.jar`

## Basic Features

### Feature 1

Save entries

### Usage

### `save` - key in `save`

Saves all the data entered so far

Example of usage:

`save`

Expected outcome:

```
Your entries have been saved :)
```

### Feature 2

Exit Duke

### Usage

### `bye` or `exit` - key in `bye` or `exit`

Saves all the data and exits duke

Example of usage:

`bye` or `exit`

Expected outcome:

```
Goodbye for now.
Hope to see you soon!
```

## Task Features

### Feature 1

Get the list of tasks

### Usage

### `list` - Key in `list`

Returns the list of tasks

Example of usage:

`list`

Expected outcome:

```
Contents:
1. <task>
2. <task>
    -
    -
```

Returns the list of all the tasks and they are numbered starting from 1.

### Feature 2

add todo task

### Usage

### `todo` - Key in `todo <task description>`

Example of Usage:

`todo read book`

Expected outcome:

```
Got it! Added: 
[T][-] read book
Now you have 6 items in your list
```

#### Note:

Task description must be two words. If your task description is one word, then type `-` in place of first word, and the
description in place of the second one. For example: `todo - run`.

### Feature 3

add deadline task

### Usage

### `deadline` - Key in `deadline <task description> /by <Date: Format(yyyy-mm-dd)> <Time: Format(hh:mm:ss)>`

Example of Usage:

`deadline finish lab /by 2021-03-15 18:00:00`

Expected outcome:

```
Got it! Added: 
[D][-] finish lab (by: Mar 15 2021 6:00PM)
Now you have 7 items in your list
```

#### Note:

1. Task description must be two words. If your task description is one word, then type `-` in place of first word, and
   the description in place of the second one. For example: `deadline - lab /by 2021-03-15 18:00:00`.

2. Also you may not key in the time, and the task would still be added.

3. Time is in 24-hour format.

### Feature 4

add event task

### Usage

### `event` - Key in `event <task description> /at <Date: Format(yyyy-mm-dd)> <Time: Format(hh:mm:ss)>`

Example of Usage:

`event attend meeting /at 2021-03-15 18:00:00`

Expected outcome:

```
Got it! Added: 
[E][-] attend meeting (at: Mar 15 2021 6:00PM)
Now you have 8 items in your list
```

#### Note:

1. Task description must be two words. If your task description is one word, then type `-` in place of first word, and
   the description in place of the second one. For example: `event - meeting /at 2021-03-15 18:00:00`.

2. Also you may not key in the time, and the task would still be added.

3. Time is in 24-hour format.

### Feature 5

Mark a task as done

### Usage

### `done` - Key in `done <index>`

Example of Usage:

`done 6`

Expected outcome:

```
Task 6 is complete:
[T][X] read book
```

Similar output is expected for other type of tasks

### Feature 6

Remove a task

### Usage

### `delete` or `remove` - Key in `delete <index>` or `remove <index>`

Example of Usage:

`remove 6`

Expected outcome:

```
Noted. Item removed:
[T][X] read book
Now you have 6 items in your list
```

Similar output is expected for other type of tasks

### Feature 7

Find matching tasks based on keyword

### Usage

### `find` or `search` - Key in `find <keyword>` or `search <keyword>`

Example of Usage:

`find meeting`

Expected outcome:

```
We have found the following 2 item(s)
Contents:
1. <task>
2. <task>
```

The output depends on the number of matching tasks found

### Feature 8

Get tasks that are currently due

### Usage

### `dues` or `reminders` - Key in `dues` or `reminders`

Example of Usage:

`reminders`

Expected outcome:

```
These are the due tasks
Contents:
1. <task>
2. <task>
    -
    -
```

The output depends on the number of matching tasks found

## Note Features

### Feature 1

Get the list of notes

### Usage

### `notes` - Simply type in `notes`

Returns the list of tasks

Example of usage:

`notes`

Expected outcome:

```
Contents:
1. <task>
2. <task>
    -
    -
```

Returns the list of all the notes and they are numbered starting from 1.

### Feature 2

add note

### Usage

### `add` - Key in `add <note description>`

Example of Usage:

`add play video games`

Expected outcome:

```
Got it! Added: 
play video games
Now you have 6 items in your list
```

### Feature 3

Remove a note

### Usage

### `delete-note` or `remove-note` - Key in `delete-note <index>` or `remove-note <index>`

Example of Usage:

`remove-note 6`

Expected outcome:

```
Noted. Item removed:
play video games
Now you have 5 items in your list
```

### Feature 4

Find matching notes based on keyword

### Usage

### `find-note` or `search-note` - Key in `find-note <keyword>` or `search-note <keyword>`

Example of Usage:

`find-note movies`

Expected outcome:

```
We have found the following 2 item(s)
Contents:
1. <note>
2. <note>
```

The size of the list depends on the number of matches

## Summary of features:

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

### Reminder:

After working on duke, make sure to key in the **exit** command (`bye` or `exit`) or **save** command (`save`) in order
to **save** your entries.

## Hope you have a wonderful time using Duke!

