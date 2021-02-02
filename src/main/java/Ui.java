import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * To output a line that consists of -
     *
     * @return String that consists of -
     */
    public String showLine() {
        return "------------------------------------------------";
    }

    /**
     * To output an error message
     *
     * @param message the error message in String
     * @return String message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * To indicate that the file is unable to be loaded
     *
     * @return String that states unable to load file
     */
    public String showLoadingError() {
        return "Unable to load file!";
    }

//    /**
//     * Takes in user input
//     *
//     * @return the full user command in String
//     */
//    public String readCommand() {
//        return sc.nextLine();
//    }

    /**
     * To output a welcome message
     *
     * @return String of welcome message
     */
    public String showWelcome() {
        String message = "Hi! I'm Timmy!\nWhat can Timmy note down for you today?";
        message = message + "\nPlease type in any of these format!" + "\ntodo [title]"
                + "\nevent [title] /at [yyyy-mm-dd] [HH:MM]" + "\ndeadline [title] /by [yyyy-mm-dd] [HH:MM]"
                + "\nlist" + "\ndelete [index]" + "\ndone [index]";
        return message;
    }

    /**
     * To output an exit message
     *
     * @return String of an exit message
     */
    public String showExit() {
        return "Bye! Hope to see you again!";
    }

    /**
     * To output the <code>Tasks</code> in a TaskList
     *
     * @param tasklist stores the <code>Task</code> in an ArrayList
     * @return String to consists of <code>Task</code> in an ArrayList
     */
    public String printList(TaskList tasklist) {
        String message = "Here are the tasks in your list:\n";

        ArrayList<Task> tasks = tasklist.getList();

        for (int j = 0; j < tasks.size(); j++) {
            message = message + (j + 1) + "." + tasks.get(j).toString() + "\n";
        }

        return message;
    }

    /**
     * To ouput a message to indicate that the task is marked
     *
     * @param task the <code>Task</code>> to be marked
     * @return String that indicates the <code>Task</code>> to be marked
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * To output a message to indicate that the task is deleted
     *
     * @param tasklist  stores the <code>Task</code> in an ArrayList
     * @param taskIndex indicates the <code>Task</code> index in the ArrayList
     * @return String that consists of deleted tasks
     */
    public String showDeleteTask(TaskList tasklist, int taskIndex) {
        String message = "Ok! I've removed this task:\n" + tasklist.getList().get(taskIndex).toString() + "\n";
        message = message + "Currently, you have " + (tasklist.getList().size() - 1) + " task(s) in the list!";

        return message;
    }

    /**
     * To output the added <code>Task</code> to the ArrayList
     *
     * @param tasklist stores the <code>Task</code> in an ArrayList
     * @return String that consists of the task added
     */
    public String showAddTask(TaskList tasklist) {
        String message = "Ok! I've added this task:\n" + tasklist.getList().get(tasklist.getList().size() - 1).toString();
        message = message + "\nCurrently, you have " + tasklist.getList().size() + " task(s) in the list!";

        return message;
    }
}
