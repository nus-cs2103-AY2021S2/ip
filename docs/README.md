
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `olaf.jar` from [here](https://github.com/vrssoorya/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your Olaf app.

4. Double-click the file to start the app. The GUI with a greeting from Olaf, similar to the below should appear in a few seconds. Notice your app will not have any data in the task list unlike the example below.<br>
   ![Ui](Ui.png)

5. Type the command in the command box below and press Enter to execute it. e.g. typing **`help`** and pressing Enter will give a response listing all the  possible commands you can use.<br>
   Some example commands you can try:

    * **`list`** : Lists all tasks in the current list.

    * **`todo`** `homework` : Adds a todo Task with description `homework` to Olaf.

    * **`delete`** `3` : Deletes the 3rd listed task shown in the current list.

    * **`done`** `3` : Marks the 3rd Task in the current list as done.

    * **`bye`** : Exits the app after showing a goodbye message.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Notations

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<UPPER CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `done <TASK NUMBER>`, `<TASK NUMBER>` is a parameter which can be used as `done 3`.

* Items in square brackets are optional.<br>
  e.g `find <SEARCH TERM> [, <MORE SEARCH TERMS>]` can be used as `find project, presentation` or as `find project`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `remind` and `bye`) will NOT be ignored.<br>
  e.g. if the command specifies `help 123`, it will not be interpreted as `help`.

</div>

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**List** | `list`
**Remind** | `remind`
**Todo** | `todo <TASK DESCRIPTION>`
**Deadline** | `deadline <TASK DESCRIPTION> /by <YYYY-MM-DD HH:mm>` e.g., `deadline project submission /by <2021-02-21 00:00>`
**Event** | `event <TASK DESCRIPTION> /at <YYYY-MM-DD HH:mm> to <YYYY-MM-DD HH:mm>` <br> e.g., `event new year's eve /at <2021-12-31 00:00> to <2022-01-01 00:00>`
**Done** | `done <TASK NUMBER>`<br> e.g., `done 2`
**Delete** | `delete <TASK NUMBER>`<br> e.g., `delete 3`
**Find** | `find <SEARCH TERM> [, <MORE SEARCH TERMS>]`<br> e.g., `find project, presentation`

--------------------------------------------------------------------------------------------------------------------

## Features

### Viewing help : `help`

Shows summary of all commands.

Format: `help`

### Listing all tasks : `list`

Shows a list of all tasks in the application.

Format: `list`

### Get reminder : `remind`

Shows a list of all upcoming deadlines and events in the next 7 days.

Format: `remind`

### Adding a todo: `todo`

Adds a todo Task to the application.

Format: `todo <TASK DESCRIPTION>`

* Description cannot be empty

Examples:
* `todo readings`
* `todo clean room`

### Adding a deadline: `deadline`

Adds a deadline Task to the application.

Format: `deadline <TASK DESCRIPTION> /by <YYYY-MM-DD HH:mm>`

* Description cannot be empty  
* Time is given in 24 hour format
* The deadline date and time has to be after current time

Examples:
* `deadline birthday /by 2021-06-21 00:00`
* `deadline project submission /by 2021-02-21 23:59`

### Adding an event: `event`

Adds an event Task to the application.

Format: `event <TASK DESCRIPTION> /at <YYYY-MM-DD HH:mm> to <YYYY-MM-DD HH:mm>`

* Description cannot be empty
* Time is given in 24 hour format
* The start date and time has to be before end date and time
* When loading existing event task from storage, it will be marked as done if the event is already over.

Examples:
* `event new year's eve /at 2021-12-31 00:00 to 2022-01-01 00:00`
* `event webinar /at 2021-02-20 13:00 to 2021-02-20 15:30`

### Completing a task : `done`

Marks the specified task in the current task list as done.

Format: `done <TASK NUMBER>`

* Marks the task with the specified `TASK NUMBER` as completed.
* The number refers to the index number shown in the displayed task list after the `list` command.
* The index **must be a valid integer in the list** 1, 2, 3, …​

Examples:
* `done 1` completes the 1st task in the task list returned after the `list` command.
* `find assignment` followed by `done 1` does NOT complete the 1st task in the results of the `find` command but rather completes the 1st task like in the previous example.

### Deleting a task : `delete`

Deletes the specified task in the current task list.

Format: `delete <TASK NUMBER>`

* Deletes the task with the specified `TASK NUMBER`.
* The number refers to the index number shown in the displayed task list after the `list` command.
* The index **must be a valid integer in the list** 1, 2, 3, …​

Examples:
* `delete 1` deletes the 1st task in the task list returned after the `list` command.
* `find assignment` followed by `delete 1` does NOT delete the 1st task in the results of the `find` command but rather deletes the 1st task like in the previous example.

### Locating tasks by name: `find`

Finds tasks whose names contain any of the given search terms.

Format: `find <SEARCH TERM> [, <MORE SEARCH TERMS>]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans, Bo` will match `Bo Hans`
* Only the name is searched.
* Partial words will be matched e.g. `pro` will match `project`
* Tasks matching at least one search term will be returned (i.e. `OR` search).
  e.g. `project, homework` will return `CS1010 Project`, `GER homework`

Examples:
* `find sub` returns `submit` and `submission`
* `find webinar, lecture` returns `AI Webinar`, `Lecture presentation`<br>

### Exiting the program : `bye`

Exits the program about 1 second after displaying a goodbye message.

Format: `bye`

### Saving the data

Olaf tasks are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Olaf tasks are saved as a .txt file `[JAR file location]/data/olaf.txt`. Advanced users are welcome to update data directly by 
editing that data file but please follow expected saving format to avoid application crashes.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Olaf will will not work as expected at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

md template adapted from: https://github.com/se-edu/addressbook-level3/blob/master/docs/UserGuide.md
