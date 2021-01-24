import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected static int numTasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        numTasks = tasks.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        numTasks = 0;
    }

    /**
     * Prints out the tasks in the ArrayList.
     */
    public void listTasks() {
        System.out.println("");

        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }

        System.out.println("");
    }

    /**
     * Adds a tasks to the ArrayList.
     *
     * @param task Task from user input.
     */
    public void addTask(Task task) {
        System.out.println("\nOkay, I've added this task:");
        tasks.add(numTasks, task);
        numTasks++;
        System.out.println(task.toString());
        System.out.println("You now have a total of " + numTasks + " tasks.\n");
    }

    /**
     * Marks a task as done.
     *
     * @param num Number representing task in the list.
     */
    public void markAsDone(int num) {
        System.out.println("\nOkay, this task has been marked as done:");
        Task currentTask = tasks.get(num - 1);
        currentTask.markAsDone();
        System.out.println(num + ". " + currentTask.toString() + "\n");
    }

    /**
     * Deletes a task from the list.
     *
     * @param num Number representing task in the list.
     */
    public void deleteTask(int num) {
        System.out.println("\nOkay, I've deleted this task:");
        System.out.println(tasks.get(num - 1).toString());
        tasks.remove(num - 1);
        numTasks--;
        System.out.println("You now have a total of " + numTasks + " tasks.\n");
    }
}
