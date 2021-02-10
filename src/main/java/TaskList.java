import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task getTaskAt(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a new TaskList containing tasks that matched the keyword.
     *
     * @param keyword String to search for in tasks.
     * @return TaskList containing tasks that matched the keyword.
     */
    public TaskList findMatchingTasks(String keyword) {
        TaskList matchingTasks = new TaskList();

        tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .forEach(matchingTasks::add);

        return matchingTasks;
    }
}
