package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "Chip the Squirrel";
    private static final String LINE_BREAK = "------------------------------------------------------------";
    private static final String INDENT = "    ";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void printWithIndentation(String... strings) {
        System.out.println(INDENT + LINE_BREAK);

        for (String s : strings) {
            System.out.println(INDENT + s);
        }

        System.out.println(INDENT + LINE_BREAK);
    }

    public boolean hasMoreTokens() {
        return sc.hasNext();
    }

    public String getUserCommand() {
        return sc.nextLine().trim();
    }

    public void showWelcomeMessage() {
        printWithIndentation("Hello! I'm " + BOT_NAME + "!", "What can I do for you today?");
    }

    public void showGoodbyeMessage() {
        printWithIndentation("Bye! Hope to see you again soon!");
    }

    public void showErrorMessage(String errorMessage) {
        printWithIndentation(errorMessage);
    }

    public void showTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            printWithIndentation("You have not added any tasks.");
        } else {
            String[] tasksArr = new String[taskList.size()];

            for (int i = 0; i < taskList.size(); i++) {
                tasksArr[i] = (i + 1) + "." + taskList.get(i).toString();
            }

            printWithIndentation(tasksArr);
        }
    }

    public void showFilteredTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            printWithIndentation("No matching tasks were found.");
        } else {
            String[] outputArr = new String[taskList.size() + 1];
            outputArr[0] = "Here are the matching tasks in your list:";

            for (int i = 0; i < taskList.size(); i++) {
                outputArr[i + 1] = (i + 1) + "." + taskList.get(i).toString();
            }

            printWithIndentation(outputArr);
        }
    }

    public void showSuccessfulDeleteMessage(int numTasks, Task task) {
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";

        printWithIndentation("Got it! I've removed this task:",
                "  " + task.toString(),
                "Now you have " + formattedTasksCount + " in the list.");
    }

    public void showSuccessfulDoneMessage(Task task) {
        printWithIndentation("Good Job! I've marked this task as done!", task.toString());
    }

    public void showAddTaskMessage(int numTasks, Task task) {
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";

        printWithIndentation("Got it! I've added this task:",
                "  " + task.toString(),
                "Now you have " + formattedTasksCount + " in the list.");
    }
}
