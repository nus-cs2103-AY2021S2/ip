package dbot.parser;

import dbot.command.Command;
import dbot.command.DeadlineCommand;
import dbot.command.DeleteCommand;
import dbot.command.DoneCommand;
import dbot.command.EventCommand;
import dbot.command.ExitCommand;
import dbot.command.FindCommand;
import dbot.command.HelpCommand;
import dbot.command.ListCommand;
import dbot.command.TodoCommand;
import dbot.exception.DBotException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Parses user input and generates representative DBot commands.
 */
public class Parser {

    /**
     * Parses user input from direct DBot interactions with the user.
     *
     * @param userInput A String containing the user input.
     * @return A Command representing the user input.
     * @throws DBotException If the user input cannot be parsed as a valid command.
     */
    public static Command parse(String userInput) throws DBotException {
        String[] userInputs = userInput.split("\\s+", 2);
        String[] strippedUserInputs = Arrays.stream(userInputs)
                .map(String::strip)
                .toArray(String[]::new);

        return Parser.parse(strippedUserInputs, false);
    }

    /**
     * Parses input containing the string representation of a saved Task and recognises whether
     * the Task was marked as done.
     *
     * @param inputFromSave A String containing the string representation of a saved Task.
     * @return A Command representing the saved Task.
     * @throws DBotException If the input text cannot be parsed as a valid command.
     */
    public static Command parseSaved(String inputFromSave) throws DBotException {
        String[] inputs = inputFromSave.split("\\|", 3);
        String[] strippedInputs = Arrays.stream(inputs)
                .map(String::strip)
                .toArray(String[]::new);

        boolean commandIsDone = Boolean.parseBoolean(strippedInputs[1]);
        String[] taskTypeAndDescription = {strippedInputs[0], strippedInputs[2]};
        return Parser.parse(taskTypeAndDescription, commandIsDone);
    }

    private static Command parse(String[] inputs, boolean isDone) throws DBotException {
        Command command;
        try {
            command = parseSwitch(inputs[0], inputs);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DBotException("Command was not properly called");
        } catch (DateTimeParseException e) {
            throw new DBotException("Date must be specified in YYYY-MM-DD format.");
        }
        command.setIsDone(isDone);
        return command;
    }

    private static Command parseSwitch(String commandType, String[] inputs) throws ArrayIndexOutOfBoundsException,
            DateTimeParseException {
        // Variables that are used in the switch case
        Command command;
        String commandDescription;
        String[] descriptors;
        switch (commandType) {
        case TodoCommand.COMMAND_WORD:
            command = parseTodoCommand(inputs[1]);
            break;
        case EventCommand.COMMAND_WORD:
            command = parseEventCommand(inputs[1]);
            break;
        case DeadlineCommand.COMMAND_WORD:
            command = parseDeadlineCommand(inputs[1]);
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case DoneCommand.COMMAND_WORD:
            command = parseDoneCommand(inputs[1]);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = parseDeleteCommand(inputs[1]);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case FindCommand.COMMAND_WORD:
            command = parseFindCommand(inputs[1]);
            break;
        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            command = new HelpCommand();
        }
        return command;
    }

    private static Command parseFindCommand(String input) {
        return new FindCommand(input);
    }

    private static Command parseDeleteCommand(String input) {
        return new DeleteCommand(Integer.parseInt(input));
    }

    private static Command parseDoneCommand(String input) {
        int targetIndex = Integer.parseInt(input);
        return new DoneCommand(targetIndex);
    }

    private static Command parseDeadlineCommand(String input) {
        String[] descriptors = getTaskDescriptorAndDate(input, "/by");

        String taskDescription = descriptors[0];
        String taskDateString = descriptors[1];
        LocalDate taskDate = LocalDate.parse(taskDateString);

        return new DeadlineCommand(taskDescription, taskDate);
    }

    private static Command parseEventCommand(String input) {
        String[] descriptors = getTaskDescriptorAndDate(input, "/at");

        String taskDescription = descriptors[0];
        String taskDateString = descriptors[1];
        LocalDate taskDate = LocalDate.parse(taskDateString);

        return new EventCommand(taskDescription, taskDate);
    }

    private static Command parseTodoCommand(String input) {
        return new TodoCommand(input);
    }

    private static String[] getTaskDescriptorAndDate(String input, String delimiter) {
        assert !delimiter.equals("\\s+") : "A Command delimiter cannot be whitespace.";

        String[] commandComponents = input.split(delimiter, 2);
        String[] strippedCommandComponents = Arrays.stream(commandComponents)
                .map(String::strip)
                .toArray(String[]::new);
        return strippedCommandComponents;
    }

}

