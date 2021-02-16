# User Guide

# Introduction
Welcome to RoboBot. This is an application for you to record your tasks into a list.
![Image of RoboBot](./Ui.png)

## Features and Usage
### 1. `ToDo` task
Add a Todo task into Robobot.
#### Format:
- todo [task description]
  
#### Example of usage:
- todo read book

#### Expected Outcome:
[T][ ] read book

### 2. `Event` task
Add an event task that has date and time allocated to it into Robobot.
#### Format:
- event [task description] /at [date]

#### Example of usage:
- event project 1 /at 2020-02-11

#### Expected Outcome:
[E][ ] project 1 (at: Feb 11 2020)


### 3. `Deadline` task
Add a deadline task that has date and time allocated to it into Robobot.
#### Format:
- deadline [task description] /by [date]

#### Example of usage:
- event project 1 /by 2020-02-11

#### Expected Outcome:
- [D][ ] project 1 (by: Feb 11 2020)


### `Delete` task
Delete an existing task from the RoboBot task list.
#### Format:
- delete [index]

#### Example of usage:
- delete 1

#### Expected Outcome:
- deleted the first task in the list.
- error if the list is empty.
- error if index > the size of the list.


### `Done` task
Check an existing task from the RoboBot task list as completed.
#### Format:
- done [index]

#### Example of usage:
- done 1

#### Expected Outcome:
- complete the first task in the list.
- error if the list is empty.
- error if index > the size of the list.


### `Find` task
Find task(s) that has a certain keyword associated to it.
#### Format:
- Find [keyword]

#### Example of usage:
- find book

#### Expected Outcome:
- display the task that has description containing "book".


### `Schedule` Task
Find task(s) that has a certain date and time associated to it.
#### Format:
- schedule /on [date]

#### Example of usage:
- schedule /on 2020-02-11

#### Expected Outcome:
- display the task that has the date time associated to "2020-02-11"


### `List` task
List down all the task(s) that RoboBot are currently recording.
#### Format:
- list

#### Example of usage:
- list

#### Expected Outcome:
- List down all the task(s) recorded in Robobot.
