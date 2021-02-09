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
    private final String ADD_MSG = "     Got it. I've added this task:\n";
    private final String DELETE_MSG = "     Noted. I've removed this task:\n";
    private final String DONE_MSG = "     Nice! I've marked this task as done:\n";
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


    /**
     * Returns String format of tasks.
     *
     * @param tl Task list containing tasks.
     * @return String format of tasks.
     */
    public String printTasks(TaskList tl) {
        if (tl.size() == 0) {
            String emptyListMsg = "     There are no tasks in your list!\n";
            return emptyListMsg;
        } else {
            String listMsg = "     These are the tasks in your list:\n";
            String taskListMsg = taskListMsg(tl);
            return listMsg + taskListMsg;
        }
    }

    private String taskListSizeMsg(int numOfTasks) {
        return String.format("     You have %d task(s) in your list.\n", numOfTasks);
    }

    private String taskListMsg(TaskList tl) {
        int index = 1;
        String msg = "";
        for (Task t : tl.getList()) {
            msg += String.format("     %d. %s\n",
                    index++, t.toString());
        }
        return msg;
    }

    private String getCommandMsg(String command) throws DukeWrongCommandException {
        String cmdMsg;
        switch(command) {
        case "add":
            cmdMsg = ADD_MSG;
            break;
        case "delete":
            cmdMsg = DELETE_MSG;
            break;
        case "done":
            cmdMsg = DONE_MSG;
            break;
        default:
            throw new DukeWrongCommandException(command);
        }
        return cmdMsg;
    }

    /**
     * Returns string message for commands for task.
     *
     * @param t Given task.
     * @param tl Task list containing the task.
     * @param command Type of command for task.
     * @return String message for action on task.
     * @throws DukeException When command is unknown.
     */
    public String printTaskMsg(Task t, TaskList tl, String command) throws DukeException {
        String cmdMsg = getCommandMsg(command);
        String taskMsg = "\t" + t.toString() + "\n";
        String listSizeMsg = taskListSizeMsg(tl.size());
        return cmdMsg + taskMsg + listSizeMsg;
    }

    /**
     * Returns string format for found task.
     * If no tasks found, return corespond message.
     *
     * @param tl Task list containing task.
     * @return String message for task(s) found.
     */
    public String printFoundTasks(TaskList tl) {
        if (tl.size() == 0) {
            String noMatchMsg = "     There are no matching task in your list!\n";
            return noMatchMsg;
        } else {
            String findMsg = "     Here are the matching tasks in your list:\n";
            String taskListMsg = taskListMsg(tl);
            return findMsg + taskListMsg;
        }
    }
}
