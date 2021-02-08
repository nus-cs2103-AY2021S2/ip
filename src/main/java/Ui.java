/**
 * Ui handles the interactions with the user.
 */
public class Ui {

    public static final int EMPTY_LIST = 0;
    public static final int SINGLE_TASK_IN_LIST = 1;

    /**
     * Prints the response depending on whether the list has 1 or more than 1 task.
     * @param taskListSize Number of tasks in the list.
     * @return A String depending on whether there is 1 task or more than 1 task.
     */
    public String printResponse(int taskListSize) {
        assert taskListSize >= 0;
        if (taskListSize == SINGLE_TASK_IN_LIST) {
            return "task";
        } else {
            return "tasks";
        }
    }

    /**
     * Creates the response after successfully adding a task.
     *
     * @param task Task that was added.
     * @param taskListSize Number of tasks in the TaskList.
     * @return A string including the acknowledgement of the new task, and the total
     * number of tasks in the list.
     */
    public String respondToAddTask(Task task, int taskListSize) {
        assert task != null;
        return "Done! One new task:\n" + task.toString() + "\nNow you have "
                 + taskListSize + " " + printResponse(taskListSize) + " in the list";
    }

    /**
     * Creates the response after successfully deleting the task.
     *
     * @param task Task that was deleted.
     * @param taskListSize Number of tasks in the TaskList.
     * @return A string including the deletion of the task, and the total
     * number of tasks in the list.
     */
    public String respondToDelete(Task task, int taskListSize) {
        assert taskListSize >= 0;
        return "Noted, I've removed this task:\n" + task.toString()
                 + "\nNow you have " + taskListSize + " " + printResponse(taskListSize) + " in " + "the list";
    }

    /**
     * Creates the response after user inputs 'bye'.
     * @return A string.
     */
    public String respondToBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the initial response to the 'list' command, depending on whether
     * the TaskList is empty.
     *
     * @param taskListSize Number of tasks in the TaskList.
     * @return A string, depending on whether the list is empty.
     */
    public String respondToList(int taskListSize) {
        assert taskListSize >= 0;
        if (taskListSize != EMPTY_LIST) {
            return "Here are the tasks in your list:";
        } else {
            return "Your list is currently empty! Let's start adding tasks!";
        }
    }

    /**
     * Creates the response after marking a task as completed.
     *
     * @param task Task that was marked completed.
     * @return A string.
     */
    public String respondToDone(Task task) {
        assert task != null;
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Creates the response after successfully finding matching tasks.
     * @return A string.
     */
    public static String respondToFind() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Creates the response after being unable to find any matching tasks.
     * @return A string.
     */
    public static String respondToNoMatches() {
        return "There are no matching tasks! Time to add one!";
    }


}
