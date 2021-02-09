package duke.parser;
import duke.command.Command;
import duke.dukeException.DukeException;

public class Parser {

    /**
     * Class constructor.
     * @param fullCommand  the commands input by user.
     * @throws DukeException  If an input or output
     *  *                      exception occurred
     */
    public static Command parse(String fullCommand) throws DukeException {
        return new Command(fullCommand);
    }
}
