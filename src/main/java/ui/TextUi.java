package ui;

import data.Task;
import data.TaskList;

public class TextUi {
    private static final String GREETING = "Hi, I am Duke. How may I help you?";

    public TextUi() {
    }

    /**
     * Returns the greeting message
     *
     * @return
     */
    public String getGreetingMessage() {
        return GREETING;
    }

    /**
     * Returns the given line with the correct formatting.
     * Currently does not do any formatting.
     *
     * @param line
     */
    public String getFormattedMessage(String line) {
        return line;
    }

    /**
     * Returns the given lines with dividers
     *
     * @param lines
     */
    public String getFormattedMessage(String[] lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    /**
     * Returns a list of tasks as a string
     *
     * @param tasks
     */
    public String getTasksMessage(TaskList tasks) {
        String[] lines = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = (i + 1) + "." + tasks.get(i).toString();
        }

        return getFormattedMessage(lines);
    }

    /**
     * Returns the acknowledgement message for adding a task
     *
     * @param task
     * @param tasks
     */
    public String getAddTaskMessage(Task task, TaskList tasks) {
        return getFormattedMessage(new String[] {
                "Got it. I've added this task:",
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    /**
     * Returns the acknowledgement message for deleting a task
     *
     * @param task
     * @param tasks
     */
    public String getDeleteTaskMessage(Task task, TaskList tasks) {
        return getFormattedMessage(new String[] {
                "Noted. I've removed this task:",
                "  " + task,
                "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    /**
     * Returns the acknowledgement message for finishing a task
     *
     * @param task
     */
    public String getDoneTaskMessage(Task task) {
        return getFormattedMessage("  [" + task.getStatusIcon() + "] " + task.getDescription());
    }
}
