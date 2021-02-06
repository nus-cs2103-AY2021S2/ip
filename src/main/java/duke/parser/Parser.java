package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;
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
    public static Command parse(String input) throws InvalidInstructionException {
        String type = input.split(" ")[0];
        Command command = null;
        if (type.equals("todo")) {
            command = new AddCommand(input);
        } else if (type.equals("deadline")) {
            command = new AddCommand(input);
        } else if (type.equals("event")) {
            command = new AddCommand(input);
        } else if (type.equals("delete")) {
            command = new DeleteCommand(input);
        } else if (type.equals("done")) {
            command = new DoneCommand(input);
        } else if (type.equals("list")) {
            command = new ListCommand(input);
        } else if (type.equals("find")) {
            command = new FindCommand(input);
        } else if (type.equals("bye")) {
            command = new ByeCommand(input);
        } else if (type.equals("update")) {
            command = new UpdateCommand(input);
        } else {
            throw new InvalidInstructionException();
        }
        assert false : "All possiblilities have been handled.";

        return command;
    }
}
