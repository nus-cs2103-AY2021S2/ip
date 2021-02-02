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
import dbot.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String userInputText) throws DukeException {
        return Parser.parse(userInputText.strip().split("\\s+", 2), false);
    }

    public static Command parseSaved(String savedInputText) throws DukeException {
        String[] inputs = savedInputText.strip().split("\\|", 3);
        boolean commandDone = Boolean.parseBoolean(inputs[1].strip());
        return Parser.parse(new String[]{inputs[0], inputs[2]}, commandDone);
    }

    public static Command parse(String[] inputs, boolean isDone) throws DukeException {
        Command command;
        try {
            command = parseSwitch(inputs[0].strip(), inputs);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Command was not properly called");
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be specified in YYYY-MM-DD format.");
        }
        command.setIsDone(isDone);
        return command;
    }

    public static Command parseSwitch(String commandType, String[] inputs) throws ArrayIndexOutOfBoundsException,
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

