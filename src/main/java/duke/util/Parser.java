package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.UndoCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeDateTimeException;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


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
        assert(split.length >= 1);
        String command = split[0];
        Command res;
        try {
            switch (command) {
            case COMMAND.TODO:
                res = handleToDo(split, userInput, tasks);
                break;
            case COMMAND.DEADLINE:
                res = handleDateTimeTasks(split, userInput, COMMAND.DEADLINE, tasks);
                break;
            case COMMAND.EVENT:
                res = handleDateTimeTasks(split, userInput, COMMAND.EVENT, tasks);
                break;
            case COMMAND.LIST:
                res = new ListCommand();
                break;
            case COMMAND.DONE:
                res = handleTaskOps(split, tasks, COMMAND.DONE);
                break;
            case COMMAND.DELETE:
                res = handleTaskOps(split, tasks, COMMAND.DELETE);
                break;
            case COMMAND.FIND:
                res = handleFind(split, userInput);
                break;
            case COMMAND.UNDO:
                res = new UndoCommand();
                break;
            case COMMAND.EXIT:
                res = new ExitCommand();
                break;
            default:
                res = new InvalidCommand("No such command!");
                break;
            }
        } catch (DukeException e) {
            return new InvalidCommand(e.getMessage());
        }
        return res;
    }

    /**
     * Handles the case when user enters a ToDo command.
     *
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @param userInput String that represents user input.
     * @param tasks A list of tasks.
     * @return An executable command that adds a ToDo task.
     * @throws DukeArgumentException If user did not enter any task description.
     */
    private static AddCommand handleToDo(
            String[] split, String userInput, TaskList tasks) throws DukeArgumentException {
        checkStringArgument(split, COMMAND.TODO);
        String description = userInput.substring(5).trim();
        assert(!description.isBlank());
        Task toAdd = new ToDo(description);
        Cache.cache(toAdd, tasks.getSize(), COMMAND.TODO);
        return new AddCommand(toAdd);
    }

    /**
     * Handles the case when user enters a Deadline or Event command.
     *
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @param userInput String that represents user input.
     * @param command The name of the command.
     * @param tasks A list of tasks.
     * @return An executable command that adds a Deadline/Event task.
     * @throws DukeArgumentException If user did not enter any task description.
     * @throws DukeDateTimeException If user did not enter any Date and Time.
     */
    private static AddCommand handleDateTimeTasks(String[] split, String userInput, String command, TaskList tasks)
            throws DukeArgumentException, DukeDateTimeException {
        checkStringArgument(split, command);
        int detailsIdx = command.equals(COMMAND.DEADLINE) ? 9 : 6;
        String regex = command.equals(COMMAND.DEADLINE) ? " /by " : " /at ";
        String[] taskDetails = userInput.substring(detailsIdx).split(regex);
        checkDateTime(taskDetails);
        assert(taskDetails.length == 2);
        String description = taskDetails[0].trim();
        assert(!description.isBlank());
        LocalDateTime dateTime = LocalDateTime.parse(taskDetails[1].trim(), formatter);
        Task toAdd = command.equals(COMMAND.DEADLINE) ? new Deadline(description, dateTime)
                                                      : new Event(description, dateTime);
        Cache.cache(toAdd, tasks.getSize(), command);
        return new AddCommand(toAdd);
    }

    /**
     * Handles the case when user enters a Find command.
     *
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @param userInput String that represents user input.
     * @return An executable command that searches for all tasks related to the keyword.
     * @throws DukeArgumentException If user did not enter any keyword to search for.
     */
    private static FindCommand handleFind(String[] split, String userInput) throws DukeArgumentException {
        checkStringArgument(split, COMMAND.FIND);
        String keyword = userInput.substring(5).trim();
        return new FindCommand(keyword);
    }

    /**
     * Handles the case when user enters a Done or Delete command.
     *
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @param tasks A list of tasks.
     * @param command The name of the command.
     * @return An executable command that deletes a task from the list or sets a task as done.
     * @throws DukeArgumentException If user did not enter any task index, or if task index is invalid.
     */
    private static Command handleTaskOps(String[] split, TaskList tasks, String command) throws DukeArgumentException {
        int taskIdx = checkNumericalArgument(split, tasks);
        assert(taskIdx >= 0 && (taskIdx < tasks.getSize()));
        Cache.cache(tasks.getTask(taskIdx), taskIdx, command);
        return command.equals(COMMAND.DONE) ? new DoneCommand(taskIdx)
                                            : new DeleteCommand(taskIdx);
    }

    /**
     * Checks whether the user has entered a description for the task to be added.
     * Checks whether the user has entered a keyword for find function.
     *
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @throws DukeArgumentException when user did not enter a keyword or description.
     */
    private static void checkStringArgument(String[] split, String command) throws DukeArgumentException {
        if (split.length == 1 && command.equals(COMMAND.FIND)) {
            throw new DukeArgumentException("You have not entered a keyword!");
        } else if (split.length == 1) {
            throw new DukeArgumentException("You have not entered a task description!");
        } else if (split.length > 1 && (split[1].equals("/by") || split[1].equals("/at"))) {
            throw new DukeArgumentException("You have not entered a task description!");
        } else {
            // Do nothing if a keyword or task description is provided.
        }
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
        try {
            LocalDateTime.parse(details[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException(
                    "Please make sure that you enter the Date and Time "
                            + "in the following format: yyyy-mm-dd HHmm");
        }
    }

    /**
     * Checks whether the user has entered a valid task index.
     *
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @param tasks List of tasks.
     * @return Task index entered by user if it is valid.
     * @throws DukeArgumentException When user enters a non-integer, an out of bounds index or if user
     *                               did not enter any index.
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
