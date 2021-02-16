# **User Guide**

## **Chatbox: Luna**

### Luna is an app that uses the CLI to manage tasks.

## **Features**


### **Adding a todo task: `todo`**

#### Adds a new `todo` task.
1. Format: `todo NAME_OF_TASK`
1. Example of usage: `todo work`


### **Adding a deadline task: `deadline`**

#### Adds a new `deadline` task.
1. Format: `deadline NAME_OF_TASK /by yyyy-mm-dd hh:mm`
1. Example of usage: `deadline work /by 2021-02-28 19:00`


### **Adding an `event` task: `event`**

#### Adds a new `event` task.
1. Format: `event` NAME_OF_TASK /at yyyy-mm-dd hh:mm
1. Example of usage: `event` work /at 2021-02-28 19:00


### **Marking a task as `done`: `done`**

#### Updates a task by marking it as `done`.
1. Format: `done INDEX_OF_TASK_IN_LIST`
1. Example of usage: `done 5`


### **Listing down all the tasks: `list`**

#### Lists down all the previously entered tasks.
1. Format: `list`
1. Example of usage: `list`


### **Finding a task using specific keywords: `find`**

#### Lists tasks which contain the keywords.
1. Format: `find KEYWORDS`
1. Example of usage: `find work`


### **Tagging an existing task: tag**

#### Adds a `tag` to the intended task.
1. Format: `tag INDEX_OF_TASK_IN_LIST`
1. Example of usage: `tag 5`


### **Finding a task which has a tag of specific keywords: `tagged`**

#### Lists tasks which contain the keywords in the tag.
1. Format: `tagged KEYWORDS`
1. Example of usage: `tagged work`


### **Deleting a task: `delete`**

#### Removes a task from the task list.
1. Format: `delete INDEX_OF_TASK_IN_LIST`
1. Example of usage: `delete 5`


### **Exiting the app: `by`e**

#### Leaves the app and saves the existing list.
1. Format: `bye`
1. Example of usage: `bye`