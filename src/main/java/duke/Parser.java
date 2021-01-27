package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeDateTimeException;
import duke.exception.DukeDescriptionException;
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
    private static final Ui ui = new Ui();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses user input and returns a Command corresponding to user input.
     * @param userInput The input of the user.
     * @param tasks List of tasks.
     * @return An executable command.
     */
    public static Command parse(String userInput, TaskList tasks) {
        String[] split = userInput.split("\\s+");
        String command = split[0];
        Command res = null;
        try {
            switch (command) {
                case "todo":
                    checkDescription(split);
                    String description = userInput.substring(5);
                    res = new AddCommand(new ToDo(description));
                    break;
                case "deadline":
                    checkDescription(split);
                    String[] deadlineDetails = userInput.substring(9).split(" /by ");
                    checkDateTime(deadlineDetails);
                    String deadlineDescription = deadlineDetails[0];
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDetails[1], formatter);
                    res = new AddCommand(new Deadline(deadlineDescription, deadlineDateTime));
                    break;
                case "event":
                    checkDescription(split);
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
                    int completedTaskIdx = checkArgument(split, tasks);
                    res = new DoneCommand(completedTaskIdx);
                    break;
                case "delete":
                    int taskToDelete = checkArgument(split, tasks);
                    res = new DeleteCommand(taskToDelete);
                    break;
                case "bye":
                    res = new ExitCommand();
                    break;
                default:
                    res = new InvalidCommand();
            }
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
        return res;
    }

    /**
     * Checks whether the user has entered a description for the task to be added.
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @throws DukeDescriptionException when user has not entered a description.
     */
    private static void checkDescription(String[] split) throws DukeDescriptionException {
        if (split.length == 1) {
            throw new DukeDescriptionException("You have not entered a description!");
        }
    }

    /**
     * Checks whether the user has entered a date and time.
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
     * @param split Array of substrings obtained from removing blank spaces from user input.
     * @param tasks List of tasks.
     * @return Task index entered by user if it is valid.
     * @throws DukeArgumentException When user enters a non-integer, an out of bounds index or if user
     * has not entered any index.
     */
    private static int checkArgument(String[] split, TaskList tasks) throws DukeArgumentException {
        if (split.length > 2) {
            throw new DukeArgumentException("Please enter a numerical value for the task index!");
        }
        try {
            int taskIndex = Integer.parseInt(split[1])-1;
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
