package duke;

import duke.exceptions.AddMissingInputException;
import duke.exceptions.DukeIncompleteInputException;
import duke.exceptions.UnknownInputException;

import java.rmi.activation.UnknownObjectException;

public class Parser {

    /** Action input from user that is parsed into an array for checking */
    String [] parsedAction;

    public Parser() {
        parsedAction = null;
    }

    public Parser(String action) {
        parsedAction = action.split(" ");
    }

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param x  X coordinate of position.
     * @param y Y coordinate of position.
     * @param zone Zone of position.
     * @return Lateral location.
     * @throws IllegalArgumentException  If zone is <= 0.
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
        } else {
            throw new UnknownInputException();
        }

    }

    public String[] getParsedAction() {
        return this.parsedAction;
    }
}
