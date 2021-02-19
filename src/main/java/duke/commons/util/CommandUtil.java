package duke.commons.util;

import static duke.commons.core.Messages.MESSAGE_INDEX_OUT_OF_BOUND;
import static duke.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.commons.core.Messages.MESSAGE_NO_SEARCH_RESULT;
import static duke.commons.core.Messages.MESSAGE_TASKLIST_EMPTY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import duke.commons.exceptions.DukeException;
import duke.model.task.TaskList;

/**
 * Contains common helper methods that is shared among the Command classes.
 */
public class CommandUtil {

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

    /**
     * Parse user's input into List of integer indexes.
     *
     * @param input user's input
     * @return list of integers
     * @throws DukeException when the user's input is in an invalid or incorrect format.
     */
    public static List<Integer> parseStringToNumbers(String input) throws DukeException {
        try {
            List<Integer> taskIndexes = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).sorted()
                    .boxed().collect(Collectors.toList());
            Collections.reverse(taskIndexes);
            return taskIndexes;
        } catch (NumberFormatException e) {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
