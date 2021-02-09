package duke.common;

import static duke.common.Messages.MESSAGE_INDEX_OUT_OF_BOUND;
import static duke.common.Messages.MESSAGE_NO_SEARCH_RESULT;
import static duke.common.Messages.MESSAGE_TASKLIST_EMPTY;

import duke.exception.DukeException;
import duke.tasks.TaskList;

/**
 * Contains common helper methods that is shared among the Command classes.
 */
public class CommandUtils {

    public static final String ALL = "all";

    /**
     * Asserts whether the inputs given are blank or empty.
     *
     * @param input input of the class
     */
    public static void assertInputs(String input) {
        assert !input.isBlank() : "input should not be blank";
        assert !input.isEmpty() : "input should not be empty";
    }

    /**
     * Checks whether the index entered by the user is within the range of the tasklist's size.
     *
     * @param index index entered
     * @param size tasklist's
     * @throws DukeException when the index is out of bounds.
     */
    public static void checkIndexOutOfBounds(int index, int size) throws DukeException {
        if (index < 0 || index >= size) {
            throw new DukeException(MESSAGE_INDEX_OUT_OF_BOUND);
        }
    }

    /**
     * Checks whether the tasklist is empty.
     *
     * @param taskList tasklist to be checked
     * @param isQuery indicator used to differentiate whether is it a search command
     * @return size of the tasklist
     * @throws DukeException when the tasklist is empty
     */
    public static int checkListIsEmpty(TaskList taskList, boolean isQuery) throws DukeException {
        int size = taskList.size();
        if (size <= 0) {
            throw new DukeException(isQuery ? MESSAGE_NO_SEARCH_RESULT : MESSAGE_TASKLIST_EMPTY);
        }
        return size;
    }
}
