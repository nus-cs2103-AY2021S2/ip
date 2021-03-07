# User Guide
JDK 11
## Features 

### Command based Response
Bot that takes in a list of tasks to do and stores it in a list. 
Tasks that are
done are marked as done with a cross. 
#### Commands
list - lists all existing tasks

todo, deadline, event {some task} - creates the respective task, date in yyyy-MM-dd

delete {index} - deletes the respective task

find {keyword} {search type (optional)} - retrieves matching task according to keyword 
and optional search parameter (p, pi, f)

done {index} - marks task as done

## Usage

### `Keyword` `task to be added` `date if present` 

Example of usage: 

### Adding a todo task Read book

`todo Read book`

Expected outcome:

Retrieving the list by typing the string list as input will give:
Here are the tasks in your list:

### Marking the todo task as done

1.[T][] Read book

`done 1`

Expected Outcome:

Nice! I've marked this task as done:

1.[T][X] Read book

### Deleting the todo task

`delete 1`

Noted. I've removed this task:
    1.[T][X] Read book
Now you have 0 tasks in your list.

### Creating a deadline

`deadline return book /by 2019-06-20`

Got it. I've added this task: [D][] return book /by 2019-06-20
Now you have 1 tasks in the list

### Listing the tasks

`list`

Here are the tasks in your list:
1. [D][] return book (by: Jun 20 2019)
