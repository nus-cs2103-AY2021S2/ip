# Duke User Guide
By: `@arihantjain97`      Since: `Feb 2021`      Licence: `MIT`

## 1. Introduction
Duke is a versatile organiser for those who wish to keep track of different types
of tasks. Duke also offers a Graphical User Interface, which allows for the user to 
see the tasks entered at a glance.
## 2. Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest duke.jar [here](https://github.com/arihantjain97/ip/releases/tag/A-Jar).

3. Copy the file to the folder you want to use as the home folder for your Duke application.

4. Double-click the file to start the app. The GUI should appear in a few seconds.

   
  ![Welcome Screen](https://github.com/arihantjain97/ip/blob/master/docs/WelcomeScreen.png?raw=true)

5. After pressing the start button, you will be taken to the main page


  ![Welcome Screen](https://github.com/arihantjain97/ip/blob/master/docs/Ui.png?raw=true)

6. Type in the command in the command box and press enter, or the send button, to execute it.
      
   e.g. typing `list` and pressing enter, or the send button, will show the current list of tasks.

7. Some example commands you can try:
   
   * list: lists all current tasks
   * delete 1: deletes the first indexed task in duke
   * bye: exits the app
   
8. Refer to Section 3, "Features" for details of each command

## 3. Features

### 3.1. Show all Tasks: `list`
* Format: `list`
* Example: `list`
* Expected Outcome:
   
   `Here are the tasks in your list:`

   `1.[T][] drink enough water`

   `2.[D][] finish homework (by: 01 March 2020)`
   
   `3.[E][] class (at: 01 January 2020 0900-1000)`

### 3.2. Add a "Todo" Task: `todo`
* Format: `todo DESCRIPTION` 
* Example: `todo drink enough water`
* Expected Outcome:

   `Got it. I've added this task:`
   
   `[T][] drink enough water`
   
   `Tasks in list: 1`
### 3.3. Add a "Deadline" Task: `deadline`
* Format: `deadline DESCRIPTION /by DATE`
  
   the date format is in `yyyy-mm-dd`
* Example: `deadline finish homework /by 2020-03-01`
* Expected Outcome:
  
  `Got it. I've added this task:`

  `[D][] finish homework (by: 01 March 2020)`

  `Tasks in list: 2`
### 3.4. Add an "Event" Task: `event`
* Format: `event DESCRIPTION /at DATE TIME`

  the date format is: `yyyy-mm-dd`
  
  the time format is: `HHmm-HHmm`
* Example: `event class /at 2020-01-01 0900-1000`
* Expected Outcome:

  `Got it. I've added this task:`

  `[E][] class (at: 01 January 2020 09:00-10:00)`

  `Tasks in list: 3`
### 3.5. Mark a task when completed: `done`
* Format: `done INDEX`
* Example: `done 3`
  
   Index is based on `list` produced task listing
* Expected Outcome:

  `Nice! I've marked this task as done:`

  `[E][X] class (at: 01 January 2020 09:00-10:00)`
### 3.6. Delete a task: `delete`
* Format: `delete INDEX`
* Example: `delete 1`

  Index is based on `list` produced task listing
* Expected Outcome:
  
  `Noted. I've removed this task:`

  `[T][] drink enough water`

  `Tasks in list: 2`
### 3.7. Find task via a keyword: `find`
* Format: find KEYWORD
* Example: `find class`
  
   keyword should be a matching word or letter in description of tasks
* Expected Outcome:

  `Matched Tasks:`

  `1.[E][X] class (at: 01 January 2020 09:00-10:00)`

  `Tasks in list: 2`
### 3.8. Sorting tasks chronologically: `sort`
* Format: `sort`
* Example: `sort`
* Expected Outcome:

  `Here are the tasks in your list:`
  
  `1.[E][X] class (at: 01 January 2020 0900-1000)`
  
  `2.[D][] finish homework (by: 01 March 2020)`

  

### 3.9. Exiting the program: `bye`
* Format: `bye`
* Example: `bye`
* Expected Outcome: the program terminates
### 3.10. Saving the data
* Duke automatically saves your data.

## 4. FAQ

Q:  How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

## 5. Command Summary
* list: `list`

* todo: `todo DESCRIPTION`

* deadline: `deadline DESCRIPTION /by DATE`

* event: `event DESCRIPTION /at DATE TIME`

* done: `done INDEX`

* delete: `delete INDEX`

* find: `find KEYWORD`

* sort: `sort`

* exit: `bye`

User guide format above adapted from [addressbook level 3](https://github.com/nus-cs2103-AY1920S2/addressbook-level3/blob/master/docs/UserGuide.adoc#Features)