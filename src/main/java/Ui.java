import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with user interactions.
 * Read data input from user.
 * Print messages to inform/update user according to Duke actions.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor for Ui class. Initialises a Scanner to read in user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the Duke Logo.
     */
    public void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Prints a partition.
     */
    public void printPartition() {
        System.out.println("\n/**********************************************************/\n");
    }

    /**
     * Prints the introduction.
     */
    public void printIntroduction() {
        printDukeLogo();
        System.out.println("Hello! I'm Duke.\n" + "What can I do for you?");
        printPartition();
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
        printDukeLogo();
    }

    /**
     * Prints the IO error.
     */
    public void printStorageIoError() {
        System.out.println("OOPS! An error occurred while attempting to create/retrieve storage file.");
        printPartition();
    }

    /**
     * Prints the IO error.
     * @param ie The IO exception.
     */
    public void printUpdateIoError(IOException ie) {
        System.out.println("OOPS! An error occurred while attempting to update storage file:\n"
                + ie.getMessage());
        printPartition();
    }

    /**
     * Prints the Duke Exception.
     * @param de The Duke Exception.
     */
    public void printDukeException(DukeException de) {
        System.out.println("OOPS! " + de.getMessage());
        printPartition();
    }

    /**
     * Prints the list of tasks.
     * @param tasks
     */
    public void printTasks(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println("" + i + ". " + taskList.get(i - 1).getStatus());
        }
        printPartition();
    }

    /**
     * Prints the list of tasks according to the input.
     * @param tasks The list of tasks to print.
     */
    public void printSomeTasks(ArrayList<Task> tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("" + i + ". " + tasks.get(i - 1).getStatus());
        }
        printPartition();
    }

    /**
     * Prints the number of tasks.
     * @param taskList The TaskList that is being kept tracked of.
     */
    public void printNumTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
        printPartition();
    }

    /**
     * Prints message when adding a task.
     */
    public void printAddTaskMessage(Task added, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + added.getStatus());
        printNumTasks(taskList);
    }

    /**
     * Prints the message when a task is marked as done.
     * @param marked
     */
    public void printMarkDone(Task marked) {
        System.out.println("This task is marked as done:\n" + marked.getStatus());
        printPartition();
    }

    /**
     * Prints the message when a task is deleted.
     * @param deleted
     * @param taskList
     */
    public void printDeleteTask(Task deleted, TaskList taskList) {
        System.out.println("This task has been removed:\n" + deleted.getStatus());
        printNumTasks(taskList);
    }

    /**
     * Read the next input line from user.
     * @return
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Close the Scanner that is opened when instantiating the Ui class.
     */
    public void closeScanner() {
        this.sc.close();
    }
}
