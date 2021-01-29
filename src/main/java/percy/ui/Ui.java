package percy.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import percy.task.Task;
import percy.task.TaskList;

public class Ui {
    private static Scanner in;
    private static PrintStream out;

    /**
     * Message templates used.
     */
    private static final String INDENT = "    ";
    private static final String DIVIDER = "    ____________________________________________________________\n";
    private static final String MESSAGE_LOGO =
            "  _____\n"
                    + "  |  __ \\\n"
                    + "  | |__) |__ _ __ ___ _   _\n"
                    + "  |  ___/ _ \\ '__/ __| | | |\n"
                    + "  | |  |  __/ | | (__| |_| |\n"
                    + "  |_|   \\___|_|  \\___|\\__, |\n"
                    + "                      __/ |\n"
                    + "                     |___/\n";
    private static final String MESSAGE_WELCOME = "    Hello! I'm Percy\n" + "    What can I do for you?";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    /**
     * Constructs an user interface.
     * @param in input stream.
     * @param out print stream.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Reads the command input by user.
     * @return the command given.
     */
    public static String readCommand() {
        String command = "";
        while (command.trim().isEmpty()) {
            command = in.nextLine();
        }
        return command;
    }

    /**
     * Prints the start up message.
     */
    public static void printStartUpMsg() {
        System.out.println(MESSAGE_LOGO + "\n" + DIVIDER
                + MESSAGE_WELCOME + "\n"
                + DIVIDER);
    }

    /**
     * Makes the startup message.
     * @return  formatted startup string.
     */
    public static String makeStartUpMsg() {
        return MESSAGE_LOGO + "\n" + DIVIDER
                + MESSAGE_WELCOME + "\n"
                + DIVIDER;
    }

    /**
     * Makes the goodbye message.
     * @return formatted goodbye string.
     */
    public static String makeByeMsg() {
        return DIVIDER + INDENT + MESSAGE_GOODBYE + "\n" + DIVIDER;
    }

    /**
     * Formats any string for output purposes.
     * @return formatted string.
     */
    public static String makeMsg(String s) {
        return DIVIDER + INDENT + s.toString() + "\n" + DIVIDER;
    }

    /**
     * Formats any array of string for multi-line output purposes.
     * @param stringArr array of strings.
     * @return formatted multi-line string.
     */
    public static String makeMsg(ArrayList<String> stringArr) {
        String str = DIVIDER;
        for (String s : stringArr) {
            str += INDENT + s.toString() + "\n";
        }
        str += DIVIDER;
        return str;
    }

    /**
     * Formats the output message for listing of tasks.
     * @param list latest task list.
     * @return the output message for list command
     */
    public static String makeListMsg(TaskList list) {
        String response;
        response = DIVIDER + INDENT + "Here are the tasks in your list:\n";
        int i = 1;
        for (Task t : list.getTaskList()) {
            response += INDENT
                    + String.valueOf(i++) + ". "
                    + t.toString() + "\n";
        }
        String taskString = (list.getTaskList().size() == 1) ? "task" : "tasks";
        response += INDENT + "Now you have " + list.getTaskList().size() + " "
                + taskString + " in the list.\n" + DIVIDER;
        return response;
    }


    /**
     * Make output message for added any task.
     * @param t task added.
     * @param list the updated list after adding task.
     * @return message for adding task
     */
    public static String makeAddMsg(Task t, TaskList list) {
        return DIVIDER + INDENT + "Got it. I've added this task:\n"
                + INDENT + INDENT + t.toString() + "\n"
                + INDENT + "Now you have " + list.getTaskList().size() + " tasks in the list.\n"
                + DIVIDER;
    }

    /**
     * Make output message for deleting task.
     * @param t task deleted.
     * @param list the updated list after deleting task.
     * @return message for deletion of task
     */
    public static String makeDeleteMsg(Task t, TaskList list) {
        return DIVIDER + INDENT + "Noted. I've removed this task:\n"
                + INDENT + INDENT + t.toString() + "\n"
                + INDENT + "Now you have " + list.getTaskList().size() + " tasks in the list.\n"
                + DIVIDER;
    }

    /**
     * Make message for marking a task as done.
     * @param t the task marked as done.
     * @return t the message for marking a task as done.
     */
    public static String makeDoneMsg(Task t) {
        return DIVIDER + INDENT + "Nice! I've marked this task as done:\n"
                + INDENT + INDENT + t.toString() + "\n"
                + DIVIDER;
    }

    /**
     * Make message for searching for task
     * @param filteredTasks the list with the search results
     * @return string to be printed
     */
    public static String makeSearchResultsMsg(TaskList filteredTasks) {
        ArrayList<Task> filteredTasksArr = filteredTasks.getTaskList();
        int taskCount = filteredTasks.getTaskList().size();
        if (taskCount == 0) {
            return DIVIDER + INDENT + "There are no matching tasks in your list.\n" + DIVIDER;
        } else {
            String s;
            s = DIVIDER + INDENT + "Here are the matching tasks in your list:\n";
            for (Task t : filteredTasksArr) {
                s += INDENT + (filteredTasksArr.indexOf(t) + 1) + "." + t.toString() + "\n";
            }
            s += DIVIDER;
            return s;
        }
    }
}
