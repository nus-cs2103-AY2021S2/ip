import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();

    static void display(String str) {
        String[] strings = str.split("\n");
        System.out.println("    " + "___________________________________________________________________");
        for (String s : strings) {
            System.out.println("     " + s);
        }
        System.out.println("    " + "___________________________________________________________________");
    }

    static void displayAllTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here's what you've ordered so far:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            String formattedTask = i + ". " + tasks.get(i - 1).toString();
            sb.append(formattedTask);
        }
        display(sb.toString());
    }

    static void displayAddedTask(Task task) {
        display("Cool! I've added the following item to your order list.\n  "
                + task
                + "You now have "
                + tasks.size()
                + " order(s)!");
    }

    static void displayRemovedTask(Task task) {
        display("Aw man... I told Ronald that was a bad item to put on the menu. He didn't listen though.\n"
        +"Anyways... Here you go, I've removed this item from your order list!\n" + task +
                "You have " + tasks.size() + " order(s) left!");
    }

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);

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
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            try {
                respond(input);
            } catch (DukeException e) {
                display(e.getMessage());
            }
        }
        display("Thanks for patronising McDonalds!\nWe hope to see you again soon!");
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
                display("Tada! Your order has been served:\n  " + toMarkDone);
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
        } else {
            throw new DukeException("Hmm... That doesn't look like an item in our menu...");
        }
    }
}

