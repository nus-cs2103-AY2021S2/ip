package duke;

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
    public void isEmptyDesc(String[] inputArr) throws DukeException {
        if (inputArr.length == 1) {
            throw new DukeException();
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
}
