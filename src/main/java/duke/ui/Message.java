package duke.ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeCorruptedStorageException;
import duke.exceptions.DukeCreateFileException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeNoDescriptionException;
import duke.exceptions.DukeSaveFileException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Message class used to create output strings for UI class to be shown as output of Duke.
 */
public class Message {
    private static final String INDENT = "\t";
    private static final String NEWLINE = System.lineSeparator();
    private static final String LOGO =
            " ____        _        " + NEWLINE
                    + "|  _ \\ _   _| | _____ " + NEWLINE
                    + "| | | | | | | |/ / _ \\" + NEWLINE
                    + "| |_| | |_| |   <  __/" + NEWLINE
                    + "|____/ \\__,_|_|\\_\\___|" + NEWLINE;
    public static final String LINE = INDENT
            + "__________________________________________________"
            + "______________" + NEWLINE;
    private static final String START_MSG =
            "Hello from" + NEWLINE + LOGO + LINE + INDENT + "Hello! I'm Duke" + NEWLINE + INDENT
                    + "What can I do for you?" + NEWLINE + LINE;
    private static final String BYE_MSG =
            INDENT + " Bye. Hope to see you again soon!" + NEWLINE;

    public static String getStartMsg() {
        return START_MSG;
    }

    public static String getByeMsg() {
        return BYE_MSG;
    }

    public static String getErrorMsg(DukeException e) {
        return INDENT + e;
    }

    public static String getErrorMsg(NumberFormatException e) {
        return INDENT + "Please enter an integer as argument. " + e.getMessage();
    }

    public static String getErrorMsg(IndexOutOfBoundsException e, TaskList taskList) {
        return String.format(INDENT + "Please enter an integer within your tasks size: %d" + NEWLINE,
                taskList.size());
    }

    public static String getErrorMsg(DukeNoDescriptionException e) {
        return String.format(INDENT + " %s", e);
    }

    public static String getErrorMsg(DateTimeParseException e) {
        return INDENT + "Date is not input correctly. Ensure input date is: YYYY-MM-DD.";
    }

    public static String getErrorMsg(DukeSaveFileException e) {
        return INDENT + e.toString();
    }

    public static String getErrorMsg(DukeCreateFileException e) {
        return INDENT + e.toString();
    }

    public static String getErrorMsg(DukeCorruptedStorageException e) {
        return INDENT + e.toString();
    }

    public static String getDoneMsg(Task task) {
        return String.format(INDENT + " Nice! I've marked this task as done:" + NEWLINE
                + INDENT + INDENT + " %s", task);
    }

    public static String getDeleteMsg(Task task, int numTasks) {
        return String.format(INDENT + " Noted. I've removed this task:" + NEWLINE + INDENT + INDENT
                        + task + NEWLINE + INDENT + " Now you have %d tasks in the list.",
                numTasks);
    }

    public static String getAddMsg(Task t, int taskSize) {
        return String.format(INDENT + " Got it. I've added this task:" + NEWLINE
                + INDENT + INDENT + " %s" + NEWLINE + INDENT + " Now you have %d tasks "
                + "in the list.", t, taskSize);
    }

    public static String getTaskListMsg(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return INDENT + "You have no tasks in the list.";
        }
        String msg = INDENT + "Here are the tasks in your list:";
        msg = getTaskListDetailMsg(taskList, msg);
        return msg;
    }

    public static String getFindMsg(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return INDENT + "You have no matching tasks in the list.";
        }
        String msg = INDENT + "Here are the matching tasks in your list:";
        msg = getTaskListDetailMsg(taskList, msg);
        return msg;
    }

    public static String getTaskListDetailMsg(ArrayList<Task> taskList, String msg) {
        int index = 1;
        for (Task task : taskList) {
            msg = msg.concat(String.format(NEWLINE + INDENT + "%d.%s", index, task));
            index++;
        }
        return msg;
    }
}
