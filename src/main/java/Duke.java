import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke is a project that eventually builds into a personal assistant chat bot.
 */
public class Duke {

    //tracks all tasks
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

        while (sc.hasNextLine()) {
            System.out.println("------------------------------------");

            input = sc.nextLine();

            //program exits on bye
            if (input.equals("bye")) {
                exit();
            //program shows entered tasks on list
            } else if (input.equals("list")) {
                list();
            //program marks task as complete on done
            } else if (input.startsWith("done")) {
                done(input);
            //program adds task
            } else if (input.startsWith("todo")
                || input.startsWith("deadline")
                || input.startsWith("event"))
            {
                add(input);
            //program echoes invalid instructions otherwise
            } else {
                echo(input);
            }

            System.out.println("------------------------------------");
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
     * Prints and informs invalid input given by the user.
     * @param input input provided by user
     */
    public static void echo(String input) {
        System.out.println("Sorry, I do not recognise the following instruction:\n" + input);
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("Bye! See you later :D");
    }

    /**
     * Parses, adds and prints the input from user as task.
     * @param input input provided by user
     */
    public static void add(String input) {
        //split input on first space to retrieve task type
        String[] parsedString = input.split("\\s+", 2);
        Task task;

        //attempt to create a task, inform user if input is invalid
        try {
            String taskType = parsedString[0];
            String taskDetails = parsedString[1];

            String taskName;
            String[] parsedTaskDetails;
            switch (taskType) {
                case "todo":
                    taskName = taskDetails.trim();
                    task = new ToDo(Task.numTasks + 1, taskName, "incomplete");
                    break;
                case "deadline":
                    parsedTaskDetails = taskDetails.split("/by");
                    taskName = parsedTaskDetails[0].trim();
                    String endDate = parsedTaskDetails[1].trim();
                    task = new Deadline(Task.numTasks + 1, taskName, "incomplete", endDate);
                    break;
                case "event":
                    parsedTaskDetails = taskDetails.split("/at");
                    taskName = parsedTaskDetails[0].trim();
                    String startEndDate = parsedTaskDetails[1].trim();
                    task = new Event(Task.numTasks + 1, taskName, "incomplete", startEndDate);
                    break;
                default:
                    System.out.println("Invalid task input!");
                    return;
            }
            tasks.add(task);
            System.out.print("Got it! I have added this task:\n" + task + "\nYou have " + Task.numTasks + " task(s) now!\n");
        } catch (Exception e) {
            System.out.println("Invalid task input!");
        }
    }

    /**
     * List all tasks entered by user.
     */
    public static void list() {
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            System.out.println(i + "." + task);
        }
    }

    /**
     * Checks and marks given task as done and informs user of success/failure.
     * @param input input provided by user
     */
    public static void done(String input) {
        String[] parsedString = input.split("\\s+");
        try {
            int taskId = Integer.parseInt(parsedString[1]);
            Task task = tasks.get(taskId - 1);
            if (task.getStatus().equals("complete")) {
                System.out.println("Task is already completed!");
            } else {
                task.markCompleted();
                System.out.println("Yay your task is done! :D");
            }
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}
