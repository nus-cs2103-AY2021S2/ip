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

    /**
     * The types of commands available.
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
            return new AddToDo("todo", description.replaceAll("todo ", ""));
        case EVENT:
            try {
                return processEvent(input);
            } catch (Exception e) {
                e.printStackTrace();
                throw new AntonioException(e.getMessage() + "\nPlease enter a valid format\n'/at "
                        + "YYYY-MM-DD TIME-TIME'");
            }
        case DEADLINE:
            try {
                return processDeadline(input);
            } catch (Exception e) {
                e.printStackTrace();
                throw new AntonioException(e.getMessage() + "\nPlease enter a valid format\n'/by "
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
            return new FindCommand(processedInput[1]);
        default:
            throw new AntonioException("Invalid command. Please enter a valid one");
        }
    }

    private static boolean isValidNumericalValue(String s) {
        return !s.matches("[0-9]+");
    }

    private static AddDeadline processDeadline(String input) throws Exception {
        String preProcessedData = input.split(" /by ")[1];
        String[] dateTime = preProcessedData.split(" ");
        String time = dateTime[1];
        if (time.length() != 4) {
            throw new IllegalArgumentException("\nYou have entered the time in a wrong format,"
                    + " please ensure it is in 24hr format");
        }
        LocalDate deadline = LocalDate.parse(dateTime[0]);
        String description = input.split(" /by ")[0].split("deadline ")[1];
        return new AddDeadline("deadline", description, deadline, time);
    }

    private static AddEvent processEvent(String input) throws Exception {
        String preProcessedData = input.split(" /at ")[1];
        String[] dateTime = preProcessedData.split(" ");
        LocalDate eventDate = LocalDate.parse(dateTime[0]);
        String startTime = dateTime[1].split("-")[0];
        String endTime = dateTime[1].split("-")[1];
        if (startTime.length() != 4) {
            throw new IllegalArgumentException("\nYou have entered the starting time in a wrong format,"
                    + " please ensure it is in 24hr format");
        }
        if (endTime.length() != 4) {
            throw new IllegalArgumentException("\nYou have entered the ending time in a wrong format,"
                    + " please ensure it is in 24hr format");
        }
        String description = input.split(" /at ")[0].split("event ")[1];
        return new AddEvent("event", description, eventDate, startTime, endTime);
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
