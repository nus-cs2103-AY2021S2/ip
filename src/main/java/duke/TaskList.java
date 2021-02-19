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
    public void deleteTask(int index) throws DukeException {
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
        try {
            for (int f = 0; f < this.getSize(); f++) {
                Task selectedTask = this.getSingleTask(f);
                if (selectedTask.toString().contains(match)) {
                    output.addTask(selectedTask);
                }
            }
        } catch (DukeException e) {
            return new TaskList();
        }

        return output;
    }

    /**
     * This method replaces the task at index with a new task.
     *
     * @param taskIndex This is the index of old task
     * @param newTask This is the new task
     */
    public void replaceTask(int taskIndex, Task newTask) {
        this.tasks.set(taskIndex, newTask);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getSingleTask(int index) throws DukeException {
        try {
            return this.tasks.get(index);
        } catch (Exception e) {
            // Catches invalid index
            throw new DukeException("Task not found! Invalid Index");
        }
    }

    public int getSize() {
        return this.tasks.size();
    }
}
