import java.util.ArrayList;

/**
 * Represents all the interactions Duke will have with the user.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "_____________________\n";

    /**
     * Returns the line variable.
     *
     * @return line variable.
     */
    public static String lineGetter() {
        return LINE;
    }

    /**
     * Returns the introductory message the user will see when opening Duke.
     *
     * @return String introductory message.
     */
    public static String introduce() {
        return "Hello from\n" + LOGO + "\n" + LINE + " Hey there! I'm Duke\n"
                + " How can I help you?\n" + LINE;
    }

    /**
     * Returns a goodbye message user will see when exiting Duke.
     *
     * @return String goodbye message.
     */
    public static String exit() {
        return LINE + " Bye, see you again!\n" + LINE;
    }

    /**
     * Returns a message informing the user to enter a task when only whitespaces are input.
     *
     * @return String message informing user to enter a task.
     */
    public static String informOnlySpaces() {
        return LINE + " Please enter a task\n" + LINE;
    }

    /**
     * Returns all the tasks in the order of input.
     *
     * @param tasks list of all tasks.
     * @return String of the tasks in the order of input.
     */
    public static String printList(ArrayList<Task> tasks) {
        String list = "";
        if (tasks.size() < 1) {
            throw new IllegalArgumentException(LINE + " No tasks so far!\n" + LINE);
        }
        list = list + LINE + " Here are the tasks in your list:\n";
        for (int j = 1; j <= tasks.size(); j++) {
            list = list + " " + j + ". " + tasks.get(j - 1) + "\n";
        }
        return list + LINE;
    }

    /**
     * Returns an error message when an illegal input is given by user.
     *
     * @return String error message when an illegal input is given by user.
     */
    public static String informIllegalArgExc() {
        return LINE + " Please enter 'todo (your task)',\n "
                + "or 'deadline (your task) / (deadline date time)',\n or "
                + "'event (event name) / (event date time)' to add tasks.\n "
                + "To see your tasks enter 'list'.\n To complete a task enter "
                + "'done (number of the task in the list)'.\n And to close Duke "
                + "enter 'bye'.\n" + LINE;
    }

    /**
     * Returns an error message to give a valid date and time.
     *
     * @return String error message to give a valid date and time.
     */
    public static String informDateTimeParseExc() {
        return LINE + " Enter date and time in this format yyyy-mm-dd hh:mm\n" + LINE;
    }

    /**
     * Returns an error message when unable to save tasks to hard drive.
     *
     * @return String error message when unable to save tasks to hard drive.
     */
    public static String informUnableSave() {
        return LINE + " Unable to save to hard drive\n" + LINE;
    }

    /**
     * Returns a message to signal to the user that his task has been added.
     *
     * @param task task that the user has input.
     * @param size size of the entire list of tasks.
     * @return String message to signal to the user that his task has been added.
     */
    public static String addTask(Task task, int size) {
        return LINE + " Got it. I've added this task:\n" + " " + task + "\n"
                + " Now you have " + size + " tasks in the list\n" + LINE;
    }

    /**
     * Returns a message to signal to the user that his task has been deleted.
     *
     * @param task task that the user has input.
     * @param size size of the entire list of tasks.
     * @return String message to signal to the user that his task has been deleted.
     */
    public static String deleteTask(Task task, int size) {
        return LINE + " I've removed the following task:\n" + " " + task
                + "\n" + " You now have " + size + " tasks in the list\n" + LINE;
    }

    /**
     * Returns a message to signal to the user that his task has completed.
     *
     * @param task task that the user has input.
     * @return String message to signal to the user that his task has completed.
     */
    public static String doneTask(Task task) {
        return LINE + " Good job on completing this task!\n" + " "
                + task + "\n" + LINE;
    }
}
