package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Text UI of the application.
 */
public class Ui {

    private final StringBuilder sb;

    /**
     * Default constructor for UI which initialise the StringBuilder.
     */
    public Ui() {
        this.sb = new StringBuilder();
    }

    /**
     * Returns the welcome message.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns a farewell message.
     */
    public static String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    private void clear() {
        sb.setLength(0);
    }

    /**
     * Returns a formatted {@code TaskList} string.
     *
     * @param taskList {@code TaskList} to be formatted
     * @param isFind an indicator used to differentiate between the list and find operation
     */
    public String showListMessage(TaskList taskList, boolean isFind) {
        clear();
        sb.append(String.format("Here are the %s in your list:%n", isFind ? "matching tasks" : "tasks"));
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(String.format("%d.%s%n", i + 1, taskList.get(i).toString()));
        }
        return sb.toString();
    }

    /**
     * Returns the information of the new {@code Task}.
     *
     * @param task the newly added {@code Task}
     * @param listSize size of the current {@code TaskList}
     */
    public String showAddMessage(Task task, int listSize) {
        clear();
        sb.append("Got it. I've added this task:");
        sb.append("\n");
        sb.append(formattedPrint(task.toString()));
        showSummaryMessage(listSize);
        return sb.toString();
    }

    /**
     * Returns the information of the completed {@code Task}.
     *
     * @param task the completed {@code Task}
     */
    public String showDoneMessage(Task task) {
        clear();
        sb.append("Nice! I've marked this task as done:");
        sb.append("\n");
        sb.append(formattedPrint(task.toString()));
        return sb.toString();
    }

    /**
     * Returns the information of the {@code TaskList} of completed {@code Task}.
     *
     * @param taskList the completed {@code TaskList} of {@code Task}
     */
    public String showDoneMessage(TaskList taskList) {
        clear();
        sb.append("Nice! I've marked all your tasks as done:");
        sb.append("\n");
        printList(taskList);
        return sb.toString();
    }

    /**
     * Returns the information of the deleted {@code Task}.
     *
     * @param task the deleted {@code Task}
     * @param listSize size of the current {@code TaskList}
     */
    public String showDeleteMessage(Task task, int listSize) {
        clear();
        sb.append("Noted. I've removed this task:");
        sb.append("\n");
        sb.append(formattedPrint(task.toString()));
        showSummaryMessage(listSize);
        return sb.toString();
    }

    /**
     * Returns the information of the cleared {@code TaskList}.
     *
     * @param taskList the {@code TaskList} to be cleared
     */
    public String showDeleteMessage(TaskList taskList) {
        clear();
        sb.append("Noted. I've removed all your tasks.");
        sb.append("\n");
        printList(taskList);
        showSummaryMessage(0);
        return sb.toString();
    }

    /**
     * Appends the {@code TaskList}'s summary information into the StringBuilder.
     *
     * @param listSize size of the current {@code TaskList}
     */
    private void showSummaryMessage(int listSize) {
        sb.append(String.format("Now you have %d %s in the list.%n", listSize, listSize >= 2 ? "tasks" : "task"));
    }

    /**
     * Returns the formatted error message.
     *
     * @param message message to be formatted
     */
    public String showError(String message) {
        return String.format("☹️ OOPS!!! %s%n", message);
    }

    /**
     * Returns the formatted message with indentation.
     *
     * @param message message to be formatted
     */
    private String formattedPrint(String message) {
        String printFormat = "    %s%n";
        return String.format(printFormat, message);
    }

    /**
     * Appends all the tasks' information stored in {@code TaskList} into the StringBuilder.
     *
     * @param taskList the {@code TaskList} to be printed
     */
    private void printList(TaskList taskList) {
        for (Task task : taskList.getTaskList()) {
            sb.append(formattedPrint(task.toString()));
        }
    }
}
