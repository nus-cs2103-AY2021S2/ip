package duke.processintructions;

import duke.tasks.Task;

import java.util.List;
import java.util.ArrayList;

public class Find {
    /**
     * Given a list of Tasks, return a list of Tasks that contains a specified string.
     */
    public List<Task> contains(String item, List<Task> list) {
        assert item != null;

        List<Task> newList = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(item)) {
                newList.add(task);
            }
        }
        return newList;
    }
}
