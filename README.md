# Duke

Duke is a Personal Assistant Chatbot that helps to keep track of various things.

# Setting up
Prerequisites:
* JDK 11
* Fork this repo and clone it to your computer!

Alternatively, check out Duke by downloading the latest jar 
release and run java -jar v0.2.jar on your terminal in the same directory.

# User Guide
## Functionality
* Adding and deleting tasks: todos/ deadlines/ events
* Updating task description and information
* Marking task as completed
* Search for tasks



## Commands
**`help` - Returns list of main commands**  

**`todo` - Adds a todo task**

Format: todo TODO DESCRIPTION

Example: todo water the plants  

**`event` - Adds an event task**

Format: event EVENT DESCRIPTION /at YYYY-MM-DD

Example: event baby shower /at 2020-02-01  

**`deadline` - Adds a deadline task**

Format: deadline DEADLINE DESCRIPTION /by YYYY-MM-DD

Example: deadline homework /by 2020-02-01  

**`delete` - Deletes a task permanently**

Format: delete TASK NUMBER

Example: delete 2  

**`list` - Returns the list of all tasks**\

**`done` - Marks a task as complete**

Format: done TASK NUMBER

Example: done 1  


**`find` - Looks for a task from the provided description**
Format: find DESCRIPTION

Example: find baby shower  



