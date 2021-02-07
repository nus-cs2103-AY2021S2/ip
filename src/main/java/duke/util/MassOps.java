package duke.util;

import java.util.ArrayList;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Class which encapsulates behaviour related to mass operations of tasks in the task list.
 */
public class MassOps {
    /**
     * Identifies range of tasks corresponding to the user's input.
     *
     * @param args user input.
     * @return range of tasks within an integer array of size 2.
     * @throws DukeException if the first digit of the range provided is larger than the second or if the arguments
     *                       provided are not integers.
     */
    public static int[] identifyRange(String args) throws DukeException {
        String[] range = args.split("-");
        int[] taskRange = new int[2];
        if (range.length == 1) {
            try {
                int taskNum = Integer.parseInt(range[0]);
                taskRange[0] = taskNum;
                taskRange[1] = taskNum;
            } catch (Exception e) {
                throw new DukeException("That doesn't seem like a valid order number...");
            }
        } else if (range.length == 2) {
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            if (start > end) {
                throw new DukeException("Please select a valid range!");
            }
            taskRange[0] = start;
            taskRange[1] = end;
        } else {
            throw new DukeException("Please enter valid order numbers!");
        }
        return taskRange;
    }

    /**
     * Gets tasks in the storage which correspond to the range provided.
     *
     * @param range integer array where index 0 represents the start of the range and index 1 represents the end of
     *              the range.
     * @return ArrayList corresponding to the tasks in the task list matching the range provided.
     * @throws DukeException if the range provided is out of bounds.
     */
    public static ArrayList<Task> getTasksWithinRange(int[] range) throws DukeException {
        ArrayList<Task> tasks = Storage.getTasks();
        ArrayList<Task> tasksInRange = new ArrayList<>();
        try {
            for (int i = range[0] - 1; i <= range[1] - 1; i++) {
                tasksInRange.add(tasks.get(i));
            }
            return tasksInRange;
        } catch (Exception e) {
            throw new DukeException("Please select a valid range!");
        }
    }
}
