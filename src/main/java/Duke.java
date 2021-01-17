import java.util.Scanner;

public class Duke {
    public static final String REPLY_OUTLINE = "    ____________________________________________________________";
    public static final String REPLY_INDENTATION = "     ";

    public static void greet() {
        System.out.println(REPLY_OUTLINE);
        System.out.println(REPLY_INDENTATION + "Hello! I'm Duke");
        System.out.println(REPLY_INDENTATION + "What can I do for you?");
        System.out.println(REPLY_OUTLINE + "\n");
    }

    public static void echo(String input) {
        System.out.println(REPLY_OUTLINE);
        System.out.println(REPLY_INDENTATION + input);
        System.out.println(REPLY_OUTLINE + "\n");
    }

    public static void exit() {
        System.out.println(REPLY_OUTLINE);
        System.out.println(REPLY_INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(REPLY_OUTLINE + "\n");
    }

    public static boolean readInput(Scanner sc) {
        String input = sc.nextLine();
        
        if (input.equals("bye")) {
            exit();
            return false;
        }

        echo(input);
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
