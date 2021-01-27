package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected final List<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public void addItem(Task task) {
        this.lst.add(task);
    }

    public void removeItem(int id) {
        this.lst.remove(id);
    }

    public void doneTask(int id) {
        this.lst.get(id).markAsDone();
    }

    public List<Task> tasksContainingKeyword(String keyword) {
        List<Task> tasksWithKeyword = new ArrayList<>();
        for (Task task : lst) {
            if (task.contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        return  tasksWithKeyword;
    }

    public List<Task> getLst() {
        return lst;
    }
}
