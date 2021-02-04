package dbot.parser;

import dbot.command.Command;
import dbot.command.DeadlineCommand;
import dbot.command.DeleteCommand;
import dbot.command.DoneCommand;
import dbot.command.EventCommand;
import dbot.command.ExitCommand;
import dbot.command.HelpCommand;
import dbot.command.ListCommand;
import dbot.command.TodoCommand;
import dbot.exception.DBotException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input and generates representative DBot commands.
 */
public class Parser {

    /**
     * Parses user input from direct DBot interactions with the user.
     *
     * @param userInputText A String containing the user input.
     * @return A Command representing the user input.
     * @throws DBotException If the user input cannot be parsed as a valid command.
     */
    public static Command parse(String userInputText) throws DBotException {
        return Parser.parse(userInputText.strip().split("\\s+", 2), false);
    }

    /**
     * Parses input containing the string representation of a saved Task and recognises whether
     * the Task was marked as done.
     *
     * @param savedInputText A String containing the string representation of a saved Task.
     * @return A Command representing the saved Task.
     * @throws DBotException If the input text cannot be parsed as a valid command.
     */
    public static Command parseSaved(String savedInputText) throws DBotException {
        String[] inputs = savedInputText.strip().split("\\|", 3);
        boolean commandDone = Boolean.parseBoolean(inputs[1].strip());
        return Parser.parse(new String[]{inputs[0], inputs[2]}, commandDone);
    }

    private static Command parse(String[] inputs, boolean isDone) throws DBotException {
        Command command;
        try {
            command = parseSwitch(inputs[0].strip(), inputs);
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
            commandDescription = inputs[1].strip();
            command = new TodoCommand(commandDescription);
            break;
        case EventCommand.COMMAND_WORD:
            commandDescription = inputs[1].strip();
            descriptors = commandDescription.split("/at", 2);
            command = new EventCommand(descriptors[0].strip(), LocalDate.parse(descriptors[1].strip()));
            break;
        case DeadlineCommand.COMMAND_WORD:
            commandDescription = inputs[1].strip();
            descriptors = commandDescription.split("/by", 2);
            command = new DeadlineCommand(descriptors[0].strip(), LocalDate.parse(descriptors[1].strip()));
            break;
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case DoneCommand.COMMAND_WORD:
            // E.g. "done 3 __the_rest_is_to_be_ignored__" marks the 3rd Task as complete
            commandDescription = inputs[1].strip();
            command = new DoneCommand(Integer.parseInt(commandDescription));
            break;
        case DeleteCommand.COMMAND_WORD:
            // E.g. "delete 3 __the_rest_is_to_be_ignored__" deletes the 3rd Task
            commandDescription = inputs[1].strip();
            command = new DeleteCommand(Integer.parseInt(commandDescription));
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            command = new HelpCommand();
        }
        return command;
    }
}

