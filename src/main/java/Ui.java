import java.io.IOException;
import java.util.ArrayList;

/**
 * Deals with user interactions.
 * Return messages to inform/update user according to Duke actions.
 */
public class Ui {
    /**
     * Returns the introduction.
     */
    public static String printIntroduction() {
        return "Hello! I'm Duke.\n" + "What can I do for you?";
    }

    /**
     * Returns the exit message.
     */
    public String printExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns the IO error.
     * @param ie The IO exception.
     */
    public String printUpdateIoError(IOException ie) {
        return "OOPS! An error occurred while attempting to update storage file:\n"
                + ie.getMessage();
    }

    /**
     * Returns the Duke Exception.
     * @param de The Duke Exception.
     */
    public String printDukeException(DukeException de) {
        return de.getMessage();
    }

    /**
     * Returns the list of tasks.
     * @param tasks Tasks at hand.
     */
    public String printTasksNormal(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getNormalTasks();
        StringBuilder toReturn = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            Task ref = taskList.get(i - 1);
            toReturn.append(i).append(". ").append(ref.getStatus()).append(System.lineSeparator());
        }
        return toReturn.toString();
    }

    /**
     * Returns the list of snoozed tasks.
     * @param tasks Tasks at hand.
     */
    public String printTasksSnoozed(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getSnoozedTasks();
        StringBuilder toReturn = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            Task ref = taskList.get(i - 1);
            toReturn.append(i).append(". ").append(ref.getStatus()).append(System.lineSeparator());
        }
        return toReturn.toString();
    }

    /**
     * Returns the list of all tasks.
     * @param tasks Tasks at hand.
     */
    public String printTasksAll(TaskList tasks) {
        ArrayList<Task> taskList1 = tasks.getNormalTasks();
        ArrayList<Task> taskList2 = tasks.getSnoozedTasks();
        StringBuilder toReturn = new StringBuilder();
        for (int i = 1; i <= taskList1.size(); i++) {
            Task ref = taskList1.get(i - 1);
            toReturn.append(i).append(". ").append(ref.getStatus()).append(System.lineSeparator());
        }
        toReturn.append("\nSnoozed:\n");
        for (int i = 1; i <= taskList2.size(); i++) {
            Task ref = taskList2.get(i - 1);
            toReturn.append(i).append(". ").append(ref.getStatus()).append(System.lineSeparator());
        }
        return toReturn.toString();
    }

    /**
     * Returns the list of tasks according to the input.
     * @param tasks The list of tasks to print.
     */
    public String printSomeTasks(ArrayList<Task> tasks) {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task ref = tasks.get(i - 1);
            if (!ref.isOnSnooze()) {
                toReturn.append(i).append(". ").append(ref.getStatus()).append(System.lineSeparator());
            }
        }
        return toReturn.toString();
    }

    /**
     * Returns the number of tasks.
     * @param taskList The TaskList that is being kept tracked of.
     */
    public String printNumTasks(TaskList taskList) {
        return "Now you have " + taskList.getNormalTasks().size() + " tasks in the list.";
    }

    /**
     * Returns message when adding a task.
     */
    public String printAddTaskMessage(Task added, TaskList taskList) {
        String toReturn = "Got it. I've added this task:\n" + added.getStatus();
        toReturn += "\n" + printNumTasks(taskList);
        return toReturn;
    }

    /**
     * Returns the message when a task is marked as done.
     * @param marked Task to be marked as done.
     */
    public String printMarkDone(Task marked) {
        return "This task is marked as done:\n" + marked.getStatus();
    }

    /**
     * Returns the message when a task is snoozed.
     * @param snoozed Task to be snoozed.
     */
    public String printSnoozedTask(Task snoozed) {
        return "This task has been snoozed:\n" + snoozed.getStatus();
    }

    /**
     * Returns the message when a task is deleted.
     * @param deleted Task to be deleted.
     * @param taskList TaskList stored.
     */
    public String printDeleteTask(Task deleted, TaskList taskList) {
        String toReturn = "This task has been removed:\n" + deleted.getStatus();
        toReturn += "\n" + printNumTasks(taskList);
        return toReturn;
    }
}
