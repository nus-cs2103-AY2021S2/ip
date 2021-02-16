package duke;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * This function adds a task in the task list
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * This function returns the task at a specified index
     * @param index the index of the task to be returned
     * @return Task object in the given position
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * This function deletes the task at a specified index
     * @param index the index of the task to be deleted
     * @return return the deleted task
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * This function sets a task in the TaskList to be done
     * @param index index of the task to be set done
     * @return return the task after setting it to be done
     */
    public Task setDone(int index) {
        tasks.get(index).setDone();
        return tasks.get(index);
    }

    /**
     * This function returns the number of tasks in the list
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }
}
