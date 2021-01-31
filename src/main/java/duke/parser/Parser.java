package duke.parser;

import duke.command.Command;
import duke.dukeException.DukeException;

public class Parser {
    public String commandName;

    public static Command parse(String fullCommand) throws DukeException {
        return new Command(fullCommand);
    }
}
