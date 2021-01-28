import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }

    public void markDone(int taskNum) {
        tasks.get(taskNum).markDone();
    }

    /**
     * Finds the tasks that contains the keyword provided.
     * @param keyword Keyword for searching a task.
     * @return An ArrayList of the Tasks that contains the specific keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> relatedTasks = new ArrayList<>();
        for (Task current : this.tasks) {
            String taskName = current.getName();
            if (taskName.contains(keyword)) {
                relatedTasks.add(current);
            }
        }
        return relatedTasks;
    }
}
