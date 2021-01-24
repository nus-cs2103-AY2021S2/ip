import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Ui ui;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Add task to the list.
     * @param task Task entered by the user.
     */

    public void addTask(Task task) {
        tasks.add(task);
        ui.print("Got it. I've added this task:\n\t\t" + task +
                "\n\n\t  You have " +
                tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list");
    }

    /**
     * Remove task from the list.
     * @param taskIndex The index of the task to remove.
     */

    public void deleteTask(int taskIndex) {
        Task toRemove = tasks.get(taskIndex);
        toRemove.markIncomplete();
        tasks.remove(taskIndex);
        ui.print("I've removed this task:\n\t\t" + toRemove +
                "\n\n\t  You have " +
                tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in your list");
    }
}
