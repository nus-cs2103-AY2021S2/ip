# MetaDuke User Guide
Current version: v0.2

MetaDuke is a to-do desktop app for managing and maintaining a to-do list via a Command Line Interface (CLI).

## Features 

### list
Displays a list of all the tasks on your to-do list.  
Usage: `list`  

### todo
Adds a new todo task to your to-do list.  
Usage: `todo [description]`  
Example: `todo Return book`  

### event
Adds a new event task to your to-do list.  
Usage: `event [description] /on [date, DD/MM/YYYY]`  
Example: `event Physics exam /on 20/04/2021`  

### deadline
Adds a new deadline task to your to-do list.  
Usage: `deadline [description] /by [date, DD/MM/YYYY]`  
Example: `deadline Homework /by 20/02/2021`  

### done
Marks a task as done.  
Usage: `done [index numbers of tasks]`  
Examples: `done 3`, `done 1 3 4`  
Aliases: `do`, `finish`, `finished`, `complete`, `completed`  

### delete
Removes a task from the list.  
Usage: `done [index numbers of tasks]`  
Examples: `delete 3`, `delete 1 3 4`  
Aliases: `remove`  

### find
Returns a list of tasks from the list that match the search term.  
Usage: `find [search term]`  
Example: `find book`  
Aliases: `search`  