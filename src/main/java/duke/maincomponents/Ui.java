package duke.maincomponents;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

public class Ui {
    private Scanner scanner;
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

    public void showGoodbyeLine() {
        System.out.println(defaultFormatting("Bye. Hope to see you again soon!"));
    }

    private String numberOfTasksLine(int numberOfTasks) {
        return padSpaces("Now you have " + numberOfTasks + " task in the list.", 5);
    }

    /**
     * Shows a task list to the user in the appropriate format
     * @param taskArray task array list to show to the viewer
     */
    public void showReturnTaskList(ArrayList<Task> taskArray) {
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine()).append('\n');

        sb.append(indentedString("Here are the task in your list:\n"));
        for (int i = 0; i < taskArray.size(); i++) {

            Task currentTask = taskArray.get(i);

            String numberIndicator = (i + 1) + ".";
            String temp = numberIndicator + currentTask.toString() + '\n';

            sb.append(indentedString(temp));
        }
        sb.append(horizontalLine());
        System.out.println(sb.toString());
    }

    /**
     * Shows a specificed error message
     * @param errorMsg error message to show to the user
     */
    public void showLoadingError(String errorMsg) {
        System.out.println(defaultFormatting(errorMsg));
    }

    public void showLoadingSucess() {
        System.out.println(defaultFormatting("Data Successfuly Restored"));
    }

    public void showErrorMsg(String msg) {
        System.out.println(defaultFormatting(msg));
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
     * Show the user that a specific task has been deleted
     * @param deletedTask Task that has been deleted
     * @param numberOfTasks Number of remaining tasks
     */
    public void showTaskDeleted(Task deletedTask, int numberOfTasks) {
        String a = "Noted. I've removed this task:\n" + padSpaces(deletedTask.toString(), 7)
                + "\n" + numberOfTasksLine(numberOfTasks);

        System.out.println(defaultFormatting(a));
    }

    /**
     * Shows the user the task that has been added to duke
     * @param addedTask Task that has been added
     * @param numberOfTasks Number of tasks
     */
    public void showAddedTask(Task addedTask, int numberOfTasks) {
        String temp = "Got it. I've added this task:\n" + padSpaces(addedTask.toString(), 7)
                + "\n" + numberOfTasksLine(numberOfTasks);

        System.out.println(defaultFormatting(temp));
    }


    public String readCommand() {
        return scanner.nextLine().trim();
    }

    private static String horizontalLine() {
        return padSpaces("____________________________________________________________", 4);
    }

    private static String indentedString(String input) {
        return padSpaces(input, 5);
    }

    private static String defaultFormatting(String input) {
        return horizontalLine() + '\n' + indentedString(input) + '\n' + horizontalLine();
    }

    /**
     * pad a string with spaces at its front
     * @param input input string
     * @param numOfSpacesToPad number of spaces to add in front of a string
     * @return string padded with spaces
     */
    private static String padSpaces(String input, int numOfSpacesToPad) {
        String toreturn = "";

        for (int i = 0; i < numOfSpacesToPad; i++) {
            toreturn = toreturn + ' ';
        }

        return toreturn + input;
    }

    /**
     * Shows task that are related to a string specificed by the user
     * @param taskArray Array list of tasks that are related to a string specificed by the user
     */
    public void showFoundTaskList(ArrayList<Task> taskArray) {
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine()).append('\n');

        sb.append(indentedString("Here are the matching tasks in your list:\n"));
        for (int i = 0; i < taskArray.size(); i++) {

            Task currentTask = taskArray.get(i);

            String numberIndicator = (i + 1) + ".";
            String temp = numberIndicator + currentTask.toString() + '\n';

            sb.append(indentedString(temp));
        }
        sb.append(horizontalLine());
        System.out.println(sb.toString());
    }
}
