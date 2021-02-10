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
    public String printTasks(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        StringBuilder toReturn = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            toReturn.append(i).append(". ").append(taskList.get(i - 1).getStatus()).append(System.lineSeparator());
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
            toReturn.append(i).append(". ").append(tasks.get(i - 1).getStatus()).append(System.lineSeparator());
        }
        return toReturn.toString();
    }

    /**
     * Returns the number of tasks.
     * @param taskList The TaskList that is being kept tracked of.
     */
    public String printNumTasks(TaskList taskList) {
        return "Now you have " + taskList.getTasks().size() + " tasks in the list.";
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
