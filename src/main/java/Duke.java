import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Todo;
import duke.Ui;
import javafx.application.Platform;


/**
 * A CLI-based task management application that allows users
 * to add tasks, events and deadlines.
 *
 * @author Justin Gnoh
 * @since 2021-02-06
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * This object is created to encapsulate other objects
     * to facilitate Ui and storage paths.
     *
     * @param filePath This is the storage filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }


    public String getIntroMessage() {
        return ui.getIntroMessage();
    }

    /**
     * This method returns a response based on user input
     *
     * @param input
     * @return This returns a response to user input
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        String trimmedInput = input.trim();
        String command = parser.getCommand(trimmedInput);
        switch (command) {
        case "bye":
            storage.save(taskList);
            Platform.exit();

        case "list":
            return ui.printList(taskList);

        case "done":
            try {
                int index = parser.getIndex(input);
                Task task = taskList.getSingleTask(index);
                task.markDone();

                return ui.printDone(task);
            } catch (DukeException e) {
                return e.printError("Please check the index!");
            }

        case "todo":
            try {
                String name = getTodoName(input);
                Todo todo = new Todo(name);
                taskList.addTask(todo);
                return ui.printTask(todo, taskList.getSize());
            } catch (DukeException e) {
                return e.printError("Come On Fella! Your ToDo description cannot be empty!");
            }

        case "deadline":
            try {
                String name = getEventOrDeadlineName(input);
                String by = getDeadlineAttribute(input);
                LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                Deadline deadline = new Deadline(name, date);
                taskList.addTask(deadline);
                return ui.printTask(deadline, taskList.getSize());

            } catch (DukeException e) {
                return e.printError("Hmm... You are either lacking a name or /by details!");
            }

        case "event":
            try {
                String name = getEventOrDeadlineName(input);
                String at = getEventAttribute(input);
                Event event = new Event(name, at);
                taskList.addTask(event);
                return ui.printTask(event, taskList.getSize());

            } catch (DukeException e) {
                return e.printError("Hmm... You are either lacking a name or /at details!");
            }

        case "delete":
            try {
                int deleteIndex = parser.getIndex(input);
                Task deletedTask = taskList.getSingleTask(deleteIndex);
                taskList.deleteTask(deleteIndex);
                return ui.printDelete(deletedTask, taskList.getSize());
            } catch (DukeException e) {
                return e.printError("Please choose the correct index for deletion.");
            }

        case "find":
            String arguments = parser.getArguments(input);
            TaskList output = taskList.matchTasks(arguments);
            return ui.printMatchingTask(output);

        case "help":
            return ui.getHelpMessage();

        default:
            return ui.printUnknownCommand();

        }
    }

    /**
     * This method gets the name of a To-Do task
     *
     * @param input This is the user input
     * @return String This is the To-Do task name
     * @throws DukeException On input error.
     */
    public static String getTodoName(String input) throws DukeException {
        try {
            return input.split(" ", 2)[1].trim();
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    /**
     * This method gets the Event or Deadline name
     *
     * @param input This is the user input
     * @return String This is the Event or Deadline name
     * @throws DukeException On input error.
     */
    public static String getEventOrDeadlineName(String input) throws DukeException {
        try {
            return input.split("/")[0].split(" ", 2)[1].trim();
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    /**
     * This method gets the Deadline attribute
     *
     * @param byDate This is the task attribute
     * @return String This is the Deadline attribute
     * @throws DukeException On input error.
     */
    public static String getDeadlineAttribute(String byDate) throws DukeException {
        try {
            return byDate.split("/by ")[1];
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    /**
     * This method gets the Event attribute
     *
     * @param at This is the task attribute
     * @return String This is the Event attribute
     * @throws DukeException On input error.
     */
    public static String getEventAttribute(String at) throws DukeException {
        try {
            return at.split("/at ")[1];
        } catch (Exception e) {
            throw new DukeException();
        }
    }

}
