package duke;

import duke.task.Task;

import java.util.List;

/**
 * <code>Ui</code> class handles the interactions with the user.
 */
public class Ui {

    /**
     * Constructor for Ui class.
     */
    public Ui() {
    }

    /**
     * Prints the horizontal divider line to separate different commands and output.
     */
    public void printDivider() {
        System.out.println("    _________________________________________________");
    }

    /**
     * Prints a welcome message when the Duke application starts up.
     */
    public void welcome() {
        String logo = "   ____        _        \n"
                + "               |  _ \\ _   _| | _____\n"
                + "               | | | | | | | |/ / _ \\\n"
                + "               | |_| | |_| |   <  __/\n"
                + "               |____/ \\__,_|_|\\_\\___|\n";

        printDivider();
        System.out.println("     Hey! I'm" + logo
                + "\n     How may I help you?");
        printDivider();
    }

    /**
     * Prints a goodbye message when the user terminates the Duke application.
     */
    public void exit() {
        printDivider();
        System.out.println("     Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints an error message when no folder for the file can be found.
     */
    public void printLoadingError() {
        System.out.println("\n     No existing folder found! Created a new folder. :)");
    }

    /**
     * Prints the list of existing tasks when user input "list" command.
     * @param list List of existing tasks.
     */
    public void printTaskList(List<Task> list) {
        if (list.size() == 0) {
            printDivider();
            System.out.println("     There is currently no task in your list.");
        } else {
            printDivider();
            System.out.println("     Here are the tasks in your list:");
        }

        for (int i = 0; i < list.size(); i++) {
            int number = 1 + i;
            System.out.println("     " + number + ". " + list.get(i));
        }
        printDivider();
    }

    /**
     * Prints a validation message when user adds a new Task to the list.
     * @param newTask New Task added by the user.
     * @param list List of existing tasks.
     */
    public void printAddTask(Task newTask, List<Task> list) {
        printDivider();
        System.out.println("      Yes sir! I've added this task:\n"
                + "            " + newTask + "\n"
                + "      Now you have " + list.size() + " tasks in the list.");
        printDivider();
    }

    /**
     * Prints a validation message when user marks a Task as done.
     * @param taskNumber Number of the task that the user wants to mark as done.
     * @param list List of existing tasks.
     */
    public void printDoneTask(int taskNumber, List<Task> list) {
        printDivider();
        System.out.println("     Nice! I've marked this task as done:\n"
                + "        " + list.get(taskNumber));
        printDivider();
    }

    /**
     * Prints a validation message when user deletes a task from the list.
     * @param taskNumber Number of the task that the user wants to delete.
     * @param list List of existing tasks.
     */
    public void printDeleteTask(int taskNumber, List<Task> list) {
        printDivider();
        System.out.println("     Noted. I've removed this task:\n"
                + "        " + list.get(taskNumber)
                + "\n     Now you have " + (list.size() - 1)
                + " tasks in the list.");
        printDivider();
    }

    /**
     * Prints a validation message when user finds a task by searching for a keyword.
     * @param tempList List of tasks containing the keyword.
     */
    public void printFindKeyword(List<Task> tempList) {
        printDivider();
        System.out.println("     Here are the matching tasks in you list:");

        for (int i = 0; i < tempList.size(); i++) {
            int number = i + 1;
            System.out.println("     " + number + ". "
                    + tempList.get(i));
        }
        printDivider();
    }
}
