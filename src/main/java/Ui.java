public class Ui {
    public Ui() {
    }

    /**
     * Formats given string with indentation and line break
     *
     * @param line Line to format.
     * @return Formatted line.
     */
    public static String formatLine(String line) {
        return line + "\n";
    }

    public static String getGreeting() {
        return formatLine("Hello! I'm Duke")
                + formatLine("What can I do for you?");
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @param tasks List of tasks.
     * @return Formatted list of tasks.
     */
    public static String list(TaskList tasks) {
        StringBuilder msg = new StringBuilder(formatLine("Here are the tasks in your list:"));

        for (int i = 0; i < tasks.size(); i++) {
            msg.append(formatLine((i + 1) + ". " + tasks.getTaskAt(i)));
        }
        return msg.toString();
    }

    public static String exit() {
        return formatLine("Bye. Hope to see you again soon!");
    }

    /**
     * Returns the reply to having added a task.
     *
     * @param task Task that was added.
     * @param numTasks Total number of tasks in task list.
     * @return Reply to having added a task.
     */
    public static String addedTaskReply(Task task, int numTasks) {
        return formatLine("Got it. I've added this task:")
                + formatLine("  " + task)
                + formatLine("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Returns the reply to having marked a task as done.
     *
     * @param task Task that was marked as done.
     * @return Reply to having marked a task as done.
     */
    public static String markDoneReply(Task task) {
        return formatLine("Nice! I've marked this task as done:")
                + formatLine("  " + task);
    }

    /**
     * Returns the reply to having deleted a task.
     *
     * @param deletedTask Task that was deleted.
     * @param numTasks Number of tasks in task list.
     * @return Reply to having deleted a task.
     */
    public static String deleteReply(Task deletedTask, int numTasks) {
        return formatLine("Noted. I've removed this task:")
                + formatLine("  " + deletedTask)
                + formatLine("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Returns the reply to having tagged a task.
     *
     * @param taskToTag Task that was tagged.
     * @param tag Tag that was added.
     * @return Reply to having tagged a task.
     */
    public static String tagTaskReply(Task taskToTag, String tag) {
        return formatLine("Nice! I've tagged this task as #" + tag)
                + formatLine("  " + taskToTag);
    }

    /**
     * Returns a string of the list of matching tasks.
     *
     * @param tasks Tasklist of matching tasks.
     * @return String of the list of matching tasks.
     */
    public static String listMatchingTasks(TaskList tasks) {
        if (tasks.size() <= 0) {
            return formatLine("No matching tasks were found.");
        }

        StringBuilder msg = new StringBuilder(formatLine("Here are the matching tasks in your list:"));

        for (int i = 0; i < tasks.size(); i++) {
            msg.append(formatLine((i + 1) + ". " + tasks.getTaskAt(i)));
        }
        return msg.toString();
    }
}
