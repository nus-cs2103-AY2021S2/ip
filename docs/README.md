# User Guide

## Features 

#### Addition of Todo's, Events and Deadlines
Add a new task of either type todo (has no associated date), events or deadlines (both of which has an associated 
date with them).

##### Marking items as Done
After a certain task is done, just mark it as done!

##### Find using Keyword
Not sure where a particular event is? Just find using a keyword, and all task descriptions which has that word will 
be returned. 

# Usage



## Adding TODO

syntax: `todo <task description>`

Adds a new task of type todo, has no explicit Date associated with it. 

Example of usage: 

`todo Help mom finish chores`

Expected outcome:

`- [T][X] Help mom finish chores`

### Adding EVENT

syntax: `event <task description> /at <Date: format : YYYY-MM-DD>`

Adds a new task of type event, has a Date associated with it.

Example of usage:

`event Go to Wedding /at 2020-03-29`

Expected outcome:

`- [E][X] Go to Wedding(at: 29 : March : 2020)`

## Adding DEADLINE

syntax: `deadline <task description> /by <Date: format : YYYY-MM-DD>`

Adds a new task of type deadline, has a Date associated with it.

Example of usage:

`deadline finish homework /by 2020-03-29`

Expected outcome:

`- [D][X] finish homework(by: 29 : March : 2020)`

## Mark As DONE

syntax : `done <task no.>`

Marks a task as done. However, does not remove the task. 

Example of usage:

`done 1`

Expected outcome: 

`- [T][tick] help mom finish chores`

## List all tasks

syntax: `list`

List all tasks (whether done or not done).

Example of Usage :

`list`

Expected output :

`- [T][tick] help mom finish chores`   
`- [E][X] Go to Wedding(at: 29 : March : 2020)`  
`- [D][X] finish homework(by: 29 : March : 2020)`

## Find using Keyword

syntax : `find <Keyword>`

Find all tasks containing the keyword. However, task no. won't be the same.

Example usage:

`find finish`

Expected output:

`- [T][tick] help mom finish chores`   
`- [D][X] finish homework(by: 29 : March : 2020)`    

## Delete 

syntax : `delete <task no.>`

Deletes the task at the given task no. 

Example usage : 

`delete 2`
`list` 

Expected output : 

`- [T][tick] help mom finish chores`   
`- [D][X] finish homework(by: 29 : March : 2020)`    















