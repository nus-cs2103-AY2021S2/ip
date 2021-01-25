import java.util.ArrayList;

class {

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
}