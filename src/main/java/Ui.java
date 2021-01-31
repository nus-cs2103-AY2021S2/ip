import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a line
     */
    public void showLine() {
        System.out.println("------------------------------------------------");
    }

    /**
     * Prints the error message
     *
     * @param message the error message in String
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints to indicate that the file is unable to be loaded
     */
    public void showLoadingError() {
        System.out.println("Unable to load file!");
    }

    /**
     * Takes in user input
     *
     * @return the full user command in String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a welcome message
     */
    public void showWelcome() {
        System.out.println("Hi! I'm Timmy!\nWhat can Timmy note down for you today?");
        System.out.println("Please type in any of these format!");
        System.out.println("todo [title]");
        System.out.println("event [title] /at [yyyy-mm-dd] [HH:MM]");
        System.out.println("deadline [title] /by [yyyy-mm-dd] [HH:MM]");
        System.out.println("list");
        System.out.println("delete [index]");
        System.out.println("done [index]");
    }

    /**
     * Prints the exit message
     */
    public void showExit() {
        sc.close();

        System.out.println("Bye! Hope to see you again!");
    }

    /**
     * Prints the <code>Tasks</code> in a TaskList
     *
     * @param tasklist stores the <code>Task</code> in an ArrayList
     */
    void printList(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");

        ArrayList<Task> tasks = tasklist.getList();

        for (int j = 0; j < tasks.size(); j++) {
            System.out.println(j + 1 + "." + tasks.get(j).toString());
        }
    }

    /**
     * Prints a message to indicate that the task is marked
     *
     * @param task the <code>Task</code>> to be marked
     */
    void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Prints a message to indicate that the task is deleted
     *
     * @param tasklist  stores the <code>Task</code> in an ArrayList
     * @param taskIndex indicates the <code>Task</code> index in the ArrayList
     */
    void showDeleteTask(TaskList tasklist, int taskIndex) {
        System.out.println("Ok! I've removed this task:\n" + tasklist.getList().get(taskIndex).toString());
        System.out.println("Currently, you have " + (tasklist.getList().size() - 1) + " task(s) in the list!");
    }

    /**
     * Prints the added <code>Task</code> to the ArrayList
     *
     * @param tasklist stores the <code>Task</code> in an ArrayList
     */
    void showAddTask(TaskList tasklist) {
        System.out.println("Ok! I've added this task:\n" + tasklist.getList().get(tasklist.getList().size() - 1).toString());
        System.out.println("Currently, you have " + tasklist.getList().size() + " task(s) in the list!");
    }
}
