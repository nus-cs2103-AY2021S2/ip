package task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns the size of the tasklist.
     *
     * @return tasklist size
     */
    public int size() {
        return this.taskList.size();
    }
    /**
     * Gets the task of that particular index in the tasklist.
     *
     * @param i
     * @return the task at that index
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }
    /**
     * Removes the task of that particular index in the tasklist.
     *
     * @param i
     */
    public Task remove(int i) {
        return this.taskList.remove(i);
    }

}


