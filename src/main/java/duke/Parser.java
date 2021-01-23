package duke;

import duke.exceptions.AddMissingInputException;
import duke.exceptions.DukeIncompleteInputException;
import duke.exceptions.UnknownInputException;

import java.rmi.activation.UnknownObjectException;

public class Parser {

    /** Action input from user that is parsed into an array for checking */
    private String [] parsedAction = null;

    public Parser() {
        parsedAction = null;
    }

    public Parser(String action) {
        parsedAction = action.split(" ");
    }

    /**
     * Checks the input and validates it
     *
     * @throws AddMissingInputException if adding task command has missing input
     * @throws DukeIncompleteInputException if command has incomplete input
     * @throws UnknownInputException if input is not recognized by duke
     */
    public void check() throws AddMissingInputException, DukeIncompleteInputException, UnknownInputException {
        if (parsedAction[0].equals("todo") || parsedAction[0].equals("deadline") ||
                parsedAction[0].equals("event")) {

            if (parsedAction.length <= 1)
                throw new AddMissingInputException(parsedAction[0]);

        } else if (parsedAction[0].equals("done") && parsedAction.length <= 1) {

            throw new DukeIncompleteInputException("done not completed");

        } else if (parsedAction[0].equals("delete") && parsedAction.length <= 1) {

            throw new DukeIncompleteInputException("delete not completed");

        } else if (parsedAction[0].equals("check") && parsedAction.length <= 1) {
            throw new DukeIncompleteInputException("check not completed");
        }

    }

    public String[] getParsedAction() {
        return this.parsedAction;
    }
}
