package duke;

import java.util.ArrayList;
import java.util.Comparator;

import duke.tasks.Deadline;
import duke.tasks.Task;


public class Ui {

    public Ui() {

    }

    /**
     * Displays the output upon addition of Task.
     *
     * @param task task.
     * @param num task number.
     * @return a String of the description.
     */
    public String displayAddedTask(Task task, int num) {
        return "☺ Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + num + " tasks in the list.";
    }

    /**
     * Displays output upon completion of Task.
     *
     * @param task Completed task.
     * @return a String of the description.
     */
    public String displayDoneTask(Task task) {
        return "☺ Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Displays output upon deletion of task.
     *
     * @param task Deleted task.
     * @param num Number of tasks left.
     * @return a String of the description.
     */
    public String displayDeletedTask(Task task, int num) {
        return "☺ Noted. I've removed this task: \n" + task + "\n"
                + "Now you have " + num + " tasks in the list.";
    }

    /**
     * Displays a list of matching tasks.*
     *
     * @param tasks A list of tasks.
     * @return a String of the description.
     */
    public String displayMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list: \n");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            sb.append(i).append(". ").append(t).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays a list of tasks.
     *
     * @param tasks A list of tasks.
     * @return a String of the description.
     */
    public String displayList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            sb.append(i).append(". ").append(t).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays the goodbye message.
     *
     * @return a String of the description.
     */
    public String goodbyeMessage() {
        return "Bye. Hope to see you again soon! ☺";
    }
}
