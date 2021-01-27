package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public void addItem(Task task) {
        lst.add(task);
    }

    public void removeItem(int id) {
        lst.remove(id);
    }

    public void doneTask(int id) {
        lst.get(id).markAsDone();
    }

    public List<Task> getLst() {
        return lst;
    }

    public int size() {
        return lst.size();
    }
}
