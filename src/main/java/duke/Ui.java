package duke;

import java.util.Scanner;

public class Ui {
    private final String LINE = "";
    private final String HELP = "     These are the formats for Duke commands:\n"
            + "    - help\n"
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
     * Print greeting message.
     */
    public String printGreeting() {
        String greeting = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
        return greeting;
    }

    public String printHelp() {
        return HELP;
    }

    /**
     * Print bye message.
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
     * Print errors by user input.
     *
     * @param error error found.
     */
    public String printError(String error) {
        return LINE + error + LINE;
    }

    /**
     * Reads next line of user input.
     * Returns in string format.
     *
     * @return string format of user input.
     */
    public String readLine() {
        return sc.nextLine();
    }

    /**
     * Prints the list of the tasks.
     *
     * @param tl task list to be printed.
     */
    public String printTasks(TaskList tl) {
        if (tl.size() == 0) {
            String emptyListMsg = "     There are no tasks in your list!\n";
            return LINE + emptyListMsg + LINE;
        } else {
            int index = 1;
            String listMsg = "     These are the tasks in your list:\n";
            String msg = "";
            msg += LINE + listMsg;
            for (Task t : tl.getList()) {
                msg += String.format("     %d. %s\n",
                        index++, t.toString());
            }
            msg += LINE;
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
        return LINE + addMsg + taskMsg + listSizeMsg + LINE;
    }

    /**
     * Print task marked done message.
     *
     * @param t task marked done.
     */
    public String printMarkedDone(Task t) {
        String doneMsg = "     Nice! I've marked this task as done:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        return LINE + doneMsg + taskMsg + LINE;
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
        return LINE + deleteMsg + taskMsg + listSizeMsg + LINE;
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
            return LINE + noMatchMsg + LINE;
        } else {
            int index = 1;
            String findMsg = "     Here are the matching tasks in your list:\n";
            String msg = "";
            msg += LINE + findMsg;
            for (Task t : tl.getList()) {
                msg += String.format("     %d. %s\n",
                        index++, t.toString());
            }
            msg += LINE;
            return msg;
        }
    }
}
