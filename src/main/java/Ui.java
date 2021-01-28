import java.util.Scanner;

public class Ui {
    private Scanner s = new Scanner(System.in);

    /**
     * Display a line
     */
    public void showLine() {
        System.out.println("----------------------------------------------");
    }

    /**
     * Display greeting messages
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.showLine();
        System.out.println("Hello! I`m Duke");
        System.out.println("How can i help you?");
        this.showLine();
    }

    /**
     * Reads input from the user
     * @return input entered by the user
     */
    private String readLine() {
        return s.nextLine();
    }

    /**
     * Reads a file path from the user
     * @return fileName entered by the user;
     */
    public String askFilePath() {
        System.out.println("Please enter file name : ");
        return this.readLine();
    }

    /**
     * Shows error message provided
     * @param error error message
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Reads a command from the user
     * @return String command , the command entered by the user
     */
    public String readCommand() {
        return this.readLine();
    }

    /**
     * Prints the representation of task with a numbering
     * @param numbering
     * @param task
     */
    public void printTask(String numbering, String task) {
        System.out.printf("%2s %s\n", numbering, task);
    }

    private void printExecuteResult(String message, String task , int numTasks, String numbering) {
        System.out.println(message);
        printTask(numbering,task);
        System.out.printf("Now you have %d task in the list\n", numTasks);
    }

    /**
     * Shows success messages upon marking a task done
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     */
    public void showSuccessMarkDone(String task, int numTasks) {
        String message = "Got it. I`ve mark this task as done:";
        this.printExecuteResult(message, task, numTasks, "");
    }

    /**
     * Shows success message upon deleting a task
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     */
    public void showSuccessDeleteTask(String task, int numTasks) {
        String message = "Noted. I`ve removed this task:";
        this.printExecuteResult(message, task, numTasks, "");
    }

    /**
     * Shows success message upon adding a task
     * @param task String representation of a task
     * @param numTasks number of task in the taskList
     */
    public void showSuccessAddTask(String task, int numTasks) {
        String message = "Got it. I`ve added this task:";
        this.printExecuteResult(message, task, numTasks, "");
    }

    public void showSuccessSearch() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void showFailSearch(String searchTerm) {
        System.out.println("There are no matching task with " + searchTerm);
    }
}
