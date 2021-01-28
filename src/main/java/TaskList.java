import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);

        return task;
    }

    public Task doneTask(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();

        return task;
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("It is empty boss.");
        } else {

            System.out.println("You have " + tasks.size() + " task(s) in the list:");

            int i = 1;
            for (Task task : tasks) {
                System.out.println(i + ". " + task);
                i++;
            }
        }
    }

    public void findTasks(String query) {
        if (tasks.isEmpty()) {
            System.out.println("Oops, the task list is empty boss, there's nothing to find!");
        } else {
            System.out.println("Here you go boss: ");

            int i = 1;
            for (Task task : tasks) {
                String string = task.toString();
                if (string.contains(query)) {
                    System.out.println(i + ". " + string);
                }
                i++;
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
