import java.util.ArrayList;

/**
 * Contains the task list which is an ArrayList of <code>Tasks</code>
 */
public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Removes the <code>Task</code> by specifying the index from the ArrayList of <code>Tasks</code>
     * @param taskIndex indicates the task index in the ArrayList <code>Tasks</code>
     * @throws InvalidDescriptionException is thrown when there is an error related to an invalid description
     */
    void deleteTask(int taskIndex) throws InvalidDescriptionException {
        if (taskIndex >= tasks.size()) {
            throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
        }

        tasks.remove(taskIndex);
    }

    /**
     * Marks the <code>Task</code> as done
     * @param taskIndex indicates the task index in the ArrayList of <code>Tasks</code>
     * @throws DukeException is thrown when there is an error related to duke
     */
    void markTask(int taskIndex) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
        }

        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Gets the ArrayList of <code>Tasks</code>
     * @return Returns the stored ArrayList
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Adds the task to the ArrayList
     * @param task represents the <code>Task</code> to be added
     */
    void addTask(Task task) {
        tasks.add(task);
    }
}
