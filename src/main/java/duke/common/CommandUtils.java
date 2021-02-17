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
     * Checks whether the index entered by the user is within the range of the size of task list.
     *
     * @param index index entered
     * @param size size of task list
     * @throws DukeException when the index is out of bounds.
     */
    public static int checkIndexOutOfBounds(int index, int size) throws DukeException {
        int posIndex = index - 1;
        if (posIndex < 0 || posIndex >= size) {
            throw new DukeException(MESSAGE_INDEX_OUT_OF_BOUND);
        }
        return posIndex;
    }

    /**
     * Checks whether the task list is empty.
     *
     * @param taskList task list to be checked
     * @param isQuery indicator used to differentiate whether is it a search command
     * @return size of the task list
     * @throws DukeException when the task list is empty
     */
    public static int checkListIsEmpty(TaskList taskList, boolean isQuery) throws DukeException {
        int size = taskList.size();
        if (size <= 0) {
            throw new DukeException(isQuery ? MESSAGE_NO_SEARCH_RESULT : MESSAGE_TASKLIST_EMPTY);
        }
        return size;
    }
}
