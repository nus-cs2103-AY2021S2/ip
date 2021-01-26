package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * Ui class to manage user input and Duke output
 */
public class Ui {
    private static final String space = "     ";
    private static final String divider = "    -------------------------------------------->>>>>\n";
    public Scanner sc;

    /**
     * Ui constructor
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public void printWelcomeText() {
        System.out.println(divider + space + "Konichiwa~ Watashi wa Duke desu! \n" + space
                + "What can I do for you today?\n" + divider);
    }

    /**
     * Prints exit message
     */
    public void printGoodbye() {
        System.out.println(divider + space + "Sayonara, matta ne~ \n" + divider);
    }

    /**
     * Prints list of tasks
     *
     * @param list List of tasks
     */
    public void printList(TaskList list) {
        String resultString = divider;

        if (list.size() == 0) {
            resultString = resultString + space + "Ara, the list is empty right now!\n";
        } else {
            resultString = resultString + space + "Hai~ Current tasks are: \n";
            for (int i = 0; i < list.size(); i++) {
                resultString = resultString + space + (i + 1) + ". " + list.getTask(i).toString() + "\n";
            }
        }

        resultString = resultString + divider;
        System.out.println(resultString);
    }

    /**
     * Prints given text in output format
     *
     * @param text Given text
     */
    public void printText(String text) {
        System.out.println(divider + space + text + "\n" + divider);
    }

    /**
     * Prints message when a task is marked as done
     *
     * @param task Given task that is marked as done
     */
    public void printCompletedMsg(Task task) {
        System.out.println(divider + space + "Otsukare! I've marked this task as done: \n" + space + "  "
                + task.toString() + "\n" + divider);
    }

    /**
     * Prints message when a task is deleted from list
     *
     * @param task Task to be deleted
     * @param size Number of tasks left in list
     */
    public void printDeleteMsg(Task task, int size) {
        System.out.println(divider + space + "Yayyyy! I've removed the task: \n" + space + "  " + task.toString() + "\n"
                + space + "Now you have " + size + " task(s) left!\n" + divider);
    }

    /**
     * Prints message when a task is added into list
     *
     * @param task Task that is added
     * @param size Number of tasks now on list
     */
    public void printTask(Task task, int size) {
        System.out.println(divider + space + "Ayyyyy I've added the task: \n" + space + "  " + task.toString() + "\n"
                + space + "Now you have " + size + " task(s) in the list.\n" + divider);
    }

    /**
     * Gets next line of user input
     *
     * @return User input
     */
    public String getNextCommand() {
        return sc.nextLine();
    }

    /**
     * Checks if there is user input
     *
     * @return True if user typed a command
     */
    public boolean hasNextCommand() {
        return sc.hasNext();
    }

    /**
     * Prints error if saved .txt file is unable to load
     */
    public void showLoadingError() {
        System.out.println("Ehhh-- There is something wrong with the file!");
    }

}
