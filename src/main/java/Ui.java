import java.util.Scanner;

public class Ui {

    /**
     * Reads user input
     * @return user input in the form of a string
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            return sc.nextLine();
        }
        return "";
    }

    /**
     * Shows a horizontal line.
     */
    public void showLine() {
        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Shows error message from exception
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Show Duke's welcome text
     */
    public void showWelcome() {
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?:");
    }

    /**
     * Show number of items in the task list.
     * @param numOfItems
     */
    public void showNumberOfItems(int numOfItems) {
        System.out.println("Now you have " + numOfItems + " tasks in the list.");
    }

    /**
     * Shows the task that was added to the list.
     * @param task
     */
    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
    }

    /**
     * Show the task being marked as done.
     * @param task
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Show the list of tasks.
     */
    public void showListItems() {
        System.out.println("Here are the tasks on your list:");
    }

    /**
     * Shows the task deleted.
     * @param task task to be deleted
     */
    public void showTaskDeleted(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task.toString());
    }

    /**
     * Show good bye to user.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows the error message from exception.
     * @param e exception encountered.
     */
    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showFoundListItems() {
        System.out.println("Here are the matching tasks in your list:");
    }
}