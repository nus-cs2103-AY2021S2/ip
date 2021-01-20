import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    /**
     * Number of tasks added by the user.
     */
    private static int count;

    /**
     * List of tasks added by the user.
     */
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Prints welcome message for the user.
     */
    public static void printWelcomeMessage() {
        String logo =
                "     ______\n" +
                "    |___  /\n" +
                "       / / \n" +
                "      / /  \n" +
                "     / /__ \n" +
                "    /_____|\n";
        System.out.println("\n~ Hello! I am Zee :) ~\n"
                + logo + "\n"
                + "~ What can I do for you today? ~\n");
    }

    /**
     * Lists out all the tasks that have been input by the user
     * along with the task type (deadline, todo or event) and
     * whether the task has been completed.
     */
    public static void printList() {
        int listCount = 1;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("  " + listCount + "." + task.toString());
            listCount++;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    /**
     * Marks the task at a particular index as done.
     * @param index index of the task to be removed.
     */
    public static void markDone(int index) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        tasks.get(index).markAsDone();
        System.out.println("Good job, I have marked this task as done!");
        System.out.println(tasks.get(index).toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    /**
     * Adds a todo task to the list.
     * @param command command input by the user
     *                which contains the name of
     *                the task.
     * @throws InvalidTodoException If Todo task does not
     *                              have a description
     */
    public static void addTodo(String command) throws InvalidTodoException {
        if(command.length() < 6) {
            throw new InvalidTodoException();
        }
        tasks.add(new Todo(command.substring(5)));
        count++;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count - 1).toString());
        System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    /**
     * Adds a deadline to the list.
     * @param command command input by the user
     *                which contains the name of
     *                the task and the deadline
     *                to submit it by.
     */
    public static void addDeadline(String command) {
        tasks.add(new Deadline(command.substring(9).split("/")[0], command.substring(9).split("/")[1].substring(3)));
        count++;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count - 1).toString());
        System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    /**
     * Adds a deadline to the list.
     * @param command command input by the user
     *                which contains the name of
     *                the event and the date and time.
     */
    public static void addEvent(String command) {
        tasks.add(new Event(command.substring(6).split("/")[0], command.substring(6).split("/")[1].substring(3)));
        count++;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count - 1).toString());
        System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    /**
     *
     * @param command command input by the user.
     * @throws InvalidCommandException if the command cannot be recognised.
     */
    public static void runCommand(String command) throws InvalidCommandException{
        if (command.equals("list")) {
            printList();
        } else if (command.split(" ")[0].equals("done")) {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            markDone(index);
        } else if (command.split(" ")[0].equals("todo")) {
            try {
                addTodo(command);
            } catch (InvalidTodoException e) {
                System.out.println("\nOOPS!!! The description of a todo cannot be empty.\n");
            }
        } else if (command.split(" ")[0].equals("deadline")) {
            addDeadline(command);
        } else if (command.split(" ")[0].equals("event")) {
            addEvent(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            try {
                runCommand(command);
            } catch(InvalidCommandException e) {
                System.out.println("\nI'm sorry, but I don't know what that means :-(\n");
            }
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("\n~ Bye! Come back soon! UwU ~\n");
    }
}