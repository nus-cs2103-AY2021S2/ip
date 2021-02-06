package duke.ui;

import duke.task.Task;

/**
 * A class to handle interaction with users
 */
public class UI {

    public static String displayStartMessage(){
        String output = "Hello! I am Will, your personal assistant." + "\n" +
                "What can I do for you today?";
        return output;
    }

    /**
     * Print out bye message to user
     */
    public static String displayEndMessage() {
        return " Bye. Hope to see you again!" + "\n";
    }

    /** Print out the task found in list
     * @param count
     * @param task
     */
    public String printTask(int count, Task task) {
        return (count + 1) + "." + task.toString();
    }


    public String printDuplicateMessage() {
        return "Existing entries with same task description was found. " +
                "Please add a new task.";
    }


    public String printNoTaskMessage() {
        return "There are currently no task available.";
    }

    /**
     * Display header for list method
     */
    public String printListHeader() {
        return "\nHere are the tasks in your list:";
    }

    public String printFindHeader() {
        return "\nHere are the matching tasks in your list:";
    }

    /** Print out message when task is added
     * @param t task
     * @param size
     */
    public static String displayAddedTaskMessage(Task t, int size) {
        return "Got it. I've added this task: \n\t" + t.toString() + "\n Now you have "
                + size + " tasks in your list \n";
    }

    /**Print out message when task is deleted
     * @param t task
     */
    public static String displayDeletedTaskMessage(Task t) {
        return "Nice! I've removed this task: \n" + t.toString() + "\n";
    }

    /** Print out message when task is done
     * @param t task
     */
    public String displayDoneTaskMessage(Task t) {
        return  "\nNice! I'll make this task as done: \n" + t.toString() + "\n";
    }

    /** Print out exception messsage
     * @param e execption messsage
     * @return
     */
    public String showError(String e) {
        return e;
    }

}
