import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void add(Task task) {
        this.tasks.add(task);
    }

    void remove(int index) {
        this.tasks.remove(index);
    }

    Task get(int index) {
        return this.tasks.get(index);
    }

    void set(int index, Task task) {
        this.tasks.set(index, task);
    }

    int size() {
        return this.tasks.size();
    }
}
