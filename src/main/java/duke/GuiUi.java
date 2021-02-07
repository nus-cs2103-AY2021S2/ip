package duke;

import duke.exceptions.DukeException;
import duke.exceptions.ParseException;
import duke.tasks.Task;

import java.util.ArrayList;

public class GuiUi {

    private static final StringBuffer boxBound = new StringBuffer();

    /**
     * Takes in a string and convert it into a chat box text.
     *
     * @param content content to put into chat box
     */
    public static String insertMsgIntoChatBox(String content) {
        return GuiUi.boxBound + "\n" + content + "\n" + GuiUi.boxBound;
    }

    /**
     * Informs the user there is error when parsing the date of tasks.
     *
     * @return String of error message
     */
    public static String displayParseError() {
        return insertMsgIntoChatBox("Input not accepted. " +
                "Hint: Use '/by OR /at YYYY-MM-DD' after description" +
                " and '/time HH:mm:ss' after date is specified.\n");
    }

    /**
     * Returns a String indicating a generic error is encountered.
     *
     * @return String of error message
     */
    public static String displayGenericError() {
        try {
            throw new ParseException("Tasker does not understand what you did there!\n");
        } catch (ParseException e) {
            return insertMsgIntoChatBox(e.getErrMsg());
        }
    }

    /**
     * Returns a String indicating Tasker is unable to save file.
     *
     * @return String of error message
     */
    public static String displayFailToSaveError() {
        try {
            throw new DukeException("Your save file or path is corrupted!\n");
        } catch (DukeException e) {
            return insertMsgIntoChatBox(e.getErrMsg());
        }
    }

    /**
     *  Returns a String to remind the user to use a proper number to delete/mark task
     *  and show the number of tasks in the taskList.
     *
     * @param taskList the list of task.
     * @return String of error message
     */
    public static String displayIndexOutOfBoundsError(TaskList taskList) {
        return insertMsgIntoChatBox("Dude. You have only "
                + taskList.getTasks().size()
                + " tasks, please delete/mark the added tasks.");
    }

    /**
     * Lists all the tasks on receive of "list" command.
     *
     * @param taskList list of tasks
     */
    public static String displayList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int n = tasks.size();
        if (n == 0) {
            return insertMsgIntoChatBox("There is no task.\n");
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buffer.append(i + 1).append(") ").append(tasks.get(i)).append("\n");
        }
        String output = new String(buffer);
        return insertMsgIntoChatBox("Here are your tasks:\n" + output);
    }

    /**
     * Displays when Tasker receives "bye" command and exits.
     */
    public static String displayExitMsg() {
        String goodbye = "Bye. Remember to do your tasks!\n" +
                "See you next time on Tasker!\n";
        return insertMsgIntoChatBox(goodbye);
    }

    /**
     * Returns a String which contains the matching results.
     *
     * @param taskList list of tasks
     */
    public static String showMatchingResult(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int numOfTasks = tasks.size();
        if (numOfTasks == 0) {
            return insertMsgIntoChatBox("There is no matching task :(\n");
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < numOfTasks; i++) {
            buffer.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        String output = new String(buffer);
        return insertMsgIntoChatBox("Here are the matching tasks in your list:\n" + output);
    }

    /**
     * Returns a 'successfully added' message.
     *
     * @param taskList list of tasks.
     * @param task task added
     */
    public static String displayAddSuccess(TaskList taskList, Task task) {
        return insertMsgIntoChatBox("Got it. I've added this task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
    }

    /**
     * Returns a 'successfully marked' message.
     *
     * @param task task done
     */
    public static String displayMarkDoneSuccess(Task task) {
        return insertMsgIntoChatBox("Nice! I've marked this Task as done:\n" + task + "\n");
    }

    /**
     * Returns a 'successfully deleted' message.
     *
     * @param taskList list of tasks.
     * @param task task deleted
     */
    public static String displayDeleteSuccess(TaskList taskList, Task task) {
        return insertMsgIntoChatBox("Got it. I've removed this task:" + '\n'
                + task + "\n" + "Now you have " +
                taskList.getTasks().size() + " tasks in the list.\n");
    }


}