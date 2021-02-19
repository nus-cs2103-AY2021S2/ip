package duke;

/**
 * Deals with the interactions with users.
 */
public class Ui {

    /**
     * Greets the user and showcases the DUKE logo.
     */
    public String greet() {
        return "Hi! I'm DUKE, your task manager. What can I do for you?";
    }

    /**
     * lists out all the tasks in the task list.
     * @param list The task list.
     * @return All the tasks in the task list.
     */
    public String provideList(String list) {
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * Informs the user that the task has been added and the number of tasks in the task list.
     * @param task The added task.
     * @param numOfTasks The number of tasks in the task list.
     * @return The added task and number of tasks in the task list.
     */
    public String addTaskReply(String task, String numOfTasks) {
        return "Got it. I've added this task:\n" + task + "\n" + "Now you have " + numOfTasks + " tasks in the list.";
    }

    /**
     * Informs the user that the task has been marked as done.
     * @param task The task marked as done.
     * @return The completed task.
     */
    public String markAsDoneReply(String task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Informs the user that the task has been deleted and the number of tasks left in the task list.
     * @param task The deleted task.
     * @param numOfTasks The number of tasks left in the task list.
     * @return The deleted task and number of tasks left in the task list.
     */
    public String deleteTaskReply(String task, String numOfTasks) {
        return "Noted. I've removed this task:\n" + task + "\n" + "Now you have " + numOfTasks + " tasks in the list.";
    }

    /**
     * Informs the user that all the task has been deleted from the list.
     * @return The deletion of all tasks.
     */
    public String deleteAllReply() {
        return "Noted. I've removed all the tasks from the list.";
    }

    /**
     * lists out all the tasks with descriptions containing the search word from the task list.
     * @param tasks The matching tasks.
     * @return The matching tasks.
     */
    public String findWordReply(String tasks) {
        return "Here are the matching tasks in your list:\n" + tasks;
    }

    /**
     * Informs the user that there are no tasks with descriptions containing the search word from the task list.
     * @return The response that there are no matching tasks.
     */
    public String wordNotFoundReply() {
        return "Your list does not contain this word.";
    }

    /**
     * Informs the user that they did not input a description.
     * @return The warning of an empty description.
     */
    public String missingDescriptionReply() {
        return "The description cannot be empty.";
    }

    /**
     * Informs the user that they did not input a date.
     * @return The warning of an empty date.
     */
    public String missingDateReply() {
        return "The date cannot be empty.";
    }

    /**
     * Informs the user that they did not input a time.
     * @return The warning of an empty time.
     */
    public String missingTimeReply() {
        return "The time cannot be empty.";
    }

    /**
     * Informs the user that they did not input a line number.
     * @return The warning of an empty line number.
     */
    public String missingLineNumberReply() {
        return "The line number cannot be empty.";
    }

    /**
     * Informs the user that they did not input a search word.
     * @return The warning of an empty search word.
     */
    public String missingSearchWordReply() {
        return "The word cannot be empty.";
    }

    /**
     * Informs the user that they inputted an invalid line number.
     * @return The warning of an invalid line number.
     */
    public String invalidLineReply() {
        return "Invalid line number.";
    }

    /**
     * Informs the user that they inputted an invalid command.
     * @return The warning of an invalid command.
     */
    public String invalidCommandReply() {
        return "Invalid command. Please try again.";
    }

    /**
     * Informs the user that the task they are adding already exists in the task list.
     * @return The warning that the task already exist.
     */
    public String detectDuplicateReply(String task) {
        return "This task: \"" + task + "\" already exists in the list.";
    }
}
