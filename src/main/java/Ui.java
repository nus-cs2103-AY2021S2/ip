import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public static String TAB = "     ";
    public static String LINE = "     ............................................................";

    public Ui() { }

    /**
     * Prints Elly logo and introduction message.
     */
    public void printIntro() {
        String logo = " _____   _   _\n"
                + "| ____| | | | |\n"
                + "| |___  | | | | __   __\n"
                + "|  ___| | | | | \\ \\ / /\n"
                + "| |___  | | | |  \\ v /\n"
                + "|_____| |_| |_|  /  /\n"
                + "                /__/\n";

        System.out.println("   C H A T   W I T H\n" + logo);

        System.out.println(LINE + "\n"
                + TAB + "Hi there! I'm Elly.\n"
                + TAB + "How can I help you today?\n"
                + LINE);
    }

    /**
     * Prints line for Elly's response display.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    public String printList(ArrayList<Task> tasks, int numTasks) {
        StringBuilder response;
        if (numTasks == 0) {
            response = new StringBuilder("Yay! There are no tasks in your list.");
        } else if (numTasks < 0) {  // print filtered tasks
            response = new StringBuilder("Here are the matching tasks in your list:\n");
            int num = 1;
            for (Task t : tasks) {
                response.append(num).append(".").append(t.toString()).append("\n");
                num++;
            }
        } else {
            response = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < numTasks; i++) {
                int num = i + 1;
                Task task = tasks.get(i);
                response.append(num).append(".").append(task.toString()).append("\n");
            }
        }
        return response.toString();
    }

    /**
     * Prints to notify user which task is marked as done.
     * Identifies task by index and formats task to String.
     * @param tasks arraylist of tasks.
     * @param index task index.
     */
    public String printDone(ArrayList<Task> tasks, int index) {
        return "Nice! I've marked this task as done:\n" + tasks.get(index).toString() + "\n";
    }

    /**
     * Prints to notify user which task is deleted.
     * @param taskString String format of deleted task
     */
    public String printDelete(String taskString) {
        return "Noted. I've removed this task:\n" + taskString + "\n";
    }

    /**
     * Prints to notify user which task is added.
     * Identifies task by index and formats task to String.
     * @param tasks arraylist of tasks.
     * @param index task index.
     */
    public String printAdd(ArrayList<Task> tasks, int index) {
        return "Got it. I've added this task:\n" + tasks.get(index).toString() + "\n";
    }

    /**
     * Prints number of tasks in list.
     * @param numTasks number of tasks.
     */
    public String printNumTasks(int numTasks) {
        return "Now you have " + numTasks + (numTasks == 1 ? " task" : " tasks") + " in your list.";
    }

    /**
     * Prints goodbye message followed by a line.
     */
    public String printBye() {
        return "Goodbye, can't wait till our next adventure together!";
    }

    /**
     * Prints error message that description cannot be empty.
     * @param task task type.
     */
    public String printEmptyDescError(String task) {
        return "Oops! Description of " + task + " cannot be empty.";
    }

    /**
     * Prints error message that command cannot be identified.
     */
    public String printIdkError() {
        return "I'm sorry, I'm not sure what that means.";
    }

    /**
     * Prints to notify user of formatting requirement for date.
     */
    public String printDateError() {
        return "Oops! Date should be in YYYY-MM-DD format.";
    }
}
