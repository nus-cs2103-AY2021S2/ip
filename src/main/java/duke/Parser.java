package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDescriptionException;

/**
 * Parser class to make sense of user input.
 */
public class Parser {

    /**
     * Constructor for Parser class.
     * Initializes a Parser object.
     */
    public Parser() {
    }

    /**
     * Checks whether input task description is missing.
     * @param inputArr user input array.
     * @throws DukeException if input task description is missing.
     */
    public void isValidDescription(String[] inputArr) throws InvalidDescriptionException {
        if (inputArr.length == 1 || inputArr[1].equals("") || inputArr[1].startsWith(" ")) {
            throw new InvalidDescriptionException();
        }
    }

    /**
     * Returns an input array of String objects given an input String.
     * @param input user input.
     * @return an array of String objects.
     */
    public String[] getInputArr(String input) {
        return input.split(" ", 2);
    }

    public String getDescription(String[] inputArr, String taskType) {
        if (taskType.equals("todo")) {
            return inputArr[1];
        } else {
            return inputArr[1].substring(0, inputArr[1].indexOf("/") - 1);
        }
    }

    public String getDate(String[] inputArr, String taskType) {
        return inputArr[1].substring(inputArr[1].indexOf("/") + 4);
    }
}
