# User Guide

## Features 

### Add tasks to be done with Todo
Allows users to add a task to be done.

### Add tasks with deadlines with Deadline
Allows users to add a task with a deadline.

### Add events with Event
Allows users to add an event with a start and end date.

### See all tasks with List
Allows users to see all tasks

### Remove tasks with Delete
Users can delete tasks

### Mark Events as Complete with Done
Users can mark when events are completed.

### Find tasks with matching names with Find
Users can find tasks with matching words

## Usage

### `todo` - Add task to be done

Tasks without date or time can be added to the list.

Example of usage: 

`todo read books`

Expected outcome:

`Added to list:`

`[T] [] todo read books`

`You now have 1 tasks`

### `deadline` - Add task to be done with a deadline

Tasks with date and time to be added to the list

Example of usage:

`deadline read books /by yyyy-MM-dd (hhmm)`

Expected outcome:

`Added to list:`

`[D] [] read books by: 28 Feb 2021, 12:00:00 PM`

`You now have 1 tasks`

### `event` - Add event with start and end date time

Event with start and end date and time to be added to the list

Example of usage:

`event lecture /at yyyy-MM-dd hhmm /to yyyy-MM-dd hhmm`

Expected outcome:

`Added to list:`

`[D] [] lecture at: 28 Feb 2021, 12:00:00 PM to 1 Mar 2021, 12:00:00 PM`

`You now have 1 tasks`

### `list` - See all available tasks

All tasks would be shown

Example of usage:

`list`

Expected outcome:

`Here's your list of tasks`

`1. [T] [] todo read books`

### `delete` - Delete one or more tasks

Remove unwanted tasks

Example of usage:

`delete 1 (2)`

Expected outcome:

`Got it! I've removed the task(s) for you`

`You now have 0 tasks`

### `done` - Mark task as complete

Tasks will be indicated to be completed with done

Example of usage:

`done 1 (2)`

Expected outcome:

`Good job! I've marked it as completed for you as well!`

### `find` - Find tasks with matching words

Tasks with specified words will be added

Example of usage:

`find read`

Expected outcome:

`Here's the tasks I found with your search term!`

`[T] [] todo read books`