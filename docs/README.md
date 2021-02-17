# User Guide

Duke is **desktop app for managing tasks**.

## Table of contents

- [User Guide](#user-guide)
  - [Quick start](#quick-start)
  - [Features](#features)
    - [Add a todo : `todo`](#add-a-todo--todo)
    - [Add a deadline : `deadline`](#add-a-deadline--deadline)
    - [Add an event : `event`](#add-an-event--event)
    - [List all tasks : `list`](#list-all-tasks--list)
    - [Find a task : `find`](#find-a-task--find)
    - [Set task as done : `done`](#set-task-as-done--done)
    - [Delete a task : `delete`](#delete-a-task--delete)
    - [Exit Duke : `bye`](#exit-duke--bye)
  - [Acknowledgement](#acknowledgement))

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/stein414/ip/releasess).

1. Copy the file to the folder you want to use as the _home folder_ for Duke.

1. Double-click the file to start the app. A GUI should appear in a few seconds.

3. Type a command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will list all tasks in Duke.<br>
   Some example commands you can try:

    * **`list`** : Lists all tasks.

    * **`todo`**` task 1` : Adds a task with description `task 1`.

    * **`deadline`**` assignment /by 2021-12-20` : Adds a deadline with description `assignment` and done by date `2021-12-20`.

    * **`event`**` birthday party /at 2021-01-15` : Adds a event with description `birthday party` and at date `2021-01-15`.

    * **`done`**`1` : Sets the 1st task to be done.

    * **`del`**`1` : deletes the 1st task to be done.

    * **`bye`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

## Features 

<div markdown="block" class="alert alert-info">
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

</div>

### Add a todo : `todo`

Adds a todo.

Format: `todo DESCRIPTION`

Examples:

* `todo Buy groceries` Adds a task with description `Buy groceries`.
* `todo Sell kidney` Adds a task with description `Sell kidney`.


### Add a deadline : `deadline`

Adds a deadline.

Format: `deadline DESCRIPTION /by DATE`

Examples:

* `deadline Submit final project /by 2021-04-20` Adds a deadline with description `Submit final project` and done by date `2021-04-20`.
* `deadline Pay off debt /by 2021-06-30` Adds a deadline with description `Pay off debt` and done by date `2021-06-30`.


### Add an event : `event`

Adds an event.

Format: `event DESCRIPTION /at DATE`

Examples:

* `event New Year celebration /at 2020-12-31` Adds an event with description `New Year celebration` and at date `2020-12-31`.
* `event Drunk driving /at 2021-01-01` Adds an event with description `Drunk driving` and at date `2021-01-01`.


### List all tasks : `list`

List all tasks in order of creation.

Format: `list`


### Find a task : `find`

Finds tasks that matches the given query.

Format: `find QUERY`

Example:

* `find birthday` returns `Buy birthday gift` and `Setup birthday venue`.


### Set task as done : `done`

Sets a tasks as done.

Format: `done INDEX`

Example:

* `done 1` Sets the 1st task in the list as done.


### Delete a task : `delete`

Deletes a task.

Format: `delete INDEX`

Example:

* `deletes 1` Deletes the 1st task in the list.


### Exit Duke : `bye`

Exits Duke.

Format: `bye`


## Acknowledgement

* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)