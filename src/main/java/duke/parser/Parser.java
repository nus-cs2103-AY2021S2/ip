package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.InvalidInstructionException;

/**
 * Parser class to handle all commands and create the correct command.
 */

public class Parser {

    /**
     * Returns command corresponding to input.
     *
     * @param input user's input to determine which type of command
     * @return c Command of the input
     * @throws InvalidInstructionException If user keys in an invalid input
     */
    public static Command parse(String input) {
        String type = input.split(" ")[0];
        Command c = null;
        if (type.equals("todo")) {
            c = new AddCommand(input);
        } else if (type.equals("deadline")) {
            c = new AddCommand(input);
        } else if (type.equals("event")) {
            c = new AddCommand(input);
        } else if (type.equals("delete")) {
            c = new DeleteCommand(input);
        } else if (type.equals("done")) {
            c = new DoneCommand(input);
        } else if (type.equals("list")) {
            c = new ListCommand(input);
        } else if (type.equals("find")) {
            c = new FindCommand(input);
        } else if (type.equals("bye")) {
            c = new ByeCommand(input);
        } else {
            new InvalidInstructionException();
        }
        return c;
    }
}
