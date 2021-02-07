package duke.util;

import java.util.ArrayList;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;

public class MassOps {
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
