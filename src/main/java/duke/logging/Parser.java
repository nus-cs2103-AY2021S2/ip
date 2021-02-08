package duke.logging;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;


/**
 * The Parser class denotes a parser.
 */
public class Parser {
    /**
     * Parse a certain string and determine which command is being called.
     * @param fullCommand   The string to be parsed.
     * @return              A command to be executed.
     */
    public static Command parse(String fullCommand) {
        String type;
        String taskDescription;
        if (fullCommand.contains(" ")) {
            int commandStrLength = fullCommand.indexOf(" ");
            type = fullCommand.substring(0, commandStrLength);
            taskDescription = fullCommand.substring(commandStrLength + 1, fullCommand.length());
        } else {
            type = fullCommand;
            taskDescription = "";
        }

        switch (type) {
        case "list":
            return new ListCommand(taskDescription);
        case "bye":
            new ByeCommand(taskDescription);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(type, taskDescription);
        case "done":
            return new DoneCommand(taskDescription);
        case "delete":
            return new DeleteCommand(taskDescription);
        case "find":
            return new FindCommand(taskDescription);
        default:
            return new UnknownCommand(taskDescription);
        }
    }
}
