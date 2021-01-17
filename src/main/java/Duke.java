import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String REPLY_OUTLINE = "    ____________________________________________________________";
    private static final String REPLY_INDENTATION = "     ";

    private static List<Task> tasks = new ArrayList<>();

    public static void greet() {
        String reply = REPLY_OUTLINE + "\n"
                + REPLY_INDENTATION + "Hello! I'm Duke\n"
                + REPLY_INDENTATION + "What can I do for you?\n"
                + REPLY_OUTLINE + "\n";
        System.out.println(reply);
    }

    public static void add(String input) {
        tasks.add(new Task(input));
        System.out.println(REPLY_OUTLINE);
        System.out.println(REPLY_INDENTATION + "added: " + input);
        System.out.println(REPLY_OUTLINE + "\n");
    }

    public static void done(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;

        try {
            tasks.get(index).markDone();

            System.out.println(REPLY_OUTLINE);
            System.out.println(REPLY_INDENTATION + "Nice! I've marked this task as done:");
            System.out.println(REPLY_INDENTATION + "  " + tasks.get(index));
            System.out.println(REPLY_OUTLINE + "\n");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(REPLY_OUTLINE);
            System.out.println(REPLY_INDENTATION + "Sorry, I was not able to find the task.");
            System.out.println(REPLY_OUTLINE + "\n");
        }


    }

    public static void list() {
        System.out.println(REPLY_OUTLINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(REPLY_INDENTATION + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(REPLY_OUTLINE + "\n");
    }

    public static void exit() {
        System.out.println(REPLY_OUTLINE);
        System.out.println(REPLY_INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(REPLY_OUTLINE + "\n");
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
