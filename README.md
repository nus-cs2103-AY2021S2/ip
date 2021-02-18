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
1. After that, locate the `src/main/java/justin/Launcher.java` file, right-click it, and choose `Run Launcher.main()`. If the setup is correct, you should see something like the below:
![Justin Image](https://mrweikiat.github.com/ip/Ui.png)
1. Alternatively, you can run the jar file named justin.jar if you have JDK 11

## Commands in Justin 

Features 

1. Instructions can be displayed by using the command key: help
   * This will display all the basic commands that Justin can do
1. Justin can show you all the tasks User added by the command key: list
1. User can add a todo task by using the command key: todo<space>td/[Task Name]
   * eg: User wants to add a todo task
      1. User keys in: todo finish iP 
      1. Justin adds the new todo task and display the newly added task
1. User can add a deadline task by using the command key: deadline<space>d/[Task Name]<space>/by<space>[YYYY-MM-DD]
   * eg: User wants to add a deadline task
      1. User keys in: deadline need some sleep /by 2020-02-14
      1. Justin adds the new deadline task and display the newly added task
1. User can add an event task by using the command key: event<space>e/[Task Name]<space>/at<space>[YYYY-MM-DD HH:MM]
   * eg: User wants to add an event task
      1. User keys in: event fly me to the moon /at 2020-07-16 21:02
      1. Justin adds the new event task and display the newly added task
1. User can find tasks by using the command: find<space>[keyword]
   * eg: User wants to find a particuler task in the list
      1. User keys in: find where is my beer
      1. Justin will return a list of tasks that matches or contain the keywords of User's input
1. User can delete a task on the list by using the command: delete<space>[Number]
   * eg: User use the command list to view all tasks on the list
      1. After viewing the list, user decides to delete third task
      1. User key in command: delete 3
      1. Justin will delete the task and display the contents of the deleted task
1. Lastly, User can exit the application by using the command: bye
