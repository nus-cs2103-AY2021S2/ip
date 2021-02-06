package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke represents a (CLI) task list application.
 * It takes in the following types of tasks: todo, deadline, event.
 * It processes these commands too: list, delete, done, bye
 * Task lists of individual users are saved locally and retrieved by running this class.
 */
// extends application
public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;
    private final Parser parser;

    private final String LIST_COMMAND = "list";
    private final String DONE_COMMAND = "done";
    private final String DELETE_COMMAND = "delete";
    private final String ADD_TODO_COMMAND = "todo";
    private final String ADD_DEADLINE_COMMAND = "deadline";
    private final String ADD_EVENT_COMMAND = "event";
    private final String FIND_COMMAND = "find";
    private final String EXIT_COMMAND = "bye";

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
     * entry point for the Duke program.
     * Initializes new Duke object with filePath: "data/tasks.txt" and calls run method.
     *
     * @param args arguments for main method
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Processes the user input and print out messages and Ui accordingly.
     *
     * @throws DukeException If invalid command. Valid commands: todo, event, deadline, delete, done, list, bye
     * @throws DukeException If there is no message after the todo command.
     * @see Scanner
     * @see Ui
     * @see Parser
     */
    public void run() {
        ui.printWelcomeGreeting();
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");
            Task newTask;
            ui.printHorizontalRule();
            try {
                switch (commandArr[0]) {
                case EXIT_COMMAND:
                    ui.printExitMessage();
                    sc.close();
                    storage.writeData(tasks.getTaskList());
                    isBye = true;
                    break;
                case LIST_COMMAND:
                    ui.printTaskList(tasks);
                    break;
                case DONE_COMMAND:
                    int taskNumber = Integer.parseInt(commandArr[1]);
                    ui.printDoneTask(tasks, taskNumber);
                    break;
                case ADD_TODO_COMMAND:
                    if (commandArr.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        newTask = parser.parseAddTodo(command);
                        ui.printAddedTask(tasks, newTask);
                    }
                    break;
                case ADD_DEADLINE_COMMAND:
                    newTask = parser.parseAddDeadline(command);
                    ui.printAddedTask(tasks, newTask);
                    break;
                case ADD_EVENT_COMMAND:
                    newTask = parser.parseAddEvent(command);
                    ui.printAddedTask(tasks, newTask);
                    break;
                case DELETE_COMMAND:
                    int taskNumToBeDeleted = parser.parseDeleteCommand(command);
                    ui.printDeletedTask(tasks, taskNumToBeDeleted);
                    break;
                case FIND_COMMAND:
                    TaskList tasksFound = parser.parseFindCommand(command, tasks);
                    ui.printFoundTasks(tasksFound);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    /**
     * Gets the response from Duke after an input by the user in the GUI.
     *
     * @throws DukeException when key word is not a valid word. Valid words: list, done, delete, todo, deadline, event, find
     * @param command input of the user that requires a response.
     */
    public String getResponse(String command) {
        String response = "";
        String[] commandArr = command.split(" ");
        Task newTask;
        ui.printHorizontalRule();
        try {
            switch (commandArr[0]) {
            case EXIT_COMMAND:
                storage.writeData(tasks.getTaskList());
                response += ui.getExitMessageString();
                break;
            case LIST_COMMAND:
                response += ui.getPrintTaskListString(tasks);
                break;
            case DONE_COMMAND:
                int taskNumber = Integer.parseInt(commandArr[1]);
                response += ui.getPrintDoneTaskString(tasks, taskNumber);
                break;
            case ADD_TODO_COMMAND:
                if (commandArr.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    newTask = parser.parseAddTodo(command);
                    response += ui.getPrintAddedTaskString(tasks, newTask);
                }
                break;
            case ADD_DEADLINE_COMMAND:
                newTask = parser.parseAddDeadline(command);
                response += ui.getPrintAddedTaskString(tasks, newTask);
                break;
            case ADD_EVENT_COMMAND:
                newTask = parser.parseAddEvent(command);
                response += ui.getPrintAddedTaskString(tasks, newTask);
                break;
            case DELETE_COMMAND:
                int taskNumToBeDeleted = parser.parseDeleteCommand(command);
                response += ui.getPrintDeletedTaskString(tasks, taskNumToBeDeleted);
                break;
            case FIND_COMMAND:
                TaskList tasksFound = parser.parseFindCommand(command, tasks);
                response += ui.getPrintFoundTasksString(tasksFound);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            response += ex.getMessage();
        }
        return response;
    }


}
