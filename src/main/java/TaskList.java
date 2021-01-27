import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task taskToAdd) {
        this.list.add(taskToAdd);
    }

    public void removeTask(int i) {
        this.list.remove(i-1);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int i) {
        return this.list.get(i - 1);
    }

    public String joinToTxt() {
        String joined = "";
        for (Task t : this.list) {
            joined += System.lineSeparator() + t.saveTask();
        }
        return joined;
    }

}
