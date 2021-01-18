import java.util.ArrayList;
import java.util.Scanner;

/**
 * A personal task managing chatbot project.
 */
public class Duke {
    static final String FORMAT_LINE = "....................................................";

    /**
     * Greets the user
     */
    public static void greetUser() {
        System.out.println(FORMAT_LINE + "\nHey, " +
                "I am Duke.\nHow can I help you?\n" +
                FORMAT_LINE);
    }

    /**
     * Bids farewell to the user
     */
    public static void farewellUser() {
        System.out.println(FORMAT_LINE +
                "\nGoodbye and see you soon!\n" +
                FORMAT_LINE);
    }

    /**
     * Prints all the tasks in the task list
     *
     * @param tasks A list of Task objects
     */
    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println(FORMAT_LINE);
        System.out.println("You have the following task(s) in your list.");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + "." + task.toString());

        }
        System.out.println(FORMAT_LINE);

    }

    /**
     * Prints the newly added task deatails along with the new size of the task list
     *
     * @param tasks A list of Task objects
     * @param task  An instance of Task representing the newly added task
     */
    public static void printAddedTask(ArrayList<Task> tasks, Task task) {
        System.out.println(FORMAT_LINE);
        System.out.println("Got it. I've added this task to your list:  \n" +
                "   " + task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        System.out.println(FORMAT_LINE);


    }

    /**
     * Marks the indicated given task as done
     *
     * @param tasks A list of Task objects
     * @param index An integer representing the index of the task to be marked as done
     */
    public static void markAsDone(ArrayList<Task> tasks, int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println(FORMAT_LINE);
        System.out.println(" Good job! I've marked this task as done: \n" +
                "   " + task.toString());
        System.out.println(FORMAT_LINE);

    }

    /**
     * main method which runs the chatbot.
     *
     * @param args empty string array.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        greetUser();

        while (sc.hasNext()) {

            String input = sc.nextLine();

            if (input.equals("list")) {
                listTasks(tasks);
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                markAsDone(tasks, index - 1);
            } else if (input.startsWith("todo")) {
                ToDo todo = new ToDo(input.replace("todo", ""));
                tasks.add(todo);
                printAddedTask(tasks, todo);
            } else if (input.startsWith("deadline")) {
                String description = input.split("/by", 2)[0].replace("deadline", "");
                String date = input.split("/by", 2)[1].strip();
                Deadline deadline = new Deadline(description, date);
                tasks.add(deadline);
                printAddedTask(tasks, deadline);
            } else if (input.startsWith("event")) {
                String description = input.split("/at", 2)[0].replace("event", "");
                String date = input.split("/at", 2)[1].strip();
                Event event = new Event(description, date);
                tasks.add(event);
                printAddedTask(tasks, event);

            } else if (input.equals("bye")) {
                farewellUser();
                break;
            } else {
                System.out.println("I'm sorry, but I don't know what that means :-(");
            }

        }


    }
}
