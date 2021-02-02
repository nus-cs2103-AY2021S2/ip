package duke;

import duke.Exceptions.MissingInputException;
import duke.Exceptions.MissingTaskException;

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
        if (action.equals("deadline") || action.equals("event") || action.equals("todo")) {
            if (arr.length <= 1) {
                throw new MissingInputException(action);
            }
        } else if (action.equals("done") || action.equals("delete")) {
            if (arr.length <= 1) {
                throw new MissingTaskException();
            }
        }
    }

    /**
     * Get command from user input
     *
     * @param cmd user input
     * @return string command
     * */
    public String getCommand(String cmd) {
        String[] arr = cmd.split(" ", 2);
        return arr[0];
    }

    /**
     * Get description of task from user input
     *
     * @param cmd user input
     * @return string description
     * */
    public String getDesc(String cmd) {
        int dateIndx = cmd.indexOf("/");
        int cmdIndx = cmd.indexOf(" ");
        String desc;
        if (cmdIndx == -1) {
            return "";
        } else if (dateIndx != -1) {
            desc = cmd.substring(cmdIndx + 1, dateIndx);
        } else {
            String[] arr = cmd.split(" ", 2);
            desc = arr[1];
        }
        return desc;
    }

    /** Get date of deadline or event
     *
     * @param cmd user input
     * @return string date
     * */
    public String getDate(String cmd) {
        int descIndx = cmd.indexOf("/");
        String date = cmd.substring(descIndx + 4);
        return date;
    }
}
