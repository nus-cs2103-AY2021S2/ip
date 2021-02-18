package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;

/**
 * Class Duke represents ChaeLisa's main component, which is in charge of managing its attributes to
 * execute commands, store data, write data, and interact with user.
 * <p>
 * Duke has 3 main components:
 * Ui: deals with interactions with the user
 * Storage: deals with loading tasks from the file and saving tasks in the file
 * TaskList: contains the task list e.g., it has operations to add/delete tasks in the list
 */
class Duke {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Returns a Duke with Ui, Storage, TaskList initialized.
     */
    public Duke() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage("data/DanhDuke.txt", "data");
        this.taskList = new TaskList();
        this.storage.writeBack(this.taskList.getListUsed());
    }

    /**
     * Illustrates full usage of ChaeLisa's main component before shutting it down
     *
     * @param args by default
     * @throws IOException exception regarding open and access taskFile
     */
    public static void main(String[] args) throws IOException {
        Duke myDuke = new Duke();
        while (myDuke.ui.stillHaveCommand()) {
            String commandLine = myDuke.ui.readCommand();
            Command command = Parser.parse(commandLine, myDuke.getTaskList().getListUsed());
            executeCommand(myDuke, command);
        }
    }

    /**
     * Performs 3 actions: mark a task in taskList as Done, show results with user and
     * change taskFile content correspondingly.
     *
     * @param duke  The duke instance related to this action
     * @param index The index of that task in taskList
     */
    public static String markTaskDone(Duke duke, int index) {
        Task task = duke.getTaskList().doneTask(index);
        duke.storage.updateFile(duke.getTaskList().getListUsed());
        return duke.ui.echoMarkTaskDone(task);
    }

    /**
     * Performs 3 actions: add new task to taskList, show results with user and change taskFile content correspondingly.
     *
     * @param duke            The duke instance related to this action
     * @param taskDescription The desciption of the task that we want to add
     */
    public static String addToList(Duke duke, String taskDescription) {
        Task task = duke.getTaskList().addTask(taskDescription);
        duke.storage.updateFile(duke.getTaskList().getListUsed());
        return duke.ui.echoAddToList(task, duke.getTaskList().getListUsed().size());
    }

    /**
     * Performs 3 actions: delete a task in taskList, show results with user and
     * change taskFile content correspondingly.
     *
     * @param duke  The duke instance related to this action
     * @param index The index of that task in taskList
     */
    public static String deleteTask(Duke duke, int index) {
        Task task = duke.getTaskList().getListUsed().get(index - 1);
        String ans = duke.ui.echoDeleteTask(task);
        duke.getTaskList().deleteTask(index);
        duke.storage.updateFile(duke.getTaskList().getListUsed());
        return ans;
    }

    /**
     * Tells Duke that this command format is wrong,
     * lets it execute the action of throwing an exception to catch and handle.
     *
     * @param command The short syntax of the wrong format command
     * @throws DukeException Special exception related to wrong format of command entered for ChaeLisa
     */
    public static void executeFalseCommand(String command) throws DukeException {
        if (command.startsWith("list")) {
            throw new DukeException("List command should not have body, Lisa!");
        } else if (command.startsWith("bye")) {
            throw new DukeException("Bye command should not have body, Lisa!");
        } else if (command.startsWith("done")) {
            throw new DukeException("No body or wrong body format or invalid number for done command, Lisa!");
        } else if (command.startsWith("delete")) {
            throw new DukeException("No body or wrong body format or invalid number for delete command, Lisa!");
        } else if (command.startsWith("todo")) {
            throw new DukeException("No body detected for todo command, Lisa!");
        } else if (command.startsWith("deadline")) {
            throw new DukeException("No body detected or no dlTime detected for deadline command, Lisa!");
        } else if (command.startsWith("event")) {
            throw new DukeException("No body detected or no eTime detected for Event command, Lisa!");
        } else if (command.startsWith("myTaskToday")) {
            throw new DukeException("myTaskToday command should not have body, Lisa!");
        } else if (command.startsWith("myTaskOn")) {
            throw new DukeException("No body or wrong body format for myTaskOn command, Lisa!");
        } else if (command.startsWith("reminder")) {
            throw new DukeException("Reminder command should not have body, Lisa!");
        } else {
            //This is not the last case but everything else.
            throw new DukeException("Invalid command format, Lisa!");
        }
    }

    /**
     * Executes the command and produce the output of that command.
     *
     * @param myDuke  Duke carrying out this command.
     * @param command The Command to be executed.
     * @return the output of that command.
     */
    public static String executeCommand(Duke myDuke, Command command) {
        String ans = "";
        switch (command.getCommandTitle()) {
        case "list":
            ans = myDuke.ui.echoPrintList(myDuke.getTaskList().getListUsed());
            break;
        case "bye":
            ans = myDuke.ui.echoBye();
            assert ans.equals("Bye. Hope to see you again soon!\n") : "Wrong bye ui output";
            break;
        case "done":
            ans = markTaskDone(myDuke, Integer.parseInt(command.getCommandContent()));
            break;
        case "delete":
            ans = deleteTask(myDuke, Integer.parseInt(command.getCommandContent()));
            break;
        case "todo":
        case "deadline":
        case "event":
            ans = addToList(myDuke, command.getCommandContent());
            break;
        case "myTaskToday":
            ans = myDuke.ui.echoTaskToday(myDuke.getTaskList().getListUsed());
            break;
        case "myTaskOn":
            ans = myDuke.ui.echoTaskThisDay(myDuke.getTaskList().getListUsed(),
                    LocalDateTime.parse(command.getCommandContent(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            break;
        case "find":
            ans = myDuke.ui.echoPrintFindResult(myDuke.getTaskList().getListUsed(), command.getCommandContent());
            break;
        case "reminder":
            ans = myDuke.ui.echoReminder(myDuke.getTaskList().getListUsed());
            break;
        default:
            try {
                executeFalseCommand(command.getCommandContent());
            } catch (DukeException err) {
                ans = myDuke.ui.echoErrMsg(err);
            }
        }
        return ans;
    }


    /**
     * Returns the task list of this Duke.
     *
     * @return the taskList object of this Duke.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input, this.getTaskList().getListUsed());
        String response = executeCommand(this, command);
        if (response.contains("Bye. Hope to see you again soon")) {
            Platform.exit();
        }
        return response;
    }
}
