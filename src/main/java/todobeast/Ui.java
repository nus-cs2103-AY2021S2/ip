package todobeast;

import java.util.List;
import java.util.Scanner;

import todobeast.tasks.Task;

/**
 * Handles User Interface interactions for the application. This includes reading of commands, as well as displaying
 * responses to the user. Note that the business logic of what to respond with is not included in here; this class
 * merely contains all the possible responses that the app can produce.
 */
public class Ui {
    private final Scanner sc;
    private StringBuilder responseOutput;
    private final static String line = "\t________________________________________________________________\n";
    private final static String oldLogo = "                                                     \n"
            + "\t88                                                   \n"
            + "\t88                                            ,d     \n"
            + "\t88                                            88     \n"
            + "\t88,dPPYba,   ,adPPYba, ,adPPYYba, ,adPPYba, MM88MMM  \n"
            + "\t88P'    \"8a a8P_____88 \"\"     `Y8 I8[    \"\"   88     \n"
            + "\t88       d8 8PP\"\"\"\"\"\"\" ,adPPPPP88  `\"Y8ba,    88     \n"
            + "\t88b,   ,a8\" \"8b,   ,aa 88,    ,88 aa    ]8I   88,    \n"
            + "\t8Y\"Ybbd8\"'   `\"Ybbd8\"' `\"8bbdP\"Y8 `\"YbbdP\"'   \"Y888  \n"
            + "\t                                                     \n"
            + "\t                                                     \n";
    private final static String logo = " _                    _   \n"
            + "| |                  | |  \n"
            + "| |__   ___  __ _ ___| |_ \n"
            + "| '_ \\ / _ \\/ _` / __| __|\n"
            + "| |_) |  __/ (_| \\__ \\ |_ \n"
            + "|_.__/ \\___|\\__,_|___/\\__|\n";

    public Ui() {
        sc = new Scanner(System.in);
        responseOutput = new StringBuilder();
    }

    public void addToResponseOutput(String response) {
        responseOutput.append(response);
    }

    /**
     * Returns the string that has been built by the responseOutput StringBuilder, and clears the StringBuilder
     * @return the response output that has been built by the responseOutput StringBuilder
     */
    public String returnResponseOutput() {
        String response = responseOutput.toString();
        responseOutput.setLength(0);
        return response;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String showLine() {
        return line;
    }

    public String showLoading() {
        return "Loading data from storage...\n";
    }

    public String showLoadingSuccess() {
        return "Data loaded successfully!\n";
    }

    public String showLoadingError() {
        return "Failed to load data!\n";
    }

    public String showDone(Task task) {
        return "Good job! You've just completed this task:\n" + "\t" + task + "\n";
    }

    public String showDeleted(Task task) {
        return "Got it! I've removed this task for you:\n\t" + task + "\n";
    }

    public String showNumOfTasks(int numOfTasks) {
        return "You now have " + numOfTasks + " tasks in total." + "\n";
    }

    public String showAdded(Task task) {
        return "One more task added to the hustle:\n\t" + task + "\n";
    }

    public String showWelcome() {
        String greeting = logo + "\nWelcome to ToDoBeast, your best productivity task tracker tool!\n"
                + "Let's get this bread! How would you like to be productive today?\n\n"
                + "[Type \"instructions\" to display instructions]\n";
        return greeting;
    }

    public String listTasks() {
        return "Here are your tasks:\n\n";
    }

    public String findTasks() {
        return "Here are the matching tasks in your list:\n\n";
    }

    public String printTaskList(List<Task> taskList) {
        StringBuilder output = new StringBuilder();
        int count = 1;
        for (Task task : taskList) {
            output.append(count++).append(". ").append(task).append("\n");
        }
        return output.toString();
    }

    public String showInstructions() {
        String instructions = "List of commands for ToDoBeast:\n\n"
                + "\t1. bye/exit - to quit ToDoBeast\n"
                + "\t2. list - lists all the tasks that have been stored in ToDoBeast\n"
                + "\t3. delete, [index] - deletes the task with the corresponding [index]\n"
                + "\t4. done, [index] - marks the task with the corresponding [index] as done\n"
                + "\t5. find, [regex] - finds all tasks with the specified [regex]\n"
                + "\t6. todo, [task description] - creates a to-do task with the specified [task description]\n"
                + "\t7. deadline, [task description], by YYYY-MM-DD HH:MM "
                + "- creates a deadline task with the "
                + "specified [task description], date and time\n"
                + "\t8. event, [task description], at YYYY-MM-DD HH:MM "
                + "- creates an event task with the specified "
                + "[task description], date and time\n";
        return instructions;
    }

    public String showError(String error) {
        return error;
    }

    public String showExit() {
        String exitMsg = "This app may have stopped but the grind never stops.\n"
                + "\tSee you again soon!\n";
        return exitMsg;
    }
}
