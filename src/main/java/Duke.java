import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke is a project that eventually builds into a personal assistant chat bot.
 */
public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Entry point of the program, first greets then listens for input from user.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        greet();
        listenInput();
    }

    /**
     * Listens for input from the user.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public static void listenInput() {
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("------------------------------------");

            input = sc.nextLine();

            System.out.println("------------------------------------");

            //program exits on bye
            if (input.equals("bye")) {
                exit();
            //program shows entered tasks on list
            } else if (input.equals("list")) {
                list();
            //program marks task as complete on done
            } else if (input.contains("done")) {
                done(input);
            //program adds task and echoes otherwise
            } else {
                add(input);
                echo(input);
            }
        }
    }

    /**
     * Greets the user upon program launch.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    /**
     * Prints the input given by the user.
     * @param input input provided by user
     */
    public static void echo(String input) {
        System.out.println(input);
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("Bye! See you later :D");
    }

    /**
     * Add the input from user as task and prints "added: ".
     * @param input input provided by user
     */
    public static void add(String input) {
        Task task = new Task(Task.numTasks + 1, input, "incomplete");
        tasks.add(task);
        System.out.print("added: ");
    }

    /**
     * List all tasks entered by user.
     */
    public static void list() {
        for (Task task: tasks) {
            if (task.getStatus().equals("incomplete")) {
                System.out.println(task.getId() + ".[ ] " + task.getTaskName());
            } else {
                System.out.println(task.getId() + ".[X] " + task.getTaskName());
            }
        }
    }

    /**
     * Mark given task as done and informs user of success/failure.
     * @param input input provided by user
     */
    public static void done(String input) {
        String[] parsedString = input.split("\\s+");
        int taskId;
        try {
            taskId = Integer.parseInt(parsedString[1]);
            Task task = tasks.get(taskId - 1);
            task.markCompleted();
            System.out.println("Yay your task is done! :D");
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }

}
