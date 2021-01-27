import java.util.Scanner;

public class Ui {
    private final Scanner scanf;

    /**
     * Constructor for Ui class.
     *
     */

    public Ui() {
        scanf = new Scanner(System.in);
    }

    /**
     * Print a response from duke.
     * @param msg message needed.
     *
     */

    public void printResponse(String msg){
        String response = ">>Duke : " + msg;
        System.out.println(response);
    }

    /**
     * Read a line.
     *
     */

    public String readLine() {
        String input = scanf.nextLine();
        return input;
    }

    /**
     * Print every task in the tasklists.
     * @param tasks the tasklist to be printed.
     *
     */

    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1));
        }
    }

}

