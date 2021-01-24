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

    public void listTasks() { // Prints out the tasks in the list
        System.out.println("");

        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }

        System.out.println("");
    }

    public void addTask(Task task) { // Add a task to the list
        System.out.println("\nOkay, I've added this task:");
        tasks.add(numTasks, task);
        numTasks++;
        System.out.println(task.toString());
        System.out.println("You now have a total of " + numTasks + " tasks.\n");
    }

    public void markAsDone(int num) { // Marks a task as done
        System.out.println("\nOkay, this task has been marked as done:");
        Task currentTask = tasks.get(num - 1);
        currentTask.markAsDone();
        System.out.println(num + ". " + currentTask.toString() + "\n");
    }

    public void deleteTask(int num) { // Delete a task from the list
        System.out.println("\nOkay, I've deleted this task:");
        System.out.println(tasks.get(num - 1).toString());
        tasks.remove(num - 1);
        numTasks--;
        System.out.println("You now have a total of " + numTasks + " tasks.\n");
    }

}
