package duke.maincomponents;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class, which creates all the string that are to be shown to the user
 */
public class Ui {
    private Scanner scanner;
    private int paddingGui = 4;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows default welcome line
     */
    public void showWelcomeLine() {
        System.out.println(defaultFormatting("Hello! I'm Duke\n"
                + padSpaces("What can I do for you?", 5)));
    }

    public String returnWelcomeLine() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    public void showGoodbyeLine() {
        System.out.println(defaultFormatting("Bye. Hope to see you again soon!"));
    }

    public String returnGoodbyeLine() {
        return "Bye. Hope to see you again soon!" + "\nPlease close this window by typing q or using the button!";
    }

    private String numberOfTasksLine(int numberOfTasks) {
        return padSpaces("Now you have " + numberOfTasks + " task in the list.", 5);
    }

    private String numberOfTasksLineGui(int numberOfTasks) {
        return padSpaces("Now you have " + numberOfTasks + " task in the list.", paddingGui);
    }

    /**
     * Shows a task list to the user in the appropriate format
     *
     * @param taskArray Task array list to show to the viewer
     */
    public void showTaskList(ArrayList<Task> taskArray) {
        if (taskArray.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(horizontalLine()).append('\n');

            sb.append(padSpaces("Here are the task in your list:\n", 5));
            for (int i = 0; i < taskArray.size(); i++) {

                Task currentTask = taskArray.get(i);

                String numberIndicator = (i + 1) + ".";
                String temp = numberIndicator + currentTask.toString() + '\n';

                sb.append(padSpaces(temp, 5));
            }
            sb.append(horizontalLine());
            System.out.println(sb.toString());
        } else {
            System.out.println(defaultFormatting("There are no tasks on your list currently!"));
        }
    }

    /**
     * Returns a string representation of a taskArray
     *
     * @param taskArray Array List of tasks
     * @return String representation of the taskArray given
     */
    public String returnTaskList(ArrayList<Task> taskArray) {
        if (taskArray.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the task in your list:\n");
            for (int i = 0; i < taskArray.size(); i++) {

                Task currentTask = taskArray.get(i);

                String numberIndicator = (i + 1) + ".";
                String temp = numberIndicator + currentTask.toString() + '\n';

                sb.append(padSpaces(temp, paddingGui));
            }
            return sb.toString();
        } else {
            return "There are no tasks on your list currently!";
        }
    }

    public void showLoadingSucess() {
        System.out.println(defaultFormatting("Data Successfuly Restored"));
    }

    /**
     * Shows a specificed error message
     *
     * @param errorMsg Error message to show to the user
     */
    public void showErrorMsg(String errorMsg) {
        System.out.println(defaultFormatting(errorMsg));
    }

    public String returnErrorMsg(String errorMsg) {
        return errorMsg;
    }

    /**
     * Shows the user that a specific task has been marked as done
     * @param doneTask Task that has been marked as done
     */
    public void showTaskDone(Task doneTask) {
        System.out.println(defaultFormatting("Nice! I've marked this task as done:\n"
                + padSpaces(doneTask.toString(), 7)));
    }

    /**
     * Returns a string representation that tells the user that a specific task has been marked as done
     *
     * @param doneTask Task that has been marked as done
     * @return String representation that tells the user that a specific task has been marked as done
     */
    public String returnTaskDone(Task doneTask) {
        return "Nice! I've marked this task as done:\n"
                + padSpaces(doneTask.toString(), paddingGui);
    }

    /**
     * Show the user that a specific task has been deleted
     *
     * @param deletedTask Task that has been deleted
     * @param numberOfTasks Number of remaining tasks
     */
    public void showTaskDeleted(Task deletedTask, int numberOfTasks) {
        String a = "Noted. I've removed this task:\n" + padSpaces(deletedTask.toString(), 7)
                + "\n" + numberOfTasksLine(numberOfTasks);

        System.out.println(defaultFormatting(a));
    }

