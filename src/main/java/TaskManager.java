import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(String taskName) {
        Task newTask = new Task(taskName);
        tasks.add(newTask);
        System.out.println("    added: " + taskName);
    }

    public void markTaskAsDone(int x) {
        Task t = tasks.get(x - 1);
        t.markAsDone();
        System.out.println("    Marked as Done: ");
        System.out.println("      " + t.toString());
    }

    public void listTasks() {
        System.out.println("    Listing all tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            Integer num = i + 1;
            System.out.println("     " +
                    (num).toString() +
                    ": " + tasks.get(i));
        }
    }

}
