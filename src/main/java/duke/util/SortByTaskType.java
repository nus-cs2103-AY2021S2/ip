package duke.util;

import duke.tasks.Task;

import java.util.Comparator;

/**
 * Sorts tasks by task type
 */
public class SortByTaskType implements Comparator<Task> {
    public int compare(Task a, Task b) {
        if (a.getTaskType().equals("Todo")) {
            return 1;
        } else if (b.getTaskType().equals("Todo")) {
            return -1;
        } else if (a.getTaskType().equals("Deadline")) {
            return 1;
        } else if (b.getTaskType().equals("Deadline")) {
            return -1;
        } else if (a.getTaskType().equals("Event")) {
            return 1;
        } else if (b.getTaskType().equals("Event")) {
            return -1;
        } else {
            return 0;
        }
    }
}
