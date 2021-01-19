import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list;
    private boolean isDone;

    public TaskManager() {
        list = new ArrayList<Task>();
        isDone = false;
    }

    public ArrayList<Task> getTasks() {
        return this.list;
    }
    public void addTask(String content) {
        list.add(new Task(content));
    }

    //note count starts from 1 not 0
    public void setTaskDone(int count) throws IllegalArgumentException {
        if (count > list.size() || count <= 0) {
            throw new IllegalArgumentException("Task number out of range.");
        } else {
            list.get(count-1).setDone();
        }
    }

    public void setExited() {
        this.isDone = true;
    }

    public boolean hasExited() {
        return this.isDone;
    }

}
