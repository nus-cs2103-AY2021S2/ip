package yoda.parser;

import yoda.command.AddCommand;
import yoda.command.Command;
import yoda.command.CommandType;
import yoda.command.EditCommand;
import yoda.command.ExitCommand;
import yoda.command.HelpCommand;
import yoda.command.ListCommand;

/**
 * Parser class that parses user input to decide the next course of action.
 */
public class Parser {
    /**
     * Parses the input provided by the user to decide how to handle it.
     * @param input Input provided by the user.
     * @return Command needed to handle the input provided.
     */
    public static Command parse(String input) {
        String[] splitInput = input.split(" ");
        if (splitInput.length == 0) {
            splitInput = new String[]{"error"};
        }
        splitInput[0] = splitInput[0].toUpperCase();
        CommandType command = checkCommand(splitInput[0]);
        splitInput[0] = command.name();
        assert !splitInput[0].equals("") : "The command name must be here";
        switch(command) {
        case LIST:
        case FIND:
            return new ListCommand(splitInput);
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(splitInput);
        case DELETE:
        case DONE:
            return new EditCommand(splitInput);
        case BYE:
            return new ExitCommand();
        case HELP:
        case ERROR:
        default:
            return new HelpCommand(splitInput);
        }
    }

    private static CommandType checkCommand(String command) {
        try {
            return CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            return CommandType.ERROR;
        }
    }
}
