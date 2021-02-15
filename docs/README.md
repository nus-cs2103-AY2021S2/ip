# User Guide

# Bob chatbox

Bob is a desktop app for **managing a list of tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Quick Start

1. Ensure you have Java `11` and above installed in your Computer.
1. Download the latest `Bob.jar` from [here](https://github.com/sylviaokt/ip/releases).
1. Copy the file to the folder you want to use as the home folder for your Bob chatbox.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
![StartScreen](/images/Start.PNG)
1. The chatbox now has no tasks stored if it is the first time you are running it in your computer.
1. All tasks added would be stored in the `/data/tasks.txt` file.
1. Type the command in the command box and press `Enter` or `Send` button to execute.
Some example commands you can try:
   * `list`: List all tasks.
   * `todo` eat: Adds a Todo task to the chatbox.
   * `delete 2`: Deletes the 2nd task shown from the current list.
   * `bye`: Exits the app.
1. Refer to the `Feature` section below for details of each command.

## Features

### **Notes about the command format** 
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo eat`.
* Parameters must follow the order given.
* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
e.g. if the command specifies `list 2`, it will be interpreted as `list`.

### Adding a todo task: `todo`
Adds a new todo task to the chatbox.
Format: `todo NAME_OF_TASK`
Examples:
* `todo eat`
* `todo sleep`

### Adding an event: `event`
Adds a new event to the chatbox.
Format: `event NAME_OF_EVENT /at: YYYY-MM-DD HHMM`
* Adds an event occurring at the specified date and time.
* The date and time **must follow the specifed format**.
* The time is in a **24-hour format**.
Examples:
* `event eat /at: 2021-02-02 0200`
* `event dinner /at: 2021-02-28 1900`

### Adding a deadline: `deadline`
Adds a new deadline to the chatbox.
Format: `deadline NAME_OF_DEADLINE /by: YYYY-MM-DD HHMM`
* Adds a deadline due at the specified date and time.
* The date and time **must follow the specifed format**.
* The time is in a **24-hour format**.
Examples:
* `deadline essay /by: 2021-03-01 2359`
* `deadline submit assignment /by: 2021-02-28 2359`

### Listing all tasks: `list`
Shows a list of all tasks stored.
Format: `list`

### Searching for a specific task by name: `find`
Search for tasks whose names contain any of the given keywords.
Format: `find KEYWORDS`
* The search is case-insensitive. e.g `eat` will match `Eat`
* The order of the keywords matters. e.g. `eat and sleep` will not match `sleep and eat`
* Only the name is searched.
Examples:
* `find eat` will return `Eat` and `eat and sleep`

### Adding a reminder to a task: `remind`
Adds a reminder to a task that would be given by the chatbox at the specified time.
Format: `remind INDEX /on: YYYY-MM-DD HHMM`
* Adds a reminder to remind about the task at the specified `index`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer 1, 2, 3, …**
* The date and time **must follow the specifed format**.
* The time is in a **24-hour format**.
Examples:
* `remind 1 /on: 2021-03-01 1300` - The bot will give a reminder about the task at 1pm on 1st of March 2021.
* `remind 3 /on: 2021-02-28 1900`

### Mark a task as completed: `done`
Marks the specified task as done in the chatbox.
Format: `done INDEX`
* Marks the task at the specified `INDEX` as completed.
* The index refers to the index number shown in the displayed task list. 
* The index **must be a positive integer 1, 2, 3, …**
Examples:
* `done 1` 

### Deleting a task: `delete`
Deletes the specified task from the chatbox.
Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list. 
* The index **must be a positive integer 1, 2, 3, …**

### Exiting the chatbox: `bye`
Exits the chatbox.
Format: `bye`

### Saving the data
Bob data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Editing the data file
Bob data are saved as a txt file [TXT file location]/data/tasks.txt. Users are welcome to update data directly by editing that data file.

#### Caution: If your changes to the data file makes its format invalid, Bob will run into an issue when running and might not run.

## Command summary
Action | Format
-------|---------
todo | `todo NAME_OF_TODO`
event | `event NAME_OF_EVENT /at: YYYY-MM-DD HHMM`
deadline | `deadline NAME_OF_DEADLINE /by: YYYY-MM-DD HHMM`
list | `list`
find | `find KEYWORDS`
remind | `remind INDEX /on: YYYY-MM-DD HHMM`
done | `done INDEX`
delete | `delete INDEX`
bye | `bye`
