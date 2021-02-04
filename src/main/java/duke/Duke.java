package duke;

import javafx.application.Platform;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class Duke represents Danh's Duke, a powerful assistant that can take note of your task everyday and
 * help you control them easily.
 * <p>
 * Duke has 3 main components:
 * Ui: deals with interactions with the user
 * Storage: deals with loading tasks from the file and saving tasks in the file
 * TaskList: contains the task list e.g., it has operations to add/delete tasks in the list
 */
class Duke {
    private final Ui ui;
    private final TaskList tasklist;
    private final Storage storage;

    /**
     * Returns a Duke with Ui, Storage, TaskList initialized.
     */
    public Duke() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage("data/DanhDuke.txt", "data");
        this.tasklist = new TaskList();
        this.storage.writeBack(this.tasklist.listUsed);
    }

    /**
     * The main method illustrates full usage of Danh's Duke before shutting it down
     *
     * @param args by default
     * @throws IOException exception regarding open and access taskFile
     */
    public static void main(String[] args) throws IOException {
        Duke myDuke = new Duke();
        myDuke.ui.echoHi();

        boolean signalToExit = false;
        while (!signalToExit && myDuke.ui.stillHaveCommand()) {
            String commandLine = myDuke.ui.readCommand();
            Command command = Parser.parse(commandLine, myDuke.tasklist.listUsed);
            executeCommand(myDuke, command);
        }
    }


    public static String executeCommand(Duke myDuke, Command command) {
        String ans = "";
        switch (command.commandTitle) {
        case "list":
            ans = myDuke.ui.echoPrintList(myDuke.tasklist.listUsed);
            break;
        case "bye":
            ans = myDuke.ui.echoBye();
            break;
        case "done":
            ans = markTaskDone(myDuke, Integer.parseInt(command.commandContent));
            break;
        case "delete":
            ans = deleteTask(myDuke, Integer.parseInt(command.commandContent));
            break;
        case "todo":
        case "deadline":
        case "event":
            ans = addToList(myDuke, command.commandContent);
            break;
        case "myTaskToday":
            ans = myDuke.ui.echoTaskToday(myDuke.tasklist.listUsed);
            break;
        case "myTaskOn":
            ans = myDuke.ui.echoTaskThisDay(myDuke.tasklist.listUsed,
                    LocalDateTime.parse(command.commandContent, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            break;
        case "find":
            ans = myDuke.ui.echoPrintFindResult(myDuke.tasklist.listUsed, command.commandContent);
            break;
        default:
            try {
                executeFalseCommand(command.commandContent);
            } catch (DukeException err) {
                ans = myDuke.ui.echoErrMsg(err);
            }
        }
        return ans;
    }

    /**
     * Perform 3 actions: add new task to taskList, show results with user and change taskFile content correspondingly.
     *
     * @param duke            The duke instance related to this action
     * @param taskDescription The desciption of the task that we want to add
     */
    public static String addToList(Duke duke, String taskDescription) {
        Task task = duke.tasklist.addTask(taskDescription);
        duke.storage.updateFile(duke.tasklist.listUsed);
        String ans = duke.ui.echoAddToList(task, duke.tasklist.listUsed.size());
        return ans;
    }

    /**
     * Perform 3 actions: mark a task in taskList as Done, show results with user and
     * change taskFile content correspondingly.
     *
     * @param duke  The duke instance related to this action
     * @param index The index of that task in taskList
     */
    public static String markTaskDone(Duke duke, int index) {
        Task task = duke.tasklist.doneTask(index);
        duke.storage.updateFile(duke.tasklist.listUsed);
        String ans = duke.ui.echoMarkTaskDone(task);
        return ans;
    }

    /**
     * Perform 3 actions: delete a task in taskList, show results with user and
     * change taskFile content correspondingly.
     *
     * @param duke  The duke instance related to this action
     * @param index The index of that task in taskList
     */
    public static String deleteTask(Duke duke, int index) {
        Task task = duke.tasklist.listUsed.get(index - 1);
        String ans = duke.ui.echoDeleteTask(task);
        duke.tasklist.deleteTask(index);
        duke.storage.updateFile(duke.tasklist.listUsed);
        return ans;
    }

    /**
     * Tell Duke that this command format is wrong,
     * let it execute the action of throwing an exception to catch and handle.
     *
     * @param command The short syntax of the wrong format command
     * @throws DukeException Special exception related to wrong format of command entered into Danh's Duke
     */
    public static void executeFalseCommand(String command) throws DukeException {
        if (command.startsWith("list")) {
            throw new DukeException("     list command should not have body, Sir!");
        } else if (command.startsWith("bye")) {
            throw new DukeException("     bye command should not have body, Sir!");
        } else if (command.startsWith("done")) {
            throw new DukeException("     No body or wrong body format or invalid number for done command, Sir!");
        } else if (command.startsWith("delete")) {
            throw new DukeException("     No body or wrong body format or invalid number for delete command, Sir!");
        } else if (command.startsWith("todo")) {
            throw new DukeException("     No body detected for todo command, Sir!");
        } else if (command.startsWith("deadline")) {
            throw new DukeException("     no body detected or no dlTime detected for deadline command, Sir!");
        } else if (command.startsWith("event")) {
            throw new DukeException("     no body detected or no eTime detected for Event command, Sir!");
        } else if (command.startsWith("myTaskToday")) {
            throw new DukeException("     myTaskToday command should not have body, Sir!");
        } else if (command.startsWith("myTaskOn")) {
            throw new DukeException("     No body or wrong body format for myTaskOn command, Sir!");
        } else {
            throw new DukeException("     Invalid command format");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input, this.tasklist.listUsed);
        String response = executeCommand(this, command);
        if (response.contains("Bye. Hope to see you again soon")) {
            Platform.exit();
        }
        return response;
    }
}


