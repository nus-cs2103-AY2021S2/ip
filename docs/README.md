# Justin project

This is a instruction page for a greenfield Java project. It's named Justin which stands for JUSt a TImetable(New). Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/justin/Launcher.java` file, right-click it, and choose `Run Launcher.main()`. 
1. Alternatively, you can run the jar file named justin.jar if you have JDK 11
1. If the setup is correct, you should see something like the below:

![Justin Image](https://github.com/mrweikiat/ip/blob/master/docs/Ui.png)



## Commands in Justin 

### Content Page
* help
* list
* todo
* deadline
* event
* find
* delete
* bye

### Features 

`help`
   * Instructions can be displayed by using the command key: help
      * This will display all the basic commands that Justin can do
 
*Expected input*
````
help
````
*Expected output*
````
To add a todo, use command
eg: todo code cs2103
To add a deadline, use command:
eg: deadline fly kite /by 2020-06-10
To add a event, use command:
event finish iP /at 2020-02-18 18:00
To find similar tasks, use eg: find my CAP
````

`list`
   * Justin can show you all the tasks User added by the command key: list
         * User can add a todo task by using the command key: todo td/[Task Name]
         * eg: User wants to add a todo task
              1. User keys in: todo finish iP 
              1. Justin adds the new todo task and display the newly added task
             
*Expected input*
````
list
````
*Expected output*
````
Here are the tasks in your list:
1. [T][COMPLETED] finish iP 
2. [E][INCOMPLETE] finish tP (at: Jun 19 2020 18:00)
````

`deadline`
   * User can add a deadline task by using the command key: deadline<space>d/[Task Name] /by [YYYY-MM-DD]
      * eg: User wants to add a deadline task
         1. User keys in: deadline need some sleep /by 2020-02-14
         1. Justin adds the new deadline task and display the newly added task

*Expected input*
````
deadline catch dog /by 2020-06-10
````
*Expected output*
````
Got it, I've added this task:
   [D][INCOMPLETE] catch dog (by: Jun 10 2020)
Now you have 1 task in the list
````

`event`
   * User can add an event task by using the command key: event e/[Task Name] /at [YYYY-MM-DD HH:MM]
      * eg: User wants to add an event task
         1. User keys in: event fly me to the moon /at 2020-07-16 21:02
         2. Justin adds the new event task and display the newly added task

*Expected input*
````
event catch cat /at 2020-06-10 18:00
````
*Expected output*
````
Got it, I've added this task:
   [E][INCOMPLETE] catch cat (by: Jun 10 2020 18:00)
Now you have 1 task in the list
````


`find`
   * User can find tasks by using the command: find [keyword]
      * eg: User wants to find a particuler task in the list
         1. User keys in: find where is my beer
         2. Justin will return a list of tasks that matches or contain the keywords of User's input

*Expected input*
````
find catch
````
*Expected output*
````
Here are the matching tasks in your list
1. [D][INCOMPLETE] catch dog (by: Jun 10 2020)
2. [T][COMPLETED] catch cat 
````

`delete`
   * User can delete a task on the list by using the command: delete [Number]
      * eg: User use the command list to view all tasks on the list
         1. After viewing the list, user decides to delete third task
         2. User key in command: delete 3
         3. Justin will delete the task and display the contents of the deleted task

*Expected input*
````
delete 3
````
*Expected output*
````
Noted. I've removed this task
   [E][INCOMPLETE] catch cat (by: Jun 10 2020 18:00)
Now you have 1 task in the list
````

`bye`
   * Lastly, User can exit the application by using the command: bye

*Expected input*
````
bye
````
*Expected output*
````
See you soon again!
````

