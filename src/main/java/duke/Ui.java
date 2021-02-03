package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Ui is a class representing the user interface. The main function of the class is
 * to output result on the console.
 */
public class Ui {
    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     * @return input by the user in string.
     */
    String readCommand() {
        return sc.nextLine();
    }

    /**
     * Greets the user when the program is being initialized.
     *
     * @return A welcome message from Duke.
     */
    String showWelcome() {
        return "Hello! I'm Duke" + System.lineSeparator()
                + "What can I do for you?";
    }

    /**
     * Returns the error message.
     *
     * @param msg The message that caused the error.
     * @return The error message.
     */
    String showError(String msg) {
        return msg;
    }

    /**
     * Returns a goodbye message before the end of program.
     *
     * @return A goodbye message to the user.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a loading error when the file containing data cannot be loaded
     * from the hard disk.
     *
     * @return The loading error message.
     */
    void showLoadingError() {
        System.out.println("Unable to load the file. Empty list created.");
    }

    /**
     * Returns a message and the added task.
     *
     * @param task The added task.
     * @param size Current size of the list.
     * @return A string of message and added task.
     */
    public String showAdd(String task, int size) {
        return "Got it. I've added this task:" + System.lineSeparator()
                + showTaskAndSize(task, size);
    }

    /**
     * Returns when deleting a task from the list is successful.
     *
     * @param task The deleted task.
     * @param size Current size of the list.
     * @return A string of message and deleted task.
     */
    public String showDelete(String task, int size) {
        return "Noted, I've removed this task: " + System.lineSeparator()
                + showTaskAndSize(task, size);
    }

    /**
     * Outputs when marking a task as done is successful.
     *
     * @param task The task that is marked as done.
     * @param size Current size of the list.
     * @return A string of message and the task marked as done.
     */
    public String showDone(String task, int size) {
        return "Nice! I've mark this task as done" + System.lineSeparator()
                + showTaskAndSize(task, size);
    }

    private String showTaskAndSize(String task, int size) {
        return task + System.lineSeparator()
                + String.format("Now you have %d tasks in the list.", size);
    }

    /**
     * Lists out the current task(s) in the list. If the current list
     * is empty, outputs "There is currently no task in the list".
     *
     * @param list The list storing the tasks.
     */
    public String showCurrentList(List<Task> list) {
        if (list.isEmpty()) {
            return "There is currently no task in the list.";
        } else {
            return "Here are the tasks in your list:" + System.lineSeparator()
                    + showListItem(list);
        }
    }

    /**
     * Outputs the task(s) that matches the keyword.
     *
     * @param list The list storing the matching tasks.
     */
    public String showFindKeywordList(List<Task> list) {
        if (list.isEmpty()) {
            return "There is no task containing the keyword.";
        } else {
            return "Here are the matching tasks in your list:"
                    + System.lineSeparator() + showListItem(list);
        }
    }

    private String showListItem(List<Task> list) {
        String res = "";
        int index = 1;
        for (Task t: list) {
            res += String.format("%d. %s", index, t.toString())
                    + System.lineSeparator();
            index++;
        }

        return res;
    }
}
