package duke.parser;

import duke.command.*;

/**
 * A Parser class to parse inputs given by user into Commands.
 */
public class Parser {
    /**
     * Parses the given String into a Command.
     * @param fullCommand The user input to be parsed.
     * @return The command as parsed from the input.
     */
    public static Command parse(String fullCommand) {
        String[] commandArr = fullCommand.split(" ", 2);
        String type = commandArr[0];
        switch (type) {
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "todo":
            case "deadline":
            case "event":
                return new AddTaskCommand(type, fullCommand);
            case "bye":
                return new ByeCommand();
            default:
                return new UnknownCommand();
        }
    }
}