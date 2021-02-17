# User Guide
##Introduction
Duke is an easy to use GUI reminder application that can be used to store tasks to be done, events, and deadlines. 
Hopefully with 
Duke, you will never forget to do anything!

## Features 

### Adding a task
####Commands
```
todo (insert task)
event (insert event) / (insert date and time)
deadline (insert event) / (insert date and time)
```
These commands allows the user to add a task to be done at any time, an event with a certain date and time, and a 
deadline with a certain date and time to Duke.

###Listing out your tasks
####Command
```aidl
list
```
This command allows the user to list out all the tasks that he has input so far.

###Complete a task
####Command
```aidl
done (insert index of the task)
```
This command allows users to mark a task as complete. To use this command, user must know the index of his task, 
which can be found by using the `list` command.

###Delete a task
####Command
```aidl
delete (insert index of task)
```
This command allows users to delete a task. To use this command, user must know the index of his task,
which can be found by using the `list` command.

###See tasks that are due soon
####Command
```aidl
reminders
```
This command allows users to view the events or deadlines that are due within the week. 


###Find a task
####Command
```aidl
find (insert 1 keyword to search)
```
This command allows users to find all the tasks that matches the keyword that they have used to search.

###Exiting Duke
####Command
```aidl
bye
```
This command allows the user to exit the Duke application.

