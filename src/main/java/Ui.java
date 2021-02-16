import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Ui {
    private final String greet = "Hello! I'm Duke \n What can I do for you?";
    private final String bye = "Bye. Hope to see you again soon!";

    private Label botText;
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    Ui() {

    }

    public String welcomeUser() {
        return greet;
    }

    public String userLeaving() {
        return bye;
    }

    /**
     * String to print when a user is done with the given task in the list.
     * @param task
     */
    public String userDoneTask(String task) {
        return "Nice! I've marked this task as done:\n " + task + ")";
    }

    /**
     * String to print when a user adds a new Task to the list.
     * @param list
     */
    public String userAddTask(TaskList list, Task task) {
        return "Got it. I've added this task: \n  "
                            + task
                            + "\nnow you have " + String.valueOf(list.size())
                            + " tasks in the list.";
    }

    /**
     * String to print when a user deletes the given task from the given list.
     * @param task that user deleted
     * @param list that user deleted from
     */
    public String userDeleteTask(Task task, TaskList list) {
        return "Noted. I've removed this task: \n "
                + task
                + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Prints header string before the filtered list of task is printed.
     */
    public String tellUserListFound() {
        return "Here are the matching tasks in your list: ";
    }

}
