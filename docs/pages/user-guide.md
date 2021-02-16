# User Guide

## Get Started

If you are new to Elaina, it is advised to read [Getting Started](/pages/getting-started) first!

## Features

### Commands

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

#### `todo` - add a new todo task

- Format: `todo <TASKNAME>`
- Description:
  - Create a new todo task with the specified name.
- Examples:
  - `todo sleep`
  - `todo 'do homework'`
  - `todo "do code review"`

#### `event` - add a new event task

- Format: `event <TASKNAME> [/at <DATETIME>]`
- Description:
  - Create a new event task with the specified name (and date time if provided).
- Examples:
  - `event tutorial`
  - `event 'CS2101 OP' /at '05-02-2021 10:00'`
  - `event サヤの誕生日 /at "08-03-2021 08:11"`

#### `deadline` - add a new deadline task

- Format: `deadline <TASKNAME> [/by <DATETIME>]`
- Description:
  - Create a new deadline task with the specified name (and date time if provided).
- Examples:
  - `deadline "CS6666 Assignment"`
  - `deadline 'CS2101 deadline' /by '19-02-2021 23:59'`
  - `deadline "Prepare Elaina's birthday present" /by "01-08-2021 05:20"`

#### `clear` - clear chat history

- Format: `clear`
- Description:
  - Clear screen.
- Examples:
  - `clear`

#### `delete` - delete a task

- Format: `delete <NUM>`
- Description:
  - Delete a saved task of the given number.
- Examples:
  - `delete 0`
  - `delete 1`

#### `find` - search for tasks

- Format: `find <REGEX>`
- Description:
  - Search for tasks by [regular expression](https://www3.ntu.edu.sg/home/ehchua/programming/howto/Regexe.html).
- Examples:
  - `find assignment`
  - `find "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)"`

#### `list` - list all tasks

- Format: `list`
- Description:
  - List all tasks that have been added.
- Examples:
  - `list`

#### `exit` / `bye` / `quit` / `sayonara` / `さよなら` - exit from Elaina

- Format: `exit` / `bye` / `quit` / `sayonara` / `さよなら`
- Description:
  - Exit from Elaina.
- Examples:
  - `exit`
  - `bye`
  - `quit`
  - `sayonara`
  - `さよなら`

#### `help` - show help message

- Format: `help`
- Description:
  - Show a help message.
- Examples:
  - `help`

### Others

- Clickable links
  - URLs starting with `http` and `https` are automatically converted to clickable links

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
| event    | `event <TASKNAME> [/at <DATETIME>]`    | add a new event task                   |
| exit     | `exit`                                 | exit                                   |
| find     | `find <REGEX>`                         | search tasks by task name (with regex) |
| help     | `help`                                 | show help                              |
| list     | `list`                                 | list all tasks added                   |
| quit     | `quit`                                 | exit                                   |
| sayonara | `sayonara`                             | exit                                   |
| todo     | `todo <TASKNAME>`                      | add a new todo task                    |
| さよなら  | `さよなら`                             | exit                                   |
