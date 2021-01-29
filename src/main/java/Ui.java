import java.util.ArrayList;

/**
 * Represents all the interactions Duke will have with the user.
 */
public class Ui {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String line = "____________________________________________"+
            "________________\n";

    /**
     * Returns the line variable.
     *
     * @return line variable.
     */
    public static String lineGetter() {
        return line;
    }

    /**
     * Returns the introductory message the user will see when opening Duke.
     *
     * @return String introductory message.
     */
    public static String intro() {
        return "Hello from\n" + logo + "\n" + line + " Hey there! I'm Duke\n" +
                " How can I help you?\n" + line;
    }

    /**
     * Returns a goodbye message user will see when exiting Duke.
     *
     * @return String goodbye message.
     */
    public static String bye() {
        return line + " Bye, see you again!\n" + line;
    }

    /**
     * Returns a message informing the user to enter a task when only whitespaces are input.
     *
     * @return String message informing user to enter a task.
     */
    public static String onlySpaces() {
        return line + " Please enter a task\n" + line;
    }

    /**
     * Returns all the tasks in the order of input.
     *
     * @param tasks list of all tasks.
     * @return String of the tasks in the order of input.
     */
    public static String printList(ArrayList<Task> tasks) {
        String s = "";
        if(tasks.size() < 1) {
            throw new IllegalArgumentException(line + " No tasks so far!\n" + line);
        }
        s = s + line + " Here are the tasks in your list:\n";
        for (int j = 1; j <= tasks.size(); j++) {
            s = s + " " + j + ". " + tasks.get(j - 1) + "\n";
        }
        return s + line;
    }

    /**
     * Returns an error message when an illegal input is given by user.
     *
     * @return String error message when an illegal input is given by user.
     */
    public static String illegalArgExc() {
        return line + " Please enter 'todo (your task)', " +
                "or 'deadline (your task) / (deadline date time)',\n or " +
                "'event (event name) / (event date time)' to add tasks.\n " +
                "To see your tasks enter 'list'.\n To complete a task enter " +
                "'done (number of the task in the list)'.\n And to close Duke " +
                "enter 'bye'.\n"+ line;
    }

    /**
     * Returns an error message to give a valid date and time.
     *
     * @return String error message to give a valid date and time.
     */
    public static String dateTimeParseExc() {
        return line + " Enter date and time in this format yyyy-mm-dd hh:mm\n" + line;
    }

    /**
     * Returns an error message when unable to save tasks to hard drive.
     *
     * @return String error message when unable to save tasks to hard drive.
     */
    public static String unableSave() {
        return line + " Unable to save to hard drive\n" + line;
    }

    /**
     * Returns a message to signal to the user that his task has been added.
     *
     * @param task task that the user has input.
     * @param size size of the entire list of tasks.
     * @return String message to signal to the user that his task has been added.
     */
    public static String addTask(Task task, int size) {
        return line + " Got it. I've added this task:\n" + " " + task + "\n"
                + " Now you have " + size + " tasks in the list\n" + line;
    }

    /**
     * Returns a message to signal to the user that his task has been deleted.
     *
     * @param task task that the user has input.
     * @param size size of the entire list of tasks.
     * @return String message to signal to the user that his task has been deleted.
     */
    public static String deleteTask(Task task, int size) {
        return line + " I've removed the following task:\n" + " " + task
                + "\n" + " You now have "+ size + " tasks in the list\n" + line;
    }

    /**
     * Returns a message to signal to the user that his task has completed.
     *
     * @param task task that the user has input.
     * @return String message to signal to the user that his task has completed.
     */
    public static String doneTask(Task task) {
        return line + " Good job on completing this task!\n" + " " +
                task + "\n" + line;
    }
}
