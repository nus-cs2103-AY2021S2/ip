package duke;

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
     *
     * @param txtPathname The pathname of the taskFile stored in hard disk to remember tasks when shut down Duke.
     * @param dirPathname The pathname of the folder that stores taskFile.
     */
    private Duke(String txtPathname, String dirPathname) {
        this.ui = new Ui();
        this.storage = new Storage(txtPathname, dirPathname);
        this.tasklist = new TaskList();
    }

    /**
     * The main method illustrates full usage of Danh's Duke before shutting it down
     *
     * @param args by default
     * @throws IOException exception regarding open and access taskFile
     */
    public static void main(String[] args) throws IOException {
        Duke myDuke = new Duke("data/DanhDuke.txt", "data");
        myDuke.ui.echoHi();
        myDuke.storage.writeBack(myDuke.tasklist.getListUsed());
        boolean signalToExit = false;
        while (!signalToExit && myDuke.ui.stillHaveCommand()) {
            String commandLine = myDuke.ui.readCommand();
            Command command = Parser.parse(commandLine, myDuke.tasklist.getListUsed());
            switch (command.getCommandTitle()) {
            case "list":
                myDuke.ui.echoPrintList(myDuke.tasklist.getListUsed());
                break;
            case "bye":
                myDuke.ui.echoBye();
                signalToExit = true;
                break;
            case "done":
                markTaskDone(myDuke, Integer.parseInt(command.getCommandContent()));
                break;
            case "delete":
                deleteTask(myDuke, Integer.parseInt(command.getCommandContent()));
                break;
            case "todo":
            case "deadline":
            case "event":
                addToList(myDuke, command.getCommandContent());
                break;
            case "myTaskToday":
                myDuke.ui.echoTaskToday(myDuke.tasklist.getListUsed());
                break;
            case "myTaskOn":
                myDuke.ui.echoTaskThisDay(myDuke.tasklist.getListUsed(),
                        LocalDateTime.parse
                                (command.getCommandContent(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                break;
            case "find":
                myDuke.ui.echoPrintFindResult(myDuke.tasklist.getListUsed(), command.getCommandContent());
                break;
            default:
                try {
                    executeFalseCommand(command.getCommandContent());
                } catch (DukeException err) {
                    myDuke.ui.echoErrMsg(err);
                }
            }
        }
    }

    /**
     * Perform 3 actions: add new task to taskList, show results with user and change taskFile content correspondingly.
     *
     * @param duke            The duke instance related to this action
     * @param taskDescription The desciption of the task that we want to add
     */
    public static void addToList(Duke duke, String taskDescription) {
        Task task = duke.tasklist.addTask(taskDescription);
        duke.storage.updateFile(duke.tasklist.getListUsed());
        duke.ui.echoAddToList(task, duke.tasklist.getListUsed().size());
    }

    /**
     * Perform 3 actions: mark a task in taskList as Done, show results with user and
     * change taskFile content correspondingly.
     *
     * @param duke  The duke instance related to this action
     * @param index The index of that task in taskList
     */
    public static void markTaskDone(Duke duke, int index) {
        Task task = duke.tasklist.doneTask(index);
        duke.storage.updateFile(duke.tasklist.getListUsed());
        duke.ui.echoMarkTaskDone(task);
    }

    /**
     * Perform 3 actions: delete a task in taskList, show results with user and
     * change taskFile content correspondingly.
     *
     * @param duke  The duke instance related to this action
     * @param index The index of that task in taskList
     */
    public static void deleteTask(Duke duke, int index) {
        Task task = duke.tasklist.getListUsed().get(index - 1);
        duke.ui.echoDeleteTask(task);
        duke.tasklist.deleteTask(index);
        duke.storage.updateFile(duke.tasklist.getListUsed());
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
}


