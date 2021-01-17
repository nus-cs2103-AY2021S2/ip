import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String REPLY_OUTLINE = "    ____________________________________________________________";
    private static final String REPLY_INDENTATION = "     ";

    private static List<String> tasks = new ArrayList<>();

    public static void greet() {
        System.out.println(REPLY_OUTLINE);
        System.out.println(REPLY_INDENTATION + "Hello! I'm Duke");
        System.out.println(REPLY_INDENTATION + "What can I do for you?");
        System.out.println(REPLY_OUTLINE + "\n");
    }

    public static void add(String input) {
        tasks.add(input);
        System.out.println(REPLY_OUTLINE);
        System.out.println(REPLY_INDENTATION + "added: " + input);
        System.out.println(REPLY_OUTLINE + "\n");
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
                add(input);
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
