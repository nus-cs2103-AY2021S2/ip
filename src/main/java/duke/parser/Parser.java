package duke.parser;

import duke.command.*;
import duke.exceptions.EmptyTaskDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.time.format.DateTimeParseException;

/**
 * Parses input from Duke
 */
public class Parser {

    /**
     * Parses user input to return the corresponding command
     * @param input the corresponding command type
     */
    public static Command parseCommand(String input) {
        String commandString = parseCommandString(input);
        String commandHelper = commandString.toUpperCase();
        switch (commandHelper) {
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            return new AddCommand();
        case "DONE":
            return new DoneCommand();
        case "LIST":
            return new ListCommand();
        case "DELETE":
            return new DeleteCommand();
        case "FIND":
            return new FindCommand();
        case "HELP":
            return new HelpCommand();
        case "BYE":
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Parses user input to return the task
     * @param input the input given by the user
     * @param taskList the current instance of task list used by Duke
     * @return a task object as described in the input
     * @throws EmptyTaskDukeException when there is an empty description
     * @throws IndexOutOfBoundsException when user enters a number in the description that does not match the task list
     * @throws NumberFormatException when user enters a non number into the number field in the description
     * @throws DateTimeParseException when user enters a bad date format in the description
     */
    public static Task parseTask(String input, TaskList taskList) throws
            EmptyTaskDukeException, IndexOutOfBoundsException,
            NumberFormatException, DateTimeParseException {
        String taskDescription = parseDescription(input);
        String commandString = parseCommandString(input);
        String commandHelper = commandString.toUpperCase();
        switch (commandHelper) {
        case "TODO":
            return new Todo(taskDescription);
        case "DEADLINE":
            return new Deadline(taskDescription);
        case "EVENT":
            return new Event(taskDescription);
        case "DONE":
        case "DELETE":
            if (taskDescription.equals("")) {
                throw new EmptyTaskDukeException();
            }
            int index = Integer.parseInt(taskDescription) - 1;
            return taskList.getTaskList().get(index);
        default:
            return null;
        }
    }

    private static String parseCommandString(String input) {
        return input.split(" ")[0];
    }

    public static String parseDescription(String input) {
        if (input.split(" ").length == 1) {
            return "";
        }
        return input.substring(parseCommandString(input).length() + 1);
    }
}
