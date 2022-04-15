# User Guide

## Features 

#### Add a task to do

#### Add an event to attend

#### Add a task with a deadline

#### Save planner

#### Mark tasks as completed

#### Delete tasks

## Usage

### `todo` - adds a todo task 

Adds a task to the list. 

Example: `todo sleep`

Expected outcome:

`Got it. I've added this task:`

`       [T][ ] sleep`

`   now you have 1 task in the list.`
________
### `event` adds a event task `/at` adds date and time 

Adds an event with date and time to the list

Example: `event mary's wedding /at 14/02/2021 1800`

Expected outcome:

`Got it. I've added this task:`

`       [E][ ] mary's wedding (at: 14 Feb 2021 06:00pm`

`   now you have 2 task in the list.`
________
### `deadline` adds a deadline task `/by` adds date and time

Adds a deadline with date and time to the list

Example: `event cs2105 assignment 1 /by 24/02/2021 2359`

Expected outcome:

`Got it. I've added this task:`

`       [D][ ] cs2105 assignment 1 (at: 14 Feb 2021 11:59pm)`

`   now you have 3 task in the list.`
________
### `delete` deletes a task at `index` 

Deletes a task at index given

Example:
`delete 3`

Expected outcome:

`Got it. I've removed this task:`

`       [D][ ] cs2105 assignment 1 (at: 14 Feb 2021 11:59pm)`

`   now you have 2 task in the list.`
________
### `list` -list all current tasks

Example: `list`

Expected outcome:

`Got it. I've removed this task:`

`       [T][ ] sleep`

`       [E][ ] mary's wedding (at: 14 Feb 2021 06:00pm`

`   now you have 2 task in the list.`
________
### `done` mark complete task at `index`

Example: `list`

Expected outcome:

`Bye! Hope to see you `
________
### `bye` - saves all tasks in the list

Expected outcome:

`Bye! Hope to see you `

![User interface](Ui.png)
