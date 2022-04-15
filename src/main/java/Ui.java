import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public Ui() { }

    /**
     * Prints introduction message.
     */
    public static String printIntro() {
        return "Hello, fellow adventurer!\n"
                + "What's our mission for today?\n";
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

    public String printEdit(ArrayList<Task> tasks, int index) {
        return "Okay! I've edited this task:\n" + tasks.get(index).toString() + "\n";
    }

    /**
     * Prints goodbye message followed by a line.
     */
    public String printBye() {
        return "Goodbye, can't wait till our next adventure together!";
    }

    /**
     * Prints error message that description cannot be empty.
     * @param command command type.
     */
    public String printEmptyDescError(String command) {
//        return "Oops! Description of " + task + " cannot be empty.";
        switch (command) {
        case ("todo"):
            return "Oops! Description of todo cannot be empty."
                    + "\nFormat: todo DESCRIPTION";
        case ("deadline"):
            return "Oops! Description of deadline cannot be empty."
                + "\nFormat: deadline DESCRIPTION /by YYYY-MM-DD";
        case ("event"):
            return "Oops! Description of event cannot be empty."
                + "\nFormat: event DESCRIPTION /at YYYY-MM-DD";
        case ("edit"):
            return "Oops! Description cannot be empty."
                    + "\nFormat: edit INDEX [/desc DESCRIPTION] [/date YYYY-MM-DD]";
        case ("done"):
            return "Oops! Please enter the task index."
                    + "\nFormat: done INDEX";
        case ("delete"):
            return "Oops! Please enter the task index."
                    + "\nFormat: delete INDEX";
        case ("find"):
            return "Oops! Please enter the search keyword."
                    + "\nFormat: find KEYWORD";
        default:
            return "Oops! Description cannot be empty.";
        }
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

    public String printToDoHasNoDateError() {
        return "Oops! Todo has no date.\nPlease only enter the description!";
    }
}
