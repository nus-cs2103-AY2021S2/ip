import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(String[] command) {
        String taskType = command[0];
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new ToDoTask(command[1]);
        } else if (taskType.equals("deadline")) {
            String[] temp = command[1].split("/by ");
            newTask = new DeadlineTask(temp[0], temp[1]);
        } else if (taskType.equals("event")) {
            String[] temp = command[1].split("/at ");
            newTask = new EventsTask(temp[0], temp[1]);
        } else {
            newTask = new NormalTask(String.join(" ", command));
        }

        tasks.add(newTask);
        System.out.println("    added: " + newTask.getName());
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
            int num = i + 1;
            System.out.println("     " +
                    num + ": " + tasks.get(i));
        }
    }

}
