# User Guide
Yoda is a Personal Assistant Chatbot that can help you
keep track of your tasks. It is optimized for use via
a Command Line Interface while still having the benefits
of a Graphical User Interface (GUI). If you can type fast, 
Yoda can get your task management tasks done faster than 
traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Quick start
1. Ensure you have java `11` or above installed.
   
1. Download the latest `Yoda.jar` from [here](https://github.com/vivegank/ip/releases).

1. Copy the downloaded file to the folder from which the app will be used.

1. Use _java -jar Yoda.jar_ or just double-click to start the app. The GUI
similar to below should appear in a few seconds.<br>
   ![UI](Ui.png)
   
1. Type the command in the command box and press Enter to execute it.<br>
Some example commands you can try:
   * **`help`** : Shows all available commands.
   * **`list`** : Lists all tasks in the task list.
   * **`todo read a book`** : Adds a ToDo task called `read a book` to the list of tasks.
   * **`done 1`** : Marks the 1st task on the task list as done.
   * **`delete 1`** : Deletes the 1st task on the task list.
   * **`bye`**: Exits the app.
   
--------------------------------------------------------------------------------------------------------------------

## Features 
**Notes about the command format:**<br>
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo TASK_DESC`, `TASK_DESC` is a parameter which can be used as `todo read a book`.
* Items in square brackets are optional.<br>
  e.g. in `list [TASK_FILTER]` can be used as `list` or `list -t`.
* A slash gives user a choice between the options available.<br>
  e.g. in `delete INDEXES \ all`, user has a choice to either `delete 1` or `delete all`.

### Viewing help : `help`  
Shows a list of commands available to the user.<br><br>
Format: `help`

### Adding a task :
There are three types of tasks that can be added:
#### ToDo : `todo`
ToDos are for tasks without any date/time attached to them.<br><br>
Format: `todo TASK_DESC`<br><br>
Examples:
* `todo read a book`
* `todo go to the bank`

#### Event : `event`
Events are for tasks that occur at a specific date and time.<br><br>
Format: `event TASK_DESC /at YYYY-MM-DD HHMM`<br><br>
Examples:
* `event group meeting /at 2021-03-21 1200`
* `event birthday party /at 2021-03-13 1700`

#### Deadline : `deadline`
Deadlines are for tasks that need to be done before a specific date and time.<br><br>
Format: `deadline TASK_DESC /by YYYY-MM-DD HHMM`<br><br>
Examples:
* `deadline finish project /by 2021-04-10 0000`
* `deadline finish quiz /by 2021-03-09 2359`

### Listing tasks : `list`
Shows a list of all or specific types of tasks in the task list.<br><br>
Format: `list [-t / -d / -e]`<br>
* `list` lists all the tasks in the task list.
* `list -t` lists all the todos in the task list.
* `list -e` lists all the events in the task list.
* `list -d` lists all the deadlines in the task list.

### Locating task by keyword : `find`
Finds tasks whose descriptions contain the given keyword.<br><br>
Format: `find KEYWORD`
* The search is case-insensitive.

Examples:
* `find book`

### Marking task(s) as done: `done`
Marks the specified task(s) in the task list as done.<br><br>
Format: `done INDEXES / all`
* `done all` can be used to mark all tasks as done or `INDEXES` can be used to specify tasks.
* `INDEXES` can be just one index or multiple indexes.
* Marks the task(s) at the specified `INDEXES` as done.

Examples:
* `done 1`
* `done 1 3 5`
* `done all`

### Deleting task(s) : `delete`
Deletes the specified task(s) in the task list.<br><br>
Format: `delete INDEXES / all`
* `delete all` can be used to delete all tasks or `INDEXES` can be used to specify tasks.
* `INDEXES` can be just one index or multiple indexes.
* Deletes the tasks(s) at the specified `INDEXES`.

Examples:
* `delete 1`
* `delete 2 4 6`
* `delete all`

### Exiting the program : `bye`
Exits the program.<br>
Format: `bye`

### Saving the data
Task list data is saved automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**ToDo** | `todo TASK_DESC` <br> e.g., `todo read a book`
**Deadline** | `deadline TASK_DESC /by YYYY-MM-DD HHMM`<br> e.g., `deadline finish quiz /by 2021-03-09 2359`
**Event** | `event TASK_DESC /at YYYY-MM-DD HHMM`<br> e.g., `event birthday party /at 2021-03-13 1700`
**List** | `list [-t / -d / -e]`<br> e.g.,`list` or `list -e`
**Find** | `find KEYWORD`<br> e.g., `find book`
**Done** | `done INDEXES / all`<br> e.g., `done 2 4` or `done all`
**Delete** | `delete INDEXES / all`<br> e.g., `delete 3 5` or `delete all`
**Bye** | `bye`
