package duke;

import duke.Exceptions.MissingInputException;
import duke.Exceptions.MissingTaskException;
import duke.Exceptions.UnclearInputException;

public class Parser {

    /** Array to store input from user that is split by empty space */
    private String[] arr;

    public Parser() {}

    /**
     * Splits user input and checks for exceptions
     *
     * @throws MissingTaskException if chosen task is not specified
     * @throws MissingInputException if description of task is not provided
     * */
    public void parse(String cmd) throws MissingInputException, MissingTaskException {
        arr = cmd.split(" ", 2);
        String action = arr[0];
        if(action.equals("deadline") || action.equals("event") || action.equals("todo")) {
            if (arr.length <= 1) {
                throw new MissingInputException(action);
            }
        } else if(action.equals("done") || action.equals("delete")) {
            if (arr.length <= 1) {
                throw new MissingTaskException();
            }
        }
    }

    /**
     * Get command from user input
     *
     * @param user input
     * @return a string
     * */
    public String[] getDesc(String cmd) {
        return cmd.split(" ", 2);
    }

}
