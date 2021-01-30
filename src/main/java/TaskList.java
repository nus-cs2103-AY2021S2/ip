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

    /**
     * Removes the task specified by the input index from the TaskList.
     * @param index Index of the file to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);

        return task;
    }

    /**
     * Sets the task specified by the input index from the TaskList as done.
     * @param index Index of the file to be set as done.
     * @return The done task.
     */
    public Task doneTask(int index) {
        Task task = tasks.get(index - 1);
        task.setDone();

        return task;
    }

    /**
     * Prints all the tasks in the TaskList.
     */
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

    /**
     * Prints all the Tasks with String representation that contains the query term.
     * @param query
     */
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
