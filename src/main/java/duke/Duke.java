package duke;

import java.io.IOException;

/**
 * Duke represents a (CLI) task list application.
 * It takes in the following types of tasks: todo, deadline, event.
 * It processes these commands too: list, delete, done, bye
 * Task lists of individual users are saved locally and retrieved by running this class.
 */

public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;
    private final Parser parser;


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
        String response = "";
        String[] commandArr = command.split(" ");
        String keyWord = commandArr[0];
        String keyWordToCompare = keyWord.toUpperCase();
        Task newTask;
        try {
            switch (Commands.valueOf(keyWordToCompare)) {
            case BYE:
                storage.writeData(tasks.getTaskList());
                response += ui.getExitMessageString();
                break;
            case LIST:
                response += ui.getPrintTaskListString(tasks);
                break;
            case DONE:
                int taskNumber = Integer.parseInt(keyWord);
                response += ui.getPrintDoneTaskString(tasks, taskNumber);
                break;
            case TODO:
                newTask = parser.parseAddTodo(command);
                response += ui.getPrintAddedTaskString(tasks, newTask);
                break;
            case DEADLINE:
                newTask = parser.parseAddDeadline(command);
                response += ui.getPrintAddedTaskString(tasks, newTask);
                break;
            case EVENT:
                newTask = parser.parseAddEvent(command);
                response += ui.getPrintAddedTaskString(tasks, newTask);
                break;
            case DELETE:
                int taskNumToBeDeleted = parser.parseDeleteCommand(command);
                response += ui.getPrintDeletedTaskString(tasks, taskNumToBeDeleted);
                break;
            case FIND:
                TaskList tasksFound = parser.parseFindCommand(command, tasks);
                response += ui.getPrintFoundTasksString(tasksFound);
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

