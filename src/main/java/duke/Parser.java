package duke;

import duke.exceptions.AddMissingInputException;
import duke.exceptions.DukeIncompleteInputException;

public class Parser {
    String [] parsedAction;

    public Parser() {
        parsedAction = null;
    }

    public Parser(String action) {
        parsedAction = action.split(" ");
    }

    public void check() throws AddMissingInputException, DukeIncompleteInputException {
        if (parsedAction[0].equals("todo") || parsedAction[0].equals("") ||
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
