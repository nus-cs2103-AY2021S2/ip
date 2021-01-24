package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.ui.UserInputType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDateTime parseDateTimeFromInput(String dateTimeString) {
        DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(dateTimeString, inputDateTimeFormatter);
    }

    public static LocalDateTime parseDateTimeFromFile(String dateTimeString) {
        DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return LocalDateTime.parse(dateTimeString, inputDateTimeFormatter);
    }

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
            case BYE:
                return new ByeCommand(details);
            default:
                throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }

    public static UserInputType parseUserInputType(String userInput) throws DukeException {
        try {
            return UserInputType.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException error) {
            throw new DukeException("Sorry, I dont understand what that means :-(");
        }
    }
}
