package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Represents the list where tasks are stored.
 */
public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in this list
     *
     * @return the number of tasks in this list
     */
    public int size() {
        return lst.size();
    }

    /**
     * Appends the task to the end of the list.
     *
     * @param task task to be appended to this list
     */
    public void add(Task task) {
        lst.add(task);
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param index index of the task to return
     * @return the task at the specified position in this list
     */
    public Task get(int index) {
        return lst.get(index);
    }

    /**
     * Returns the task at the specified position in this list. Shifts any subsequent tasks to the left.
     *
     * @param index the index of the task to be removed
     * @return the task that was removed from this list
     */
    public Task remove(int index) {
        return lst.remove(index);
    }

    /**
     * Performs the given action for each task in the list until all tasks have been processed. Actions are performed
     * in order of iteration.
     *
     * @param action the action to be performed for each task
     */
    public void forEach(Consumer<? super Task> action) {
        lst.forEach(action);
    }

    public List<Task> findTasksWithStr(String target) {
        return lst.stream().filter(task -> task.hasStrInProps(target)).collect(Collectors.toList());
    }
}
