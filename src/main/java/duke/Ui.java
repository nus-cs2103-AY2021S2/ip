package duke;

import duke.data.Data;
import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    private static void display(String str) {
        int longest = 0;
        String[] strings = str.split("\n");
        for (String s : strings) {
            if (s.length() > longest) {
                longest = s.length();
            }
        }
        String topBorder = "    ╭" + "-".repeat(longest + 4) + "╮\n"
                + "    |" + " ".repeat(longest + 4) + "|\n";
        String botBorder = "    |" + " ".repeat(longest + 4) + "|\n"
                + "    ╰" + "-".repeat(longest + 4) + "╯\n";
        StringBuilder mainText = new StringBuilder();
        for (String s : strings) {
            String toAppend = "    |  " + s + " ".repeat(longest - s.length() + 2) + "|\n";
            mainText.append(toAppend);
        }
        System.out.println(topBorder + mainText + botBorder);
    }

    protected static void displayWelcome() {
        display(
                "█▄ ▄█ ▄▀▀ █▀▄ ▄▀▄ █▄ █ ▄▀▄ █   █▀▄ ▄▀▀\n"
                        +"█ ▀ █ ▀▄▄ █▄▀ ▀▄▀ █ ▀█ █▀█ █▄▄ █▄▀ ▄██\n\n"
                        + "Welcome!\n"
                        + "I'm Ronald, the best McSpicy ever.\n"
                        + "What can I do for you today?");
    }

    protected static void displayFarewell() {
        display("█▄ ▄█ ▄▀▀ █▀▄ ▄▀▄ █▄ █ ▄▀▄ █   █▀▄ ▄▀▀\n"
                + "█ ▀ █ ▀▄▄ █▄▀ ▀▄▀ █ ▀█ █▀█ █▄▄ █▄▀ ▄██\n\n"
                + "Thanks for coming!\nWe hope to see you again!");
    }

    public static void displayAllTasks() {
        ArrayList<Task> tasks = Data.tasks;
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

    public static void displayAddedTask(Task task) {
        display("Cool! I've added the following item to your order list.\n\n    "
                + task
                + "\nYou now have "
                + Data.tasks.size()
                + " order(s)!");
    }

    public static void displayRemovedTask(Task task) {
        display("Aw man... I told Donald that was a bad item to put on the menu.\n"
                + "Here you go, I've removed this item from your order list!\n\n    " + task +
                "\nYou have " + Data.tasks.size() + " order(s) left!");
    }

    public static void displayDone(Task task) {
        display("Your order has been served!\n\n  " + task);
    }

    public static void displayError(String msg) {
        display("Oops!\n" + msg);
    }
}
