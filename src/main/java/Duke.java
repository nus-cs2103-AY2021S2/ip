import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String GREETING = "Hi, I am Sonia! How can I help you?";
    public static String CLOSING = "Goodbye!";

    public static void terminate() {
        echo(CLOSING);
        System.exit(0);
    }

    public static void addTodo(String[] args) {
        String task = String.join(" ", args);

        Task t = new Todo(task);
        tasks.add(t);

        echo("I added this to-do to the task list!");
        System.out.println("   " + t);
    }

    public static void addDeadline(String[] args) {
        String input = String.join(" ", args);
        String[] pair = input.split(" /by ");
        String name = pair[0];
        String timestamp = pair[1];

        Task t = new Deadline(name, timestamp);
        tasks.add(t);

        echo("I added this deadline to the task list!");
        System.out.println("   " + t);
    }

    public static void addEvent(String[] args) {
        String input = String.join(" ", args);
        String[] pair = input.split(" /at ");
        String name = pair[0];
        String timestamp = pair[1];

        Task t = new Event(name, timestamp);
        tasks.add(t);

        echo("I added this event to the task list!");
        System.out.println("   " + t);
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

    public static void completeTask(int id) {
        Task t = tasks.get(id - 1);
        t.markAsDone();
        echo("I marked the following task as done!");
        System.out.println("   " + t);
    }

    public static void parse(String[] input) {
        String command = input[0];
        String[] args = Arrays.copyOfRange(input, 1, input.length);

        if (command.equals("bye")) {
            terminate();
        } else if (command.equals("list")) {
            showTasks();
        } else if (command.equals("done")) {
            int id = Integer.parseInt(input[1]);
            completeTask(id);
        } else if (command.equals("todo")) {
            addTodo(args);
        } else if (command.equals("deadline")) {
            addDeadline(args);
        } else if (command.equals("event")) {
            addEvent(args);
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
