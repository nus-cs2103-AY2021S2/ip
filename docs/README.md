# User Guide

## Features 

+ Greet Doge Duke: `hi`
+ Add a **Todo** command: `todo`
+ Add an **Deadline** command: `deadline`
+ Add an **Event** command: `event`
+ List all commands : `list`
+ Complete a command: `done`
+ Delete a command : `delete`
+ Locate a command : `find`
+ Archive all commands: `archive`
+ Retrieve archived commands: `retrieve`
+ Exit Doge Duke : `bye`

## Usage

### `hello` - Greet Doge Duke

Starts the Doge Duke Programme.

Format: `hello`

Example of usage:
```
hello
```

Expected outcome:

```
Woof! I'm Doge Duke
What do you want me to do?
Type your request in below!
```

### `todo` - Add a Todo command
Adds a Todo Command to the current working Commands List.

Format: `todo {COMMAND_DESCRIPTION}`
+ `{COMMAND_DESCRIPTION}` is added to the Command List.


Example of usage:

```
todo rollover
```

Expected outcome:

```
Mlem I've added a new command for you to do:
[T] [ ] rollover
Now I can do a total of 1 commands!
```

### `deadline` - Add a Deadline command
Adds a Deadline Command to the current working Commands List.

Format: `deadline {COMMAND_DESCRIPTION} /by {DUE_DATE}`
+ `{COMMAND_DESCRIPTION}` is added to the Command List.
+ `{DUE_DATE}` isis added as a Due Date in YYYY-MM-DD format.

Example of usage:

```
deadline finish doggo treats /by 2021-03-11
```

Expected outcome:

```
Woofers! I've added a new command with a Ded-line:
[D] [ ] finish doggo treats | by: 11 Mar 2021
Now I can do a total of 3 commands!
```

### `event` - Add a Event command
Adds a Event Command to the current working Commands List.

Format: `event {COMMAND_DESCRIPTION} /by {EVENT_DATE}`
+ `{COMMAND_DESCRIPTION}` is added to the Command List.
+ `{EVENT_DATE}` is added as a Event Date in YYYY-MM-DD format.

Example of usage:

```
event dog festival /at 2021-02-10
```

Expected outcome:

```
Much wow! I've added a new command with an Event:
[E] [ ] dog festival | at: 10 Feb 2021
Now I can do a total of 2 commands!
```

### `list` - List all commands
Lists all Commands in the current working Command List in
an indexed format.

Format: `list`

Example of usage:

```
list
```

Expected outcome:

```
1. [T] [ ] rollover
2. [D] [ ] finish doggo treats | by: 11 Mar 2021
```

### `done` - Complete a command
Mark a Command for which the index/id is identified by the user
as Done from the current working Command List.

Format: `done {COMMAND_ID}`
+ Marks the Command numbered {COMMAND_ID} in the Command List as Done.

Example of usage:

```
done 2
```

Expected outcome:

```
1. [T] [ ] rollover
2. [D] [X] finish doggo treats | by: 11 Mar 2021
```

### `delete` - Delete a command
Deletes the Command for which the index/id is identified by the user
from the current working Command List.

Format: `delete {COMMAND_ID}`
+ Delete the Command numbered {COMMAND_ID} in the Command List.

Example of usage:

```
delete 2
```

Expected outcome:

```
1. [T] [ ] rollover
```

### `find` - Locate a command
Deletes the Command for which the index/id is identified by the user
from the current working Command List.

Format: `find {KEYWORD}`
+ Searches for `KEYWORD` in all the Command Descriptions of Commands in the current working
Command list.

Example of usage:

```
find roll
```

Expected outcome:

```
1. [T] [ ] rollover
2. [T] [ ] roll back
```

### `archive` - Archive all commands
Archive all Commands in the current working Command List to
a text file located in the data directory. User will start on a clean
slate, Command List will be empty.

Format: `archive`

Example of usage:

```
archive
```

Expected outcome:

```
Archived all commands in text file in your data folder!
```

### `retrieve` - Retrieved Archive commands
Retrieves all Commands previously archived and appends to
the back of current working Command List.

Format: `retrieve`

Example of usage:

```
retrieve
```

Expected outcome:

```
Your archived commands have been added back to your
current commands!
```

### `bye` - Exit Doge Duke
Terminates the Doge Duke Programme.

Format: `bye`

Example of usage:

```
bye
```

Expected outcome:

```
Bye! Hope I was a good dog, see you again soon!
```

