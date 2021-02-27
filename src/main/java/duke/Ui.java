package duke;

import java.util.ArrayList;

/**
 * Generates messages to display to the user.
 */
public class Ui {
    /**
     * Returns an exit message to be displayed when the user exits the program.
     *
     * @return Message.
     */
    public String getExitMsg() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Returns a message confirming that a new task has been added.
     *
     * @param taskString String representation of the task.
     * @param numOfTasks Number of tasks in the list of tasks.
     * @return Message.
     */
    public String getAddTaskMsg(String taskString, int numOfTasks) {
        return "Got it! I've added this task:\n    " + taskString + "\nNow you have "
                + numOfTasks + " task" + (numOfTasks == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Returns a message confirming that a task has been marked as completed.
     *
     * @param taskString String representation of the task.
     * @return Message.
     */
    public String getMarkTaskAsDoneMsg(String taskString) {
        return "Nice! I've marked this task as done:\n    " + taskString;
    }

    /**
     * Returns a message confirming that a task has been deleted.
     *
     * @param taskString String representation of the task.
     * @param numOfTasksRemaining Number of tasks remaining in the list of tasks.
     * @return Message.
     */
    public String getDeleteTaskMsg(String taskString, int numOfTasksRemaining) {
        return "Noted! I've removed this task:\n    " + taskString + "\nNow you have "
                + numOfTasksRemaining + " task" + (numOfTasksRemaining == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Returns a String that lists out all tasks in the list of tasks.
     *
     * @param printableTasks ArrayList of Strings representing the tasks.
     * @return String representing the list of tasks.
     */
    public String getListOfTasks(ArrayList<String> printableTasks) {
        if (printableTasks.size() == 0) {
            return "You have no tasks currently.";
        } else {
            StringBuilder listOfTasks = new StringBuilder("Here are the tasks in your list:");
            for (String printableTask : printableTasks) {
                listOfTasks.append("\n    ").append(printableTask);
            }
            return listOfTasks.toString();
        }
    }

    /**
     * Returns a String that lists out all tasks in the list of tasks whose description
     * contains the keyword specified by the user.
     *
     * @param printableTasks ArrayList of Strings representing the tasks.
     * @return String representing the list of tasks containing the keyword.
     */
    public String getListOfFoundTasks(ArrayList<String> printableTasks) {
        if (printableTasks.size() == 0) {
            return "Found no matching tasks.";
        } else {
            StringBuilder listOfTasks = new StringBuilder("Here are the matching tasks in your list:");
            for (String printableTask : printableTasks) {
                listOfTasks.append("\n    ").append(printableTask);
            }
            return listOfTasks.toString();
        }
    }

    /**
     * Returns an error message to be displayed when the user tries to create a new
     * task without a description.
     *
     * @return Error message.
     */
    public String getEmptyDescriptionErrorMsg() {
        return "The description of a task cannot be empty!";
    }

    /**
     * Returns an error message to be displayed when the user enters an invalid command.
     *
     * @return Error message.
     */
    public String getInvalidCommandErrorMsg() {
        return "Invalid command! Please try again.";
    }

    /**
     * Returns an error message to be displayed when an error is encountered while trying
     * to save tasks to the hard disk.
     *
     * @return Error message.
     */
    public String getWritingErrorMsg() {
        return "Encountered an error when trying to write tasks to the hard disk.";
    }
}
