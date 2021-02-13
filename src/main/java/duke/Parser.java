package duke;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.InvalidIndexException;

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
     *
     * @param inputArr user input array.
     * @throws DukeException if input task description is missing.
     */
    public void isValidDescription(String[] inputArr) throws InvalidDescriptionException {
        if (inputArr.length == 1 || inputArr[1].equals("") || inputArr[1].startsWith(" ")) {
            throw new InvalidDescriptionException();
        }
    }

    /**
     * Checks whether input index is out of range.
     *
     * @param inputArr user input array.
     * @throws InvalidIndexException
     */
    public void isValidIndex(String[] inputArr, ArrayList<Task> list) throws InvalidIndexException {
        int indexToDelete = Integer.parseInt(inputArr[1]);
        if (indexToDelete < 1 || indexToDelete > list.size()) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Returns an input array of String objects given an input String.
     *
     * @param input user input.
     * @return an array of String objects.
     */
    public String[] getInputArr(String input) {
        return input.split(" ", 2);
    }

    /**
     *  Gets the description of task from input array
     *
     * @param inputArr user input array.
     * @param taskType type of task.
     * @return
     */
    public String getDescription(String[] inputArr, String taskType) {
        if (taskType.equals("todo")) {
            return inputArr[1];
        } else {
            return inputArr[1].substring(0, inputArr[1].indexOf("/") - 1);
        }
    }

    /**
     *  Gets the date of task from input array
     *
     * @param inputArr user input array.
     * @param taskType type of task.
     * @return
     */
    public String getDate(String[] inputArr, String taskType) {
        if (taskType.equals("deadline")) {
            return inputArr[1].substring(inputArr[1].indexOf("/by") + 4);
        } else {
            assert taskType.equals("event") : "Task type should be an event";
            return inputArr[1].substring(inputArr[1].indexOf("/at") + 4);
        }
    }
}
