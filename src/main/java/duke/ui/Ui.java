package duke.ui;

import static duke.common.Messages.MESSAGE_ERROR;
import static duke.common.Messages.MESSAGE_GOODBYE;
import static duke.common.Messages.MESSAGE_LIST;
import static duke.common.Messages.MESSAGE_TASKLIST_SUMMARY;
import static duke.common.Messages.MESSAGE_TASK_ADD;
import static duke.common.Messages.MESSAGE_TASK_ALL_DELETE;
import static duke.common.Messages.MESSAGE_TASK_ALL_DONE;
import static duke.common.Messages.MESSAGE_TASK_DELETE;
import static duke.common.Messages.MESSAGE_TASK_DONE;
import static duke.common.Messages.MESSAGE_WELCOME;

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
        return MESSAGE_WELCOME;
    }

    /**
     * Returns a farewell message.
     */
    public String showExit() {
        return MESSAGE_GOODBYE;
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
        sb.append(String.format(MESSAGE_LIST, isFind ? "matching tasks" : "tasks"));
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
        sb.append(MESSAGE_TASK_ADD);
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
        sb.append(MESSAGE_TASK_DONE);
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
        sb.append(MESSAGE_TASK_ALL_DONE);
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
        sb.append(MESSAGE_TASK_DELETE);
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
        sb.append(MESSAGE_TASK_ALL_DELETE);
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
        sb.append(String.format(MESSAGE_TASKLIST_SUMMARY, listSize, listSize >= 2 ? "tasks" : "task"));
    }

    /**
     * Returns the formatted error message.
     *
     * @param message message to be formatted
     */
    public String showError(String message) {
        return String.format(MESSAGE_ERROR, message);
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
        taskList.getTaskList().forEach(task -> {
            sb.append(formattedPrint(task.toString()));
        });
    }
}
