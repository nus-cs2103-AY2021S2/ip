package duke.Ui;

import duke.Model.TaskList;
import duke.Tasks.Task;

/**
 * Provides some functions which handle formatting display messages to display to the user.
 */
public class MessageGenerator {

    /**
     * Returns the welcome message when first starting duke.
     */
    public String getWelcomeMessage() {
        return  "Hello! I'm Duke\n" + "What can I do for you?";
    }


    /**
     * Returns the string of all the tasks stored on the TaskList.
     */

    public static String getDisplayOfAllTasks(TaskList listOfTasks) {
        String output = "";
        int counter = 1;
        for (Task currentTask : listOfTasks) {
            output += (counter + "." + currentTask + "\n");
            counter++;
        }
        return output;
    }

    public static String getDisplayOfNumberOfTasks(TaskList listOfTasks) {
        return "Now you have " + listOfTasks.size() + " tasks in the list.";
    }

    public String generateAddMessage(Task taskToBeAdded, TaskList tasks) {
        return "Got it. I've added this task:\n\t" + taskToBeAdded + "\n"
                + MessageGenerator.getDisplayOfNumberOfTasks(tasks);
    }

    public String generateDeleteMessage(Task taskToDelete, TaskList tasks) {
        return "Noted. I've removed this task:\n\t" + taskToDelete
                + "\n" + MessageGenerator.getDisplayOfNumberOfTasks(tasks);
    }
    public String generateExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String generateFindTaskMessage(TaskList filteredTaskList) {
        return "Here are the matching tasks in your list:" + "\n" +
                getDisplayOfAllTasks(filteredTaskList);
    }
    public String generateMarkTaskMessage(Task taskToMarkDone) {
        return "Nice! I've marked this task as done:\n" + "\t" + taskToMarkDone;
    }


}

