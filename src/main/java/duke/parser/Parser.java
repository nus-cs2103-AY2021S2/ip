package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.IncorrectCommand;
import duke.command.ListCommand;
import duke.dukeexception.DukeException;

public class Parser {
    /**
     * Transforms a String command to a Command object.
     *
     * @param fullCommand the commands input by user.
     *
     * @return the transformed Command object.
     *
     * @throws DukeException If an input or output exception occurred
     */
    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.trim();
        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.split(" ")[0].equals("done")) {
            return new DoneCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("todo")
                || fullCommand.split(" ")[0].equals("deadline")
                || fullCommand.split(" ")[0].equals("event")) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.split(" ")[0].equals("find")) {
            return new FindCommand(fullCommand);
        } else if (fullCommand.equals("bye")) {
            return new ByeCommand();
        } else if (fullCommand.equals("help")) {
            return new HelpCommand();
        } else {
            return new IncorrectCommand();
        }
    }
}
