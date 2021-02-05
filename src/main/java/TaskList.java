import java.util.ArrayList;
import java.util.HashSet;

/**
 * Keeps track of the tasks at hand.
 * Provide methods to add/remove/modify tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor to create a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to create a TaskList according to data from hard drive.
     * @param tasks ArrayList of tasks loaded in from hard drive.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter to retrieve the ArrayList of Tasks.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Add a task.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a Task.
     * @param taskNum Task number of the Task to be deleted.
     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    /**
     * Mark a task as done.
     * @param taskNum Task number of the Task to be marked as done.
     */
    public void markDone(int taskNum) {
        tasks.get(taskNum).markDone();
    }

    /**
     * Finds the tasks that contains the keyword provided.
     * @param keywords Keyword for searching a task.
     * @return An ArrayList of the Tasks that contains the specific keyword.
     */
    public ArrayList<Task> findTasks(String[] keywords) {
        ArrayList<Task> relatedTasks = new ArrayList<>();
        HashSet<Integer> toAdd = new HashSet<>();
        for (String keyword : keywords) {
            for (int i = 0; i < this.tasks.size(); i++) {
                Task current = this.tasks.get(i);
                String taskName = current.getName();
                if (taskName.contains(keyword)) {
                    toAdd.add(i);
                }
            }
        }
        for (int taskToAdd : toAdd) {
            relatedTasks.add(this.tasks.get(taskToAdd));
        }
        return relatedTasks;
    }
}
