package duke.ui;

import duke.model.TaskList;
import duke.tasks.Task;

/**
 * Provides some functions which handle formatting display messages to display to the user.
 */
public class MessageGenerator {

    /**
     * Returns the welcome message when first starting duke.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Monique the Monitor Lizard\n"
                + "I can help you monitor your life and beat procrastination!\n"
                + "What can I do for you?";
    }


    /**
     * Returns the string of all the tasks stored on the TaskList.
     */

    public static String getDisplayOfAllTasks(TaskList listOfTasks) {
        if (listOfTasks.size() == 0) {
            return "You have nothing on currently.";
        }
        String output = "";
        int counter = 1;
        for (Task currentTask : listOfTasks) {
            output += (counter + "." + currentTask + "\n");
            counter++;
        }
        return output;
    }

    /**
     * Get the display message to display the number of tasks.
     *
     * @param listOfTasks a list of Tasks to display info about
     * @return the string to display
     */
    public static String getDisplayOfNumberOfTasks(TaskList listOfTasks) {
        return "Now you have " + listOfTasks.size() + " tasks in the list.";
    }


    /**
     * Get the display message upon successful execution of adding a task to the list
     * @param taskToBeAdded the task that is added
     * @param tasks the list of tasks that is changed.
     * @return the string to display.
     */
    public String generateAddMessage(Task taskToBeAdded, TaskList tasks) {
        return "Got it. I've added this task:\n\t" + taskToBeAdded + "\n"
                + MessageGenerator.getDisplayOfNumberOfTasks(tasks);
    }

    /**
     * Get the display message upon successful execution of deleting a task from the list
     * @param taskToDelete the task that is deleted
     * @param tasks the list of tasks that is changed.
     * @return the string to display.
     */

    public String generateDeleteMessage(Task taskToDelete, TaskList tasks) {
        return "Noted. I've removed this task:\n\t" + taskToDelete
                + "\n" + MessageGenerator.getDisplayOfNumberOfTasks(tasks);
    }

    /**
     * Get the display message upon successful execution of a exit command
     * @return the string to display.
     */

    public String generateExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Get the display message upon successful execution of a find Task Command.
     * @param filteredTaskList the listOfTasks that is filtered
     * @return the string to display.
     */

    public String generateFindTaskMessage(TaskList filteredTaskList) {
        return "Here are the matching tasks in your list:" + "\n"
                + getDisplayOfAllTasks(filteredTaskList);
    }

    /**
     * Get the display message upon successful execution of a Done Command.
     * @param taskToMarkDone task that is marked done.
     * @return the string to display.
     */

    public String generateMarkTaskMessage(Task taskToMarkDone) {
        return "Nice! I've marked this task as done:\n" + "\t" + taskToMarkDone;
    }


}

