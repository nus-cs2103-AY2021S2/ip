package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;

public class Ui {

    /**
     * Formats the string to be printed to the user.
     *
     * @param error error message that is to be printed.
     * @return string to be printed to the user.
     */
    public String showError(String error) {
        System.out.println(error);
        System.out.println("");
        return error + "\n";
    }

    /**
     * Formats the task that was added and outputs a string to be printed to the user.
     *
     * @param task task that was added.
     * @param numOfTasks current number of tasks the user has.
     * @return string message to be printed to the user.
     */
    public String showAddedTask(Task task, int numOfTasks) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + numOfTasks + " tasks in the list. \n";
    }

    /**
     * Formats the task that was deleted and outputs a string to be printed to the user.
     *
     * @param taskList lists of tasks that were deleted.
     * @param numOfTasks current number of tasks the user has.
     * @return string message to be printed to the user.
     */
    public String showRemovedTask(ArrayList<Task> taskList, int numOfTasks) {
        String result = "Noted. I've removed this task(s): \n";
        for (Task task : taskList) {
            result = result + task + "\n";
        }
        result = result + "\nNow you have " + numOfTasks + " tasks in the list. \n";
        return result;
    }

    /**
     * Formats the task that was marked as done and outputs a string to be printed to the user.
     *
     * @param task task that was marked done.
     * @return string message to be printed to the user.
     */
    public String showDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Returns a string containing all tasks in the list.
     *
     * @param list list of tasks.
     * @return String containing all the task descriptions.
     */
    public String showAllTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "There are no tasks in your list. \n";
        } else {
            String result = "Here are the tasks in your list: \n";
            for (int i = 0; i < list.size(); i++) {
                result += String.valueOf(i + 1) + "." + list.get(i).toString() + "\n";
            }
            return result;
        }
    }

    /**
     * Returns a string containing a bye message that is to be printed to the user.
     *
     * @return String which is a bye message.
     */
    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /** Returns a string containing the tasks that are due by the given date.
     *
     * @param list list of tasks that are due on the specified date.
     * @param date deadline of tasks.
     * @return String containing list of tasks that are due.
     */
    public String showDueTasks(ArrayList<Task> list, String date) {
        LocalDate currentDate = LocalDate.parse(date);
        String result = "Here are the tasks due on " + String.valueOf(currentDate) + ": " + "\n";
        for (int i = 0; i < list.size(); i++) {
            result += String.valueOf(i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Returns a string containing the tasks that have the matching keyword.
     *
     * @param list list of tasks that are have the keyword.
     * @return String containing list of tasks that have the keyword.
     */
    public String showMatchingTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "Sorry! There are no matching task.";
        } else {
            String result = "Here are the matching tasks in your list: " + "\n";
            for (int i = 0; i < list.size(); i++) {
                result += String.valueOf(i + 1) + ". " + list.get(i).toString() + "\n";
            }
            return result;
        }
    }
}
