package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Responsible for methods setting and sending appropriate responses to GUI.
 */
public class Ui {
    private static String response;

    /**
     * Returns welcome message.
     *
     * @return welcome message at the start of application.
     */
    public static String welcomeString() {
        return "Hi! I'm Ronald the McSpicy.\n"
                + "What can I do for you today?\n\n"
                + "(Type [help] to see what I can do.)";
    }

    public static void displayHelp() {
        String str = "If you would like to add a TODO, type\n"
                + "[todo <orderName>]\n\n"
                + "If you would like to add a DEADLINE, type\n"
                + "[deadline <orderName> /by <dd/MM/yyyy HHmm>]\n\n"
                + "If you would like to add an EVENT, type\n"
                + "[event <orderName> /at <dd/MM/yyyy HHmm>]\n\n"
                + "If you would like to remove an item from your menu, type\n"
                + "[delete <orderNumber>] or [delete <start>-<end>]\n\n"
                + "If you would like to mark an order as complete, type\n"
                + "[done <orderNumber>] or [done <start>-<end>]\n\n"
                + "If you would like to find orders containing a particular keyword, type\n"
                + "[find <keyword>]\n";
        assignResponse(str);
    }

    /**
     * Prints farewell message.
     */
    public static void displayFarewell() {
        String str = "Thanks for coming, we hope to see you again!\n"
                + "We will be closing shortly...";
        assignResponse(str);
    }

    /**
     * Prints all tasks.
     *
     * @param tasks tasks to be printed.
     */
    public static void displayAllTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("You don't have anything on your menu at the moment!");
        } else {
            sb.append("Here's what you've ordered so far:\n\n");
            for (int i = 1; i <= tasks.size(); i++) {
                String formattedTask = i + ". " + tasks.get(i - 1).toString();
                sb.append(formattedTask);
            }
        }
        assignResponse(sb.toString());
    }

    /**
     * Displays tasks matching a particular keyword that the user entered.
     *
     * @param tasks tasks which have descriptions containing the keyword.
     */
    public static void displayMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("I can't seem to find an order matching your keyword!");
        } else {
            sb.append("Here are the orders that match!\n\n");
            for (int i = 1; i <= tasks.size(); i++) {
                String formattedTask = i + ". " + tasks.get(i - 1).toString();
                sb.append(formattedTask);
            }
        }
        assignResponse(sb.toString());
    }

    /**
     * Prints added task.
     *
     * @param task  task that was added to the list of tasks.
     * @param tasks all existing tasks.
     */
    public static void displayAddedTask(Task task, ArrayList<Task> tasks) {
        String str = "Cool! I've added the following item to your order list.\n\n    "
                + task
                + "\nYou now have "
                + tasks.size()
                + " order(s)!";
        assignResponse(str);
    }

    /**
     * Prints deleted tasks.
     *
     * @param deletedTasks tasks that were deleted.
     * @param tasks        all existing tasks.
     */
    public static void displayDeletedTask(ArrayList<Task> deletedTasks, ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        sb.append("Here you go, I've removed these item(s) from your order list!\n\n");
        for (Task t : deletedTasks) {
            sb.append(t);
        }
        sb.append("\nYou have " + tasks.size() + " order(s) left!");
        assignResponse(sb.toString());
    }

    /**
     * Prints tasks that were marked as done.
     *
     * @param tasks task(s) that were marked as done.
     */
    public static void displayDone(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t);
        }
        assignResponse("Your order(s) have been served!\n\n" + sb);
    }

    /**
     * Prints error.
     *
     * @param msg error message.
     */
    public static void displayError(String msg) {
        assignResponse("Oops!\n" + msg);
    }

    private static void assignResponse(String s) {
        Ui.response = s;
    }

    public static String getNextResponse() {
        return Ui.response;
    }
}
