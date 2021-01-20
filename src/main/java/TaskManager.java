import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list;
    private boolean isDone;

    public TaskManager() {
        list = new ArrayList<>();
        isDone = false;
    }

    public ArrayList<Task> getTasks() {
        return this.list;
    }

    public void addTask(Task content) {
        list.add(content);
    }

    //note count starts from 1 not 0
    public void setTaskDone(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: Task number out of range.");
        } else {
            list.get(count-1).setDone();
        }
    }

    public Task deleteTask(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Error: Task number out of range.");
        } else {
            Task removedTask = list.remove(count-1);
            return removedTask;
        }
    }

    public void setExited() {
        this.isDone = true;
    }

    public boolean hasExited() {
        return this.isDone;
    }

}
