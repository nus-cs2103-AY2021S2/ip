package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private int numOfTasks = 0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int num) {
        return this.tasks.get(num - 1);
    }

    public int getNumOfTasks() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void add(Task task) {
        tasks.add(task);
        this.numOfTasks++;
    }

    public void delete(int num) {
        tasks.remove(num - 1);
        this.numOfTasks--;
    }

    public void markAsDone(int num) {
        tasks.get(num - 1).markAsDone();
    }


}
