# User Guide
![Duke Screenshot](./Ui.png)

Duke is a personal chatbot that helps you manage your tasks. He can track your Todos, Events, and Deadlines. He comes in the form of a simple GUI with CLI.
## Quick Start
1. Ensure you have Java 11 or above installed on your Computer.
2. Download the latest `ip.jar` [here](https://github.com/cnlinh/ip/releases).
3. Double-click the file to start the app. The GUI should launch in a few seconds.
4. Type a command into the command box and press <kbd>Enter</kbd> or click <kbd>Send</kbd> to execute it.

### Format of this guide
- User input is prefixed with `$`.
- Required parts are surrounded by angle brackets `<>`.
- The brackets are not part of the actual commands.

### Features
Duke can support 3 types of tasks: Todos, Deadlines and Events. You can create, read or delete these tasks, or mark them as done. If the list gets too long, Duke can also help you search for tasks.

## Usage
#### `list` &mdash; List Tasks
Lists all the Tasks you have asked Duke to remind you.
```
$ list
```

#### `find` &mdash; Find Tasks
Searches for Tasks with descriptions containing the key word. Duke can find it even if you make some minor typos.
```
$ find <key_word>
```

#### `done` &mdash; Mark a Task as done
Marks a Task at the specified index as done. The task index is as shown in `list` command.
```
$ done <task_index>
```

#### `delete` &mdash; Remove a Task
Deletes a Task at the specified index. The task index is as shown in `list` command.
```
$ delete <task_index>
```

#### `todo` &mdash; Add a Todo
Adds a Todo task.
```
$ todo <description>
```

#### `deadline` &mdash; Add a Deadline
Adds a Deadline task by a certain time. The time input should be of the format `yyyy-mm-dd`, e.g. `2021-02-20`.
```
$ deadline <description> /by <time: yyyy-mm-dd>
```

#### `event` &mdash; Add an Event
Adds an Event task at a certain time. The time input should be of the format `yyyy-mm-dd`, e.g. `2021-02-20`.
```
$ event <description> /at <time: yyyy-mm-dd>
```

#### `bye` &mdash; Exit Duke
Exits the application. Duke will remember all the tasks you have given him the next time you launch the application.
```
$ bye
```

## Acknowledgements
- GUI from [JavaFX](https://openjfx.io)
- Unit Testing from [JUnit5](https://github.com/junit-team/junit5)
- Commons Text from [Apache](https://commons.apache.org/proper/commons-text/)