    /**
     * Returns a string representation that tells the user a specific task has been deleted
     *
     * @param deletedTask Task that has been deleted
     * @param numberOfTasks Number of remaining tasks
     * @return String representation that tells the user a specific task has been deleted
     */
    public String returnTaskDeleted(Task deletedTask, int numberOfTasks) {
        return "Noted. I've removed this task:\n" + padSpaces(deletedTask.toString(), paddingGui)
                + "\n" + numberOfTasksLineGui(numberOfTasks);
    }

    /**
     * Shows the user the task that has been added to duke
     *
     * @param addedTask Task that has been added
     * @param numberOfTasks Number of tasks
     */
    public void showAddedTask(Task addedTask, int numberOfTasks) {
        String temp = "Got it. I've added this task:\n" + padSpaces(addedTask.toString(), 7)
                + "\n" + numberOfTasksLine(numberOfTasks);

        System.out.println(defaultFormatting(temp));
    }

    /**
     * Returns a string represention that tells the user a ask that has been added to duke
     *
     * @param addedTask Task that has been added
     * @param numberOfTasks Number of tasks
     * @return String represention that tells the user a ask that has been added to duke
     */
    public String returnAddedTask(Task addedTask, int numberOfTasks) {
        String temp = "Got it. I've added this task:\n" + padSpaces(addedTask.toString(), paddingGui)
                + "\n" + numberOfTasksLineGui(numberOfTasks);

        return temp;
    }

    /**
     * Shows tasks that are related to a string specificed by the user
     *
     * @param taskArray Array List of tasks that are related to a string specificed by the user
     */
    public void showFoundTaskList(ArrayList<Task> taskArray) {
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine()).append('\n');

        sb.append(padSpaces("Here are the matching tasks in your list:\n", 5));
        for (int i = 0; i < taskArray.size(); i++) {

            Task currentTask = taskArray.get(i);

            String numberIndicator = (i + 1) + ".";
            String temp = numberIndicator + currentTask.toString() + '\n';

            sb.append(padSpaces(temp, 5));
        }
        sb.append(horizontalLine());
        System.out.println(sb.toString());
    }

    /**
     * Returns a string represention that tells the user the tasks that
     * are related to a string specificed by the user
     *
     * @param taskArray Array List of tasks that are related to a string specificed by the user
     * @return A string represention that tells the user the tasks that are related to a string specificed by the user
     */
    public String returnFoundTaskList(ArrayList<Task> taskArray) {
        StringBuilder sb = new StringBuilder();

        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskArray.size(); i++) {

            Task currentTask = taskArray.get(i);

            String numberIndicator = (i + 1) + ".";
            String temp = numberIndicator + currentTask.toString() + '\n';

            sb.append(padSpaces(temp, 4));
        }
        return sb.toString();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    private static String horizontalLine() {
        return padSpaces("____________________________________________________________", 4);
    }

    private static String defaultFormatting(String input) {
        return horizontalLine() + '\n' + padSpaces(input, 5) + '\n' + horizontalLine();
    }

    /**
     * Pads a string with spaces at its front
     *
     * @param input Input string
     * @param numOfSpacesToPad Number of spaces to add in front of a string
     * @return String padded with spaces
     */
    private static String padSpaces(String input, int numOfSpacesToPad) {
        StringBuilder toreturn = new StringBuilder();

        toreturn.append(" ".repeat(Math.max(0, numOfSpacesToPad)));

        return toreturn.toString() + input;
    }

    /**
     * Shows tasks that have been updated
     *
     * @param task Updated taks to print out
     */
    public void showChangedTask(Task task) {
        System.out.println(defaultFormatting("Alright! I've update the task as follows:\n"
                + padSpaces(task.toString(), 7)));
    }

    public String returnChangedTask(Task task) {
        return "Alright! I've update the task as follows:\n" + padSpaces(task.toString(), paddingGui);
    }
}
