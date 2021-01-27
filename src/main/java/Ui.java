import java.util.ArrayList;

/**
 * Stores all the output and error messages printed by the program.
 */
public class Ui {
    /** Constants that help in the formatting of the printed messages. */
    private static final String SPACE = "     ";
    private static final String LINE = "\n     <<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>\n";

    /**
     * Message to be printed when the program starts.
     */
    public static void greet() {
        System.out.println(LINE + SPACE + "Heyyoo!! I am Luna :D\n" + SPACE + "What can I do for you today?" + LINE);
    }

    /**
     * Message to printed if any number that has to be inputted is invalid.
     * @return
     */
    public static String  InvalidNumberExceptionMessage() {
        return LINE + SPACE + "ERROR! D: Please give a number greater than zero and smaller than the total number of tasks" + LINE;
    }

    /**
     * Message to be printed if the input format for a specific task is wrong.
     * @param task The type of task that had the wrong format.
     * @return The error message string.
     */
    public static String  InvalidTaskFormatExceptionMessage(String task) {
        return LINE + SPACE + "ERROR! D: The format for the following task is wrong: " + task + LINE;
    }

    /**
     * Message to be printed after a Task has been added.
     * @param keyword The type of Task added.
     * @param task The actual Task object that has been created with the user input.
     */
    public static void outputMessageTask(String keyword, Task task) {
        System.out.println(LINE + SPACE + "Done adding the " + keyword + " Task: " + task + LINE);
    }

    /**
     * Message to be printed after a task is done.
     * @param task The Task object that has been created with the user input.
     */
    public static void outputMessageDone(Task task) {
        System.out.println(LINE + SPACE + "Good job! Another Task completed! I have marked it as done:\n" + SPACE + task + LINE);
    }

    /**
     * Message to be printed after a task is deleted.
     * @param task The task object that has been deleted according to the user input.
     */
    public static void outputMessageDelete(Task task) {
        System.out.println(LINE + SPACE + "Deleted the following task: " + task + LINE);
    }

    /**
     * Lists all the tasks that are currently stored.
     * @param storage Array List of tasks.
     * @param count Number of tasks in the Array List.
     */
    public static void outputMessageList(ArrayList<Task> storage, int count) {
        System.out.println(LINE);
        for (int i = 1; i <= count; i++) {
            System.out.println(SPACE + i + ". " + storage.get(i-1));
        }
        System.out.println(LINE);
    }

    /**
     * Lists all the tasks with the searched for words.
     * @param storage Array List of tasks.
     * @param spl Array with the words that are being searched for in the Array List.
     */
    public static void outputMessageFind(ArrayList<Task> storage, String[] spl) {
        System.out.println(LINE);
        int i = 1;
        for (Task t : storage) {
            if (t.getDescription().contains(spl[1])) {
                System.out.println(SPACE + i + ". " + t);
                i++;
            }
        }
        System.out.println(LINE);
    }

    /**
     * Message to be printed when the program ends.
     */
    public static void outputMessageBye() {
        System.out.println(LINE + SPACE + "Byee, hope to see you again soon!" + LINE);
    }
}
