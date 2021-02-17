#<span style="color:#4285F4">B.O.B Task Manager User Guide</span>
A todo task manager bot to record and store list of tasks.

## <span style="color:#4285F4">Features</span>
Below are the features currently implemented:

1. [**`Add`**](#add) Adding tasks to the bot.
2. [**`List`**](#list) Listing all existing tasks.
3. [**`Delete`**](#delete) Deleting tasks using index.
4. [**`Marking Done`**](#done) Mark completed tasks as done.
5. [**`Storage & Retrieval`**](#storage) Storing of tasks in local drive.
6. [**`Find`**](#find) Finding tasks based on keywords.
7. [**`Remind`**](#remind) Remind Users about upcoming tasks.

### <span style="color:#4285F4">Feature 1 - Adding tasks</span><a name="add"></a>
The user will be able to add new tasks into B.O.B Task Manager. 

There are 3 types of tasking of which the user can
segment his tasks into.
- Todo
- Event
- Deadline

### Usage
- **Todo** 
    - `todo <task description>`

Adds a todo task with **task description** into B.O.B Task Manager.

**Example of usage:**

    todo read book

**Expected outcome:**

    ADDED: [TODO][X] read book

![image](./images/add-todo.png)

- **Event** 
    - `event <task description>/at<Date & Time>`

Adds an event task with the **task description** and 
**Date and Time** into B.O.B Task Manager.

**Date & Time format:** `yyyy-MM-dd HHmm`

**Example of usage:**

    event watch movie/at2021-02-14 1700

**Expected outcome:**

    ADDED: [EVENT][X] watch movie (at: 14 Feb 2021, 17:00)

![image](./images/add-event.png)

- **Deadline** 
    - `deadline <task description>/by<Date & Time>`

Adds a deadline task with the **task description** and
**Date and Time** into B.O.B Task Manager.

**Date & Time format:** `yyyy-MM-dd HHmm`

**Example of usage:**
 
    deadline return book/by2021-03-11 2359

**Expected outcome:**

    ADDED: [DEADLINE][X] return book (by: 11 Mar 2021, 23:59)

![image](./images/add-deadline.png)

### <span style="color:#4285F4">Feature 2 - Listing existing tasks</span><a name="list"></a>
The user will be able to list existing tasks in B.O.B Task Manager.

There are 3 types of tasking of which the user can
segment his tasks into.
- Todo
- Event
- Deadline


## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
