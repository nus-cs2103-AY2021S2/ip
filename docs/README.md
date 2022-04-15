# User Guide
Duke is a desktop app for managing tasks, for use via a Command Line Interface (CLI).

--------------------------------------------------------------------------------------------------------------------
## Quick start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest duke.jar from [here](https://github.com/zhengruoxin/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your duke.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
![screenshot of ui](Ui.png)
5. Type the command in the text box and press Enter to execute it. Some example commands to start:
* **`todo`**`bring water`: Adds a todo task named bring water to the tasklist.
* **`list`**: Lists all tasks.
* **`bye`**: Exits the app.
6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Features
**Notes about the command format:**
- Words in `UPPER_CASE` are the parameters to be supplied by the user.\
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo exercise`.

- When `DATE` is used as a parameter, it must be specified in the YYYY-MM-DD format.\
e.g. in `event DESCRIPTION at DATE`, user can input date as `event fieldtrip at 2021-02-17`.
  
- Extraneous parameters for commands, parameters in wrong formats or missing will not be recognised.\
eg. `deadline by 03-02-2021`, `help 123` will not be registered.
  
### Viewing help: `help`
Displays all available commands.\
Format: `help`

### Adding a task: `todo`, `deadline`, `event`
Adds a todo or deadline or event task into tasklist.\
Format: `todo DESCRIPTION`, `deadline DESCRIPTION by DATE`, `event DESCRIPTION at DATE`\
Examples:
- `todo bring water`
- `deadline lab report by 2021-03-02`
- `event fieldtrip at 2021-02-17`

### Removing a task: `delete`
Removes the task at the specified position.\
Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​ within the range of number of tasks.
- Extra inputs will be ignored. eg. `delete 5 2` will be interpreted as `delete 2`.

Example:
- `delete 2`

### Marking a task done: `done`
Marks the task at the specified position as done.\
Format: `done INDEX`
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the displayed task list. 
- The index must be a positive integer 1, 2, 3, …​ within the range of number of tasks.
- Extra inputs will be ignored. eg. `done 1 3` will be interpreted as `done 1`.

Example:
- `done 3`

### Listing all tasks: `list`
Shows a list of all tasks in Duke.\
Format: `list`

### Locating tasks by name: `find`
Finds tasks whose description contains the given keyword.\
Format: `find KEYWORD`
- The seach is case in-sensitive. eg. `WATER` will match `water`
- Partial words can be matched. eg. `epo` will return `report`
- All inputs after "find " will be registered as a single keyword. eg. `find ncil cas` will find all tasks containing "ncil cas".
  
Examples:
- `find fOreSt`
- `find ieldtRi`

### Exiting the program: `bye`
Exits the program.\
Format: `bye`

### Saving the date
Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------
## Command summary

Action | Format, Examples
--------|------------------
Add deadline |`deadline DESCRIPTION by DATE`<br> e.g., `deadline lit review by 2021-05-09`
Add event | `event DESCRIPTION at DATE`<br> e.g., `event production at 2020-03-12`
Add todo | `todo DESCRIPTION`<br> e.g., `todo wash clothes`
Delete | `delete INDEX`<br> e.g., `delete 3`
Done | `done INDEX`<br> e.g., `done 2`
Find | `find KEYWORD`<br> e.g.,`find oMeWor`
Help | `help`
List | `list`
Exit | `bye`