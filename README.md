# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

# User Guide for Duke Memo

![duke1](https://user-images.githubusercontent.com/67280376/108384335-9f8c1600-7245-11eb-8b6a-990e3830b0fc.png)

![duke2](https://user-images.githubusercontent.com/67280376/108384416-b16db900-7245-11eb-8769-92e7f3970c8c.png)

![duke3](https://user-images.githubusercontent.com/67280376/108384424-b3377c80-7245-11eb-8d43-f66288539e9f.png)

## Features 

1. add tasks
2. mark tasks as done
3. list out tasks
4. delete tasks


## Usage
i. add tasks by using `add "x" description` where `x` can be `event`, `deadline` or `todo`

ii. mark tasks as done by using `done "task number"`

iii. delete tasks by using `delete "task number`

iv. show tasks by using `list`


### `Keyword` - sample actions

Example of usage: 

`add todo learn java`

Expected outcome:

`Got it, I have added the following task to the list`

Example of usage:

`bye`

Expected outcome:

`Program exist`
