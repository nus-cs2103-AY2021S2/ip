package duchess;

import duchess.Exceptions.MissingInputException;
import duchess.Exceptions.MissingTaskException;

public class Parser {

    public Parser() {}

    /**
     * Splits user input and checks for exceptions
     *
     * @throws MissingTaskException if chosen task is not specified
     * @throws MissingInputException if description of task is not provided
     * */
    public void parse(String command) throws MissingInputException, MissingTaskException {
        String[] arr = command.split(" ", 2);
        String action = arr[0];
        assert action != null : "Command action should not be empty";

        Boolean isDeadline = action.equals("deadline");
        Boolean isEvent = action.equals("event");
        Boolean isTodo = action.equals("todo");
        Boolean isDone = action.equals("done");
        Boolean isDelete = action.equals("delete");

        if (isDeadline || isEvent || isTodo) {
            if (arr.length <= 1) {
                throw new MissingInputException(action);
            }
        } else if (isDone || isDelete) {
            if (arr.length <= 1) {
                throw new MissingTaskException();
            }
        }
    }

    /**
     * Get command from user input
     *
     * @param command user input
     * @return string command
     * */
    public String getCommand(String command) {
        String[] arr = command.split(" ", 2);
        return arr[0];
    }

    /**
     * Get description of task from user input
     *
     * @param command user input
     * @return string description
     * */
    public String getDesc(String command) {
        int dateIndx = command.indexOf("/");
        int cmdIndx = command.indexOf(" ");
        if (cmdIndx == -1) {
            return "";
        } else if (dateIndx != -1) {
            return command.substring(cmdIndx + 1, dateIndx);
        } else {
            String[] arr = command.split(" ", 2);
            return arr[1];
        }
    }

    /** Get date of deadline or event
     *
     * @param command user input
     * @return string date
     * */
    public String getDate(String command) {
        int descIndx = command.indexOf("/");
        String date = command.substring(descIndx + 4);
        assert date.length() == 10 : "Task date not proper";
        return date;
    }
}
