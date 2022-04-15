package utility;

import java.util.ListIterator;

import duke.DukeException;
import duke.Tag;
import task.Task;

/**
 * Aids Duke in displaying messages on its UI
 */
public class Ui {

    public static final String INDENT = "         ";
    public static final String HORIZ_SEP = INDENT + "________________________________________________";
    public static final String FILE_PATH = "./data/duke.txt";
    private static final String greeting = INDENT + " Hello! I'm Duke\n" + INDENT + " What can I do for you?\n";
    private static final String farewell = INDENT + " Bye. Hope to see you again soon!\n";

    /**
     * Creates Duke's response when the program starts up.
     * @return a String representing Duke's response
     */
    public static String showInitUi() {
        return greeting;
    }

    /**
     * Creates Duke's response when the program exits.
     * @return a String representing Duke's response
     */
    public static String showExitUi() {
        return farewell;
    }

    /**
     * Creates Duke's response when the user requests for the saved Task's to be shown in a list
     * @param taskList the current list of tasks
     * @return a String representing Duke's response
     */
    public static String showList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        ListIterator<Task> taskIter = taskList.getTasks().listIterator();

        stringBuilder.append(INDENT + " Here are the tasks in your list: \n");
        while (taskIter.hasNext()) {
            Task curr = taskIter.next();
            stringBuilder.append(String.valueOf(taskIter.nextIndex()))
                    .append(".")
                    .append(curr)
                    .append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Creates Duke's response when a Task has been successfully added to the list.
     * @param numTasks current number of tasks in the list
     * @param relevantTask the newly added Task
     * @return a String representing Duke's response
     */
    public static String showSuccessfulAdd(int numTasks, Task relevantTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " Got it. I've added this task: \n");
        stringBuilder.append(relevantTask).append("\n\n");
        stringBuilder.append(INDENT + " Now you have ").append(numTasks).append(" tasks in the list.");

        return stringBuilder.toString();
    }

    /**
     * Creates Duke's response when a Task has been marked as done.
     * @param relevantTask the Task that has just been done
     * @return a String representing Duke's response
     */
    public static String showSuccessfulDone(Task relevantTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " Nice! I've marked this task as done: \n");
        stringBuilder.append(relevantTask);
        return stringBuilder.toString();
    }

    /**
     * Creates Duke's response when a Task is deleted
     * @param numTasks current number of tasks in the list
     * @param relevantTask the deleted Task
     * @return a String representing Duke's response
     */
    public static String showSuccessfulDelete(int numTasks, Task relevantTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " Noted. I've removed this task \n");
        stringBuilder.append(relevantTask).append("\n\n");
        stringBuilder.append(INDENT + " Now you have ").append(numTasks).append(" tasks in the list.");

        return stringBuilder.toString();
    }

    /**
     * Creates Duke's response when the program catches a DukeException
     * @param ex the DukeException caught
     * @return a String representing Duke's response
     */
    public static String showDukeException(DukeException ex) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(ex.getMessage());

        return stringBuilder.toString();
    }


    /**
     * Creates Duke's response when the program catches a Exception
     * @param ex the Exception caught
     * @return a String representing Duke's response
     */
    public static String showException(Exception ex) {
        return INDENT + " " + ex.getMessage();
    }

    /**
     * Creates Duke's response to user's request to find tasks if the keyword found relevant tasks.
     * @param taskList a non-empty TaskList containing the tasks relevant to what the user is searching for.
     * @return a String representing Duke's response
     */
    public static String showMatchedTasks(TaskList taskList) throws IllegalArgumentException {
        if (taskList.getTasks().size() == 0) {
            throw new IllegalArgumentException("Ui.showMatchedTasks() expects a non-empty taskList argument");
        }
        StringBuilder stringBuilder = new StringBuilder();

        ListIterator<Task> taskIter = taskList.getTasks().listIterator();

        stringBuilder.append(INDENT + " Here are the matching tasks in your list: \n");
        while (taskIter.hasNext()) {
            Task curr = taskIter.next();
            stringBuilder.append(String.valueOf(taskIter.nextIndex()))
                    .append(".")
                    .append(curr)
                    .append("\n");
        }

        return stringBuilder.toString();
    }

    public static String showNoMatchedTasks() {
        return INDENT + " No relevant tasks were found!";
    }

    public static String showTagHandling(Tag action, String tag, Task relevantTask) {
        StringBuilder stringBuilder = new StringBuilder();

        switch (action) {
        case ADD:
            stringBuilder.append("The tag #").append(tag).append(" was added to the Task ")
                    .append("\"").append(relevantTask.getDescription()).append("\"");
            return stringBuilder.toString();

        case DELETE:
            stringBuilder.append("The tag #").append(tag).append(" was deleted from the Task ")
                    .append("\"").append(relevantTask.getDescription()).append("\"");
            return stringBuilder.toString();

        default:
            throw new AssertionError();
        }
    }

}
