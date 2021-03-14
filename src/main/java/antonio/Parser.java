package antonio;

import java.time.LocalDate;
import java.util.Arrays;

import antonio.command.AddDeadline;
import antonio.command.AddEvent;
import antonio.command.AddToDo;
import antonio.command.ByeCommand;
import antonio.command.Command;
import antonio.command.DeleteCommand;
import antonio.command.DoneCommand;
import antonio.command.FindCommand;
import antonio.command.HelpCommand;
import antonio.command.ListCommand;

/**
 * Represents a parser that handles input from a user.
 */
public class Parser {

    private static final int MAX_TIME_VALUE = 2359;
    private static final int MIN_TIME_VALUE = 0;

    /**
     * Represents the types of commands available.
     */
    enum CommandType {
        HELP,
        LIST,
        BYE,
        DONE,
        TODO,
        EVENT,
        DEADLINE,
        DELETE,
        FIND;
    }

    /**
     * Parses the input provided and returns a command.
     * @param input Input string to be parsed.
     * @return A command after parsing the input.
     * @throws AntonioException If invalid command is given.
     */
    public static Command parse(String input) throws AntonioException {

        String[] processedInput = input.split(" ");
        String description = processDescription(processedInput);
        CommandType commandType = identifyCommandType(processedInput);
        assert commandType != null : "commandType should not be null";

        switch (commandType) {
        case HELP:
            return new HelpCommand();
        case LIST:
            return new ListCommand();
        case BYE:
            return new ByeCommand();
        case DONE:
            if (processedInput.length == 1) {
                throw new AntonioException("Please enter valid numerical input to mark done");
            } else if (isValidNumericalValue(processedInput[1])) {
                throw new AntonioException("Please enter a valid task number to mark done");
            }
            return new DoneCommand(Integer.parseInt(processedInput[1]));
        case TODO:
            String[] processTodo = input.split("todo ");
            if (processTodo.length <= 1) {
                throw new AntonioException("Please enter something as a task description");
            }
            return new AddToDo("todo", description.replaceAll("todo ", ""));
        case EVENT:
            try {
                return processEvent(input);
            } catch (InvalidTimeFormatException e) {
                e.printStackTrace();
                throw new AntonioException(e.getMessage() + "\nPlease enter a valid format\n'event <description> /at "
                        + "YYYY-MM-DD TIME /to YYYY-MM-DD TIME'");
            } catch (Exception e) {
                e.printStackTrace();
                throw new AntonioException("\nPlease enter a valid format\n'event <description> /at "
                        + "YYYY-MM-DD TIME /to YYYY-MM-DD TIME'");
            }
        case DEADLINE:
            try {
                return processDeadline(input);
            } catch (InvalidTimeFormatException e) {
                e.printStackTrace();
                throw new AntonioException(e.getMessage() + "\nA valid format is:\n'deadline <description> /by "
                        + "YYYY-MM-DD TIME'");
            } catch (Exception e) {
                throw new AntonioException("Please enter a valid format:\n'deadline <description> /by "
                        + "YYYY-MM-DD TIME'");
            }
        case DELETE:
            if (processedInput.length == 1) {
                throw new AntonioException("Please enter a task number to delete");
            } else if (isValidNumericalValue(processedInput[1])) {
                throw new AntonioException("Please enter a valid task number to delete");
            }

            return new DeleteCommand(Integer.parseInt(processedInput[1]));
        case FIND:
            String[] processFind = input.split("find ");
            if (processFind.length <= 1) {
                throw new AntonioException("Please enter something to find");
            }
            String key = processFind[1];
            return new FindCommand(key);
        default:
            throw new AntonioException("Invalid command. Please enter a valid one");
        }
    }

    private static boolean isValidNumericalValue(String s) {
        return !s.matches("[1-9]+");
    }

    private static AddDeadline processDeadline(String input) throws Exception {
        String preProcessedData = input.split(" /by ")[1];
        String[] dateTime = preProcessedData.split(" ");

        assert dateTime[1] != null : "dateTime[1] should have a value";
        int time = Integer.parseInt(dateTime[1]);
        boolean isValidTimeFormat = (time >= MIN_TIME_VALUE) && (time <= MAX_TIME_VALUE);

        if (!isValidTimeFormat) {
            throw new InvalidTimeFormatException("You have entered the time in a wrong format,"
                    + " please ensure it is in 24hr format");
        }
        LocalDate deadline = LocalDate.parse(dateTime[0]);
        String description = input.split(" /by ")[0].split("deadline ")[1];
        return new AddDeadline("deadline", description, deadline, Integer.toString(time));
    }

    private static AddEvent processEvent(String input) throws Exception {
        String[] preProcessedData = input.split(" /at ");
        String eventDuration = preProcessedData[1];
        String[] startData = eventDuration.split(" /to ")[0].split(" ");
        String[] endData = eventDuration.split(" /to ")[1].split(" ");

        LocalDate startDate = LocalDate.parse(startData[0]);
        LocalDate endDate = LocalDate.parse(endData[0]);
        String startTime = startData[1];
        String endTime = endData[1];

        int startTimeCheck = Integer.parseInt(startTime);
        int endTimeCheck = Integer.parseInt(endTime);

        boolean isStartTimeValid = (startTimeCheck >= MIN_TIME_VALUE) && (startTimeCheck <= MAX_TIME_VALUE);
        boolean isEndTimeValid = (endTimeCheck >= MIN_TIME_VALUE) && (endTimeCheck <= MAX_TIME_VALUE);

        if (!isStartTimeValid && !isEndTimeValid) {
            throw new InvalidTimeFormatException("You have entered BOTH times in a wrong format,"
                    + " please ensure it is in 24hr format");
        }
        if (!isStartTimeValid) {
            throw new InvalidTimeFormatException("You have entered the STARTING time in a wrong format,"
                    + " please ensure it is in 24hr format");
        }
        if (!isEndTimeValid) {
            throw new InvalidTimeFormatException("You have entered the ENDING time in a wrong format,"
                    + " please ensure it is in 24hr format");
        }

        String description = input.split(" /at ")[0].split("event ")[1];
        return new AddEvent("event", description, startDate, startTime, endDate, endTime);
    }

    private static String processDescription(String[] processedInput) {
        assert processedInput.length >= 1 : "input length must be at least 1";
        StringBuilder sb = new StringBuilder();
        Arrays.stream(processedInput).forEach(inputLine -> sb.append(inputLine).append(" "));
        return sb.toString();
    }

    private static CommandType identifyCommandType(String[] processedInput) throws AntonioException {
        String commandString = processedInput[0];
        switch (commandString) {
        case "help":
            return CommandType.HELP;
        case "list":
            return CommandType.LIST;
        case "bye":
            return CommandType.BYE;
        case "done":
            return CommandType.DONE;
        case "todo":
            return CommandType.TODO;
        case "event":
            return CommandType.EVENT;
        case "deadline":
            return CommandType.DEADLINE;
        case "delete":
            return CommandType.DELETE;
        case "find":
            return CommandType.FIND;
        default:
            throw new AntonioException("There is no such command, enter a correcto one per favore?"
                    + "\nIf you need help, enter 'help'.");
        }
    }

}
