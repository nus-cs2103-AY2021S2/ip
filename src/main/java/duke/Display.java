package duke;

import java.util.ArrayList;

public class Display {
    private static void display(String str) {
        int longest = 0;
        String[] strings = str.split("\n");
        for (String s : strings) {
            if (s.length() > longest) {
                longest = s.length();
            }
        }
        String topBorder = "    ╭" + "-".repeat(longest + 2) + "╮\n";
        String botBorder = "    ╰" + "-".repeat(longest + 2) + "╯\n";
        StringBuilder mainText = new StringBuilder();
        for (String s : strings) {
            mainText.append("    | " + s + " ".repeat(longest - s.length() + 1) + "|\n");
        }
        System.out.println(topBorder + mainText + botBorder);
    }

    protected static void displayWelcome() {
        display(
                "  __  __      _____                    _     _     \n" +
                        " |  \\/  |    |  __ \\                  | |   | |    \n" +
                        " | \\  / | ___| |  | | ___  _ __   __ _| | __| |___ \n" +
                        " | |\\/| |/ __| |  | |/ _ \\| '_ \\ / _` | |/ _` / __|\n" +
                        " | |  | | (__| |__| | (_) | | | | (_| | | (_| \\__ \\\n" +
                        " |_|  |_|\\___|_____/ \\___/|_| |_|\\__,_|_|\\__,_|___/\n" +
                        "                                                   \n"
                        + "Welcome to McDonalds!\n"
                        + "I'm Ronald, the best McSpicy ever!\n"
                        + "What can I do for you today?");
    }

    protected static void displayFarewell() {
        display("Thanks for patronising McDonalds!\nWe hope to see you again soon!");
    }

    protected static void displayAllTasks() {
        ArrayList<Task> tasks = Duke.tasks;
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("You don't have anything on your menu at the moment!\n\n" +
                    "If you would like to add a TODO, type\n" +
                    "    todo <orderName>\n\n" +
                    "If you would like to add a DEADLINE, type\n" +
                    "    deadline <orderName> /by <dd/MM/yyyy HHmm>\n\n" +
                    "If you would like to add an EVENT, type\n" +
                    "    event <orderName> /at <dd/MM/yyyy HHmm>\n\n");
        } else {
            sb.append("Here's what you've ordered so far:\n\n");
            for (int i = 1; i <= tasks.size(); i++) {
                String formattedTask = i + ". " + tasks.get(i - 1).toString();
                sb.append(formattedTask);
            }
            sb.append("\nIf you would like to remove an item from your menu, type\n" +
                    "    delete <orderNumber>\n\n" +
                    "If you would like to mark an order as complete, type\n" +
                    "    done <orderNumber>");
        }
        display(sb.toString());
    }

    protected static void displayAddedTask(Task task) {

        display("Cool! I've added the following item to your order list.\n\n    "
                + task
                + "\nYou now have "
                + Duke.tasks.size()
                + " order(s)!");
    }

    protected static void displayRemovedTask(Task task) {
        display("Aw man... I told Donald that was a bad item to put on the menu.\n"
                + "Here you go, I've removed this item from your order list!\n\n    " + task +
                "\nYou have " + Duke.tasks.size() + " order(s) left!");
    }

    protected static void displayDone(Task task) {
        display("Tada! Your order has been served!\n\n  " + task);
    }

    protected static void displayError(String msg) {
        display(msg);
    }
}
