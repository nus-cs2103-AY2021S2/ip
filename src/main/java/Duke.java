import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String REPLY_OUTLINE = "    ____________________________________________________________";
    private static final String REPLY_INDENTATION = "     ";

    private static List<Task> tasks = new ArrayList<>();

    public static void reply(String msg) {
        System.out.println(REPLY_OUTLINE + "\n" + msg + REPLY_OUTLINE + "\n");
    }

    public static void greet() {
        String msg = REPLY_INDENTATION + "Hello! I'm Duke\n"
                + REPLY_INDENTATION + "What can I do for you?\n";
        reply(msg);
    }

    public static void add(String input) {
        tasks.add(new Task(input));
        reply(REPLY_INDENTATION + "added: " + input + "\n");
    }

    public static void addTodo(String input) {
        String description = input.split(" ", 2)[1];
        Task todo = new Todo(description);
        tasks.add(todo);

        String msg = REPLY_INDENTATION + "Got it. I've added this task:\n"
                + REPLY_INDENTATION +  "  " + todo + "\n"
                + REPLY_INDENTATION +  "Now you have " + tasks.size() + " tasks in the list.\n";
        reply(msg);
    }

    public static void done(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;

        try {
            tasks.get(index).markDone();

            String msg = REPLY_INDENTATION + "Nice! I've marked this task as done:\n"
                    + REPLY_INDENTATION + "  " + tasks.get(index) + "\n";
            reply(msg);
        } catch (IndexOutOfBoundsException exception) {
            reply(REPLY_INDENTATION + "Sorry, I was not able to find the task.\n");
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
        reply(REPLY_INDENTATION + "Bye. Hope to see you again soon!");
    }

    public static boolean readInput(Scanner sc) {
        String input = sc.nextLine();

        switch (input) {
            case "bye":
                exit();
                return false;
            case "list":
                list();
                break;
            default:
                if (input.matches("done \\d+")) {
                    done(input);
                } else if (input.matches("todo .+")) {
                    addTodo(input);
                } else {
                    add(input);
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
