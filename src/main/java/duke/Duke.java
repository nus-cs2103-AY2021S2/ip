package duke;

import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Deadline;
import duke.Event;
import duke.Duke;
import duke.Storage;
import duke.Ui;
import duke.Parser;
import duke.DukeException;


import java.io.IOException;
import java.util.Scanner;

/**
 * Duke represents a (CLI) task list application.
 * It takes in the following types of tasks: todo, deadline, event.
 * It processes these commands too: list, delete, done, bye
 * Task lists of individual users are saved locally and retrieved by running this class.
 */

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Duke class.
     * Initializes Ui object to print Ui.
     * Initializes parser object to make sense of data passed in and output accordingly.
     * initializes storage object and initializes user's saved tasklist into a TaskList object.
     * @exception IOException caught if incorrect filepath that fails to retrieve user's task list.
     * @param filePath of where user's task list is saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.getTaskList());
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * entry point for the Duke program.
     * Initializes new Duke object with filePath: "data/tasks.txt" and calls run method.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Processes the user input and print out messages and Ui accordingly.
     *
     * @see Scanner
     * @see Ui
     * @see Parser
     * @throws DukeException If invalid task type. Valid tasks types: ToDo, Event, Deadline.
     * @throws DukeException If there is no message after the todo command.
     */
    public void run() {
        final String EXIT_COMMAND = "bye";
        final String LIST_COMMAND = "list";
        final String DONE_COMMAND = "done";
        final String DELETE_COMMAND = "delete";
        final String ADD_TODO_COMMAND = "todo";
        final String ADD_DEADLINE_COMMAND = "deadline";
        final String ADD_EVENT_COMMAND = "event";
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
                    }
                    else {
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
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
