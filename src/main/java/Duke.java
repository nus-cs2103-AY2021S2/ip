import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();

    static void display(String str) {
        int longest = 0;
        String[] strings = str.split("\n");
        for (String s : strings) if (s.length() > longest) longest = s.length();
        String topBorder = "    ╭" + "-".repeat(longest + 2) + "╮\n";
        String botBorder = "    ╰" + "-".repeat(longest + 2) + "╯\n";
        StringBuilder mainText = new StringBuilder();
        for (String s : strings) mainText.append("    | " + s + " ".repeat(longest - s.length() + 1) + "|\n");
        System.out.println(topBorder + mainText + botBorder);
    }

    static void displayAllTasks() {
        StringBuilder sb = new StringBuilder();
        if (tasks.size() == 0) {
            sb.append("You don't have anything on your menu at the moment!\n\n" +
                    "If you would like to add a TODO, type\n" +
                    "    todo <orderName>\n\n" +
                    "If you would like to add a DEADLINE, type\n" +
                    "    deadline <orderName> /by <date/time>\n\n" +
                    "If you would like to add an EVENT, type\n" +
                    "    event <orderName> /at <date/time>\n\n");
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

    static void displayAddedTask(Task task) {
        display("Cool! I've added the following item to your order list.\n\n    "
                + task
                + "\nYou now have "
                + tasks.size()
                + " order(s)!");
    }

    static void displayRemovedTask(Task task) {
        display("Aw man... I told Ronald that was a bad item to put on the menu.\n"
                + "Here you go, I've removed this item from your order list!\n\n    " + task +
                "\nYou have " + tasks.size() + " order(s) left!");
    }

    private static void displayDone(Task task) {
        display("Tada! Your order has been served!\n\n  " + task);
    }

    private static void displayWelcome() {
        display(
                "  __  __      _____                    _     _     \n" +
                        " |  \\/  |    |  __ \\                  | |   | |    \n" +
                        " | \\  / | ___| |  | | ___  _ __   __ _| | __| |___ \n" +
                        " | |\\/| |/ __| |  | |/ _ \\| '_ \\ / _` | |/ _` / __|\n" +
                        " | |  | | (__| |__| | (_) | | | | (_| | | (_| \\__ \\\n" +
                        " |_|  |_|\\___|_____/ \\___/|_| |_|\\__,_|_|\\__,_|___/\n" +
                        "                                                   \n"
                        + "BALAPALAPA~ Welcome to McDonalds!\n"
                        + "I'm McSpicy, the best burger ever!\n"
                        + "What can I do for you today?");
    }

    private static void displayFarewell() {
        display("Thanks for patronising McDonalds!\nWe hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        displayWelcome();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            try {
                respond(input);
            } catch (DukeException e) {
                display(e.getMessage());
            }
        }
        displayFarewell();
        sc.close();
    }

    public static void respond(String input) throws DukeException {
        String[] command = input.split(" ");
        Task task;
        if (command[0].equals("list")) {
            displayAllTasks();
        } else if (command[0].equals("done")) {
            if (command.length > 2)
                throw new DukeException("We can only serve one order at a time...\nTry again!");
            try {
                Task toMarkDone = tasks.get(Integer.parseInt(command[1]) - 1);
                toMarkDone.markDone();
                displayDone(toMarkDone);
            } catch (Exception e) {
                throw new DukeException("Oops! That doesn't seem like a valid order number...\nTry again!");
            }
        } else if (command[0].equals("todo")) {
            if (command.length == 1)
                throw new DukeException("I can't add an item with no name to your orders!\nTry again!");
            task = new Todo(input);
            tasks.add(task);
            displayAddedTask(task);
        } else if (command[0].equals("deadline")) {
            if (!input.contains("/by"))
                throw new DukeException("Hmm.. When do you want this order to be served by?\nTry again!");
            task = new Deadline(input);
            tasks.add(task);
            displayAddedTask(task);
        } else if (command[0].equals("event")) {
            if (!input.contains("/at"))
                throw new DukeException("Hmm.. When do you want this order to be served?\nTry again!");
            task = new Event(input);
            tasks.add(task);
            displayAddedTask(task);
        } else if (command[0].equals("delete")) {
            if (command.length > 2)
                throw new DukeException("I can only delete one order at a time...\nTry again!");
            try {
                task = tasks.remove(Integer.parseInt(command[1]) - 1);
                displayRemovedTask(task);
            } catch (Exception e) {
                throw new DukeException("Oops! That doesn't seem like a valid order number...\nTry again!");
            }
        } else {
            throw new DukeException("Hmm... That doesn't look like an item in our menu...");
        }
    }
}

