# User Guide
The Duke is a desktop app for managing tasks, which can be used via the GUI or CLI.
The Duke does not greet you, as he is simulating an alien predator that has no interest
in small talk. You, a representation of Arnold Schwarzenegger, have enslaved this predator
and turned him into your task manager.
## Features 

### Adding tasks
Add Deadline, Event and Todo tasks to the Duke
#### * Deadline
Format: `deadline [body] /by [datetime]`
#### * Event
Format: `event [body] /at [datetime]`
#### * Todo
Format: `todo [body]`

### Done
Mark task as done by index of the task. A X will appear next to the task for your reference. <br />
Format `done [index]`
### Deleting tasks
Delete tasks from the list via index. <br />
Format: `delete [index]`
### Listing list
Lists all the current tasks in your list. <br />
Format: `list`
### Save
Save tasks from this session into long term storage. <br />
Format: `save` <br />
Tip: the duke will autosave for you. This command is just for peace of mind. 
### Exit
Click the x at the top left of the screen. <br />
### Find
Find tasks with via a keyword or keyphrase. <br />
Formaat: `find [keyphrase/keyword]`
### Dates and Times
If you input datetime as <yyyy-mm-dd 24h-time> or <yyyy/mm/dd 24h-time>,
the Duke will be able to understand that.
eg. ` deadline return book /by 2/12/2019 1800 `
### Detecting Duplicates
The duke will detect if you have added a duplicate task and infomr you. However, it is up to you to delete it.
