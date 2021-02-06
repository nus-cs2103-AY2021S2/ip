package duke;

import duke.exceptions.DukeIncompleteInputException;
import duke.exceptions.MissingTaskInputException;
import duke.exceptions.UnknownInputException;

/**
 * The Parser class parses is a class which parses the user input
 * to check if the user input is valid
 * If the user input is invalid, an exception will be thrown
 */
public class Parser {

    /** Action input from user that is parsed into an array for checking */
    private String [] parsedAction = null;
    /**
     * Constructor to initialize the Parser object
     */
    public Parser() {
        parsedAction = null;
    }

    /**
     * Constructor to initialize the Parser object with given action
     * @param  action action refers to the user input when Duke asks what it should do
     */
    public Parser(String action) {
        parsedAction = action.split(" ");
    }

    /**
     * Checks the input and validates it
     *
     * @throws MissingTaskInputException if adding task command has missing input
     * @throws DukeIncompleteInputException if command has incomplete input
     * @throws UnknownInputException if input is not recognized by duke
     */
    public void check() throws MissingTaskInputException, DukeIncompleteInputException {

        boolean aTaskCommandEntered = parsedAction[0].equals("todo") || parsedAction[0].equals("deadline")
                                        || parsedAction[0].equals("event");
        
        boolean incompleteCommand = parsedAction.length <= 1;
        
        if (aTaskCommandEntered && incompleteCommand) {
            
            throw new MissingTaskInputException(parsedAction[0]);
            
        } else if (parsedAction[0].equals("done") && incompleteCommand) {

            throw new DukeIncompleteInputException("done not completed");

        } else if (parsedAction[0].equals("delete") && incompleteCommand) {

            throw new DukeIncompleteInputException("delete not completed");

        } else if (parsedAction[0].equals("check") && incompleteCommand) {

            throw new DukeIncompleteInputException("check not completed");

        } else if (parsedAction[0].equals("find") && incompleteCommand) {

            throw new DukeIncompleteInputException("find not completed");
        }

    }
    /**
     * Returns the result of the action parsed by the parser
     *
     * @return A string array containing the parsed user input
     */
    public String[] getParsedAction() {
        return this.parsedAction;
    }
}
