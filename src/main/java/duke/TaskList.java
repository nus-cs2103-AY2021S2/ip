package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public void remove(Task t) {
        this.taskList.remove(t);
    }

    public List<Task> getList() {
        return this.taskList;
    }

}
