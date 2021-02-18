# User Guide
Duke is a personal assistant chatbot that helps you to keep track of various tasks, such as
deadlines, events and todos.

- [Quick start](#quickStart)
- [Features](#features)
    - [Viewing help: `help`](#help)
    - [Adding a todo: `todo`](#todo)
    - [Adding a deadline: `deadline`](#deadline)
    - [Adding an event: `event`](#event)
    - [Listing all tasks: `list`](#list)
    - [Marking a task as done: `done`](#done)
    - [Locating tasks by keyword: `find`](#find)
    - [Deleting a task: `delete`](#delete)
    - [Exiting the program: `bye`](#bye)
- [Command summary](#commandSummary)

---

## <a id="quickStart"></a> <span style="color:darkorange">Quick start</span>
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `jar` from [here](https://github.com/pngsebastian/ip/releases)
3. Copy the file to the folder you want to use as the *home folder* for your Duke
4. Double-click the file to start the app. The GUI should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the [Features](#features) below for details of each command.

---

## <a id="features"></a> <span style="color:darkorange">Features</span>
>#### Notes about the command format:
>- Words in `UPPER_CASE` are the parameters to be supplied by the user. <br>
  e.g in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as <br>
  `todo homework`.

### <a id="help"></a> <span style="color:darkorange">Viewing help: `help`</span>
Shows a message with all recognised commands and their formats. <br>
Format: `help`

### <a id="todo"></a> <span style="color:darkorange">Adding a todo: `todo`</span>
Adds a todo to the task list. <br>
Format: `todo DESCRIPTION` <br>
Example of usage: <br>
`todo borrow book`

### <a id="deadline"></a> <span style="color:darkorange">Adding a deadline: `deadline`</span>
Adds a deadline to the task list. <br>
Format: `deadline DESCRIPTION /by DATE` <br>
- The date **must be in the format of `YYYY-MM-DD`**

Example of usage: <br>
`deadline return book /by 2021-02-04`

### <a id="event"></a> <span style="color:darkorange">Adding an event: `event`</span>
Adds an event to the task list. <br>
Format: `event DESCRIPTION /at DATE` <br>
- The date **must be in the format of `YYYY-MM-DD`**

Example of usage: <br>
`event project meeting /at 2021-03-05`

### <a id="list"></a> <span style="color:darkorange">Listing all tasks: `list`</span>
Shows a list of all the tasks in the task list. <br>
Format: `list`

### <a id="done"></a> <span style="color:darkorange">Marking a task as done: `done`</span>
Marks the specified task from the task list as done. <br>
Format: `done INDEX`
- Marks the task at the specified `INDEX` as done
- The index refers to the index number shown in the displayed task list. <br>
- The index **must be a positive integer (E.g. 1, 2, 3...)** <br>

Example of usage: <br>
`done 2`

### <a id="find"></a> <span style="color:darkorange">Locating tasks by keyword: `find`</span>
Finds tasks with names that contain the given keyword <br>
Format: `find KEYWORD` <br>
Example of usage: <br>
`find book`

### <a id="delete"></a> <span style="color:darkorange">Deleting a task: `delete`</span>
Deletes the specified task from the task list. <br>
Format: `delete INDEX`
- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list. <br>
- The index **must be a positive integer (E.g. 1, 2, 3...)** <br>

Example of usage: <br>
`delete 2`

### <a id="bye"></a> <span style="color:darkorange">Exiting the program: `bye`</span>
Exits the program. <br>
Format: `bye`

---

## <a id="commandSummary"></a> <span style="color:darkorange">Command Summary</span>
<table>
  <tr>
    <th>Command</th>
    <th>Format, Examples</th>
  </tr>
  <tr>
    <td><a href="#bye"><strong>Bye</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">bye</code></td>
  </tr>
  <tr>
    <td><a href="#deadline"><strong>Deadline</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">deadline DESCRIPTION /by DATE</code> <br /> e.g. <code class="language-plaintext highlighter-rouge">deadline return book /by 2021-02-04</code></td>
  </tr>
  <tr>
    <td><a href="#delete"><strong>Delete</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">delete INDEX</code> <br /> e.g. <code class="language-plaintext highlighter-rouge">delete 2</code></td>
  </tr>
  <tr>
    <td><a href="#done"><strong>Done</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">done INDEX</code> <br /> e.g. <code class="language-plaintext highlighter-rouge">done 2</code></td>
  </tr>
  <tr>
    <td><a href="#event"><strong>Event</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">event DESCRIPTION /at DATE</code> <br /> e.g. <code class="language-plaintext highlighter-rouge">event project meeting /at 2021-03-05</code></td>
  </tr>
  <tr>
    <td><a href="#find"><strong>Find</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">find KEYWORD</code> <br /> e.g. <code class="language-plaintext highlighter-rouge">find book</code></td>
  </tr>
  <tr>
    <td><a href="#help"><strong>Help</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">help</code></td>
  </tr>
  <tr>
    <td><a href="#list"><strong>List</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">list</code></td>
  </tr>
  <tr>
    <td><a href="#todo"><strong>Todo</strong></a></td>
    <td><code class="language-plaintext highlighter-rouge">todo DESCRIPTION</code> <br /> e.g. <code class="language-plaintext highlighter-rouge">todo borrow book</code></td>
  </tr>
</table>
