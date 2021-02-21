package duke;

import java.util.ArrayList;

/**
 * Displays messages to the user.
 */
public class Ui {
    /**
     * Displays an exit message when the user exits the program.
     */
    public String getExitMsg() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Displays a message confirming that a new task has been added.
     */
    public String getAddTaskMsg(String taskString, int numOfTasks) {
        return "Got it! I've added this task:\n    " + taskString + "\nNow you have " +
            numOfTasks + " task" + (numOfTasks == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Displays a message confirming that a task has been marked as completed.
     */
    public String getMarkTaskAsDoneMsg(String taskString) {
        return "Nice! I've marked this task as done:\n    " + taskString;
    }

    /**
     * Displays a message confirming that a task has been deleted.
     */
    public String getDeleteTaskMsg(String taskString, int numOfTasksRemaining) {
        return "Noted! I've removed this task:\n    " + taskString + "\nNow you have " +
            numOfTasksRemaining + " task" + (numOfTasksRemaining == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Prints the current tasks in the list of tasks.
     *
     * @param printableTasks ArrayList of Strings representing the tasks.
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
     * Prints the current tasks in the list of tasks whose description contains the keyword given by the user.
     *
     * @param printableTasks ArrayList of Strings representing the tasks.
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
     * Displays a error message when the user tries to create a new task without a description.
     */
    public String getEmptyDescriptionErrorMsg() {
        return "The description of a task cannot be empty!";
    }

    /**
     * Displays a error message when the user enters an invalid command.
     */
    public String getInvalidCommandErrorMsg() {
        return "Invalid command! Please try again.";
    }

    /**
     * Displays a error message when an error is encountered while trying to save tasks to the hard disk.
     */
    public String getWritingErrorMsg() {
        return "Encountered an error when trying to write tasks to the hard disk.";
    }
}
