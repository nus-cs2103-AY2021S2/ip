import java.util.List;
import java.util.ArrayList;

/**
 * Store all the tasks in a list DS
 */
public class Tasks {
    private List<Task> tasks;

    /**
     * Initialize the task list with an empty ArrayList
     */
    public Tasks() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Add the given task into the list
     * @param taskName The task to be added to the list
     */
    public void addTask(String taskName) {
        tasks.add(new Task(taskName));
    }

    /**
     * Get the given task done, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    public void getTaskDone(int index) {
        try {
            tasks.get(index - 1).setDone(true);
            System.out.println("Nice! I have marked this task as done: ");
            System.out.print("----");
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, the task number you specified is not valid. " +
                    "Try enter \'list\' to see the range of task numbers you can enter.");
        }
    }

    /**
     * Print out the list in a user-friendly format
     */
    public void printList() {
        System.out.println("These are the tasks in your list so far:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i, tasks.get(i - 1)));
        }
    }
}