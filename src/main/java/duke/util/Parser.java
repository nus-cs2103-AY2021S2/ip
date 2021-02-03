package duke.util;

import duke.command.AddCommand;
import duke.command.InvalidCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeDateTimeException;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a Parser.
 */
public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses user input and returns a Command corresponding to user input.
     *
     * @param userInput The input of the user.
     * @param tasks List of tasks.
     * @return An executable command.
     */
    public static Command parse(String userInput, TaskList tasks) {
        String[] split = userInput.split("\\s+");
        String command = split[0];
        Command res;
        try {
            switch (command) {
            case "todo":
                checkStringArgument(split, command);
                String description = userInput.substring(5);
                res = new AddCommand(new ToDo(description));
                break;
            case "deadline":
                checkStringArgument(split, command);
                String[] deadlineDetails = userInput.substring(9).split(" /by ");
                checkDateTime(deadlineDetails);
                String deadlineDescription = deadlineDetails[0];
                LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDetails[1], formatter);
                res = new AddCommand(new Deadline(deadlineDescription, deadlineDateTime));
                break;
            case "event":
                checkStringArgument(split, command);
                String[] eventDetails = userInput.substring(6).split(" /at ");
                checkDateTime(eventDetails);
                String eventDescription = eventDetails[0];
                LocalDateTime eventDateTime = LocalDateTime.parse(eventDetails[1], formatter);
                res = new AddCommand(new Event(eventDescription, eventDateTime));
                break;
            case "list":
                res = new ListCommand();
                break;
            case "done":
                int completedTaskIdx = checkNumericalArgument(split, tasks);
                res = new DoneCommand(completedTaskIdx);
                break;
            case "delete":
                int taskToDelete = checkNumericalArgument(split, tasks);
                res = new DeleteCommand(taskToDelete);
                break;
            case "find":
                checkStringArgument(split, command);
                String keyword = userInput.substring(5);
                res = new FindCommand(keyword);
                break;
            case "bye":
                res = new ExitCommand();
                break;
            default:
                res = new InvalidCommand("No such command!");
            }
        } catch (DukeException e) {
            res = new InvalidCommand(e.getMessage());
        }
        return res;
    }

    /**
     * Checks whether the user has entered a description for the task to be added.
     * Checks whether the user has entered a keyword for find function.
     *
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @throws DukeArgumentException when user did not enter a keyword or description.
     */
    private static void checkStringArgument(String[] split, String command) throws DukeArgumentException {
        if (split.length == 1 && command.equals("find")) {
            throw new DukeArgumentException("You have not entered a keyword!");
        } else if (split.length == 1) {
            throw new DukeArgumentException("You have not entered a task description!");
        } else {}
    }

    /**
     * Checks whether the user has entered a date and time.
     *
     * @param details Pair of substrings obtained from removing "/by" or "/at" from user input.
     * @throws DukeDateTimeException When user has not entered a date and time.
     */
    private static void checkDateTime(String[] details) throws DukeDateTimeException {
        if (details.length < 2 || details[1].isBlank()) {
            throw new DukeDateTimeException("You have not entered a date and time!");
        }
    }

    /**
     * Checks whether the user has entered a valid task index.
     *
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @param tasks List of tasks.
     * @return Task index entered by user if it is valid.
     * @throws DukeArgumentException When user enters a non-integer, an out of bounds index or if user
     * did not enter any index.
     */
    private static int checkNumericalArgument(String[] split, TaskList tasks) throws DukeArgumentException {
        if (split.length > 2) {
            throw new DukeArgumentException("Please enter a numerical value for the task index!");
        }
        try {
            int taskIndex = Integer.parseInt(split[1]) - 1;
            tasks.getTask(taskIndex);
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new DukeArgumentException("Please enter a numerical value for the task index!");
        } catch (IndexOutOfBoundsException e) {
            if (split.length == 1) {
                throw new DukeArgumentException("You have not entered a task index!");
            } else {
                throw new DukeArgumentException("Please enter a valid task index!");
            }
        }
    }
}
