package duke.logging;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.UnknownCommand;
import duke.command.Command;
import duke.command.FindCommand;

/**
 * The Parser class denotes a parser.
 */
public class Parser {
    /**
     * Parse a certain string and determine which command is being called.
     * @param fullCommand   The string to be parsed.
     * @return              A command to be executed.
     */
    public static Command parse(String[] fullCommand) {
        String type = fullCommand[0];
        String taskDescription = fullCommand[1];

        if (type.equals("list")) {
            return new ListCommand(taskDescription);
        } else if (type.equals("bye")) {
            return new ByeCommand(taskDescription);
        } else if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
            return new AddCommand(type, taskDescription);
        } else if (type.equals("done")) {
            return new DoneCommand(taskDescription);
        } else if (type.equals("delete")) {
            return new DeleteCommand(taskDescription);
        } else if (type.equals("find")) {
            return new FindCommand(taskDescription);
        } else {
            return new UnknownCommand(taskDescription);
        }
    }
}
