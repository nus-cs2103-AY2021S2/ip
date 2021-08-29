package duke;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the component of the Duke program
 * that contains the list of Tasks.
 */
public class TaskList implements Serializable {

    protected ArrayList<Task> tasks;

    /**
     * Class constructor.
     *
     * @param tasks the list of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of Tasks stored in this TaskList.
     *
     * @return the list of Tasks stored in this TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the Task in the specified int index of
     * the list of Tasks contained in this TaskList.
     *
     * @param index the int index of the Task to be returned.
     * @return the Task in the specified int index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a Task into the list of Tasks
     * contained in this TaskList.
     *
     * @param task the Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks a Task in the specified int index of
     * the list of Tasks contained in this TaskList
     * as done.
     *
     * @param index the int index of the Task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        this.getTask(index).markAsDone();
    }

    /**
     * Deletes a Task in the specified int index of
     * the list of Tasks contained in this TaskList.
     *
     * @param index the int index of the Task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns a TaskList containing Tasks that
     * have descriptions matching the String keyword.
     *
     * @param keyword the String keyword to be matched with Task descriptions.
     * @return a TaskList containing matching Tasks.
     */
    public TaskList findMatchingTasks(String keyword) {
        TaskList matchingTasks = new TaskList(new ArrayList<>());
        for (Task task : tasks) {
            String description = task.description;
            String[] words = description.split(" ");
            boolean isMatching = false;
            for (String word : words) {
                if (word.equals(keyword)) {
                    isMatching = true;
                    break;
                }
            }
            if (isMatching) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
