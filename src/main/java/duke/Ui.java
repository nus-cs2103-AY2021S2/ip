package duke;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {

        this.sc = new Scanner(System.in);
    }

    /**
     * This function prints the greeting message to the console
     */
    public void greet() {

        System.out.println("Hello! What can I do for you:>");
    }

    /**
     * This function prints the goodbye message to the console
     */
    public void end() {
        this.sc.close();
        System.out.println("Bye. See you again!");
    }

    /**
     * This function prints the task which had just been set to be done
     * @param task task to be printed
     */
    public void printSetDone(Task task) {
        System.out.println("Nice, I have set this task as done!");
        printTask(task);
    }

    /**
     * This function prints the task which had just been deleted
     * @param task task to be printed
     */
    public void printDeleteTask(Task task) {
        System.out.println("Noted. I have deleted this task:");
        printTask(task);
    }

    /**
     * This function prints the task which had just been added to the TaskList
     * @param task task to be printed
     */
    public void printAddTask(Task task) {
        System.out.println("Got it. I have added this task:");
        printTask(task);
    }

    /**
     * This function prints a given task
     * @param task task to be printed
     */
    public void printTask(Task task) {

        System.out.println(task.toString());
    }

    /**
     * This function prints out the tasks in the TaskList
     * @param tasks TaskList to be printed
     */
    public void printList(TaskList tasks) {
        int size = tasks.getSize();

        for (int i = 0; i < size; i++) {
            printTask(tasks.getTask(i));
        }
    }

    /**
     * This function prints the message if the TaskList is empty
     */
    public void printEmptyList() {

        System.out.println("You have no tasks in the list!");
    }

    /**
     * This function gets one line of input from the user
     * @return a string which is the user input
     */
    public String getInput() {
        while (sc.hasNextLine()) {
            return sc.nextLine();
        }

        return "null";
    }

    /**
     * This function prints the error message when there is a reading error
     */
    public void showLoadingError() {

        System.out.println("An error has occurred while reading duke.txt");
    }

    /**
     * This function prints the error message when there is an error writing the final taskList to the file
     */
    public void showWritingError() {

        System.out.println("Error occurred while writing to file.");
    }

    /**
     * This function prints the error message when the user has entered an invalid command
     */
    public void showInvalidCommandError() {
        System.out.println("You have entered invalid commands, please try again!");
    }

    /**
     * This function prints the error message
     * @param e which is the exception to be printed
     */
    public void showError(Exception e) {

        System.out.println(e.getMessage());
    }

}
