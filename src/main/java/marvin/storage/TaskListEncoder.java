package marvin.storage;

import java.util.ArrayList;
import java.util.List;

import marvin.task.TaskList;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {
    /**
     * Encodes the {@code TaskList} into a decodable
     * string representation of the task list.
     * @param toSave The task list to encode.
     * @return The decodable string representation of the task list.
     */
    public static List<String> encodeTaskList(TaskList toSave) {
        final List<String> encodedTasks = new ArrayList<>();
        toSave.forEach(task -> encodedTasks.add(task.encode()));

        return encodedTasks;
    }
}
