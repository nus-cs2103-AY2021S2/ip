import java.util.ArrayList;
import java.util.Scanner;

/**
 * In charge of interactions with the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Initializes a Ui object and greets the user
     */
    public Ui() {
        greetUser();
    }

    /**
     * Greets the user with a message
     */
    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nHow can I help?\n");
    }

    /**
     * Prints out the message that the specified task has been deleted
     * @param task to be deleted
     */
    public void showTaskDeleted(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
    }

    /**
     * Prints out the message that the specified file does not exist
     */
    public void showLoadingError() {
        System.out.println("File ./savedTasks.txt not found! Creating one...\n");
    }

    /**
     * Prints out the list of tasks that match the given keyword
     */
    public void showMatchingTasks(ArrayList<String> matchedTasks) {
        for (int i = 0; i < matchedTasks.size(); i++) {
            System.out.println(matchedTasks.get(i));
        }
        System.out.println();
    }

    /**
     * Prints out the message that the specified task has been added to the task list
     */
    public void showTaskAdded() {
        System.out.println("added!");
    }

    /**
     * Reads the next command-line input supplied by the user
     * @return the command-line input supplied by the user
     */
    public String readUserCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out a farewell message
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you soon!\n");
    }

    /**
     * Prints out the message that the specified task has been marked as done
     * @param task that had been marked as done
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println();
    }

    /**
     * Prints out line-by-line all of the tasks in the task list
     */
    public void showTaskList() {
        System.out.println("Here are your tasks!");

        for (int i = 1; i <= TaskList.updatedTaskList.size(); i++) {
            Task task = TaskList.updatedTaskList.get(i - 1);
            System.out.print(i + ".");
            System.out.println(task);
        }
        System.out.println();
    }

    /**
     * Prints out the number of tasks in the task list
     */
    public void showNumberOfTasks() {
        int numTasks = TaskList.updatedTaskList.size();

        System.out.println("you have " + numTasks + " tasks in your list");
        System.out.println();
    }
}
