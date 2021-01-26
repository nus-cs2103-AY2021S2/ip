import java.util.ArrayList;

/**
 * Contains the task list, and methods to add and delete tasks from the list
 *
 * @author Amanda Ang
 */
public class TaskList {
    ArrayList<Task> list;

    /**
     * Construct a TaskList object for new users
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Construct a TaskList object for old users
     *
     * @param list pre-existing list of tasks from previous usage of program
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Add a task to the list
     *
     * @param task the task to be added to the list
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Delete a task from the list
     *
     * @param taskIndex the task number of the task to be removed from the list
     */
    public void removeTask(int taskIndex) {
        list.remove(taskIndex);
    }

}
