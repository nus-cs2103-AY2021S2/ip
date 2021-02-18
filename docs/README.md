# User Guide
SWEH (Simple, Word-Executed Helper) is a chatbot designed to help you keep track
of simple todo tasks, deadlines and events.

## Features 

### help
Type in `help` any time to view command usage within the app.

### todo, deadline, event
Add tasks to your task list with commands `todo`, `deadline` and `event`. Sweh
will automatically track your tasks for you!

### list
Type `list` to take a look at what tasks your have.

### find
Type `find` and some keywords to search for tasks by descriptions.

### done, delete
Use commands `done` and `delete` to mark tasks as done and remove them from your list.

### save data
Automatically stores all data after each command, so you don't lose any progress even
in the case of a program crash. Stores all data in the "data/sweh.txt" path. If the 
app is moved without the .txt file, all data will be lost.


## Commands

### `help` - Show helper text for the commands in the app

#### Usage: 

`help` - list out all possible commands

`help <command>` - show detailed information about an individual command

#### Example:

`help deadline`:

Help for "deadline":  
Usage: "deadline <description>" /by <YYYY-MM-DD>" - Creates
a Deadline with the supplied description and date.

======================

### `todo` - Add a Todo task to your task list

#### Usage:

`todo <description>` - Add a Todo task with the supplied description.

* Note that double/multiple spaces `"  "` in the description will be ignored and shortened into a single space

#### Example:

`todo finish CS2103 iP`:

"Gotcha. I've added the task:  
[T][ ] finish CS2103 iP  
Now you have 7 task(s) in your list"

======================

### `deadline` - Add a Deadline task to your task list

#### Usage:

`deadline <description> /by <YYYY-MM-DD>` - Add a Deadline task with the supplied description and date.

* Note that double/multiple spaces `"  "` in the description will be ignored and shortened into a single space

#### Example:

`deadline Steam Summer Sale 2020 /by 2020-07-31`:

"Gotcha. I've added the task:  
[D][ ] Steam Summer Sale 2020 (by: Fri, 31 Jul 20)  
Now you have 3 task(s) in your list"

======================

### `event` - Add an Event task to your task list

#### Usage:

`event <description> /at <YYYY-MM-DD>` - Add an Event task with the supplied description and date.

* Note that double/multiple spaces `"  "` in the description will be ignored and shortened into a single space

#### Example:

`event Free GongCha Giveaway /at 2021-02-18`:

"Gotcha. I've added the task:  
[E][ ] Free Gongcha Giveaway (at: Thu, 18 Feb 21)  
Now you have 5 task(s) in your list"

======================

### `list` - List out all tasks in your task list

#### Usage:

`list` - Lists out all tasks

* The first box indicates the type of task. `[T]`  for Todo, `[D]` for Deadline and
`[E]` for Events
* The second box indicates if the task is completed `[✓]` for done, and `[ ]` for not done

Example:

`list`:

"Here is your list of tasks:
1. [T][✓] finish CS2103 iP
2. [D][ ] Steam Summer Sale 2020 (by: Fri, 31 Jul 20)
3. [E][✓] Free GongCha Giveaway (at: Thu, 18 Feb 21)"

======================

### `done` - Mark a task as done

#### Usage:

`done <index>` - Mark the task in the list at position <index> as done.

#### Example:

If the starting list looks like this:  
"Here is your list of tasks:
1. [T][ ] finish CS2103 iP
2. [D][ ] Steam Summer Sale 2020 (by: Fri, 31 Jul 20)
3. [E][ ] Free GongCha Giveaway (at: Thu, 18 Feb 21)"

then `done 2` will result in:

"Nice, another job well done!  
[D][✓] Steam Summer Sale 2020 (by: Fri, 31 Jul 20)"

======================

### `delete` - Remove a task from the list

#### Usage:

`delete <index>` - Remove the task in the list at position <index>.

#### Example:

If the starting list looks like this:  
"Here is your list of tasks:
1. [T][ ] finish CS2103 iP
2. [D][ ] Steam Summer Sale 2020 (by: Fri, 31 Jul 20)
3. [E][ ] Free GongCha Giveaway (at: Thu, 18 Feb 21)"

then `delete 2` will result in:

"I've removed the task:   
[D][✓] Steam Summer Sale 2020 (by: Fri, 31 Jul 20)  
Now you have 2 task(s) in your list"

======================

### `find` - List the tasks that match against keywords

#### Usage:

`find <keyword1> <keyword2> ...` - Lists out all tasks that match all of the keywords

* Each keyword should be separated by a space `" "`
* Only tasks that contain all of the keywords will be shown
* The sequence of the keywords do not matter
* The keywords are case-sensitive

Example:

If the starting list looks like this:  
"Here is your list of tasks:
1. [T][ ] apple bee
2. [T][ ] Apple Bee"
3. [T][ ] applebee
4. [T][ ] bee apple


`find apple bee`:

"Here are the tasks that match your search:
1. [T][ ] apple bee
2. [T][ ] applebee
3. [T][ ] bee apple"

* The above example occurs because the 3 tasks all contain the sequence "`apple`" and "`bee`"
* The entry "Apple Bee" doesn't match because the search is case-sensitive

======================

### `quit` - Terminate the application

#### Usage:

`quit` - Closes the application immediately so you don't waste precious seconds reaching for the mouse
and moving the cursor to the red 'X' at the top.