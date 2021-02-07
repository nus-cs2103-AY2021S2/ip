package duke.parser;

import duke.command.Command;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.MarkTaskAsDoneCommand;
import duke.command.FindTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ListTaskCommand;
import duke.command.UndoCommand;
import duke.command.ByeCommand;
import duke.exception.DukeException;

import duke.ui.UserInputType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Parses String from user input into a LocalDateTime object.
     *
     * @param dateTimeString String input entered by user.
     * @return LocalDateTime object of a task.
     */
    public static LocalDateTime parseDateTimeFromInput(String dateTimeString) {
        DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(dateTimeString, inputDateTimeFormatter);
    }

    /**
     * Parses String from file into a LocalDateTime object.
     *
     * @param dateTimeString String from file.
     * @return LocalDateTime object of a task.
     */
    public static LocalDateTime parseDateTimeFromFile(String dateTimeString) {
        DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return LocalDateTime.parse(dateTimeString, inputDateTimeFormatter);
    }

    /**
     * Parses LocalDateTime object into a formatted string.
     *
     * @param dateTime LocalDateTime object for parsing.
     * @return Formatted string representing the time of a task.
     */
    public static String parseDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput Input entered by user.
     * @return Command object based on user input.
     * @throws DukeException Unrecognized input by user.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] userInputArr = userInput.split(" ", 2);
        if (userInputArr.length != 2) {
            userInputArr = new String[]{userInputArr[0], ""};
        }
        String details = userInputArr[1].trim();

        switch (parseUserInputType(userInputArr[0].trim())) {
        case TODO:
            return new AddTodoCommand(details);
        case DEADLINE:
            return new AddDeadlineCommand(details);
        case EVENT:
            return new AddEventCommand(details);
        case DONE:
            return new MarkTaskAsDoneCommand(details);
        case FIND:
            return new FindTaskCommand(details);
        case DELETE:
            return new DeleteTaskCommand(details);
        case LIST:
            return new ListTaskCommand(details);
        case UNDO:
            return new UndoCommand(details);
        case BYE:
            return new ByeCommand(details);
        default:
            throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }

    /**
     * Returns in the type of input entered by user.
     *
     * @param userInput Input entered by user.
     * @return UserInputType object based on user input.
     * @throws DukeException Unrecognized input by user.
     */
    public static UserInputType parseUserInputType(String userInput) throws DukeException {
        try {
            return UserInputType.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }
}
