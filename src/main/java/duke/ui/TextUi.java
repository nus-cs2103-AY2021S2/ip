package duke.ui;

import java.util.ArrayList;

import duke.ParseException;
import duke.TaskList;
import duke.task.Task;

/**
 * Used by Momo to read or write to the user.
 */
public class TextUi {

    private static final StringBuffer boundOfChatBox = new StringBuffer();

    /**
     * Defines the appearance of chat box.
     */
    public static void setBoundOfChatBox() {
        int lenOfChatBox = 50;
        boundOfChatBox.append("-".repeat(lenOfChatBox));
    }

    /**
     * Formats string to stimulate a chat box of Momo.
     *
     * @param s contents in the chat box
     */
    public static String formatInChatBox(String s) {
        return boundOfChatBox + "\n" + s + "\n" + boundOfChatBox;
    }

    /**
     * Displays when the Momo inits and starts to run.
     * @return
     */
    public static String showInitUi() {
        setBoundOfChatBox();
        String logo = "    __       __        ___ \n"
                + "   /  \\     /  \\      / __ \\\n"
                + "  / /\\ \\  / /\\ \\    | |   | |\n"
                + " / /  \\ \\/ /  \\ \\   | |__| |\n"
                + "/_/    \\_/     \\_\\ \\____/";
        String greeting = "Hello! I'm Momo\nWhat can I do for you?";
        return formatInChatBox('\n' + greeting + '\n');
    }

    /**
     * Displays when Momo receives "bye" command and exits.
     */
    public static String showExitUi() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        return formatInChatBox(goodbye);
    }

    /**
     * Lists all the tasks when Momo receives "list" command.
     *
     * @param taskList the list of tasks.
     */
    public static String showList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int n = tasks.size();
        if (n == 0) {
            return formatInChatBox("There is no task\n");
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        String res = new String(buf);
        return formatInChatBox("Here are the tasks in your list:\n" + res);
    }

    /**
     * Informs the user the searching is successful.
     *
     * @param taskList the list of tasks.
     */
    public static String showMatchingResult(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int n = tasks.size();
        if (n == 0) {
            return formatInChatBox("There is no matching task\n");
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        String res = new String(buf);
        return formatInChatBox("Here are the matching tasks in your list:\n" + res);
    }

    /**
     * Informs the user the addition is successful.
     *
     * @param taskList the list of the tasks.
     * @param task the task to be added.
     */
    public static String showSuccessfulAdd(TaskList taskList, Task task) {
        return formatInChatBox("Got it. I've added this task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
    }

    /**
     * Informs the user the mark to make task done is successful.
     *
     * @param task the task that is done.
     */
    public static String showSuccessfulMark(Task task) {
        return formatInChatBox("Nice! I've marked this duke.task as done:\n" + task + "\n");
    }

    /**
     * Informs the user the deletion is successful.
     *
     * @param taskList the list of tasks.
     * @param task the task that is deleted.
     */
    public static String showSuccessfulDelete(TaskList taskList, Task task) {
        return formatInChatBox("Got it. I've removed this duke.task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
    }

    /**
     * Informs the user there is error when parsing the date of tasks.
     */
    public static String showDateParseError() {
        return formatInChatBox("OOPS!!! Please use '/by YYYY-MM-DD' after description.\n");
    }

    /**
     * Informs the user there is error that Momo cannot understand.
     */
    public static String showGeneralError() {
        try {
            throw new ParseException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (ParseException e) {
            return formatInChatBox(e.getMsgDes());
        }
    }

    /**
     * Informs the user that the loading is unsuccessful.
     */
    public static String showLoadingError() {
        return formatInChatBox("OOPS!!! Something wrong happens when loading.\n");
    }

    /**
     * Informs the user delete or mark done properly and tells the number of tasks in the taskList.
     *
     * @param taskList the list of task.
     */
    public static String showIndexOutOfBoundsError(TaskList taskList) {
        return formatInChatBox("OOPS! Now you have only "
                + taskList.getTasks().size()
                + " tasks, please mark/delete the added tasks.");
    }
}
