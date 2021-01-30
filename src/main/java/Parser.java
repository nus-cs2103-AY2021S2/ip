import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parses a valid input string into the corresponding valid Commmand.
     * @param input Input string to be parsed.
     * @return The corresponding Command of the input string.
     * @throws DukeException Thrown exception caused by the reason specified in its error body.
     */
    public static Command parse(String input) throws DukeException {
        String[] commandAndParams = input.split(" ", 2);
        String command = commandAndParams[0].toLowerCase();
        Command parsed = null;
        boolean hasParams = commandAndParams.length > 1 && !commandAndParams[1].isBlank();

        switch (command) {
        case "bye":
            parsed = new CommandBye();
            break;

        case "deadline":
            if (!hasParams) {
                throw new DukeException("Deadline description and date cannot be empty!");
            }

            String[] deadlineArgs = commandAndParams[1].split(" /by ", 2);
            if (deadlineArgs.length != 2) {
                throw new DukeException("Deadline command must follow the format: description /by time");
            }

            String[] deadlineDateTime = deadlineArgs[1].split(" ");
            String deadlineDate = deadlineDateTime[0];
            String deadlineTime = deadlineDateTime.length > 1 // if time is not provided, default time is 23:59:59
                    ? deadlineDateTime[1].substring(0, 2) + ":" + deadlineDateTime[1].substring(2, 4)
                    : "23:59:59";
            try {
                LocalDateTime parsedDeadlineDateTime = LocalDateTime.parse(deadlineDate + "T" + deadlineTime);
                parsed = new CommandDeadline(deadlineArgs[0], parsedDeadlineDateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please follow the Date-Time format: YYYY-MM-DD TIME");
            }

            break;

        case "delete":
            if (!hasParams) {
                throw new DukeException("Task index cannot be empty!");
            }

            parsed = new CommandDelete(Integer.parseInt(commandAndParams[1]));
            break;

        case "done":
            if (!hasParams) {
                throw new DukeException("Task index cannot be empty!");
            }

            parsed = new CommandDone(Integer.parseInt(commandAndParams[1]));
            break;

        case "event":
            if (!hasParams) {
                throw new DukeException("Event description and date cannot be empty!");
            }

            String[] eventArgs = commandAndParams[1].split(" /at ", 2);
            if (eventArgs.length != 2) {
                throw new DukeException("Event command must follow the format: description /at time");
            }

            String[] eventDateTime = eventArgs[1].split(" ");
            String eventDate = eventDateTime[0];
            String eventTime = eventDateTime.length > 1 // if time is not provided, default time is 00:00
                    ? eventDateTime[1].substring(0, 2) + ":" + eventDateTime[1].substring(2, 4)
                    : "00:00:00";
            try {
                LocalDateTime parsedEventDateTime = LocalDateTime.parse(eventDate + "T" + eventTime);
                parsed = new CommandEvent(eventArgs[0], parsedEventDateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please follow the Date-Time format: YYYY-MM-DD TIME");
            }

            break;

        case "find":
            if (!hasParams) {
                throw new DukeException("Search term cannot be empty!");
            }

            parsed = new CommandFind(commandAndParams[1]);
            break;

        case "list":
            parsed = new CommandList();
            break;

        case "todo":
            if (!hasParams) {
                throw new DukeException("ToDo description cannot be empty!");
            }

            parsed = new CommandToDo(commandAndParams[1]);
            break;

        default:
            throw new DukeException("Invalid command, please provide a supported command.");
        }

        if (parsed == null) {
            throw new DukeException("Error in parsed command");
        }

        return parsed;
    }
}
