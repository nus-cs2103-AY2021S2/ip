import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private boolean isDone;

    public TaskList() {
        list = new ArrayList<>();
        isDone = false;
    }

    public ArrayList<Task> getTasks() {
        return this.list;
    }

    public void addTask(Task content) {
        list.add(content);
    }

    public void addTask(String[] tasks) {
        String type = tasks[0].strip();
        String done = tasks[1].strip();
        String desc = tasks[2].strip();
        Task task;
        task = new ToDo(desc);
        if (type.equals("T")) {
            task = new ToDo(desc);
            this.addTask(task);
        } else if (type.equals("D")) {
            String byDate = tasks[3];
            task = new Deadline(desc,byDate);
            this.addTask(task);
        } else if (type.equals("E")) {
            String atDate = tasks[3];
            task = new Event(desc,atDate);
            this.addTask(task);
        } else {
            System.out.println("File has invalid entries");
        }
        if (done.equals("1")) {
            task.setDone();
        }

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
