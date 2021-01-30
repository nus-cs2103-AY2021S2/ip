package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Used by Momo to read or write to the user.
 */
public class Ui {

    private static final StringBuffer boundOfChatBox = new StringBuffer();

    /**
     * Defines the appearance of chat box.
     */
    public static void setBoundOfChatBox() {
        boundOfChatBox.append('\n');
        int lenOfChatBox = 50;
        boundOfChatBox.append("-".repeat(lenOfChatBox));
        boundOfChatBox.append('\n');
    }

    /**
     * Formats string to stimulate a chat box of Momo.
     *
     * @param s contents in the chat box
     */
    public static void formatInChatBox(String s) {
        System.out.println(boundOfChatBox + s + boundOfChatBox);
    }

    /**
     * Displays when the Momo inits and starts to run.
     */
    public static void showInitUi() {
        setBoundOfChatBox();
        String logo = "    __      __      ____ \n"
                + "   /  \\    /  \\    / __ \\\n"
                + "  / /\\ \\  / /\\ \\  | |  | |\n"
                + " / /  \\ \\/ /  \\ \\ | |__| |\n"
                + "/_/    \\__/    \\_\\ \\____/";
        String greeting = "Hello! I'm Momo\nWhat can I do for you?";
        formatInChatBox(logo);
        formatInChatBox(greeting + '\n');
    }

    /**
     * Displays when Momo receives "bye" command and exits.
     */
    public static void showExitUi() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        formatInChatBox(goodbye);
    }

    /**
     * Lists all the tasks when Momo receives "list" command.
     *
     * @param taskList the list of tasks.
     */
    public static void showList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int n = tasks.size();
        if (n == 0) {
            formatInChatBox("There is no task\n");
            return;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        String res = new String(buf);
        formatInChatBox("Here are the tasks in your list:\n" + res);
    }

    /**
     * Informs the user the searching is successful.
     *
     * @param taskList the list of tasks.
     */
    public static void showMatchingResult(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int n = tasks.size();
        if (n == 0) {
            formatInChatBox("There is no matching task\n");
            return;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        String res = new String(buf);
        formatInChatBox("Here are the matching tasks in your list:\n" + res);
    }

    /**
     * Informs the user the addition is successful.
     *
     * @param taskList the list of the tasks.
     * @param task the task to be added.
     */
    public static void showSuccessfulAdd(TaskList taskList, Task task) {
        formatInChatBox("Got it. I've added this task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
    }

    /**
     * Informs the user the mark to make task done is successful.
     *
     * @param task the task that is done.
     */
    public static void showSuccessfulMark(Task task) {
        formatInChatBox("Nice! I've marked this duke.task as done:\n" + task + "\n");
    }

    /**
     * Informs the user the deletion is successful.
     *
     * @param taskList the list of tasks.
     * @param task the task that is deleted.
     */
    public static void showSuccessfulDelete(TaskList taskList, Task task) {
        formatInChatBox("Got it. I've removed this duke.task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
    }

    /**
     * Informs the user there is error when parsing the date of tasks.
     */
    public static void showDateParseError() {
        formatInChatBox("OOPS!!! Please use '/by YYYY-MM-DD' after description.\n");
    }

    /**
     * Informs the user there is error that Momo cannot understand.
     */
    public static void showGeneralError() {
        try {
            throw new ParseException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (ParseException e) {
            formatInChatBox(e.getMsgDes());
        }
    }

    /**
     * Informs the user that the loading is unsuccessful.
     */
    public static void showLoadingError() {
        formatInChatBox("OOPS!!! Something wrong happens when loading.\n");
    }

    /**
     * Informs the user delete or mark done properly and tells the number of tasks in the taskList.
     *
     * @param taskList the list of task.
     */
    public static void showIndexOutOfBoundsError(TaskList taskList) {
        formatInChatBox("OOPS! Now you have only "
                + taskList.getTasks().size()
                + "tasks, please mark/delete the added tasks.");
    }
}
