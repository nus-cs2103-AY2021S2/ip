# Moomin User Guide

By: `eksinyue`      Since: `Feb 2020` 

Hi there! I am your good friend Moomin :)

I am very good at managing tasks. Tell me what you have on your plate 
and let me help you keep track of your `todo`, `deadline` and `event`!!!

## Quick Start
* Ensure you have Java `11` or above installed in your Computer.
* Download the latest `moomin.jar` [here](https://github.com/eksinyue/ip/releases) .
* Copy the file to the folder you want to use as the home folder for your Address Book.
* Double-click the file to start the app. The GUI should appear in a few seconds.

<img src="./Ui.png" width="800"/>

## Feature List

Commands | Usage
--------|-------
`list` | Lists all tasks
`todo <taskName>` | Adds a `todo`
`deadline <taskName> /by dd/MM/yyyy HHmm` | Adds a `deadline`
`event    <taskName> /at dd/MM/yyyy HHmm` | Adds an `event`
`done <taskNumber>` | Marks the task at `taskNumber` of the list as done
`delete <taskNumber>` | Deletes task at `taskNumber` of the list
`find <keyword>` | Finds all tasks containing `keyword`
`undo` | Undo the most recent command
`bye` | Exits the application

### 1. Add a `todo`

Example: `todo submit homework`

Expected Outcome:

```
Got it. I've added this task:
[T][ ] submit homework
Now you have 1 item(s) in the list.
```

If `undo`

```
Got it. I've removed this task:
[T][ ] submit homework
Now you have 0 item(s) in the list.
```

### 2. Add a `deadline`

Example: `deadline finish project /by 29/02/2021 2359`

Expected Outcome:

```
Got it. I've added this task:
[D][ ] finish project (by: Feb 28 2021 11:59 PM)
Now you have 2 item(s) in the list.
```

If `undo`

```
Got it. I've removed this task:
[D][ ] finish project (by: Feb 28 2021 11:59 PM)
Now you have 1 item(s) in the list.
```

### 3. Add an `event`

Example: `event cny dinner /at 12/02/2022 1900`

Expected Outcome:

```
Got it. I've removed this task:
[E][ ] cny dinner (at: Feb 12 2022 07:00 PM)
Now you have 2 item(s) in the list.
```

If `undo`

```
Goodbye. Have a nice day!!
```

### 4. Mark tasks as `done`

Example: `done 1`

Expected Outcome:

```
Congratulations! You have completed this task:
[T][✘] submit homework
```

If `undo`

```
Okay! I have marked this task as not done:
[T][ ] submit homework
```

### 4. `delete` tasks

Example: `delete 1`

Expected Outcome:

```
Noted. This task has been removed:
[T][✘] submit homework
Now you have 2 item(s) in the list.
```

If `undo`

```
Noted. This task has been added back:
[T][✘] submit homework
Now you have 3 item(s) in the list.
```

### 5. `find` tasks

Example: `find dinner`

Expected Outcome:

```
Here are the matching tasks in your list:
1. [E][ ] cny dinner (at:12 Feb 2021, 07:00PM)
```

If `undo`

```
Hmm.. How do I unfind the tasks?
```

### 6. `list` all tasks

Example: `list`

Expected Outcome (with multiple existing items):

```
Here are the tasks in your list:
1. [D][ ] finish project (by:29 Feb 2021, 11:59PM)
2. [E][ ] cny dinner (at:12 Feb 2021, 07:00PM)
```

Expected Outcome (with no items):

```
It seems like there is nothing in your list.
```

If `undo`

```
You wanna see the list again?
```

### 7. `bye`

Example: `bye`

Expected Outcome:

```
Goodbye. Have a nice day!!
```

## Credits

__________
* This user guide format has been adapted from 
[address book 3](https://github.com/nus-cs2103-AY1920S2/addressbook-level3/blob/master/docs/UserGuide.adoc)
* The GUI was created with the help
  of [this JavaFX tutorial](https://se-education.org/guides/tutorials/javaFx.html)
