# User Guide
`Quackers` is a cross-platform chat-bot specialised in task management.

---
## Quick start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `quackers.jar` from [here](https://github.com/deyixtan/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Quackers.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![Image of Quackers](./docs/Ui.png)

---
## Features
### Viewing usage: `usage`
Shows a message explaining the different commands available with their arguments (if any).

Format: `usage`

---
### Exiting the program: `bye`
Exits the program.

Format: `bye`

---
### Listing all tasks: `list`
Shows a list of all tasks being managed by Quackers.

Format: `list`

---
### Locating task\(s\) with a keyword: `find`
Find tasks whose description contains the given keyword.

Format: `find KEYWORD`
- The search is case-sensitive. e.g hans will not match Hans.
- Only the task description is searched.

---
### View statistics: `stats`
Shows a message describing the breakdown of the task list by their task type.

Format: `stats`

---
### Adding a To-do task: `todo`
Adds a Todo task to the list of tasks being managed by Quackers.

Format: `todo DESCRIPTION`

---
### Adding a Deadline task: `deadline`
Adds a Deadline task to the list of tasks being managed by Quackers.

Format: `deadline DESCRIPTION /by DATETIME`
- The datetime argument must conform to the format `YYYY-MM-DD HH:mm`.

---
### Adding an Event task: `event`
Adds a Event task to the list of tasks being managed by Quackers.

Format: `event DESCRIPTION /at DATETIME`
- The datetime argument must conform to the format `YYYY-MM-DD HH:mm`.

---
### Deleting a task: `delete`
Deletes the specified task from the list of tasks being managed by Quackers.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

---
### Saving the data
Quackers data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
