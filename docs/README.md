# User Guide
The Oracle is program with such insight into human psychology that she has clairvoyant abilities. 
She was initially created to interpret aspects of the human psyche and was responsible for the key principles 
behind the third "current" version of the Matrix. After having served her purpose, she decided to retrain under
the SkillsFuture scheme and pivot her career towards providing task-management services to the common human.
## Features

### Adding tasks
Add a Todo, Deadline or Event
#### * Todo
Format: `todo {description}`
Eg. output: `1. [T][ ] walk the dog`
#### * Deadline
Format: `deadline {description} /{day} {month} {year} {hour}{minute}`<br>
Eg. input: `deadline meeting with cs2103 team /19 2 2021 2300` <br>
Eg. output: `2. [D][ ] meeting with cs2103 team (Fri 23:00, 19 FEBRUARY 2021)`
#### * Event
Format: `event {description} /{day} {month} {year} {hour}{minute}`<br>
Eg. input: `event meeting with cs2103 team /19 2 2021 2300`<br>
Eg. output: `3. [E][ ] meeting with cs2103 team (Fri 23:00, 19 FEBRUARY 2021)`
### Listing list
Lists all the current tasks in your list. <br />
Format: `list`<br>
Eg.: <br>`1. [T][ ] walk the dog`<br>
`2. [D][ ] meeting with cs2103 team (Fri 23:00, 19 FEBRUARY 2021)`<br>
`3. [E][ ] meeting with cs2103 team (Fri 23:00, 19 FEBRUARY 2021)`
### Done
Mark a Todo, Description or Event by its index to be completed. 
An indicator will appear beside the task in the list <br />
Format `done [index]` <br>
Eg. input: `done 2` <br>
Eg. output: `2. [D][X] meeting with cs2103 team (Fri 23:00, 19 FEBRUARY 2021)`
### Deleting tasks
Delete a Todo Description or Event by its index permanently <br />
Format: `delete [index]`<br>
Eg. input: `delete 2` <br>
Eg. output: `Erased: 2. [D][X] meeting with cs2103 team (Fri 23:00, 19 FEBRUARY 2021)`

### Exit
The Oracle doesn't appreciate you closing the application without saying bye and will 
not save your information unless you say goodbye to her. Note that Oracle will not save your information otherwise. This is a design choice and a
key feature, not a bug. <br />
Format: `bye`<br>
Eg. output: `Very well, we shall meet again`
, and information is saved. <br>

### Find
Find tasks with a keyword or phrase. Oracle will return results containing those keywords/phrase<br />
Format: `find {keyword}`<br>
Eg. input: `find meeting`<br>
Eg. output: <br>`Here are the results we found for: "meeting"`<br>
`[D][X] meeting with cs2103 team (Fri 23:00, 19 FEBRUARY 2021)`

### Postpone
Postpone your tasks if you cannot finish them in time. Choice of 'measure' include:
`mins/hrs/days/weeks`<br />
Format: `postpone {taskIndex} {value} {measure}`<br>
Eg. input: `postpone 2 2 days` <br>
Eg. output: `Postponed: 2. [D][ ] meeting with cs2103 team (Fri 23:00, 19 FEBRUARY 2021)`