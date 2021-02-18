# Juke Bot User Guide

Welcome to Juke's user guide. This is a comprehensive user guide to teach you how to use the chat bot, Juke.

Juke is a command-line based Task Manager application. A user will type a series of command to instruct Juke.

## User Interface
![UI](Ui.png)

## Content Page
Here is an overview of the commands that Juke supports and a user can input.
* [`help`](#help-command)
* [`todo`](#todo-command)
* [`deadline`](#deadline-command)
* [`event`](#event-command)
* [`delete`](#delete-command)
* [`find`](#find-command)
* [`done`](#done-command)
* [`list`](#list-command)
* [`bye`](#bye-command)
<br/><br/>


## Features 

### Task Manager
Juke Chat Bot is a personal task manager. It allows users to add, delete and search tasks.

## Usage

# `help` command
* Description: Shows a list of supported commands and their descriptions.
* Format: `help`
* Example: `help`

<img height="740" width="512" src="https://raw.githubusercontent.com/branzuelajohn/ip/master/docs/images/help.png">

### `todo` command
* Description: Creates a todo task and adds it to your task list.
* Format: `todo <description>`
* Example: `todo read book`
* Expected outcome: 
```
Got it. I've added this task. 
[T][X] read book
Now you have XX tasks in the list.
```
<p align="center">
  <img height="740" width="512" src="https://raw.githubusercontent.com/branzuelajohn/ip/master/docs/images/todo.png">
</p>


### `deadline` command
* Description: Creates a deadline task due at a specific date and time and adds it to your task list.
* Format: `deadline <description> /by <deadline date>`
* Example: `deadline group project /by 2021-01-10 1800` 
  Note: `deadline date` has to be in the `yyyy-MM-dd HH:mm` or `yyyy-MM-dd` format or else an exception will be thrown. 
* Expected outcome: 
```
Got it. I've added this task. 
[D][X] group project (by:Jan 10 2021 6PM) 
Now you have XX tasks in the list.
```
<p align="center">
<img height="740" width="512" src="https://raw.githubusercontent.com/branzuelajohn/ip/master/docs/images/deadline.png">
</p>

### `event` command
* Description: Creates an event task with an event at a specific date and time and adds it to your task list.
* Format: `event <description> /at <event time>`
* Example: `event meeting /at Wednesday 10PM` 
* Expected outcome: 
```
Got it. I've added this task. 
[E][X] meeting (at:Wednesday 10PM) 
Now you have XX tasks in the list.
```
<p align="center">
<img height="740" width="512" src="https://raw.githubusercontent.com/branzuelajohn/ip/master/docs/images/event.png">
</p>

### `delete` command
* Description: Shows a list of supported commands and their descriptions.
* Format: `delete <taskID>`
* Example: `delete 1`
<p align="center">
<img height="740" width="512" src="https://raw.githubusercontent.com/branzuelajohn/ip/master/docs/images/delete.png">
</p>

### `find` command
* Description: Shows a list of supported commands and their descriptions.
* Format: `find <regex>`
* Example: `find read`
<p align="center">
<img height="740" width="512" src="https://raw.githubusercontent.com/branzuelajohn/ip/master/docs/images/find.png">
</p>

### `done` command
* Description: Shows a list of supported commands and their descriptions.
* Format: `done <taskID>`
* Example: `done 1`
<p align="center">
<img height="740" width="512" src="https://raw.githubusercontent.com/branzuelajohn/ip/master/docs/images/done.png">
</p>

### `list` command
* Description: Shows a list of supported commands and their descriptions.
* Format: `list`
* Example: `list`
<p align="center">
<img height="740" width="512" src="https://raw.githubusercontent.com/branzuelajohn/ip/master/docs/images/list.png">
</p>

### `bye` command
* Description: Exits the application.
* Format: `bye`
* Example: `bye`





