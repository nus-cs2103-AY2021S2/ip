package duke.logging;

import duke.command.*;

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
        } else {
            return new UnknownCommand(taskDescription);
        }
    }
}
