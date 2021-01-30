package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Task objects in a more abstract way.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a TaskList.
     *
     * @param tasks the tasks so far
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task get(int index) { return tasks.get(index); }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the Task at the given index as done.
     *
     * @param index the index of the finished Task.
     */
    public void markAsDone(int index) {
        tasks.set(index, tasks.get(index).markAsDone());
    }
}
