package duke;

import duke.Exceptions.MissingInputException;
import duke.Exceptions.MissingTaskException;
import duke.Exceptions.UnclearInputException;

public class Parser {

    private String[] arr;

    public Parser() {
    }

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

    public String[] getDesc(String cmd) {
        return cmd.split(" ", 2);
    }

}
