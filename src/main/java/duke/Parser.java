package duke;

/**
 * This class is in charge of understanding the user's input and process input
 * to be passed to other classes for further processing
 */
public class Parser {

    public Parser() {
    }

    /**
     * Returns boolean value indicating whether input text is numerical
     *
     * @param str string to be tested
     * @return boolean value
     */
    public static boolean isNumber(String str) {
        // check for null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns an array of strings with processed text inputs
     * e.g. a processed input for deadline reading /by 2019-02-20 8pm will be ["DDL", "0", "reading", "2019-02-20 8pm"]
     * the returned array will then be passed to other functions in Duke.java for further processing
     *
     * @param inputLine input by user
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processInput(String inputLine) throws DukeException{

        String[] results = new String[5];

        if (inputLine.equals("list")) {
            results[0] = "LST";
        } else if (inputLine.equals("bye")) {
            results[0] = "BYE";
        } else if (inputLine.equals("done")) {
            throw new DukeException("The task number of a done cannot be empty. Please try again.");
        } else if (inputLine.startsWith("done")) {
            results = processDone(inputLine, results);
        } else if (inputLine.startsWith("todo")) {
            results = processTodo(inputLine, results);
        } else if (inputLine.startsWith("deadline")) {
            results = processDl(inputLine, results);
        } else if (inputLine.startsWith("event")) {
            results =processEv(inputLine, results);
        } else if (inputLine.startsWith("delete")) {
            results = processDelete(inputLine, results);
        } else if (inputLine.startsWith("find")) {
            results = processFind(inputLine, results);
        } else {
            throw new DukeException("I'm sorry, I don't understand what that means.");
        }
        return results;
    }

    /**
     * Returns an array of strings with processed text inputs for Done
     * the returned array will then be passed to other functions in Duke.java for further processing
     *
     * @param inputLine input by user
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processDone(String input, String[] results) throws DukeException {
        String index = input.substring(5);

        if (isNumber(index)) {
            results[0] = "DON";
            results[1] = index;
        } else {
            throw new DukeException("Please enter a numerical task number.");
        }
        return results;
    }

    /**
     * Returns an array of strings with processed text inputs for Todo
     * the returned array will then be passed to other functions in Duke.java for further processing
     *
     * @param inputLine input by user
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processTodo(String input, String[] results) throws DukeException {
        if (input.equals("todo")) {
            throw new DukeException("Please enter a todo description.");
        } else {
            String todoDesc = input.substring(5);
            results[0] = "TDO";
            results[1] = todoDesc;
        }
        return results;
    }

    /**
     * Returns an array of strings with processed text inputs for Deadline
     * the returned array will then be passed to other functions in Duke.java for further processing
     *
     * @param inputLine input by user
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processDl(String input, String[] results) throws DukeException {
        if (input.equals("deadline")) {
            throw new DukeException("Please enter a deadline description.");
        } else {
            String dlMsg = input.substring(9);
            String[] temp = dlMsg.split(" /by ");

            if (temp.length == 1) {
                throw new DukeException("Please enter a deadline completion time.");
            } else {
                String dlDesc = temp[0];
                String by = temp[1];

                results[0] = "DDL";
                results[1] = dlDesc;
                results[2] = by;
            }
        }
        return results;
    }

    /**
     * Returns an array of strings with processed text inputs for Event
     * the returned array will then be passed to other functions in Duke.java for further processing
     *
     * @param inputLine input by user
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processEv(String input, String[] results) throws DukeException {
        if (input.equals("event")) {
            throw new DukeException("Please enter an event description.");
        } else {
            String dlMsg = input.substring(6);
            String[] temp = dlMsg.split(" /at ");

            if (temp.length == 1) {
                throw new DukeException("Please enter an event time.");
            } else {
                String evDesc = temp[0];
                String at = temp[1];

                results[0] = "ENT";
                results[1] = evDesc;
                results[2] = at;
            }
        }
        return results;
    }

    /**
     * Returns an array of strings with processed text inputs for Delete
     * the returned array will then be passed to other functions in Duke.java for further processing
     *
     * @param inputLine input by user
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processDelete(String input, String[] results) throws DukeException {
        if (input.equals("delete")) {
            throw new DukeException("Please tell me which task you'd like to delete.");
        } else {
            String temp = input.substring(7);

            if (isNumber(temp)) {
                results[0] = "DLT";
                results[1] = temp;
            } else {
                throw new DukeException("Please enter a numerical task number.");
            }
        }
        return results;
    }

    /**
     * Returns an array of strings with processed text inputs for Find
     * the returned array will then be passed to other functions in Duke.java for further processing
     *
     * @param inputLine input by user
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processFind(String input, String[] results) throws DukeException {
        if (input.equals("find")) {
            throw new DukeException("Please tell me which task you'd like to find.");
        } else {
            String temp = input.substring(5);
            results[0] = "FND";
            results[1] = temp;
        }
        return results;
    }


}
