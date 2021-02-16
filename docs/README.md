# User Guide
JDK 11
## Features 

### Command based Response
Bot that takes in a list of tasks to do and stores it in a list. 
Tasks that are
done are marked as done with a cross. 
####Commands
list - lists all existing tasks

todo, deadline, event {some task} - creates the respective task

delete {index} - deletes the respective task

find {keyword} {search type (optional)} - retrieves matching task according to keyword 
and optional search parameter (p, pi, f)

done {index} - marks task as done

## Usage

### `Keyword` - Expected Outcome

Describe command and its outcome.
Example of usage: 

`todo Read book`

Expected outcome:

Retrieving the list by typing the string list as input will give:
Here are the tasks in your list:

1.[T][] Read book

`done 1`

Expected Outcome:

Nice! I've marked this task as done:

1.[T][X] Read book

`delete 1`

Noted. I've removed this task:
    1.[T][X] Read book
Now you have 0 tasks in your list.


