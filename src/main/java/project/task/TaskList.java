package project.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        int totalBefore = tasks.size();
        tasks.add(task);
        assert totalBefore + 1 == tasks.size();
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
     * Returns a {@code TaskList} of the {@code Task}s that match the search.
     * Checks if any of the words in the search are present in the task descriptions.
     *
     * @param search The search expression.
     */
    public TaskList findTasks(String[] search) {
        ArrayList<Task> matches = tasks.stream()
                // Solution adapted from: https://stackoverflow.com/questions/8992100/
                // test-if-a-string-contains-any-of-the-strings-from-an-array
                .filter(task -> Arrays.stream(search).map(term -> term.trim().toLowerCase())
                        .filter(term -> term.length() > 0)
                        .anyMatch(task.getDescription().toLowerCase()::contains))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(matches);
    }

    /**
     * Returns a {@code TaskList} of the {@code Task}s that are upcoming in the next 7 days.
     * Outputs the result in chronological order.
     */
    public TaskList getUpcomingTasks() {
        ArrayList<Task> matches = tasks.stream()
                .filter(task -> task.getIfScheduled()
                        && task.getOccurrence().isBefore(LocalDateTime.now().plusDays(7)))
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(matches);
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
        int count = (int) tasks.stream()
                .filter(x -> x.getStatusIcon().equals(" "))
                .count(); // returns long
        assert count <= this.getTotalNumberOfTasks();
        return count;
    }

    /**
     * Returns {@code String} representation of this {@code TaskList}.
     * {@code Task}s are indexed by natural counting numbers from 1,2,3...
     */
    @Override
    public String toString() {
        if (this.hasTasks()) {
            // Adapted from: https://stackoverflow.com/questions/49080255/get-index-while-iterating-list-with-stream
            return IntStream.range(0, tasks.size())
                    .mapToObj(index -> String.format("  %s. %s\n", index + 1, tasks.get(index).toString()))
                    .reduce((a, b) -> a + b)
                    .get();
        }
        assert !this.hasTasks();
        return "";
    }
}
