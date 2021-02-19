# DBot User Guide

![DBot example usage](Ui.png)

DBot is a chat-bot built to handle task tracking through keyboard interactions.  
Tasks are automatically persisted beyond each individual session.

## Setup

### `Run` - Run the DBot
To activate or run the DBot, go to the directory where `dbot.jar` is located and type the following
command:
```bash
java -jar dbot.jar
```

## Features
1. View a list of all *Tasks*  
2. Add three types of *Tasks* to the list:  
  * `Todo` - Only contains a description
  * `Event` - Contains a description and an `at` field representing a *date*
  * `Deadline` - Contains a description and a `by` field representing a *date*
3. Mark *Tasks* as done
4. Delete *Tasks*
5. Find Tasks that contain a given description
6. Type `help` to see a list of possible commands


### Feature 1: List
Format: `list`  
The `list` command takes no arguments and outputs a list of all currently stored tasks.
Each task will be shown with a checkbox `[x]` or `[ ]` indicating whether it has been completed 
or not, respectively.  

Example outcome:
```
1. [T][x] Bake a cake
1. [E][ ] House party (at: Jan 2, 2021)
1. [D][ ] Bake a cake (by: Mar 5, 2021)
```
The above represents a task list with three tasks: Todo, Event, Deadline, respectively.  

### Feature 2: Add Tasks
Format: `todo DESCRIPTION`, `event DESCRIPTION /at DATE`, `deadline DESCRIPTION /by DATE`  
Note that `DATE` must be in `YYYY-MM-DD` format.  
These commands add a *Task* to the overall task list, according to their respective task types.

Example usage: `todo Bake another cake`  
Example outcome: `Added: [T][ ] Bake another cake`

Example usage: `event Sam's birthday /at 2021-03-03`  
Example outcome `Added [E][ ] Sam's birthday (at: Mar 3, 2021)`

### Feature 3:
Format: `done TASK_INDEX`  
Where `TASK_INDEX` is the index of the task as reported by the `list` command.  
By marking a task as done, its checkbox representation change from `[ ]` to `[x]`.  
> Note: It is not possible to remove the 'Done' mark from a task.  

Example usage: `done 2`  
Example outcome: `Completed: [E][x] Sam's birthday (at: Mar 3, 2021)`

### Feature 4:
Format `delete TASK_INDEX`  
Where `TASK_INDEX` is the index of the task as reported by the `list` command.  
When a task is deleted it is removed from the task list as shown in the `list` command.  

Example usage: `delete 2`  
Example outcome: `Deleted: [E][x] Sam's birthday (at: Mar 3, 2021)`

### Feature 5:
Format `find TERM`  
Where `TERM` is a search term to be used to match against existing task descriptions.  
An output task list of all relevant tasks will be shown.

Example usage: `find bake`  
Example output: 
```
Here are the matching tasks in your list:
1. [T][ ] Bake another cake
```

### Feature 6:
Format `help`  
Shows a help message stating the various commands a user can enter and briefly explains 
what they do.  

Example usage: `help`  
Example outcome: 
```
Use any of the following commands:
To view all stored tasks: list
To find relevant stored tasks: find TERM
To add a new `todo`: todo DESC
To add a new `event`: event DESC /at DATE
...
```

