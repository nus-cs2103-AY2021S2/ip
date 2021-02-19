# User Guide

## Features 

### Basic Add/Delete/Done features 

Simple addition, deletion, of task, and the ability to mark them as done

### Continuous Saving

Duke will continuously save your current list of tasks as you work.

### Search tasks

Search tasks with a intuitive `search` command.

### Different types of tasks to handle different time information

Simple `todo`, free-form `event`, and easy `deadline`

## Usage


### `todo`/`event`/`deadline` - Add tasks

Adds a task with the corresponding type to the end of the list 

Usage:

```
todo (Description)
event (Description) /at (Event Time)
deadline (Description) /by (Deadline*)
```
\* The deadline format has special syntax. See below for details.

Example of usage: 

`event blah /at end of the world`

Expected response:

```
The following task has been added:
Entry 1|[E][ ]: blah (Event Time: end of the world)
```
Example of usage: 

`todo See the World`

Expected response:

```
The following task has been added:
Entry 2|[T][ ]: See the World
```

Example of usage: 

`deadline Surprise /by 20 02 20201`

Expected response:

```
The following task has been added:
Entry 3|[D][ ]: Surprise (Deadline: February 20, 2021)
```

#### Deadline date format

Keywords such as `yesterday`, `today`/`now`, and `tmr`/`tomorrow`, will create a deadline with the appropriate date.

Days of the week from `Monday`, `Tuesday` ... `Sunday`, will create a deadline for the next date with that day, excluding today.

Eg: Today is Friday, so `Friday` will result in a date exactly one week from now.

There is also absolute dates, which must strictly follow the format of `DD MM YYYY`. So `07 03 2020` will be accepted, but **not** `7 03 2020`, `07 3 2020`, or `07 03 20`. 


### `ls`/`list` - List all tasks

List all tasks in order, showing details and "done" status.

Example of usage: 

`list`

Example outcome:

```
Entry 1|[E][*]: blah (Event Time: end of the world)
Entry 2|[T][ ]: See the World
Entry 3|[D][ ]: Surprise (Deadline: February 20, 2021)
```

### `rm`/`del`/`remove`/`delete` - Remove task

Removes a task based on its list index.

Example of usage: 

`rm 2`

Expected outcome:

```
The following Task has been deleted:
Entry 3|[D][ ]: Surprise (Deadline: February 20, 2021)
```

### `done` - Mark task as done

Marks a task as completed.

Example of usage: 

`done 2`

Expected outcome:

```
The following task is now marked as done:
Entry 2|[T][*]: See the World
```

The resultant list should be as follows:
```
Entry 1|[E][*]: blah (Event Time: end of the world)
Entry 2|[T][*]: See the World
```


### `find`/`search` - Describe action

Searches tasks based on a search string

Suppose our current list looks like this:

```
Entry 1|[E][*]: blah (Event Time: end of the world)
Entry 2|[T][*]: See the World
Entry 3|[T][ ]: see tokyo
```

Example of usage: 
`find see`

Expected outcome:

```
Matching Task(s):
Entry 2|[T][*]: See the World
Entry 3|[T][ ]: see tokyo
```
#### More detailed search behaviour:

##### Default search behaviour
By default, Duke matches from the beginning of each word in a task, so `find he` would match "**He**ro of the sea" but not "T**he** tales of Genji"

This is *disabled* if you have a multi word search, like `find he tale`, which triggers a substring search, and would successfully match "T**he tale**s of Genji"

##### Case insensitivity
When searching in lowercase, Eg: `find see`, we ignore case and match both "**see** the world", and "**See** Tokyo"

To disable this, search with at least one capital letter, Eg: `find See`, which will match only "**See** Tokyo". 

Note that a single capital letter will make the entire search case sensitive. So `find See`, will **not** match "**SEE** HOW TO GO FAST"

### `exit`/`bye` - Exit duke

Exits duke.

Example of usage: 

`exit`

Expected outcome:

Duke exits. :)