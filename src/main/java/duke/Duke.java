package duke;

import java.io.IOException;

import duke.commands.Commands;
import duke.commands.Task;
import duke.exception.DukeException;


/**
 * Duke represents a task list application.
 * It takes in the following types of tasks: todo, deadline, event.
 * It processes these commands too: list, delete, done, bye
 * Task lists of individual users are saved locally and retrieved by running this class.
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;
    private final Parser parser;
    private String lastCommand;
    private Task lastDeletedTask;

    /**
     * Constructor for Duke class.
     * Initializes Ui object to print Ui.
     * Initializes parser object to make sense of data passed in and output accordingly.
     * initializes storage object and initializes user's saved task list into a TaskList object.
     *
     * @param filePath of where user's task list is saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.getTaskList());
            lastCommand = storage.getLastCommand();
            lastDeletedTask = storage.getLastDeletedTask();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Gets the response from Duke after an input by the user in the GUI.
     * Valid words: list, done, delete, todo, deadline, event, find
     *
     * @param command input of the user that requires a response.
     * @throws DukeException when key word is not a valid word.
     */
    public String getResponse(String command) {
        assert ui != null : "ui object should not be null";
        assert storage != null : "storage object should not be null";
        assert parser != null : "parser object should not be null";

        String response = "";

        String[] commandArr = command.split(" ");
        String keyWord = commandArr[0];
        String keyWordToCompare = keyWord.toUpperCase();

        Task newTask;

        try {
            switch (Commands.valueOf(keyWordToCompare)) {
            case BYE:
                storage.writeAllData(tasks, lastCommand, lastDeletedTask);
                response += ui.getExitMessageString();
                break;
            case LIST:
                response += ui.getPrintTaskListString(tasks);
                lastCommand = command;
                break;
            case DONE:
                int taskNumber = Integer.parseInt(commandArr[1]);
                Task doneTask = parser.parseDoneCommand(taskNumber, tasks);
                assert doneTask.getTaskStatus() : "task should be marked as done";

                response += ui.getPrintDoneTaskString(doneTask);
                lastCommand = command;
                break;
            case TODO:
                newTask = parser.parseAddTodo(command, tasks);
                assert tasks.checkTaskPresent(newTask) : "task should be added into user's task list";

                response += ui.getPrintAddedTaskString(tasks, newTask);
                lastCommand = command;
                break;
            case DEADLINE:
                newTask = parser.parseAddDeadline(command, tasks);
                assert tasks.checkTaskPresent(newTask) : "task should be added into user's task list";

                response += ui.getPrintAddedTaskString(tasks, newTask);
                lastCommand = command;
                break;
            case EVENT:
                newTask = parser.parseAddEvent(command, tasks);
                assert tasks.checkTaskPresent(newTask) : "task should be added into user's task list";

                response += ui.getPrintAddedTaskString(tasks, newTask);
                lastCommand = command;
                break;
            case DELETE:
                Task taskToBeDeleted = parser.parseDeleteCommand(command, tasks);
                lastDeletedTask = taskToBeDeleted;
                assert !tasks.checkTaskPresent(taskToBeDeleted) : "task shouldn't be present in user's list";

                response += ui.getPrintDeletedTaskString(taskToBeDeleted, tasks);
                lastCommand = command;
                break;
            case FIND:
                TaskList tasksFound = parser.parseFindCommand(command, tasks);
                response += ui.getPrintFoundTasksString(tasksFound);
                lastCommand = command;
                break;
            case UNDO:
                boolean undoStatus = parser.parseUndoCommand(lastCommand, lastDeletedTask, tasks);
                response += ui.getPrintUndoCommandString(undoStatus);
                lastCommand = command;
                break;
            default:
                //do nothing
            }
        } catch (Exception ex) {
            if (ex instanceof IllegalArgumentException) {
                response += "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            } else {
                response += ex.getMessage();
            }
        }
        return response;
    }

}

