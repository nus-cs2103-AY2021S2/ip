package duke.maincomponents;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeLine() {
        System.out.println(defaultFormatting("Hello! I'm Duke\n"
                + padSpaces("What can I do for you?", 5)));
    }

    public void showGoodbyeLine() {
        System.out.println(defaultFormatting("Bye. Hope to see you again soon!"));
    }

    private String numberOfTasksLine(int numberOfTasks) {
        return padSpaces("Now you have " + String.valueOf(numberOfTasks) + " task in the list.", 5);
    }

    public static void showReturnTaskList(ArrayList<Task> taskArray) {
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

    public void showLoadingError(String errorMsg) {
        System.out.println(defaultFormatting(errorMsg));
    }

    public void showLoadingSucess() {
        System.out.println(defaultFormatting("Data Successfuly Restored"));
    }

    public void showErrorMsg(String msg) {
        System.out.println(defaultFormatting(msg));
    }

    public void showTaskDone(Task doneTask) {
        System.out.println(defaultFormatting("Nice! I've marked this task as done:\n"
                + padSpaces(doneTask.toString(), 7)));
    }

    public void showTaskDeleted(Task deletedTask, int numberOfTasks) {
        String a = "Noted. I've removed this task:\n" + padSpaces(deletedTask.toString(), 7)
                + "\n" + numberOfTasksLine(numberOfTasks);

        System.out.println(defaultFormatting(a));
    }

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

    private static String padSpaces(String input, int numOfSpacesToPad) {
        String toreturn = "";

        for (int i = 0; i < numOfSpacesToPad; i++) {
            toreturn = toreturn + ' ';
        }

        return toreturn + input;
    }

    public void showFoundTaskList(ArrayList<Task> taskArray){
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine()).append('\n');

        sb.append(indentedString("Here are the matching tasks in your list:\n"));
        for (int i = 0; i < taskArray.size(); i++) {

            Task currentTask = taskArray.get(i);

            String numberIndicator =  (i+1) + ".";
            String temp = numberIndicator + currentTask.toString() + '\n';

            sb.append(indentedString( temp ));
        }
        sb.append(horizontalLine());
        System.out.println(sb.toString());
    }
}
