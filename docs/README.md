# <span style="color:#4285F4">B.O.B Task Manager User Guide</span>
A todo task manager bot to record and store list of tasks.
## Features 
Below are the features currently implemented:

1. [**`Add`**](#add) Adding tasks to the bot.
2. [**`List`**](#list) Listing of all existing tasks.
3. [**`Delete`**](#delete) of tasks no longer desired to be in the list.
4. [**`Marking Done`**](#done) Mark completed tasks as done.
5. [**`Storage & Retrieval`**](#storage) Storing of tasks in local drive.
6. [**`Find`**](#find) Finding tasks based on keywords.
7. [**`Remind`**](#remind) Remind Users about upcoming tasks.

### 1. Adding tasks to the bot <a name="add"></a>
The user will be able to add new tasks into B.O.B Task Manager. 

There are 3 types of tasking of which the user can
segment his tasks into.
- Todo
- Event
- Deadline

#### Commands
- **Todo** - `todo <task details>`

Simply adds a tasking with the 'task details' into PikaDuke.

**Example of usage:**

    todo eat genki sushi

**Expected outcome:**

    Steady! I add... wait ah...
        ADDED: [T][X] eat genki sushi
    Now you got 1 tasks

![image](./add-todo.png)

- **Event** - `event <task description>/at<Date & Time>`

Adds an event tasking with the 'task details' as well as Date and Time into PikaDuke.

**Date & Time format:** `yyyy-MM-dd HHmm`

**Example of usage:**

    event watch movie/at2021-02-14 1700

**Expected outcome:**

    ADDED: [EVENT][&#x2718] watch movie (at: 14 Feb 2021, 17:00)
    Now you got 2 tasks

![image](./add-event.png)

- **Deadline** - `deadline <task details> /by <Date & Time>`

Adds an deadline tasking with the 'task details' as well as Date and Time into PikaDuke.

**Date & Time format:** `yyyy-MM-dd HH:mm`

**Example of usage:**

    deadline eat steak /by 2020-11-14 11:00

**Expected outcome:**

    Steady! I add... wait ah...
        ADDED: [D][X] eat steak (by: 14 Nov 2020, 11:00AM)
    Now you got 3 tasks

![image](./add-deadline.png)

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
