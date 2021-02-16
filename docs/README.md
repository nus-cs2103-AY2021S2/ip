# User Guide
Welcome to LukBot!

LukBot is here to help you keep track of your activities, as well as your friends!

When using LukBot, simply type 'help' if you are unsure of what you can do.

## Features 

### Managing Tasks
LukBot offers task management! You can add, remove, view and search for specific tasks. 
You can also mark your tasks as done if you have completed them. LukBot also allows you to 
categorise your tasks into three types: To-dos, Deadlines and Events! 

### Managing Contacts
LukBot also offers contact management! You can add, remove, edit and remove contacts. Keeping track
of your friends' phone numbers and addresses has never been easier!

## Commands

### Adding Tasks

* #### `todo` - Adds To-do

  Adds a **To-do** to your task list.
  
Example of usage:

    todo Homework

Expected outcome:

    I have added the following task to your list:
    [T][ ] Homework
    There are 1 tasks in your list. I hope this pleases you.

* #### `deadline` - Adds Deadline

  Adds a **Deadline** to your task list.

Example of usage:

    deadline IP Submission /by 19/02/2021 2359

Expected outcome:

    I have added the following task to your list:
    [D][ ] IP submission (by: 19-02-2021 2359)
    There are 2 tasks in your list. I hope this pleases you.

* #### `event` - Adds Event

  Adds an **Event** to your task list.

Example of usage:

    event Xmas Party /from 25/12/2021 1700 /to 25/12/2021 2300

Expected outcome:

    I have added the following task to your list:
    [E][ ] Xmas Party (from: 25-12-2021 1700 - 25-12-2021 2300)
    There are 3 tasks in your list. I hope this pleases you.

### Managing Task List

* #### `delete` - Deletes Task

  **Delete** a task from your task list.

Example of usage:

    delete 2

Expected outcome:

    I have removed the following task from your list:
    [D][ ] IP submission (by: 19-02-2021 2359)
    Does this bring you the satisfaction you so crave?

* #### `done` - Checks Task

  Marks a task from your task list as **Done**.

Example of usage:

    done 1

Expected outcome:

    Congratulations on conquering this task:
    [T][X] Homework
    You are one step closer to victory

* #### `find` - Finds Tasks

  **Finds** tasks from your task list containing a *keyword*.

Example of usage:

    find Xmas

Expected outcome:

    Here are the tasks that fit your search criteria:
    [E][ ] Xmas Party (from: 25-12-2021 1700 - 25-12-2021 2300)
    I can only pray that I have been of use to you.

* #### `list` - Lists Tasks

  **List** all tasks from your task list.

Example of usage:

    list

Expected outcome:

    Here are the tasks in your list:
    1. [T][X] Homework
    2. [E][ ] Xmas Party (from: 25-12-2021 1700 - 25-12-2021 2300)
    Do what you will with this information.

### Managing Contacts

* #### `contact add` - Adds Contact

  **Adds** a contact to your contact list.

Examples of usage:

    contact add /name Romelu /number 98765432 /address San Siro, Milan, Italy

    contact add /name Fellaini /address Goodison Park, Liverpool, England

    contact add /name Richard Tan /number 98128723

Expected outcome:

    I have added a new companion to your list of allies:
    Romelu [Num: 98765432] [Address: San Siro, Milan, Italy]
    Keep your friends close, and your enemies closer.

    I have added a new companion to your list of allies:
    Fellaini [Address: Goodison Park, Liverpool, England]
    Keep your friends close, and your enemies closer.

    I have added a new companion to your list of allies:
    Richard Tan [Num: 98128723]
    Keep your friends close, and your enemies closer.

* #### `contact delete` - Deletes Contact

  **Delete** a contact from your contact list.

Examples of usage:

    contact delete 3

Expected outcome:

    Friends turn to foes in a blink of an eye. I shall remove:
    Richard Tan [Num: 98128723]    
    I pray for your safety.

* #### `contact edit` - Edits Contact

  **Edits** a contact from your contact list.

Examples of usage:

    contact edit 1 /name Romelu Lukaku

    contact edit 2 /number 91827364

    contact edit 2 /address UTown Residence

Expected outcome:

    Your mistake has been forgiven. Do check if this is the correct contact:
    Romelu Lukaku [Num: 98765432] [Address: San Siro, Milan, Italy]
    Feel free to make as many mistakes as you please. I am here to resolve all.

    Your mistake has been forgiven. Do check if this is the correct contact:
    Fellaini [Num: 91827364] [Address: Goodison Park, Liverpool, England]
    Feel free to make as many mistakes as you please. I am here to resolve all.

    Your mistake has been forgiven. Do check if this is the correct contact:
    Fellaini [Num: 91827364] [Address: UTown Residence]
    Feel free to make as many mistakes as you please. I am here to resolve all.

* #### `contact list` - List Contacts

  **Lists** all contacts from your contact list.

Examples of usage:

    contact list

Expected outcome:

    Here are your reliable comrades:
    1. Romelu Lukaku [Num: 98765432] [Address: San Siro, Milan, Italy]
    2. Fellaini [Num: 91827364] [Address: UTown Residence]
    Do not fear to call upon their friendship.

## Acknowledgement

Reused @banchiang code for GUI implementation.
