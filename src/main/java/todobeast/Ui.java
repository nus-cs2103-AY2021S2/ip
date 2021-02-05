package todobeast;

import todobeast.tasks.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Handles User Interface interactions for the application. This includes reading of commands, as well as displaying
 * responses to the user. Note that the business logic of what to respond with is not included in here; this class
 * merely contains all the possible responses that the app can produce.
 */
public class Ui {
    private final Scanner sc;
    private final static String line = "\t________________________________________________________________\n";
    private final static String logo = "                                                     \n" +
            "\t88                                                   \n" +
            "\t88                                            ,d     \n" +
            "\t88                                            88     \n" +
            "\t88,dPPYba,   ,adPPYba, ,adPPYYba, ,adPPYba, MM88MMM  \n" +
            "\t88P'    \"8a a8P_____88 \"\"     `Y8 I8[    \"\"   88     \n" +
            "\t88       d8 8PP\"\"\"\"\"\"\" ,adPPPPP88  `\"Y8ba,    88     \n" +
            "\t88b,   ,a8\" \"8b,   ,aa 88,    ,88 aa    ]8I   88,    \n" +
            "\t8Y\"Ybbd8\"'   `\"Ybbd8\"' `\"8bbdP\"Y8 `\"YbbdP\"'   \"Y888  \n" +
            "\t                                                     \n" +
            "\t                                                     \n";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showLoading() {
        System.out.println("\tLoading data from storage...");
    }

    public void showLoadingSuccess() {
        System.out.println("\tData loaded successfully!");
    }

    public void showLoadingError() {
        System.out.println("\tFailed to load data!");
    }

    public void showDone(Task task) {
        System.out.println("\tGood job! You've just completed this task:\n" + "\t\t" + task);
    }

    public void showDeleted(Task task) {
        System.out.println("\tGot it! I've removed this task for you:\n\t\t" + task);
    }

    public void showNumOfTasks(int numOfTasks) {
        System.out.println("\tYou now have " + numOfTasks + " tasks in total.");
    }

    public void showAdded(Task task) {
        System.out.println("\tOne more task added to the hustle:\n\t\t" + task);
    }

    public void showWelcome() {
        String greeting = line + logo + "\tWelcome to ToDoBeast, your best productivity task tracker tool!\n"
                + "\tLet's get this bread! How would you like to be productive today?\n";
        System.out.println(greeting);
    }

    public void listTasks() {
        System.out.println("\tHere are your tasks:");
    }

    public void findTasks() {
        System.out.println("\tHere are the matching tasks in your list:");
    }

    public void printTaskList(List<Task> taskList) {
        int count = 1;
        for (Task task : taskList) {
            System.out.println("\t" + count++ + ". " + task);
        }
    }

    public void showInstructions() {
        System.out.println("\tinstructions or wtv");
    }

    public void showError(String error) {
        System.out.println("\t" + error);
    }

    public void showExit() {
        String exitMsg = line + "\tThis app may have stopped but the grind never stops.\n\tSee you again soon!\n" + line;
        System.out.println(exitMsg);
        System.exit(0);
    }
}
