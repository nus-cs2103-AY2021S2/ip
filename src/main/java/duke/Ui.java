package duke;

import java.util.ListIterator;

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
     * Displays the message when Duke starts up.
     */
    public static String showInitUi() {
        return greeting;
    }

    /**
     * Displays the message when Duke exits.
     */
    public static String showExitUi() {
        return farewell;
    }

    /**
     * Displays the current list of Task objects
     *
     * @param taskList the current list of tasks
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
     * Displays the message when a Task has been added.
     *
     * @param numTasks current number of tasks in the list
     * @param relevantTask the newly added Task
     */
    public static String showSuccessfulAdd(int numTasks, Task relevantTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " Got it. I've added this task: \n");
        stringBuilder.append(INDENT + "   ")
                .append(relevantTask)
                .append("\n\n");
        stringBuilder.append(INDENT + " Now you have ").append(numTasks).append(" tasks in the list.");
        //stringBuilder.append(HORIZ_SEP + "\n");
        
        return stringBuilder.toString();
    }

    /**
     * Displays the message when a Task is marked as done.
     *
     * @param relevantTask the Task that has just been done
     */
    public static String showSuccessfulDone(Task relevantTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " Nice! I've marked this task as done: \n");
        stringBuilder.append(INDENT + "   ").append(relevantTask);
        //stringBuilder.append(HORIZ_SEP + "\n");
        
        return stringBuilder.toString();
    }

    /**
     * Displays the message when a Task is deleted
     *
     * @param numTasks current number of tasks in the list
     * @param relevantTask the deleted Task
     */
    public static String showSuccessfulDelete(int numTasks, Task relevantTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " Noted. I've removed this task \n");
        stringBuilder.append(INDENT + "   ")
                .append(relevantTask)
                .append("\n\n");
        stringBuilder.append(INDENT + " Now you have ").append(numTasks).append(" tasks in the list.");
        //stringBuilder.append(HORIZ_SEP + "\n");

        return stringBuilder.toString();
    }

    public static String showDukeException(DukeException ex) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(INDENT + " " + ex.getMessage());

        return stringBuilder.toString();
    }

    public static String showMatchedTasks(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();

        ListIterator<Task> taskIter = taskList.getTasks().listIterator();

        stringBuilder.append(INDENT + " Here are the matching tasks in your list: \n");
        while (taskIter.hasNext()) {
            Task curr = taskIter.next();
            stringBuilder.append(INDENT + " ")
                    .append(String.valueOf(taskIter.nextIndex()))
                    .append(".")
                    .append(curr)
                    .append("\n");
        }

        return stringBuilder.toString();
    }

}
