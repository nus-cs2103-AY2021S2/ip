package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    public String[] processInput(String inputLine) throws DukeException {

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
            results = processEv(inputLine, results);
        } else if (inputLine.startsWith("delete")) {
            results = processDelete(inputLine, results);
        } else if (inputLine.startsWith("find")) {
            results = processFind(inputLine, results);
        } else if (inputLine.startsWith("update")) {
            results = processUpdate(inputLine, results);
        } else {
            throw new DukeException("I'm sorry, I don't understand what that means.");
        }
        return results;
    }

    /**
     * Returns an array of strings with processed text inputs for Done
     * the returned array will then be passed to other functions in Duke.java for further processing
     *
     * @param input input by user
     * @param results results array
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
     * @param input input by user
     * @param results results array
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
     * @param input input by user
     * @param results results array
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processDl(String input, String[] results) throws DukeException {
        if (input.equals("deadline")) {
            throw new DukeException("Please follow the correct deadline input format:\n"
                    + "deadline <desc> /by <yyyy-mm-dd> <time>\n");
        } else {
            String dlMsg = input.substring(9);
            String[] temp = dlMsg.split(" /by ");
            if (temp.length == 1) {
                throw new DukeException("Please follow the correct deadline input format:\n"
                        + "deadline <desc> /by <yyyy-mm-dd> <time>\n");
            } else {
                String dlDesc = temp[0];
                String by = temp[1];
                checkDateTimeFormat(by);
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
     * @param input input by user
     * @param results results array
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processEv(String input, String[] results) throws DukeException {
        if (input.equals("event")) {
            throw new DukeException("Please follow the correct event input format:\n"
                    + "event <desc> /at <yyyy-mm-dd> <time>\n");
        } else {
            String dlMsg = input.substring(6);
            String[] temp = dlMsg.split(" /at ");
            if (temp.length == 1) {
                throw new DukeException("Please follow the correct event input format:\n"
                        + "event <desc> /at <yyyy-mm-dd> <time>\n");
            } else {
                String evDesc = temp[0];
                String at = temp[1];
                checkDateTimeFormat(at);
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
     * @param input input by user
     * @param results results array
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
     * @param input input by user
     * @param results results array
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
            return results;
        }

    }

    /**
     * Processes relevant update information
     *
     * @param input input by user
     * @param results results array
     * @return an array of processes strings
     * @throws DukeException If user input is in invalid formats
     */
    public String[] processUpdate(String input, String[] results) throws DukeException {
        if (input.equals("update")) {
            throw new DukeException("Please tell me what you like to update.");
        } else {
            String updateMsg = input.substring(7);
            String[] temp = updateMsg.split(" ", 3);
            for (String str : temp) {
                System.out.println("input: " + str);
            }

            if (temp.length < 3) {
                throw new DukeException("Please follow the correct input format: update <task index> <TYPE> <details>");
            } else {
                String taskIndex = temp[0];
                String type = temp[1];
                String details = temp[2];

                if (type.equals("dt") || type.equals("desc")) {
                    results[0] = "UPD";
                    results[1] = taskIndex;
                    results[2] = type;
                    results[3] = details;
                } else {
                    throw new DukeException("Please tell me which attribute you want to update");
                }

                if (type.equals("dt")) {
                    checkDateTimeFormat(details);
                }
            }
        }
        return results;
    }

    /**
     * Checks if the date and time input from the user is of the valid format
     *
     * @param dateTimeInput input by user
     * @throws DukeException If user input is in invalid formats
     */
    public void checkDateTimeFormat(String dateTimeInput) throws DukeException {
        String[] inputs = dateTimeInput.split(" ");
        try {
            LocalDate date = LocalDate.parse(inputs[0]);
            date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (inputs.length > 1) {
                LocalTime time = LocalTime.parse(inputs[1]);
                time.format(DateTimeFormatter.ofPattern("HH:mm"));
            }
        } catch (Exception ex) {
            throw new DukeException("Please follow the correct datetime input format: <yyyy-mm-dd> <HH:mm>\n");
        }
    }
}
