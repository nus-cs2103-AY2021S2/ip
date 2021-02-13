package duke.storage;

import java.util.ArrayList;
import java.util.List;

import duke.task.TaskList;

public class TaskListEncoder {
    public static List<String> encodeTaskList(TaskList toSave) {
        final List<String> encodedTasks = new ArrayList<>();
        toSave.forEach(task -> encodedTasks.add(task.encode()));

        return encodedTasks;
    }
}
