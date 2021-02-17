# User Guide

<p align="center"><img src="https://soonkeatneo.github.io/ip/Ui.png"/></p>
<h1 align="center">DukeNukem</h1>

## Table of Contents
* [Introduction](#introduction)
* [Getting Started](#getting-started)
* [Features](#features)
* [Advanced Setup](#advanced-setup)

## Introduction

DukeNukem is a Java-based application that aims to help remind you of upcoming tasks, deadlines and events.

## Getting Started

Prerequisites: JDK 11

1. Download the latest release from [here](https://github.com/SoonKeatNeo/ip/releases/). 
1. Double click the downloaded JAR file. If pathing is not set up, then you will have to use: `java -jar dukenukem.jar`.

## Features

### View all Tasks
Shows all tasks that are present in the list.
Command: `list`

### Find a Task
Format: `find <item>`
Example: `find abcd`, where the task I wanted to find has `abcd` as a part of it.
Expected Outcome: Tasks matching the string would be printed.
<p align="center">

</p>

### Clear all Tasks
Command: `clear`
Expected Outcome: All tasks will be cleared.

### Add To-Do
Format: `todo <description>`
Example: `todo Bring the kids to school`
Expected Outcome: Adds a new To-Do with the given details.
<p align="center">

</p>

### Add Event
Format: `event <description> /at YYYY-MM-DD`
Example: `event Meeting with Minister /at 2021-02-20`
Expected Outcome: Adds a new Event with the given details.
<p align="center">

</p>

### Add Deadline
Format: `deadline <description> /by YYYY-MM-DD`
Example: `deadline Submit Project /by 2021-02-19`
Expected Outcome: Adds a new Deadline with the given details.
<p align="center">

</p>


### Mark as Complete
Format: `done <task number>`
Example: `done 2`
Expected Outcome: Marks the 2nd task in the list as complete.
<p align="center">

</p>

### Delete a Task
Format: `delete <task number>`
Example: `delete 2`
Expected Outcome: Deletes the 2nd task in the list.
<p align="center">

</p>

### Show Help
Command: `help`
Expected Outcome: Help text would be printed.

### Exit
Command: `bye`
Expected Outcome: Application is exited.
