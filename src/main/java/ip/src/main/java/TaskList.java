package ip.src.main.java;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    protected List<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public boolean Empty() {
        return this.list.isEmpty();
    }

    public void printTasks() {
        int counter = 1;
        for (Task element : this.list) {
            System.out.println(String.valueOf(counter) + ". " + element);
            counter++;
        }
    }

    public Task getTask(int id) {
        return this.list.get(id);
    }

    public void remove(int id) {
        this.list.remove(id);
    }

    public int size(){
        return this.list.size();
    }
}
