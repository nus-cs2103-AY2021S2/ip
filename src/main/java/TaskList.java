import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Keeps track of the tasks at hand.
 * Provide methods to add/remove/modify tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final ArrayList<Task> tasksOnSnooze;

    /**
     * Constructor to create a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.tasksOnSnooze = new ArrayList<>();
    }

    /**
     * Constructor to create a TaskList according to data from hard drive.
     * @param normalTasks ArrayList of tasks loaded in from hard drive.
     * @param snoozedTasks ArrayList of snoozed tasks loaded in from hard drive.
     */
    public TaskList(ArrayList<Task> normalTasks, ArrayList<Task> snoozedTasks) {
        this.tasks = normalTasks;
        this.tasksOnSnooze = snoozedTasks;
    }

    /**
     * Getter to retrieve the ArrayList of Tasks.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getNormalTasks() {
        return this.tasks;
    }

    /**
     * Getter to retrieve the ArrayList of Snoozed Tasks.
     * @return ArrayList of Snoozed Tasks.
     */
    public ArrayList<Task> getSnoozedTasks() {
        return this.tasksOnSnooze;
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
     * Snooze a task.
     * @param taskNum Task number of the Task to be snoozed.
     */
    public void snoozeTask(int taskNum) throws DukeException {
        Task ref = tasks.get(taskNum);
        if (ref.isOnSnooze()) {
            throw new DukeException("Task is already on snooze.");
        }
        ref.toggleSnooze();
        tasksOnSnooze.add(ref);
        tasks.remove(taskNum);
    }

    /**
     * UnSnooze a task.
     * @param taskNum Task number of the Task to be un-snoozed.
     */
    public void unSnoozeTask(int taskNum) throws DukeException {
        Task ref = tasksOnSnooze.get(taskNum);
        if (!ref.isOnSnooze()) {
            throw new DukeException("Task is already not on snooze.");
        }
        ref.toggleSnooze();
        tasks.add(ref);
        tasksOnSnooze.remove(taskNum);
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

        toAdd.forEach(taskToAdd -> relatedTasks.add(this.tasks.get(taskToAdd)));
        return relatedTasks;
    }
}
