# Chadbot - User Guide

## Introduction
**Chadbot** is a greenfield Java project modified from the _Duke_ project. The project was created for NUS's software
engineering module, CS2103T.

Chadbot is a simple application which will help you keep track of your tasks. Chadbot differentiates between the
following types of tasks:
* Todos: Tasks without any special features.
* Deadlines: Tasks which are to be completed by a specified date.
* Events: Tasks which are held on a specified date.

## Features
1. **Create:** adds a task into the task list.
    ```
   todo <description>
   deadline <description> /by <date>
   event <description> /at <date>
   ```
1. **Read:** shows the tasks in the task list.
    ```
   list
   ```
1. **Update:** edits a task in the task list.
    ```
   edit <index> /desc <description>
   edit <index> /date <date>
   ```
1. **Delete:** removes a task from the task list.
    ```
   delete <index>
   ```
1. **Exit:** terminates Chadbot.
    ```
   bye
   ```
1. **Search:** finds a list of tasks associated with a date or containing a keyword.
    ```
   list <date>
   find <keyword>
   ```
1. **Mark:** marks a task in the task list as done.
    ```
   done <index>
   ```
1. **Arrange:** sorts the list of tasks into the specified ordering.
    ```
   sort
   sort /by type
   sort /by date
   ```
1. **Stats:** shows the tally of to-dos, deadlines, and events in the task list.
    ```
   stats
   ```
1. **Help:** displays a help page containing commands supported by Chadbot.
    ```
   help
   help /more
   ```

## Usage

1. ### Create

    * `todo <description>` - Adds a _to-do_ to the task list.

        **Example of usage:** `todo cs2103t ip`
        
        Adds a simple _to-do_ with description "cs2103t ip" to the task list.

        **Expected outcome:**
        
        ![todo](images/todo.png)

    * `deadline <description> /by <date>` - Adds a _deadline_ to the task list.

        **Example of usage:** `deadline cs2104 tutorial /by 2021-02-12`
        
        Adds a _deadline_ with description "cs2104 tutorial" and due date "2021-02-12" to the task list.

        **Expected outcome:**

        ![deadline](images/deadline.png)

    * `event <description> /at <date>` - Adds an _event_ to the task list.

        **Example of usage:** `event cs2105 exam /at 2021-03-21`
        
        Adds an _event_ with description "cs2105 exam" and date "2021-03-21" to the task list.

        **Expected outcome:**

        ![event](images/event.png)

1. ### Read
 
    * `list` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`

1. ### Update

    * `edit` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`

1. ### Delete

    * `delete` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`

1. ### Exit

    * `bye` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`

1. ### Search

    * `find` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`

1. ### Mark

    * `done` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`
        
1. ### Arrange

    * `sort` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`
        
1. ### Stats

    * `stats` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`
        
1. ### Help

    * `help` - Describe action

        Describe action and its outcome.

        Example of usage: 

        `keyword (optional arguments)`

        Expected outcome:

        `outcome`
