
Duke Project User Guide
---  
Duke Project is an **to do list application** made for busy people to keep track of their importance task to be done. It allows user to manage their contracts as well. This allows for a fast and efficient way for you to manage both your task and contacts at the same time.

## Quick start

1. Ensure you have Java `11`  or above installed in your Computer.

1. Download the latest `duke.jar`.

1. Double-click the file to start the app.

1. Type the command in the command box and press Enter to execute it. <br>


1. Refer to the [Features](#features) below for details of each command.


## Features


### Adding a todo task: `todo`

Adds a to do task to the application.

Format: `todo [TASK NAME]`

Examples:
* `todo borrow book`

Expected Outcome:  
`Got it. I've added this task:`  
`[T][ ] borrow book`  
`Now you have 1 tasks in the list.`
  
---

### Adding a task with deadline: `deadline`

Adds a task with deadline to the application.

Format: `deadline [TASK NAME] [/by DATE]`
* **DATE** must be in the `YYYY-MM-DD HHMM`  format.

Examples:
* `deadline return book /by 2020-03-10 1800`

Expected Outcome:  
`Got it. I've added this task:`  
`[D][ ] return book  (by: 10 Mar 2020 - 1800)`  
`Now you have 2 tasks in the list.`
  
---

### Adding an event task: `event`

Adds an event task with a specific datetime to the application.

Format: `event [TASK NAME] [/at DATE]`
* **DATE** must be in the `YYYY-MM-DD HHMM`  format.

Examples:
* `event project meeting /at 2020-01-28 0800`

Expected Outcome:  
`Got it. I've added this task:`  
`[E][ ] project meeting  (at: 28 Jan 2020 - 0800)`  
`Now you have 3 tasks in the list.`
  
---

### Listing all task: `list`

Shows a list of all the task in the application.

Format: `list`

Expected Outcome:  
`1. [T][ ] borrow book`  
`2. [D][ ] return book  (by: 10 Mar 2020 - 1800)`  
`3. [E][ ] project meeting  (at: 28 Jan 2020 - 0800)`
  
---

### Finding task using keywords: `find`

Finds a task that matches the given keywords.

Format: `find KEYWORD`

Examples:
* `find book`  returns `borrow books`  task.

Expected Outcome:  
`Here are the matching tasks in your list:`  
`1. [T][ ] borrow book`  
`2. [D][ ] return book  (by: 10 Mar 2020 - 1800)`
  
---

### Completing an event: `done`

Marks a task in the application as completed.

Format: `done INDEX`
* The task at the specified `INDEX`  is completed.

Examples:
* `done 2`  Marks the 2nd task in the task list as completed.

Expected Outcome:  
`Nice! I've marked this task as done:`  
`[D][X] return book  (by: 10 Mar 2020 - 1800)`
  
---

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete INDEX`
* The task at the specified `INDEX`  is deleted.

Examples:
* `delete 1`  Deletes the 1st task in the task list.

Expected Outcome:  
`Noted. I've removed this task:`  
`[T][ ] borrow book`  
`Now you have 2 tasks in the list.`
  
---
### Adding a contact : `contact add`

Adds a contact person to the contact list.

Format: `contact add [NAME] [EMAIL] [CONTACT NUMBER]`
* All the [] fields cannot have space in between.

Examples:
* `contact add Enhao enhao96@gmail.com 81234567`

Expected Outcome:  
`The contact has been successfully added.`
  
---
### Listing all contacts: `contact list`

Adds a to do task to the application.

Format: `contact list`

Expected Outcome:  
`Here are the contacts in your list:`  
`1. fullName=Enhao, emailAddress=enhao96@gmail.com, contactNumber=81234567`

---

### Deleting a contact: `contact delete`

Deletes a contact from the contact list.

Format: `contact delete INDEX`
* The task at the specified `INDEX`  is deleted.

Examples:
* `contact delete 1`  Deletes the 1st contact in the contact list.

Expected Outcome:  
`The contact has been successfully deleted.`
  
---
### Exiting the program : `bye`

Exits the program.

Format: `bye`