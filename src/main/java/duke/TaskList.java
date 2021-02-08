package duke;

import java.util.ArrayList;

/**
 * The TaskList class stores an ArrayList of type Task and
 * has methods to add, delete and get the entire Task List
 * or a singular task.
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * This methods adds a Task into the TaskList.
     *
     * @param task This is a single task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * This method deletes a Task at a specified index
     * in the TaskList.
     *
     * @param index This is the index of the Task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * This method matches tasks to provided string and returns
     * a new TaskList with matched tasks.
     *
     * @param match This is the string to be matched
     * @return A new TaskList with matched tasks
     */
    public TaskList matchTasks(String match) {
        TaskList output = new TaskList();
        for (int f = 0; f < this.getSize(); f++) {
            Task selectedTask = this.getSingleTask(f);
            if (selectedTask.toString().contains(match)) {
                output.addTask(selectedTask);
            }
        }
        return output;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getSingleTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }
}
