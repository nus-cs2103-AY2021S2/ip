package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;

/**
 * Responsible for parsing command line inputs.
 */
public class Parser {
    /**
     * Returns the type of Command indicated by the user input.
     *
     * @param fullCommand User input to be parsed.
     * @return Command indicated by user input.
     */
    public static Command parse(String fullCommand) {
        Command command;
        if (isExitCommand(fullCommand)) {
            command = new ExitCommand();
        } else if (isListCommand(fullCommand)) {
            command = new ListCommand();
        } else {
            String[] splitCommands = fullCommand.split(" ");
            if (isDoneCommand(splitCommands[0])) {
                command = new DoneCommand(fullCommand);
            } else if (isDeleteCommand(splitCommands[0])) {
                command = new DeleteCommand(fullCommand);
            } else if (isFindCommand(splitCommands[0])) {
                command = new FindCommand(fullCommand);
            } else {
                command = new AddCommand(fullCommand);
            }
        }
        return command;
    }

    private static boolean isExitCommand(String fullCommand) {
        return fullCommand.equals("bye");
    }

    private static boolean isListCommand(String fullCommand) {
        return fullCommand.equals("list");
    }

    private static boolean isDoneCommand(String command) {
        return command.equals("done");
    }

    private static boolean isDeleteCommand(String command) {
        return command.equals("delete");
    }

    private static boolean isFindCommand(String command) {
        return command.equals("find");
    }
}
