package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of task.
 * Supports various operations for manipulating the task list.
 */
public class TaskList {

    private List<Task> tasks;

    /** Constructor to instantiate an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to instantiate a pre-populated task list.
     *
     * @param tasks list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Add {@code Task} to the task list.
     *
     * @param task {@code Task} to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Delete {@code Task} from the task list.
     *
     * @param index index of the task to be removed
     * @return the deleted task
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns {@code Task} at the given index.
     *
     * @param index index of the task to be retrieve
     * @return the task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list
     *
     * @return size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Clones the current task list.
     *
     * @return copy of the current task list
     */
    public TaskList clone() {
        return new TaskList(tasks);
    }

    /**
     * Deletes all the {@code Task} in the task list.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Sets all the tasks in the task list to done.
     */
    public void setAllDone() {
        for (Task task : tasks) {
            task.setDone();
        }
    }

    /**
     * Checks whether are all the tasks in the task lists done.
     *
     * @return boolean to indicate are all the tasks done
     */
    public boolean isAllDone() {
        for (Task task : tasks) {
            if (!task.getDone()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Filters the task list and returns the {@code Task} occurring on the specified date.
     *
     * @param date query date
     * @return task list containing {@code Task} that is occurring on the specified date
     */
    public TaskList filterByDate(String date) {
        List<Task> printTasks = new ArrayList<>(tasks);
        LocalDate queryDate = LocalDate.parse(date);
        printTasks.removeIf(t -> {
            if (t instanceof Deadline) {
                return !(((Deadline) t).getBy().isEqual(queryDate));
            } else if (t instanceof Event) {
                return !((Event) t).getAt().isEqual(queryDate);
            }
            return true;
        });
        return new TaskList(printTasks);
    }

    /**
     * Formats all the {@code Task}'s information into a formatted string that is suitable for storing.
     *
     * @return formatted string that is suitable for storing
     */
    public String toStorageString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(String.format("%s%n", t.toStorageString()));
        }
        return sb.toString();
    }
}
