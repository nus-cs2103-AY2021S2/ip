# B.O.B Task Manager User Guide
A todo task manager bot to record and store list of tasks.

# Features
Below are the features currently implemented:

1. [**`Add`**](#add) Adding tasks to the bot.
   - 1.1 Todo
   - 1.2 Event
   - 1.3 Deadline
2. [**`List`**](#list) Listing all existing tasks.
3. [**`Marking Done`**](#done) Mark completed tasks as done.
4. [**`Delete`**](#delete) Deleting tasks using index.
5. [**`Storage & Retrieval`**](#storage) Storing of tasks in local drive.
6. [**`Find`**](#find) Finding tasks based on keywords.
7. [**`Remind`**](#remind) Remind Users about upcoming tasks.

## Feature Details

### :large_blue_circle: Feature 1 - Adding tasks<a name="add"></a>
The user will be able to add new tasks into B.O.B Task Manager. 

There are 3 types of tasking of which the user can
segment his tasks into.
- Todo
- Event
- Deadline

### Todo
Adds a todo task with **task description** into B.O.B Task Manager.

**Command Syntax**
    
    todo <task description> 


**Example of usage:**

    todo read book

**Expected outcome:**

    ADDED: [TODO][X] read book

![image](./images/add-todo.png)

### Event
Adds an event task with the **task description** and 
**Date and Time** into B.O.B Task Manager.

**Command Syntax**
    
    event <task description>/at<Date & Time>


**Date & Time format:** `yyyy-MM-dd HHmm`

**Example of usage:**

    event watch movie/at2021-02-14 1700

**Expected outcome:**

    ADDED: [EVENT][X] watch movie (at: Feb 14 2021, 17:00)

![image](./images/add-event.png)

### Deadline
Adds a deadline task with the **task description** and
**Date and Time** into B.O.B Task Manager.
      
**Command Syntax**
    
    deadline <task description>/by<Date & Time>


**Date & Time format:** `yyyy-MM-dd HHmm`

**Example of usage:**
 
    deadline return book/by2021-03-11 2359

**Expected outcome:**

    ADDED: [DEADLINE][X] return book (by: Mar 11 2021, 23:59)

![image](./images/add-deadline.png)

---

### :large_blue_circle: Feature 2 - Listing tasks<a name="list"></a>
The user will be able to list all existing tasks in B.O.B Task Manager.

**Command Syntax** 
  
    list

**Example of usage:**

    list

**Expected outcome:**

    Ok Human. Here are your tasks:
        1. [TODO][X] read book
        2. [EVENT][X] watch movie (at: Feb 14 2021, 17:00)
        3. [DEADLINE][X] return book (by: Mar 11 2021, 23:59)

![image](./list.png)      

---

### 4. Marking Done  <a name="done"></a>
  
    done <index of task>

Marks the completion, indicated by a `X` or `✓`. Completed tasks will display `[✓]`.
The index of the task corresponds to the number of which it is listed, you can simply run `list`
to check the index of the task.

**Example of usage:**

    done 1

**Expected outcome:**

    Swee la, task done liao:
        [T][✓] eat genki sushi



## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
