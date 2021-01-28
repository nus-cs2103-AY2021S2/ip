package surrealchat.command;

import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

import java.util.NoSuchElementException;

public class EditCommand extends Command{
    protected final String rawDescription;

    public EditCommand(String rawDescription) {
        super("edit");
        this.rawDescription = rawDescription;
    }

    private boolean checkInvalidTaskNumber(int taskNumber, TaskManagement taskManagement) {
        return ((taskNumber <= 0) || (taskNumber > taskManagement.getNumberOfTasks()));
    }

    private String printOutput(Task editedTask) {
        String outputString = "You have edited a task description to this:\n";
        outputString += String.format("%s\n", editedTask);
        return outputString;
    }

    public String execute(TaskManagement taskManagement) {
        if (this.rawDescription.isEmpty()) {
            throw new NoSuchElementException("Did you forget to add the task number and new description? Not stonks!");
        }
        //Split the description into task number and description
        String[] descriptionSplitArray = this.rawDescription.split("/edit");
        try {
            int taskNumber = Integer.valueOf(descriptionSplitArray[0].trim());
            String newDescription = descriptionSplitArray[1].trim();

            //Edit task description
            if (this.checkInvalidTaskNumber(taskNumber, taskManagement)) {
                throw new IllegalArgumentException("Invalid task number. Not stonks!");
            } else if (newDescription.isEmpty()) {
                throw new IllegalArgumentException("No description provided for editing. Not stonks!");
            } else {
                Task editedTask = taskManagement.editDescription(taskNumber, newDescription);
                return this.printOutput(editedTask);
            }
        } catch (NumberFormatException e) { //Can happen if clean split does not occur.
            throw new NumberFormatException(
                    "Task number not parsed. Did you forget to put '/edit'? Or did you not put a number? Not stonks!");
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur.
            throw new ArrayIndexOutOfBoundsException(
                    "Wrong formatting. Did you forget to put '/edit' and/or the description? Not stonks!");
        }
    }
}
