# User Guide

## Get Started

If you are new to Elaina, it is advised to read [Getting Started](/pages/getting-started) first!

## Usage

Lorum ipsum

## Features

Lorum ipsum

## Appendix A: List of Commands

::: tip Notes
- each command consists of **tokens** that can be:
  - either a single word (characters without whitespaces)
  - or some words surrounded by single or double quotation marks
- `[...]`: optional arguments (tokens between square brackets are not necessary)
- `<TASKNAME>`: a *token* that represents the name of the task
- `<DATETIME>`: a *token* that represents a date time in the format of `dd-mm-yyyy HH:MM` **(must be surrounded by single or double quotation marks because of the space in between)**
- `<NUM>`: a number (e.g. `0`, `1`, `2`)
- `<REGEX>`: a *token* in the form of a [regular expression](https://www3.ntu.edu.sg/home/ehchua/programming/howto/Regexe.html)
:::

Below is a list of all supported commands in alphabetical order:

| Command  | Format                                 | Description                            |
|:--------:|:--------------------------------------:|:--------------------------------------:|
| bye      | `bye`                                  | exit                                   |
| clear    | `clear`                                | clear screen                           |
| deadline | `deadline <TASKNAME> [/by <DATETIME>]` | add a new deadline task                |
| delete   | `delete <NUM>`                         | delete a task                          |
| done     | `done <NUM>`                           | mark a task as done                    |
| event    | `event <TASKNAME> [/by <DATETIME>]`    | add a new event task                   |
| exit     | `exit`                                 | exit                                   |
| find     | `find <REGEX>`                         | search tasks by task name (with regex) |
| help     | `help`                                 | show help                              |
| list     | `list`                                 | list all tasks added                   |
| quit     | `quit`                                 | exit                                   |
| sayonara | `sayonara`                             | exit                                   |
| todo     | `todo <TASKNAME>`                      | add a new todo task                    |
| さよなら  | `さよなら`                             | exit                                   |
