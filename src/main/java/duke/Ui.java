package duke;

import java.util.Scanner;

public class Ui {
    private final String LINE = "    ____________________________________________________________\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Print greeting message.
     */
    public void printGreeting() {
        String greeting = "     Hello! I'm Duke\n" + "     What can I do for you?\n";
        System.out.print(LINE + greeting + LINE);
    }

    /**
     * Print bye message.
     */
    public void printBye() {
        String byeMessage = "     Bye. Hope to see you again soon!\n";
        System.out.print(LINE + byeMessage + LINE);
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
    public void printError(String error) {
        System.out.print(LINE + error + LINE);
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
    public void printTasks(TaskList tl) {
        int index = 1;
        System.out.print(LINE);
        for (Task t : tl.getList()) {
            System.out.print(String.format("     %d. %s\n",
                    index++, t.toString()));
        }
        System.out.print(LINE);
    }

    private String taskListMessage(int numOfTasks) {
        return String.format("     Now you have %d task(s) in the list\n", numOfTasks);
    }

    /**
     * Print task added to task list message.
     *
     * @param t task added to task list.
     */
    public void printAddedTask(Task t) {
        String addMsg = "     Got it. I've added this task:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        System.out.print(LINE + addMsg + taskMsg + LINE);
    }

    /**
     * Print task marked done message.
     *
     * @param t task marked done.
     */
    public void printMarkedDone(Task t) {
        String doneMsg = "     Nice! I've marked this task as done:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        System.out.print(LINE + doneMsg + taskMsg + LINE);
    }

    /**
     * Print deleted task message.
     *
     * @param t task deleted.
     */
    public void printDeletedTask(Task t) {
        String deleteMsg = "     Noted. I've removed this task:\n";
        String taskMsg = "\t" + t.toString() + "\n";
        System.out.print(LINE + deleteMsg + taskMsg + LINE);
    }

    public void printFoundTasks(TaskList tl) {
        int index = 1;
        String findMsg = "     Here are the matching tasks in your list:\n";
        System.out.print(LINE);
        System.out.print(findMsg);
        for (Task t : tl.getList()) {
            System.out.print(String.format("     %d. %s\n",
                    index++, t.toString()));
        }
        System.out.print(LINE);
    }
}