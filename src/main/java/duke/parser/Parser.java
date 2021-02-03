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
        if (fullCommand.equals("bye")) {
            command = new ExitCommand();
        } else if (fullCommand.equals("list")) {
            command = new ListCommand();
        } else {
            String[] splitCommands = fullCommand.split(" ");
            if (splitCommands[0].equals("done")) {
                command = new DoneCommand(fullCommand);
            } else if (splitCommands[0].equals("delete")) {
                command = new DeleteCommand(fullCommand);
            } else if (splitCommands[0].equals("find")) {
                command = new FindCommand(fullCommand);
            } else {
                command = new AddCommand(fullCommand);
            }
        }
        return command;
    }
}
