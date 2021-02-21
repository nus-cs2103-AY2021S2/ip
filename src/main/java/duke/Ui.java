package duke;

import java.util.ArrayList;

/**
 * Displays messages to the user.
 */
public class Ui {
    private static final String INDENTATION = "        ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String SEPARATOR = INDENTATION + HORIZONTAL_LINE;

    /**
     * Displays a welcome message when the program is first launched.
     */
    public void displayWelcomeMsg() {
        String logo = "         ____        _        \n"
            + "        |  _ \\ _   _| | _____ \n"
            + "        | | | | | | | |/ / _ \\\n"
            + "        | |_| | |_| |   <  __/\n"
            + "        |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(SEPARATOR);
        System.out.println(logo);
        System.out.println(INDENTATION + "Hello! I'm Duke\n" + INDENTATION + "What can I do for you?");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays an exit message when the user exits the program.
     */
    public void displayExitMsg() {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message confirming that a new task has been added.
     */
    public void displayAddTaskMsg(String taskString, int numOfTasks) {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + "    " + taskString);
        System.out.println(INDENTATION + "Now you have " + numOfTasks + " task" + (numOfTasks == 1 ? "" : "s") + " in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message confirming that a task has been marked as completed.
     */
    public void displayMarkTaskAsDoneMsg(String taskString) {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:\n"+ INDENTATION + "    " + taskString);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message confirming that a task has been deleted.
     */
    public void displayDeleteTaskMsg(String taskString, int numOfTasksRemaining) {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + "    " + taskString);
        System.out.println(INDENTATION + "Now you have " + numOfTasksRemaining + " task" + (numOfTasksRemaining == 1 ? "" : "s") + " in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the current tasks in the list of tasks.
     *
     * @param printableTasks ArrayList of Strings representing the tasks.
     */
    public void printTasks(ArrayList<String> printableTasks) {
        System.out.println(SEPARATOR);
        if (printableTasks.size() == 0) {
            System.out.println(INDENTATION + "You have no tasks currently.");
        } else {
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (String printableTask : printableTasks) {
                System.out.println(INDENTATION + "    " + printableTask);
            }
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the current tasks in the list of tasks whose description contains the keyword given by the user.
     *
     * @param printableTasks ArrayList of Strings representing the tasks.
     */
    public void printFoundTasks(ArrayList<String> printableTasks) {
        System.out.println(SEPARATOR);
        if (printableTasks.size() == 0) {
            System.out.println(INDENTATION + "Found no matching tasks.");
        } else {
            System.out.println(INDENTATION + "Here are the matching tasks in your list:");
            for (String printableTask : printableTasks) {
                System.out.println(INDENTATION + "    " + printableTask);
            }
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a error message when the user tries to create a new task without a description.
     */
    public void displayEmptyDescriptionError() {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "The description of a task cannot be empty.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a error message when the user enters an invalid command.
     */
    public void displayInvalidCommandError() {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Invalid command. Please try again.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a error message when an error is encountered while trying to load tasks from the hard disk.
     */
    public void displayLoadingError() {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Encountered an error when loading existing tasks.");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a error message when an error is encountered while trying to save tasks to the hard disk.
     */
    public void displayWritingError() {
        System.out.println(SEPARATOR);
        System.out.println(INDENTATION + "Encountered an error when trying to write tasks to the hard disk.");
        System.out.println(SEPARATOR);
    }
}
