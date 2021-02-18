package todobeast;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import todobeast.tasks.Task;

/**
 * Handles User Interface interactions for the application. This includes reading of commands, as well as displaying
 * responses to the user. Note that the business logic of what to respond with is not included in here; this class
 * merely contains all the possible responses that the app can produce.
 */
public class Ui {
    private static final String line = "\t________________________________________________________________\n";
    private static final String logo = " _                    _   \n"
            + "| |                  | |  \n"
            + "| |__   ___  __ _ ___| |_ \n"
            + "| '_ \\ / _ \\/ _` / __| __|\n"
            + "| |_) |  __/ (_| \\__ \\ |_ \n"
            + "|_.__/ \\___|\\__,_|___/\\__|\n";

    private final Scanner sc;
    private StringBuilder responseOutput;

    /**
     * Constructor for Ui.
     */
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
        return "Good job! You've just completed this task:\n\t" + task + "\n";
    }

    public String showTaskNotesAdded(Task task) {
        return "I've added notes to this task:\n\t" + task + "\n";
    }

    public String showTaskNotesCleared(Task task) {
        return "I've cleared notes from this task:\n\t" + task + "\n";
    }

    public String showDeleted(Task task) {
        return "Got it! I've removed this task for you:\n\t" + task + "\n";
    }

    public String showNumOfTasks(int numOfTasks) {
        return "You now have " + numOfTasks + " tasks in total." + "\n";
    }

    /**
     * Returns a string representing the UI output for the task that has been added.
     * @param task the task that has been added.
     * @return a string representing the UI output for the task that has been added.
     */
    public String showAdded(Task task) {
        return "One more task added to the hustle:\n\t" + task + "\n";
    }

    /**
     * Returns a string with the welcome message.
     * @return a string with the welcome message.
     */
    public String showWelcome() {
        String greeting = logo + "\nWelcome to ToDoBeast, your best productivity task tracker tool!\n"
                + "Let's get this bread! How would you like to be productive today?\n\n"
                + "[Type \"instructions\" to display instructions]\n";
        return greeting;
    }

    public String listTasks() {
        return "Here are your tasks:\n\n";
    }

    /**
     * Returns a string representing all tasks that have been found in the task list.
     * @return a string representing all tasks that have been found in the task list.
     */
    public String findTasks() {
        return "Here are the matching tasks in your list:\n\n";
    }

    /**
     * Returns a string representing all tasks in the task list.
     * @param taskList the task list to be printed.
     * @return a string representing all tasks in the task list.
     */
    public String printTaskList(List<Task> taskList) {
        StringBuilder output = new StringBuilder();
        for (Task task : taskList) {
            int listIndex = taskList.indexOf(task) + 1;
            output.append(listIndex).append(". ").append(task).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns a string representing all filtered tasks in the map.
     * @param filteredMap the map to be printed.
     * @return a string representing all filtered tasks in the map.
     */
    public String printFilteredMap(Map<Integer, Task> filteredMap) {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<Integer, Task> entry : filteredMap.entrySet()) {
            int taskIndex = entry.getKey() + 1;
            Task task = entry.getValue();
            output.append("Task #").append(taskIndex).append(". ").append(task).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns a string representation of the instructions for ToDoBeast.
     * @return a string representation of the instructions for ToDoBeast.
     */
    public String showInstructions() {
        String instructions = "List of commands for ToDoBeast:\n\n"
                + "1. bye/exit - to quit ToDoBeast\n"
                + "2. list - lists all the tasks that have been stored in ToDoBeast\n"
                + "3. delete, [index] - deletes the task with the corresponding [index]\n"
                + "4. done, [index] - marks the task with the corresponding [index] as done\n"
                + "5. find, [regex] - finds all tasks with the specified [regex]\n"
                + "6. note/notes, [index], taskNotes - adds specified taskNotes to the task with corresponding "
                + "[index]\n"
                + "7. todo, [task description] - creates a to-do task with the specified [task description]\n"
                + "8. deadline, [task description], by YYYY-MM-DD HH:MM "
                + "- creates a deadline task with the "
                + "specified [task description], date and time\n"
                + "9. event, [task description], at YYYY-MM-DD HH:MM "
                + "- creates an event task with the specified "
                + "[task description], date and time\n";
        return instructions;
    }

    /**
     * Returns a string representation of an error.
     * @param error the error to be shown.
     * @return a string representation of an error.
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Returns a string representation of the exit message for ToDoBeast.
     * @return a string representation of the exit message for ToDoBeast.
     */
    public String showExit() {
        String exitMsg = "This app may have stopped but the grind never stops.\n"
                + "\tSee you again soon!\n";
        return exitMsg;
    }
}
