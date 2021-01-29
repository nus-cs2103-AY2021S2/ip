package seedu.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Represents a list of {@code Task}s in the application.
 * Encapsulates storage and operations on {@code Task}s.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates an instance of {@code TaskList} with empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates an instance of {@code TaskList} with existing tasks.
     *
     * @param savedData {@code ArrayList} of all the tasks stored in local file.
     */
    public TaskList(ArrayList<Task> savedData) {
        tasks = savedData;
    }

    /**
     * Checks if there are {@code Task}s in the application.
     */
    public boolean hasTasks() {
        return tasks.size() > 0;
    }

    /**
     * Retrieves a {@code Task} in the application by list index.
     *
     * @param idx List index of task as shown in {@code String} representation of {@code TaskList}.
     */
    public Task getTask(int idx) {
        return tasks.get(idx - 1);
    }

    /**
     * Adds a {@code Task} to the application.
     *
     * @param task any {@code Task} to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Changes status of a {@code Task} to "done".
     *
     * @param idx List index of task as shown in {@code String} representation of {@code TaskList}.
     */
    public void markTaskAsDone(int idx) {
        tasks.get(idx - 1).markAsDone();
    }

    /**
     * Deletes a {@code Task} in the application by list index.
     *
     * @param idx List index of task as shown in {@code String} representation of {@code TaskList}.
     */
    public Task deleteTask(int idx) {
        return tasks.remove(idx - 1);
    }

    /**
     * Returns total number of {@code Task}s in the application.
     */
    public int getTotalNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Returns total number of {@code Task}s that are not completed in the application.
     */
    public int getTotalNumberOfTasksUndone() {
        return tasks.stream()
                .mapToInt(Task::convertNotDoneStatusToOne)
                .reduce(0, Integer::sum);
    }

    /**
     * Returns {@code String} representation of this {@code TaskList}.
     * {@code Task}s are indexed by natural counting numbers from 1,2,3...
     */
    @Override
    public String toString() {
        if (this.hasTasks()) {
            // adapted from: https://stackoverflow.com/questions/49080255/get-index-while-iterating-list-with-stream
            return IntStream.range(0, tasks.size())
                    .mapToObj(index -> String.format("  %s. %s\n",
                            index + 1, tasks.get(index).toString()))
                    .reduce((a, b) -> a + b)
                    .get();
        }
        return "";
    }
}
