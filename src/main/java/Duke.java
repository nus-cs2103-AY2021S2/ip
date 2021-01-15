import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String GREETING = "Hi, I am Sonia! How can I help you?";
    public static String CLOSING = "Goodbye!";

    public static void terminate() {
        echo(CLOSING);
        System.exit(0);
    }

    public static void showTasks() {
        echo("Here are your tasks!");
        if (tasks.size() > 0) {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println("   " + i + ": " + tasks.get(i - 1));
            }
        } else {
            System.out.println("   Looks like you haven't added any tasks.");
        }
    }

    public static void addTask(String task) {
        Task t = new Task(task);
        tasks.add(t);
        echo("I added \"" + task + "\" to the task list!");
    }

    public static void parse(String[] input) {
        String command = input[0];

        if (command.equals("bye")) {
            terminate();
        } else if (command.equals("list")) {
            showTasks();
        } else {
            String task = String.join(" ", input);
            addTask(task);
        }
    }

    public static void echo(String message) {
        System.out.println("Sonia: " + message);
    }

    public static String[] prompt() {
        System.out.print("You: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().split(" ");
    }

    public static void main(String[] args) {
        echo(GREETING);
        while (true) {
            String[] input = prompt();
            parse(input);
        }
    }
}
