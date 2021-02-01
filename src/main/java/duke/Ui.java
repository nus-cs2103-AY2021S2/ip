package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Responsible for methods printing to CLI.
 */
public class Ui {
    private static String response;

    /**
     * Returns welcome message.
     * @return welcome message at the start of application
     */
    public static String welcomeString() {
        return "Welcome!\n"
                        + "I'm Ronald, the best McSpicy ever.\n"
                        + "What can I do for you today?";
    }

    /**
     * Prints farewell message.
     */
    public static void displayFarewell() {
        Ui.response = "Thanks for coming, we hope to see you again!\n"
                + "We will be closing shortly...";
    }

    /**
     * Prints all tasks.
     *
     * @param tasks tasks to be printed
     */
    public static void displayAllTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("You don't have anything on your menu at the moment!\n\n"
                    + "If you would like to add a TODO, type\n"
                    + "[todo <orderName>]\n\n"
                    + "If you would like to add a DEADLINE, type\n"
                    + "[deadline <orderName> /by <dd/MM/yyyy HHmm>]\n\n"
                    + "If you would like to add an EVENT, type\n"
                    + "[event <orderName> /at <dd/MM/yyyy HHmm>]\n\n");
        } else {
            sb.append("Here's what you've ordered so far:\n\n");
            for (int i = 1; i <= tasks.size(); i++) {
                String formattedTask = i + ". " + tasks.get(i - 1).toString();
                sb.append(formattedTask);
            }
            sb.append("\nIf you would like to remove an item from your menu, type\n"
                    + "[delete <orderNumber>]\n\n"
                    + "If you would like to mark an order as complete, type\n"
                    + "[done <orderNumber>]\n\n"
                    + "If you would like to find orders containing a particular keyword, type\n"
                    + "[find <keyword>]\n");
        }
        Ui.response = sb.toString();
    }

    /**
     * Displays tasks matching a particular keyword that the user entered.
     *
     * @param tasks tasks which have descriptions containing the keyword
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
        Ui.response = sb.toString();
    }

    /**
     * Prints added task.
     *
     * @param task  task that was added to the list of tasks
     * @param tasks all existing tasks
     */
    public static void displayAddedTask(Task task, ArrayList<Task> tasks) {
        String str = "Cool! I've added the following item to your order list.\n\n    "
                + task
                + "\nYou now have "
                + tasks.size()
                + " order(s)!";
        Ui.response = str;
    }

    /**
     * Prints deleted task.
     *
     * @param task  task that was deleted
     * @param tasks all existing tasks
     */
    public static void displayDeletedTask(Task task, ArrayList<Task> tasks) {
        String str = "Aw man... I told Donald that was a bad item to put on the menu.\n"
                + "Here you go, I've removed this item from your order list!\n\n    "
                + task
                + "\nYou have " + tasks.size() + " order(s) left!";
        Ui.response = str;
    }

    /**
     * Prints task that was marked as done.
     *
     * @param task task that was marked as done
     */
    public static void displayDone(Task task) {
        Ui.response = "Your order has been served!\n\n  " + task;
    }

    /**
     * Prints errors.
     *
     * @param msg error message
     */
    public static void displayError(String msg) {
        Ui.response ="Oops!\n" + msg;
    }

    public static String getNextResponse() {
        return Ui.response;
    }
}
