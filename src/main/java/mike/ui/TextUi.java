package mike.ui;

import mike.ParseException;
import mike.TaskList;
import mike.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Used by Mike to relay information to the user
 */
public class TextUi {
    private final StringBuilder lengthOfChatBox = new StringBuilder();

    /**
     * Defines the length of the chat box
     */
    public void setLengthOfChatBox() {
        lengthOfChatBox.append("\n");
        for (int i = 0; i < 50; i++) {
            lengthOfChatBox.append("-");
        }
        lengthOfChatBox.append("\n");
    }

    /**
     * Formats the string to simulate the chat box of Mike
     *
     * @param s the String Object to be formatted
     */
    public String formatInChatBox(String s) {
        return lengthOfChatBox + s + lengthOfChatBox;
    }

    /**
     * Initializes the Ui Object
     */
    public String init() {
        setLengthOfChatBox();
        return formatInChatBox("Hello I'm Mike.\nWhat can I do for you?\n");
    }

    /**
     * Displays when the "bye" command is received and exits
     */
    public String showExitUi() {
        return formatInChatBox("Bye. Hope to see you again soon!\n");
    }

    /**
     * Lists all the tasks in taskList when the "list" command is received
     *
     * @param taskList the TaskList to be listed
     */
    public String showListUi(TaskList taskList) {
        int numOfText = taskList.size();
        StringBuilder output = new StringBuilder();

        if (taskList.isEmpty()) {
            output.append("No tasks saved\n");
        } else {
            output.append("Here are the tasks in your list: \n");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                output.append(i + 1).append(". ").append(currentTask).append("\n");
            }
        }
        return formatInChatBox(output.toString());
    }

    /**
     * Informs user that the addition of the task is successful
     *
     * @param taskList the list of tasks
     * @param task     the task to be added to the taskList
     */
    public String showAddSuccess(TaskList taskList, Task task) {
        return formatInChatBox("Got it. I've added this task:\n  " + task + "\n Now you have "
                + taskList.size() + " tasks in the list.");
    }

    /**
     * Informs user that the marking of the task is successful
     *
     * @param task the task to be marked asa done
     */
    public String showMarkSuccess(Task task) {
        return formatInChatBox("Nice! I've marked this task as done: \n" + task);
    }

    /**
     * Informs user that the deletion of the task is successful
     *
     * @param taskList the list of tasks
     * @param task     the task to be deleted
     */
    public String showDeleteSuccess(TaskList taskList, Task task) {
        return formatInChatBox("Noted. I've removed this task:\n  " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public String showError(ParseException e) {
        return formatInChatBox(e.getMessage());
    }

    public String showLoadingError(ParseException e) {
        return formatInChatBox(e.getMessage());
    }

    public String showMatchingResults(TaskList matchingResults) {
        String output = "";
        if (matchingResults.isEmpty()) {
            output += "OOP!!! There are no matching tasks in your list";
        } else {
            output += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingResults.size(); i++) {
                output += (i + 1) + "." + matchingResults.get(i).toString() + "\n";
            }
        }

       return formatInChatBox(output);
    }

    public String showIndexOutOfBoundsError(TaskList taskList) {
        return formatInChatBox("OOPS!!! You only have " + taskList.size() + " tasks, please enter an index within the range");
    }

    public String showIOError(IOException e) {
        return formatInChatBox(e.getMessage());
    }

    public String showDateTimeParseError(DateTimeParseException e) {
        return formatInChatBox(e.getMessage());
    }

    public String showGeneralError() {
        try {
            throw new ParseException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        } catch (ParseException e) {
            return formatInChatBox(e.getMessage());
        }
    }

    public String showListError() {
        return formatInChatBox("OOPS!!! The list is empty.");
    }
}
