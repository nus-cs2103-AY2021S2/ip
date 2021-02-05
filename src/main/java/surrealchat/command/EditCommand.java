package surrealchat.command;

import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

/**
 * Command object for editing description of Task objects.
 */
public class EditCommand extends Command {
    protected final String rawDescription;

    /**
     * Creates a new EditCommand object
     * @param rawDescription The task number to edit + /edit tag + new description.
     */
    public EditCommand(String rawDescription) {
        super("edit");
        this.rawDescription = rawDescription;
    }

    private boolean isInvalidTaskNumber(int taskNumber, TaskManagement taskManagement) {
        return ((taskNumber <= 0) || (taskNumber > taskManagement.getNumberOfTasks()));
    }

    private String printOutput(Task editedTask) {
        String outputString = "You have edited a task description to this:\n";
        outputString += String.format("%s\n", editedTask);
        return outputString;
    }

    /**
     * Executes edit command to edit a Task description.
     * @param taskManagement TaskManagement object where Task to be edited is stored.
     * @return String to describe the new task.
     */
    public String execute(TaskManagement taskManagement) {
        if (rawDescription.isEmpty()) {
            return "Did you forget to add the task number and new description? Not stonks!\n";
        }
        //Split the description into task number and description
        String[] descriptionSplitArray = rawDescription.split("/edit");
        try {
            int taskNumber = Integer.valueOf(descriptionSplitArray[0].trim());
            String newDescription = descriptionSplitArray[1].trim();

            //Edit task description
            if (isInvalidTaskNumber(taskNumber, taskManagement)) {
                return "Invalid task number. Not stonks!\n";
            } else if (newDescription.isEmpty()) {
                return "No description provided for editing. Not stonks!\n";
            } else {
                Task editedTask = taskManagement.editDescription(taskNumber, newDescription);
                return printOutput(editedTask);
            }
        } catch (NumberFormatException e) { //Can happen if clean split does not occur.
            return "Task number not parsed. Did you forget to put '/edit'? Or did you not put a number? Not stonks!\n";
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur.
            return "Wrong formatting. Did you forget to put '/edit' and/or the description? Not stonks!\n";
        }
    }

    /**
     * Describes usage of edit command.
     * @return String describing the edit command.
     */
    public static String displayHelp() {
        String outputString = "Given a task number and new description, edits that task with new description.\n";
        outputString += "Format of arguments: edit [task number] /edit [new description]\n";
        return outputString;
    }
}
