package duke.storage;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents an encoder used to encode TaskList into its data representation.
 */
public class StorageEncoder {
    private static final String NEW_LINE = "\n";

    /**
     * Returns data representation of TaskList encoded using the tasks.
     * @param tasks TaskList used to be encoded to be saved in save file.
     * @return encoded representation for the save file.
     */
    public static String encodeTasks(ArrayList<Task> tasks) {
        String res = "";
        for (Task task : tasks) {
            res = res.concat(task.data() + NEW_LINE);
        }
        return res;
    }
}
