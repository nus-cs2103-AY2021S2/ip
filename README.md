# Duke project Jiaxiang

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
   
## Message regarding the ordering of tags

Hi Prof ,TAs and friends,

If you happen to be wandering why are the ordering of tags for the first 10 tags (speciafically level-2, level-4, A-textUI-test) messy and not at an incremental way, please let me explain.

I did develop the levels at an incremental way and commited accordingly as reflected in the commit history. However, I made some mistakes by tagging the commit on the respective branches where I developed level-2 and level-4 and A-textUI-test. Thus, after I merged these branches and deleted them, some of the tags are not captured by the plugin to capture iP progress.

As such, I decided to remove the old and incorrect tags and retagged those commits. A side effect is that the tags are now showing in a messy ordering.

Hope you can understand and have a nice day:)
