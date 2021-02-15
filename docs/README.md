# User Guide
A simple and *definitely non-sarcastic* guide to **Duwuke**, your friendly neighbourhood chatbot.
## Features 

### Friendly
Never have to worry about a boring chatbot experience with **Duwuke!**

### Keep track of your events and deadlines
A beautiful interface reminds you of all the things you have yet to fail at doing!

### Tag your to-do list
What better way to motivate yourself to complete tasks than adding some tags!

## Usage

### `list` - Display the current todo list

The current todo list, if any, will be displayed for easy reference.

Example of usage:

`list`

Expected Outcome:

`Here are the tasks in your list uwu:`

`1. [T][X] Watch normies enjoy their weekend while coding alone`

### `done` - Mark a task as completed

Choose any one task to mark as done, based on their numbering in the list,
and feel a sense of accomplishment.

Example of usage:

`done 1`

Expected outcome:

`Sugoi! I've marked this task as done uwu:`

`[T][X] Let down the expectations of people around you`

### `bye` - Quit the application

Entering this command closes the application.

Example of usage:

`bye`

Expected outcome:

`exit application`

### `todo` - Input a new task 

Adds a new task to the list. 

Example of usage:

`todo Envy the success of others while not performing self-improvement`

Expected outcome:

`Hai. I've added this task:`

`[T][] Envy the success of others while not performing self-improvement`

`Now you have 1 task(s) in the list uwu`

### `deadline` - Input a new deadline

Adds a new deadline into the list, with a time to complete the task by.

Time should be provided in the YYYY-MM-DD HH:MM format.

If no time is given, it is automatically assumed to be 0000 hours.

Example of usage:

`deadline Submit 2103 iP /by 2021-02-19 23:59`

Expected outcome:

`Hai. I've added this task:`

`[D][] Submit 2103 iP (by:Feb 19 2021 2359PM)`

`Now you have 1 task(s) in the list uwu`

### `event` - Input a new event

Adds a new event into the list, with a time for when it occurs.

Time should be provided in the YYYY-MM-DD HH:MM format.

If no time is given, it is automatically assumed to be 0000 hours.

Example of usage:

`event Watch movie alone /at 2021-02-14 18:00`

Expected outcome:

`Hai. I've added this task:`

`[E][] Watch movie alone (at:Feb 14 2021 0600PM)`

`Now you have 1 task(s) in the list uwu`

### `delete` - Deletes a task

Delete a selected task from the list, based on their numbering in the list .
This action is irreversible.

Example of usage:

`delete 1`

Expected outcome:

`Noted. I've removed this task uwu:`

`[T][] Lie down and cry`

`Now you have 0 task(s) in the list uwu`

### `find` - find a related task

Searches through the list and returns any tasks containing the keyword.

Example of usage:

`find milk`

Expected outcome:

`Here are the matching tasks in your list uwu:`

`1. [T][X] buy milk`

`2. [T][] finish milk before it expires`

### `tag` - Add a tag to any task

Adds a #tag to any specified task, based on their numbering in the list.

Example of usage: 

`tag 1 hopefully`

Expected outcome:

`I see! What an interesting tag uwu`

`[T][] Achieve something in life #hopefully`
