package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> leest;

    public TaskList() {
        this.leest = new ArrayList<>();
    }

    public void add(Task t) {
        this.leest.add(t);
    }

    public boolean isEmpty() {
        return this.leest.isEmpty();
    }

    public int size() {
        return this.leest.size();
    }

    public Task get(int i) {
        return this.leest.get(i);
    }

    public void remove(Task t) {
        this.leest.remove(t);
    }

    public List<Task> getList() {
        return this.leest;
    }

}
