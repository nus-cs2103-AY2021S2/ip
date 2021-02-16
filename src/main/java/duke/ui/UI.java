package duke.ui;

import duke.task.Task;

/**
 * UI class to handle interaction with users
 */
public class UI {

    /**
     * Display the greeting message upon launching the application
     */
    public static String displayWelcomeMessage() {
        String output = "Hello! I am Will, your personal assistant." + "\n"
                + "What can I do for you today?";
        return output;
    }

    /**
     * Display bye message
     */
    public static String displayEndMessage() {
        return " Bye. Hope to see you again!" + "\n";
    }

    /**
     * Display a particular task in list with its customized format
     * @param index index of task in task list
     * @param task task
     */
    public String displayTask(int index, Task task) {
        return (index + 1) + "." + task.toString();
    }

    /**
     * Display message to inform users that this is a duplicated task
     */
    public String displayDuplicatedMessage() {
        return "Existing entries with same task description was found. " +
                "Please add a new task.";
    }

    /**
     * Display message to inform users that this are no task
     */
    public String DisplayNoTaskMessage() {
        return "There are currently no task available.";
    }

    /**
     * Display header to show the tasks in the current list
     */
    public String displayListHeader() {
        return "Here are the tasks in your list:";
    }

    /**
     * Display header to show the tasks in the current list
     */
    public String displayFindHeader() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Display message upon successful addition of task to task list
     * @param task task
     * @param size amount of items in task list
     */
    public static String displayAddedTaskMessage(Task task, int size) {
        return "Got it. I've added this task: \n\t" + task.toString() + "\n Now you have "
                + size + " tasks in your list \n";
    }

    /**
     * Display message upon successful deletion of task
     * @param task task
     */
    public static String displayDeletedTaskMessage(Task task) {
        return "Nice! I've removed this task: \n" + task.toString() + "\n";
    }

    /**
     * Display message when task status is changed to completed
     * @param task task
     */
    public String displayDoneTaskMessage(Task task) {
        return "Nice! I'll make this task as completed: \n" + task.toString() + "\n";
    }

    /** Display exception messages upon encountering errors
     * @param e exception messages
     * @return
     */
    public String showError(String e) {
        return e;
    }

}
