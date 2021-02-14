package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import duke.exception.DukeException;

/**
 * Represents the list where tasks are stored.
 */
public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    private String getNotFoundErrorString(int index) {
        return String.format("Item no. %d cannot be found in list", index + 1);
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
        if (index < 0 || index >= lst.size()) {
            throw new DukeException(getNotFoundErrorString(index));
        }
        return lst.get(index);
    }

    /**
     * Returns the task at the specified position in this list. Shifts any subsequent tasks to the left.
     *
     * @param index the index of the task to be removed
     * @return the task that was removed from this list
     */
    public Task remove(int index) {
        if (index < 0 || index >= lst.size()) {
            throw new DukeException(getNotFoundErrorString(index));
        }
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

    /**
     * Finds tasks with the specified target string in it.
     *
     * @param target target string to find
     * @return list of Task objects that matches the target string
     */
    public List<Task> findTasksWithStr(String target) {
        return lst.stream().filter(task -> task.hasStrInProps(target)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        ArrayList<Task> tmp = new ArrayList<>(lst);

        if (tmp.isEmpty()) {
            return "No tasks found.\n";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tmp.size(); i++) {
            Task task = tmp.get(i);
            sb.append(String.format("%d. %s\n", i + 1, task.toString()));
        }

        return sb.toString();
    }
}
