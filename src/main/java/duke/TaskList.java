package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Represents a collection of Task instances.
 *
 * @author Aaron Saw Min Sern
 */
public class TaskList {
    private final List<Task> tasks;
    private final Statistics stats;

    /**
     * Constructor for class TaskList supplied with some Task instances.
     *
     * @param tasks a list of Tasks instances
     * @param stats a Statistics instance
     */
    public TaskList(List<Task> tasks, Statistics stats) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
        this.stats = stats;
    }

    /**
     * Constructor for class TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.stats = new Statistics();
    }

    /**
     * Returns the encoded format of the list of tasks in this TaskList object.
     *
     * @return the encoded format of the list of tasks
     */
    public List<String> encode() {
        List<String> encoding = tasks.stream()
            .map(task -> task.encode())
            .filter(str -> str != null)
            .collect(Collectors.toList());
        encoding.add("%");
        encoding.addAll(stats.encode());

        return encoding;

    }

    /**
     * Returns the number of tasks present in this TaskList instance.
     *
     * @return the number of tasks present in this TaskList instance
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks the status of a Task instance at a particular index as finished.
     *
     * @param index the index of the Task instance in the list
     * @see duke.task.Task#markAsDone()
     */
    public void markAsDone(int index) {
        boolean wasNotAlreadyDone = tasks.get(index).markAsDone();
        if (wasNotAlreadyDone) {
            stats.addCompletionDatetime();
        }
    }

    /**
     * Returns the total number of completed tasks in the past week.
     */
    public int getCompletedTasksCountLastWeek() {
        return stats.getCompletedTasksCountLastWeek();
    }

    /**
     * Returns the task description of a Task instance at a particular index.
     *
     * @param index the index of the Task instance in the list
     */
    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Returns the task description of all Task instances in this TaskList instance.
     */
    public String list() {
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        for (final Task t : tasks) {
            sb.append(String.format("\t%d. %s\n", ++i, t));
        }
        return sb.toString();
    }

    /**
     * Returns a list of string representation of Tasks instances that contains the
     * keyword ignoring case.
     *
     * @param keyword the keyword to be tested with
     * @return a list of tasks that matches the keyword
     */
    public List<String> find(String keyword) {
        return tasks.stream().filter(task -> task.isMatching(keyword)).map(task -> task.toString())
                .collect(Collectors.toList());
    }

    /**
     * Removes a Task instance at a particular index.
     *
     * @param index the index of the Task instance in the list
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Appends a Task instance to the end of this TaskList instance.
     *
     * @param task the Task instance to be added to the collection
     */
    public boolean add(Task task) {
        return tasks.add(task);
    }
}
