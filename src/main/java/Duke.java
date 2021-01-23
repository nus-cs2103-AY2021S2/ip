import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

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

    private static void display(String str) {
        int longest = 0;
        String[] strings = str.split("\n");
        for (String s : strings) if (s.length() > longest) longest = s.length();
        String topBorder = "    ╭" + "-".repeat(longest + 2) + "╮\n";
        String botBorder = "    ╰" + "-".repeat(longest + 2) + "╯\n";
        StringBuilder mainText = new StringBuilder();
        for (String s : strings) mainText.append("    | " + s + " ".repeat(longest - s.length() + 1) + "|\n");
        System.out.println(topBorder + mainText + botBorder);
    }

    private static void displayAllTasks() {
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

    private static void displayAddedTask(Task task) {
        display("Cool! I've added the following item to your order list.\n\n    "
                + task
                + "\nYou now have "
                + tasks.size()
                + " order(s)!");
    }

    private static void displayRemovedTask(Task task) {
        display("Aw man... I told Ronald that was a bad item to put on the menu.\n"
                + "Here you go, I've removed this item from your order list!\n\n    " + task +
                "\nYou have " + tasks.size() + " order(s) left!");
    }

    private static void displayDone(Task task) {
        display("Tada! Your order has been served!\n\n  " + task);
    }

    private static void addTask(CommandType type, String[] command) throws DukeException {
        if (command.length == 1) throw new DukeException("I can't add an empty menu item!");
        switch (type) {
            case TODO:
                addTodo(command[1]);
                break;
            case DEADLINE:
                addDeadline(command[1]);
                break;
            case EVENT:
                addEvent(command[1]);
                break;
        }
    }

    private static void addTodo(String desc) {
        Task task = new Todo(desc);
        tasks.add(task);
        displayAddedTask(task);
    }

    private static void addDeadline(String desc) throws DukeException {
        if (!desc.contains("/by"))
            throw new DukeException("Hmm.. When do you want this order to be served by?\nTry again!");
        String description = desc.split(" /by ", 2)[0];
        String date = desc.split(" /by ", 2)[1];
        Task task = new Deadline(description, date);
        tasks.add(task);
        displayAddedTask(task);
    }

    private static void addEvent(String desc) throws DukeException {
        if (!desc.contains("/at"))
            throw new DukeException("Hmm.. When do you want this order to be served?\nTry again!");
        String description = desc.split(" /at ", 2)[0];
        String date = desc.split(" /at ", 2)[1];
        Task task = new Event(description, date);
        tasks.add(task);
        displayAddedTask(task);
    }

    private static void markDone(String[] command) throws DukeException {
        if (command.length > 2) throw new DukeException("We can't serve more than 1 order at a time...\nTry again!");
        try {
            Task toMarkDone = tasks.get(Integer.parseInt(command[1]) - 1);
            toMarkDone.markDone();
            displayDone(toMarkDone);
        } catch (Exception e) {
            throw new DukeException("Oops! That doesn't seem like a valid order number...\nTry again!");
        }
    }

    private static void deleteTask(String[] command) throws DukeException {
        try {
            Task task = tasks.remove(Integer.parseInt(command[1]) - 1);
            displayRemovedTask(task);
        } catch (Exception e) {
            throw new DukeException("Oops! That doesn't seem like a valid order number...\nTry again!");
        }
    }

    private static CommandType getCommandType(String command) throws DukeException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (Exception e) {
            throw new DukeException("That doesn't seem to be an item on our menu...\nTry again!");
        }
    }

    public static void handleInput() {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            String[] command = input.split(" ", 2);

            try {
                CommandType type = getCommandType(command[0]);
                switch (type) {
                    case LIST:
                        displayAllTasks();
                        break;
                    case DONE:
                        markDone(command);
                        break;
                    case TODO:
                    case EVENT:
                    case DEADLINE:
                        addTask(type, command);
                        break;
                    case DELETE:
                        deleteTask(command);
                        break;
                    default:
                        throw new DukeException("Hmm... That doesn't seem to be an item in our menu...\nTry again!");
                }
            } catch (DukeException e) {
                display(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        displayWelcome();
        handleInput();
        displayFarewell();
        sc.close();
    }
}

