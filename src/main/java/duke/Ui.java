package duke;

import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Ui class to manage user input and Duke output
 */
public class Ui {
    private static final String SPACE = "     ";
    private static final String DIVIDER = "    -------------------------------------------->>>>>\n";
    private final Scanner sc;

    /**
     * Ui constructor
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public String printWelcomeText() {
        return DIVIDER + SPACE + "Konichiwa~ Watashi wa Duke desu! \n" + SPACE
                + "What can I do for you today?\n" + DIVIDER;
    }

    /**
     * Prints exit message
     */
    public String printGoodbye() {
        return DIVIDER + SPACE + "Sayonara, matta ne~ \n" + DIVIDER;
    }

    /**
     * Prints list of tasks
     *
     * @param list List of tasks
     */
    public String printList(TaskList list) {
        String resultString = DIVIDER;

        if (list.size() == 0) {
            resultString = resultString + SPACE + "Ara, the list is empty right now!\n";
        } else {
            resultString = resultString + SPACE + "Hai~ Current tasks are: \n";
            for (int i = 0; i < list.size(); i++) {
                resultString = resultString + SPACE + (i + 1) + ". " + list.getTask(i).toString() + "\n";
            }
        }

        resultString = resultString + DIVIDER;
        return resultString;
    }

    /**
     * Prints given text in output format
     *
     * @param text Given text
     */
    public String printText(String text) {
        return DIVIDER + SPACE + text + "\n" + DIVIDER;
    }

    /**
     * Prints message when a task is marked as done
     *
     * @param task Given task that is marked as done
     */
    public String printCompletedMsg(Task task) {
        return DIVIDER + SPACE + "Otsukare! I've marked this task as done: \n" + SPACE + "  "
                + task.toString() + "\n" + DIVIDER;
    }

    /**
     * Prints message when a task is deleted from list
     *
     * @param task Task to be deleted
     * @param size Number of tasks left in list
     */
    public String printDeleteMsg(Task task, int size) {
        return DIVIDER + SPACE + "Yayyyy! I've removed the task: \n" + SPACE + "  " + task.toString() + "\n"
                + SPACE + "Now you have " + size + " task(s) left!\n" + DIVIDER;
    }

    /**
     * Prints message when a task is added into list
     *
     * @param task Task that is added
     * @param size Number of tasks now on list
     */
    public String printTask(Task task, int size) {
        return DIVIDER + SPACE + "Ayyyyy I've added the task: \n" + SPACE + "  " + task.toString() + "\n"
                + SPACE + "Now you have " + size + " task(s) in the list.\n" + DIVIDER;
    }

    /**
     * Prints tasks in given search result list
     *
     * @param taskList Given list of tasks
     */
    public String printSearchResults(TaskList taskList) {
        if (taskList.size() == 0) {
            return DIVIDER + SPACE + "There are no matching results D:\n" + DIVIDER;
        } else {
            String resultString = DIVIDER;
            resultString = resultString + SPACE + "Hai~ The search results are are: \n";
            for (int i = 0; i < taskList.size(); i++) {
                resultString = resultString + SPACE + (i + 1) + ". " + taskList.getTask(i).toString() + "\n";
            }
            resultString = resultString + DIVIDER;
            return resultString;
        }

    }

    public String printUnknowInputError() {
        return DIVIDER + SPACE + "Sorry! I do not understand what you just said. Try again pwease?"
                + DIVIDER;
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
