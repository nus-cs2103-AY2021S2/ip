package duke.task;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public void add(Task t) {
        lst.add(t);
    }

    public Task get(int idx) {
        return lst.get(idx);
    }

    public Task remove(int idx) {
        return lst.remove(idx);
    }

    public void forEach(Consumer<? super Task> consumer) {
        lst.forEach(consumer);
    }

    public int size() {
        return this.lst.size();
    }
}
