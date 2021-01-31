package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.ChatBotCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exceptions.ChatBotException;
import duke.exceptions.InvalidCommandTypeException;
import duke.exceptions.InvalidDateFormatException;
import duke.exceptions.InvalidTimeFormatException;



public class Parser {
    private static final String[] COMMAND_TYPE_ARRAY = {
        "bye", "list", "todo", "deadline", "event", "find", "done", "delete"
    };
    private static LocalDate formatDate(String date) throws InvalidDateFormatException {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
            return LocalDate.parse(date, dateFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    private static LocalDateTime formatTime(String time) throws InvalidTimeFormatException {
        try {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd MM yyyy hh:mm a");
            return LocalDateTime.parse(time, timeFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException();
        }
    }

    /**
     *  Parses the string input to a command line
     *
     *  @param command command input.
     *  @return ChatBotCommand object.
     *  @throws ChatBotException when encounters an error.
     */

    public static ChatBotCommand parse(String command) throws ChatBotException {
        String[] words = command.strip().split(" ", 2);
        String commandType = words[0];
        boolean isValidCommand = false;
        for (String expectedCommand : COMMAND_TYPE_ARRAY) {
            if (commandType.equals(expectedCommand)) {
                isValidCommand = true;
                break;
            }
        }
        if (!isValidCommand) {
            throw new InvalidCommandTypeException();
        }

        try {
            switch (commandType) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new TodoCommand(words[1]);
            case "deadline":
                String[] deadlineArray = words[1].split(" /by ");
                String ddlName = deadlineArray[0].strip();
                String deadline = deadlineArray[1].strip();
                return new DeadlineCommand(ddlName, formatDate(deadline));
            case "event":
                String[] timeArray = words[1].split(" /at ");
                String eventName = timeArray[0].strip();
                String time = timeArray[1];
                String[] timePeriod = time.split("-");
                String startTime = timePeriod[0].strip();
                String date = startTime.substring(0, 10);
                String endTime = date + " " + timePeriod[1].strip();
                return new EventCommand(eventName, formatTime(startTime), formatTime(endTime));
            case "find":
                return new FindCommand(words[1]);
            case "done":
                return new DoneCommand(Integer.parseInt(words[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(words[1]));
            default:
                throw new InvalidCommandTypeException();
            }
        } catch (ChatBotException e) {
            throw e;
        }
    }
}
