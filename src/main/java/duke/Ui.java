package duke;

import java.util.Scanner;

public class Ui {
    private final String HELP = "     These are the formats for Duke commands:\n"
            + "    - help\n"
            + "    - bye\n"
            + "    - list\n"
            + "    - todo (taskName)\n"
            + "    - deadline (taskName) /by (YYYY-M-D TIME)\n"
            + "    - event (taskName) /at (YYYY-M-D TIME-TIME)\n"
            + "    - find (relevantName)\n"
            + "    - delete (taskNumber from list)\n"
            + "    - done (taskNumber from list)\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns greeting message to be printed.
     *
     * @return String format of greeting.
     */
    public String printGreeting() {
        String greeting = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
        return greeting;
    }

    /**
     * Returns help message to be printed.
     *
     * @return String message of help.
     */
    public String printHelp() {
        return HELP;
    }

    /**
     * Returns string of bye message.
     *
     * @return String format of bye message.
     */
    public String printBye() {
        String byeMessage = "     Bye. Hope to see you again soon!\n";
        return byeMessage;
    }

    /**
     * Print message to user.
     *
     * @param message message to user.
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns String format of error.
     *
     * @param error String format of error.
     * @return String format of error.
     */
    public String printError(String error) {
        return error;
    }


    public String printTasks(TaskList tl) {
        if (tl.size() == 0) {
            String emptyListMsg = "     There are no tasks in your list!\n";
            return emptyListMsg;
        } else {
            int index = 1;
            String listMsg = "     These are the tasks in your list:\n";
            String msg = "";
            msg += listMsg;
            for (Task t : tl.getList()) {
                msg += String.format("     %d. %s\n",
                        index++, t.toString());
            }
            return msg;
        }
    }

    private String taskListSizeMsg(int numOfTasks) {
        return String.format("     Now you have %d task(s) in the list\n", numOfTasks);
    }

    /**
     * Print task added to task list message.
     *
     * @param t task added to task list.
     */
    public String printAddedTask(Task t, TaskList tl) {
        String addMsg = "     Got it. I've added this task:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        String listSizeMsg = taskListSizeMsg(tl.size());
        return addMsg + taskMsg + listSizeMsg;
    }

    /**
     * Print task marked done message.
     *
     * @param t task marked done.
     */
    public String printMarkedDone(Task t) {
        String doneMsg = "     Nice! I've marked this task as done:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        return doneMsg + taskMsg;
    }

    /**
     * Print deleted task message.
     *
     * @param t task deleted.
     */
    public String printDeletedTask(Task t, TaskList tl) {
        String deleteMsg = "     Noted. I've removed this task:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        String listSizeMsg = taskListSizeMsg(tl.size());
        return deleteMsg + taskMsg + listSizeMsg;
    }

    /**
     * Print matched tasks as a list.
     * If list size is zero, print no matching task.
     *
     * @param tl list of task to be printed.
     */
    public String printFoundTasks(TaskList tl) {
        if (tl.size() == 0) {
            String noMatchMsg = "     There are no matching task in your list!\n";
            return noMatchMsg;
        } else {
            int index = 1;
            String findMsg = "     Here are the matching tasks in your list:\n";
            String msg = "";
            msg += findMsg;
            for (Task t : tl.getList()) {
                msg += String.format("     %d. %s\n",
                        index++, t.toString());
            }
            return msg;
        }
    }
}
