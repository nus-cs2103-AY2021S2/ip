import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {
    protected static int numTasks;
    protected ArrayList<Task> tasks;

    /**
     * Creates a task list given existing ArrayList of tasks.
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        numTasks = tasks.size();
    }

    /**
     * Creates a new empty Task List.
     */
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

    /**
     * Finds matching results from a given keyword.
     *
     * @param keyword Keyword from user input.
     */
    public void findString(String keyword) {
        boolean hasMatch = false;
        ArrayList<Task> matches = new ArrayList<>();
        ArrayList<Integer> taskNumber = new ArrayList<>();
        int num = 1;

        for (Task t : tasks) {
            String temp = t.toString();

            if (temp.contains(keyword)) {
                hasMatch = true;
                matches.add(t);
                taskNumber.add(num);
            }
            num++;
        }

        if (hasMatch) {
            System.out.println("\nBingo Flamingo! I've found these matching results:");
            for (int j = 0; j < matches.size(); j++) {
                System.out.println(taskNumber.get(j) + ". " + matches.get(j));
            }
            System.out.println("");
        } else {
            System.out.println("\nOh no Flamingo! No matches were found.\n");
        }
    }
}
