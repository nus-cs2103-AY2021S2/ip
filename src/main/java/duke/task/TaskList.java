package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * A class represents a TaskList.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
    }

    /**
     * Constructs a TaskList.
     * @param tasks An ArrayList of tasks to be stored in this TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    /**
     * Adds the task into this TaskList.
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task according to the index from the TaskList.
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the size of the TaskList.
     * @return The size of the TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns a task according to the index in the TaskList.
     * @param index The index of the task.
     * @return The task.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    public void forEach(Consumer<? super Task> action) {
        taskList.forEach(action);
    }

    /**
     * Returns a sublist of TaskList that matches the keyword.
     * @param keyword Is the keyword specified by user.
     * @return A sublist of TaskList that matches the keyword.
     */
    public TaskList findMatchingTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        List<Task> matchingList = taskList.stream().filter(t -> t.matchKeyword(keyword)).collect(Collectors.toList());
        matchingTasks.addAll(matchingList);
        return new TaskList(matchingTasks);
    }
}
