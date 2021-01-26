public class Ui {
    private static final String INDENTATION = "    ";
    private static final String REPLY_OUTLINE = INDENTATION + "____________________________________________________________";

    public Ui() {}

    /**
     * Formats given string with indentation and line break
     *
     * @param line Line to format.
     * @return Formatted line.
     */
    public String formatLine(String line) {
        return INDENTATION + line + "\n";
    }

    /**
     * Styles and prints given string.
     *
     * @param msg Message to print.
     */
    public void reply(String msg) {
        System.out.println(REPLY_OUTLINE + "\n" + msg + REPLY_OUTLINE + "\n");
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String msg = formatLine("Hello! I'm Duke")
                + formatLine("What can I do for you?");
        reply(msg);
    }

    /**
     * Prints a list of all tasks.
     *
     * @param tasks List of tasks.
     */
    public void list(TaskList tasks) {
        StringBuilder msg = new StringBuilder(formatLine("Here are the tasks in your list:"));

        for (int i = 0; i < tasks.size(); i++) {
            msg.append(formatLine((i + 1) + ". " + tasks.getTaskAt(i)));
        }
        reply(msg.toString());
    }

    public void exit() {
        reply(formatLine("Bye. Hope to see you again soon!"));
    }

    /**
     * Prints a reply, having added a task.
     *
     * @param task Task that was added.
     * @param numTasks Total number of tasks in task list.
     */
    public void addedTaskReply(Task task, int numTasks) {
        String msg = formatLine("Got it. I've added this task:")
                + formatLine("  " + task)
                + formatLine("Now you have " + numTasks + " tasks in the list.");
        reply(msg);
    }

    /**
     * Prints a reply, having marked a task as done.
     *
     * @param task Task that was marked as done.
     */
    public void markDoneReply(Task task) {
        String msg = formatLine("Nice! I've marked this task as done:")
                + formatLine("  " + task);
        reply(msg);
    }

    /**
     * Prints a reply, having deleted a task.
     *
     * @param deletedTask Task that was deleted.
     * @param numTasks Number of tasks in task list.
     */
    public void deleteReply(Task deletedTask, int numTasks) {
        String msg = formatLine("Noted. I've removed this task:")
                + formatLine("  " + deletedTask)
                + formatLine("Now you have " + numTasks + " tasks in the list.");
        reply(msg);
    }
}
