import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> tasks = new ArrayList<>();
    public static String GREETING = "Hi, I am Sonia! How can I help you?";

    public static void echo(String message) {
        System.out.println("Sonia: " + message);
    }

    public static void parse(String command) {
        if (command.equals("bye")) {
            echo("Goodbye!");
            System.exit(0);
        } else if (command.equals("list")) {
            echo("Here are your tasks!");
            prettyPrint(tasks);
        } else {
            tasks.add(command);
            echo("I added \"" + command + "\" to the task list!");
        }
    }

    public static void prettyPrint(ArrayList<String> tasks) {
        if (tasks.size() > 0) {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println("   " + i + ": " + tasks.get(i - 1));
            }
        } else {
            System.out.println("   Looks like you haven't added any tasks.");
        }
    }

    public static String prompt() {
        System.out.print("You: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void main(String[] args) {
        echo(GREETING);
        while (true) {
            String command = prompt();
            parse(command);
        }
    }
}
