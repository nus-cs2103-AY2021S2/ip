import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String REPLY_OUTLINE = "    ____________________________________________________________";
    private static final String REPLY_INDENTATION = "     ";

    private static List<Task> tasks = new ArrayList<>();

    // Helper functions
    public static void reply(String msg) {
        System.out.println(REPLY_OUTLINE + "\n" + msg + REPLY_OUTLINE + "\n");
    }

    public static void addReply(Task task) {
        String msg = REPLY_INDENTATION + "Got it. I've added this task:\n"
                + REPLY_INDENTATION +  "  " + task + "\n"
                + REPLY_INDENTATION +  "Now you have " + tasks.size() + " tasks in the list.\n";
        reply(msg);
    }

    public static String removeCommand(String input, String command) {
        String[] parts = input.split(" ", 2);

        if (parts.length != 2) {
            throw new EmptyDescriptionException("☹ The description of a " + command + " cannot be empty.");
        } else {
            return parts[1];
        }
    }

    // Commands
    public static void greet() {
        String msg = REPLY_INDENTATION + "Hello! I'm Duke\n"
                + REPLY_INDENTATION + "What can I do for you?\n";
        reply(msg);
    }

    public static void addTodo(String input) {
        String description = removeCommand(input, "todo");
        Task todo = new Todo(description);
        tasks.add(todo);
        addReply(todo);
    }

    public static void addDeadline(String input) {
        String[] parts = removeCommand(input, "deadline").split("/by");

        if (parts.length != 2) {
            throw new InvalidTaskException("☹ You have provided an invalid deadline.");
        } else {
            Task deadline = new Deadline(parts[0], parts[1]);
            tasks.add(deadline);
            addReply(deadline);
        }
    }

    public static void addEvent(String input) {
        String[] parts = removeCommand(input, "event").split("/at");

        if (parts.length != 2) {
            throw new InvalidTaskException("☹ You have provided an invalid event.");
        } else {
            Task event = new Event(parts[0], parts[1]);
            tasks.add(event);
            addReply(event);
        }
    }

    public static void done(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;

        try {
            tasks.get(index).markDone();

            String msg = REPLY_INDENTATION + "Nice! I've marked this task as done:\n"
                    + REPLY_INDENTATION + "  " + tasks.get(index) + "\n";
            reply(msg);
        } catch (IndexOutOfBoundsException exception) {
            reply(REPLY_INDENTATION + "☹ Sorry, I was not able to find the task.\n");
        }
    }

    public static void list() {
        String msg = REPLY_INDENTATION + "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.size(); i++) {
            msg += REPLY_INDENTATION + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        reply(msg);
    }

    public static void exit() {
        reply(REPLY_INDENTATION + "Bye. Hope to see you again soon!\n");
    }

    public static boolean readInput(Scanner sc) {
        String input = sc.nextLine().trim();

        switch (input) {
            case "bye":
                exit();
                return false;
            case "list":
                list();
                break;
            default:
                try {
                    if (input.matches("done \\d+")) {
                        done(input);
                    } else if (input.matches("todo.*")) {
                        addTodo(input);
                    } else if (input.matches("deadline.*")) {
                        addDeadline(input);
                    } else if (input.matches("event.*")) {
                        addEvent(input);
                    } else {
                        reply(REPLY_INDENTATION + "☹ I'm sorry, but I don't know what that means :-(\n");
                    }
                } catch (EmptyDescriptionException | InvalidTaskException ex) {
                    reply(REPLY_INDENTATION + ex.getMessage() + "\n");
                }
                break;
        }

        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        Scanner sc = new Scanner(System.in);

        while (readInput(sc));
    }
}
