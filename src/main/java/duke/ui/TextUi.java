package duke.ui;

import java.util.ArrayList;

import duke.ParseException;
import duke.TaskList;
import duke.task.Task;

/**
 * Used by Momo to read or write to the user.
 */
public class TextUi {

    /**
     * Displays when the Momo inits and starts to run.
     * @return the greeting.
     */
    public static String showInitUi() {
        String greeting = "Hello! I'm Rem\nWhat can I do for you?";
        return '\n' + greeting + '\n';
    }

    /**
     * Displays when Momo receives "bye" command and exits.
     */
    public static String showExitUi() {
        return "Bye. Hope to see you again soon!\n";
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
            return "There is no task\n";
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        String res = new String(buf);
        return "Here are the tasks in your list:\n" + res;
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
            return "There is no matching task\n";
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        String res = new String(buf);
        return "Here are the matching tasks in your list:\n" + res;
    }

    /**
     * Informs the user the addition is successful.
     *
     * @param taskList the list of the tasks.
     * @param task the task to be added.
     */
    public static String showSuccessfulAdd(TaskList taskList, Task task) {
        return "Got it. I've added this task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n";
    }

    /**
     * Informs the user the mark to make task done is successful.
     *
     * @param task the task that is done.
     */
    public static String showSuccessfulMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Informs the user the deletion is successful.
     *
     * @param taskList the list of tasks.
     * @param task the task that is deleted.
     */
    public static String showSuccessfulDelete(TaskList taskList, Task task) {
        return "Got it. I've removed this task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n";
    }

    /**
     * Informs the user there is error when parsing the date of tasks.
     */
    public static String showDateParseError() {
        return "OOPS!!! Please use '/by YYYY-MM-DD' after description.\n";
    }

    /**
     * Informs the user there is error that Momo cannot understand.
     */
    public static String showGeneralError() {
        try {
            throw new ParseException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (ParseException e) {
            return e.getMsgDes();
        }
    }

    /**
     * Informs the user that there is no file in the storage.
     */
    public static String showLoadingWithoutFile() {
        return "OOPS!!! There is no file in the storage. I will create one to you.\n";
    }

    /**
     * Informs the user that the loading is unsuccessful.
     */
    public static String showLoadingError() {
        return "OOPS!!! Something wrong happens when loading. I will create one to you.\n";
    }

    /**
     * Informs the user that the loading is successful.
     */
    public static String showLoadingSuccess() {
        return "Your tasks in the file are loaded successfully.\n";
    }

    /**
     * Informs the user delete or mark done properly and tells the number of tasks in the taskList.
     *
     * @param taskList the list of task.
     */
    public static String showIndexOutOfBoundsError(TaskList taskList) {
        return "OOPS! Now you have only "
                + taskList.getTasks().size()
                + " tasks, please mark/delete the added tasks.";
    }
}
