package duke;

import java.util.ArrayList;
import duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the task of the given id.
     * @param id ID of the needed task.
     * @return Task to be retrieved.
     */
    public Task getTask(int id) {
        return tasks.get(id - 1);
    }

    /**
     * Gets a list of all tasks.
     * @return ArrayList of tasks to be retrieved.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the size of the task list.
     * @return Number of tasks in the list.
     */
    public int getSize() {
        if(tasks.isEmpty()) {
            return 0;
        } else {
            return tasks.size();
        }
    }

    /**
     * Adds a task into the task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removed a task of the given id.
     * @param id ID of the removed task.
     * @return Task to be removed.
     */
    public Task removeTask(int id) {
        return tasks.remove(id-1);
    }

    /**
     * Generates output message that displays all the tasks in the list.
     * @return Message output of a TaskList.
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        if(this.getSize()==0) {
            message.append("There is no task in the list.\n");
        } else {
            for (int i = 1; i <= this.getSize(); i++) {
                message.append(i + ". " + this.getTask(i - 1) + "\n");
            }
        }
        return message.toString();
    }


}
