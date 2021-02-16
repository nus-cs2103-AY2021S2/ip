import java.util.Scanner;

/**
 * Ui is a class that handles the input/output of Duke.
 */
public class Ui {
    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line as a string in the scanner input.
     *
     * @return a string that is in the scanner input.
     */
    public String read() {
        return sc.nextLine();
    }

    /**
     * Checks if the next line is available. Usually used before read().
     *
     * @return whether the scanner contains the next line.
     */
    public boolean canRead() {
        return sc.hasNextLine();
    }

    /**
     * Prints the input string to the console.
     *
     * @param str The string to be printed to the console.
     */
    public void println(String str) {
        System.out.println(str);
    }

    /**
     * Returns the text when a task is marked as done.
     *
     * @return String that displays the message when a task is marked as done.
     */
    public static String showDoneText() {
        return "Here, I've marked this task as done: \n";
    }

    /**
     * Returns the text when a task is added to the list.
     *
     * @return String that displays the task just added to the list.
     */
    public static String showAddText() {
        return "Here, I've added this task to the list: \n";
    }

    /**
     * Returns the text when a list of tasks is requested.
     *
     * @return String that precedes the list of tasks.
     */
    public static String showListText() {
        return "Here are the tasks in your list: \n";
    }

    /**
     * Returns the text when a list of tasks that matches to keyword is requested.
     *
     * @return String that precedes the list of tasks that matches to keyword.
     */
    public static String showFindText() {
        return "Here are the tasks that matches the keyword: \n";
    }

    /**
     * Returns the app welcome message when the app is first opened.
     *
     * @return String that displays the Duke welcome message
     */
    public static String showWelcomeText() {
        return "Welcome! What can I do for you today?";
    }

    public static String showDeleteText() {
        return "Here, I've deleted this task from the list: \n";
    }

    public static String showGoodbyeText() {
        return "See you next time!";
    }
}
