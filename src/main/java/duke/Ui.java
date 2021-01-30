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
    private static String farewell = INDENT + " Bye. Hope to see you again soon!\n";

    /**
     * Displays the message when Duke starts up.
     */
    public static void showInitUi() {
        System.out.println(HORIZ_SEP + "\n" + greeting + HORIZ_SEP + "\n");
    }

    /**
     * Displays the message when Duke exits.
     */
    public static void showExitUi() {
        System.out.println(HORIZ_SEP + "\n" + farewell + HORIZ_SEP + "\n");
    }

    /**
     * Displays the current list of Task objects
     *
     * @param taskList the current list of tasks
     */
    public static void showList(TaskList taskList) {

        ListIterator<Task> taskIter = taskList.getTasks().listIterator();

        System.out.println(HORIZ_SEP);
        System.out.println(INDENT + " Here are the tasks in your list:");
        while (taskIter.hasNext()) {
            Task curr = taskIter.next();
            System.out.println(INDENT + " " + String.valueOf(taskIter.nextIndex()) + "." + curr);
        }
        System.out.println(HORIZ_SEP + "\n");
    }

    /**
     * Displays the message when a Task has been added.
     *
     * @param numTasks current number of tasks in the list
     * @param relevantTask the newly added Task
     */
    public static void showSuccessfulAdd(int numTasks, Task relevantTask) {
        System.out.println(HORIZ_SEP + "\n" +  INDENT + " Got it. I've added this task: ");
        System.out.println(INDENT + "   " + relevantTask);
        System.out.println(INDENT + " Now you have " + numTasks + " tasks in the list.");
        System.out.println(HORIZ_SEP + "\n");
    }

    /**
     * Displays the message when a Task is marked as done.
     *
     * @param relevantTask the Task that has just been done
     */
    public static void showSuccessfulDone(Task relevantTask) {
        System.out.println(HORIZ_SEP + "\n" + INDENT + " Nice! I've marked this task as done:");
        System.out.println(INDENT + "   " + relevantTask);
        System.out.println(HORIZ_SEP + "\n");
    }

    /**
     * Displays the message when a Task is deleted
     *
     * @param numTasks current number of tasks in the list
     * @param relevantTask the deleted Task
     */
    public static void showSuccessfulDelete(int numTasks, Task relevantTask) {
        System.out.println(HORIZ_SEP + "\n" +  INDENT + " Noted. I've removed this task ");
        System.out.println(INDENT + "   " + relevantTask);
        System.out.println(INDENT + " Now you have " + numTasks + " tasks in the list.");
        System.out.println(HORIZ_SEP + "\n");
    }

    public static void showDukeException(DukeException ex) {
        System.out.println(HORIZ_SEP);
        System.out.println(INDENT + " " + ex.getMessage());
        System.out.println(HORIZ_SEP + "\n");
    }

    public static void showMatchedTasks(TaskList taskList) {

        ListIterator<Task> taskIter = taskList.getTasks().listIterator();

        System.out.println(HORIZ_SEP);
        System.out.println(INDENT + " Here are the matching tasks in your list:");
        while (taskIter.hasNext()) {
            Task curr = taskIter.next();
            System.out.println(INDENT + " " + String.valueOf(taskIter.nextIndex()) + "." + curr);
        }
        System.out.println(HORIZ_SEP + "\n");
    }

}
