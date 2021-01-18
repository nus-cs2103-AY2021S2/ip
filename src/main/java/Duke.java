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
     * Prints the newly added task details along with the new size of the task list
     *
     * @param tasks A list of Task objects
     * @param task  An instance of Task representing the newly added task
     */
    public static void printAddedTask(ArrayList<Task> tasks, Task task) {
        System.out.println(FORMAT_LINE);
        System.out.println("Got it. I've added this task to your list:\n" +
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
        System.out.println(" Good job! I've marked this task as done:\n" +
                "   " + task.toString());
        System.out.println(FORMAT_LINE);

    }

    /**
     * Deletes the indicated task from the task list
     *
     * @param tasks A list of Task objects
     * @param index An integer representing the index of the task to be deleted
     */
    public static void deleteTask(ArrayList<Task> tasks, int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println(FORMAT_LINE);
        System.out.println(" Noted. I've removed this task:\n" +
                "   " + task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
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

            try {
                System.out.println("\n>>> " + input);
                if (input.equals("list")) {
                    listTasks(tasks);
                } else if (input.startsWith("done")) {
                    int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                    if (index > tasks.size() || index <= 0) {
                        throw new DukeException("The list item number provided is invalid");
                    }
                    markAsDone(tasks, index - 1);
                } else if (input.startsWith("todo")) {
                    String description = input.replace("todo", "");
                    ToDo todo = new ToDo(description);
                    if (description.strip().equals("")) {
                        throw new DukeException("todo description cannot be empty");
                    }
                    tasks.add(todo);
                    printAddedTask(tasks, todo);
                } else if (input.startsWith("deadline")) {
                    if (input.strip().equals("deadline")) {
                        throw new DukeException("deadline description cannot be empty");
                    }
                    if (!input.contains("/by")) {
                        throw new DukeException("Wrong command format: Missing `/by`");
                    }
                    if (input.split("/by").length < 2) {
                        throw new DukeException("deadline date/time cannot be empty");
                    }
                    String description = input.split("/by", 2)[0].replace("deadline", "");
                    String date = input.split("/by", 2)[1].strip();
                    Deadline deadline = new Deadline(description, date);
                    tasks.add(deadline);
                    printAddedTask(tasks, deadline);
                } else if (input.startsWith("event")) {

                    if (input.strip().equals("event")) {
                        throw new DukeException("event description cannot be empty");
                    }
                    if (!input.contains("/at")) {
                        throw new DukeException("Wrong command format: Missing `/at`");
                    }
                    if (input.split("/at").length < 2) {
                        throw new DukeException("event date/time cannot be empty");
                    }
                    String description = input.split("/at", 2)[0].replace("event", "");
                    String date = input.split("/at", 2)[1].strip();

                    Event event = new Event(description, date);
                    tasks.add(event);
                    printAddedTask(tasks, event);

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                    if (index > tasks.size() || index <= 0) {
                        throw new DukeException("The list item number provided is invalid");
                    }
                    deleteTask(tasks, index - 1);

                } else if (input.equals("bye")) {
                    farewellUser();
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }


    }
}
