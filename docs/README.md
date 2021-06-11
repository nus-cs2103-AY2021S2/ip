# Duke project

This is a project named after the Java mascot Duke. Given below are instructions on how to use the interactive Duke bot.

# User Guide
## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()`.
1. Refer to the Features below for details of each command.

## Features

***NOTE: Words in UPPER_CASE are the parameters to be supplied by the user.***

### Adding a task:
&nbsp;
#### Adding a ToDo task: `todo`

Adds a new ToDo task to the Duke's tasklist.

Format: `todo CONTENT`

- The content field must be provided.

Examples:

- `todo hello`
-  `todo read book`

&nbsp;

#### Adding a Deadline task: `deadline`

Adds a new Deadline task to the Duke's tasklist.

Format: `deadline CONTENT /by DATE_AND_TIME`

- The date and time must be in the format DD/MM/YYYY HH:MM
- Both fields must be provided.

Examples:
- `deadline read book /by 12/12/2020 18:30`
- `deadline return book /by 10/10/2020 10:00`

&nbsp;
#### Adding an Event task: `event`

Adds a new Event task to the Duke's tasklist.

Format: `event CONTENT /at DATE_AND_TIME`

- The date and time must be in the format DD/MM/YYYY HH:MM
- Both fields must be provided.

Examples:
- `event go for meeting /at 12/10/2020 18:00`
- `event meet groupmates /at 10/10/2020 10:30`

&nbsp;

### Listing all tasks: `list`

Shows a list of all tasks in the Duke's tasklist.

Format: `list`

&nbsp;
### Marking a task as done: `done`

Marks an existing task in the Duke's tasklist as done.

Format: `done INDEX`

- Updates the task status to done for the task at the specified `INDEX`
- The index refers to the index number shown in the displayed tasklist.
- The index **must be a positive integer**.

Examples:
- `list` followed by `done 3` marks the 3rd task in the tasklist as done.

&nbsp;
### Deleting a task: `delete`

Deletes an existing task from the Duke's tasklist.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed tasklist.
- The index **must be a positive integer**.

Examples:
- `list` followed by `delete 1` deletes the 1st task in the tasklist.

&nbsp;
### Finding a task by content: `find`

Finds tasks with contents that contain the given keyword/s.

Format: `find KEYWORD OR KEYWORDS`

- The search is **case-sensitive**.
- Only the contents of the tasks are searched.
- The following tasks will be returned:
    1. content matching the keyword/s
    2. the keyword/s form a part of the content

Examples:
- `find read` returns both task with content `read book` and task with content `read` from the tasklist.

&nbsp;

### Editing a task: `edit`

&nbsp;

#### Editing a task's content: `edit_content`

Edits an existing task's content in the Duke's tasklist.

Format: `edit INDEX content NEW_CONTENT`

- Edits the content of the task at the specified `INDEX`
- The index refers to the index number shown in the displayed tasklist.
- The index **must be a positive integer**.
- Existing content will be updated to `NEW_CONTENT`.
- All fields must be provided.

Examples:
- `edit 1 content read new book` edits the content of the 1st task in the tasklist to `read new book`.

&nbsp;

#### Editing a task's date and time: `edit_datetime`

Edits an existing task's date and time in the Duke's tasklist.

Format: `edit INDEX datetime NEW_DATETIME`

- Edit the date and time of the task at the specified `INDEX`
- The index refers to the index number shown in the displayed tasklist.
- The index **must be a positive integer**.
- The date and time must be in the format DD/MM/YYYY HH:MM
- Existing date and time will be updated to `NEW_DATETIME`.
- All fields must be provided.

Examples:
- `edit 2 datetime 12/12/2020 18:30` edits the date and time of the 2nd task in the tasklist to `12/12/2020 18:30`.

&nbsp;
### Exiting the program: `bye`

Saves the tasklist's changes to the file at relative filepath `/data/duke.txt`.

Format: `bye`


&nbsp;
##Command Summary


Action | Format, Examples
------------ | -------------
Add | ToDo Task <br /> `todo CONTENT` </br> e.g. `todo hello` </br> </br> Deadline Task </br> `deadline CONTENT /by DATE_AND_TIME` </br>  e.g. `deadline read book /by 12/12/2020 18:30` </br></br> Event Task </br> `event CONTENT /at DATE_AND_TIME` </br> e.g. `event go for meeting /at 12/10/2020 18:00`
List | `list`
Done | `done INDEX` <br /> e.g. `done 3`
Delete | `delete INDEX` <br /> e.g. `delete 1`
Find | `find KEYWORD OR KEYWORDS` <br /> e.g. `find read`
Edit | Edit content <br />`edit INDEX content NEW_CONTENT` <br /> e.g. `edit 1 content read new book` <br /> <br /> Edit date and time <br /> `edit INDEX datetime NEW_DATETIME` <br /> e.g.  `edit 2 datetime 12/12/2020 18:30` <br />
Exit | `bye`


