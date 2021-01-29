package surrealchat.command;

import surrealchat.task.DeadlineTask;
import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

/**
 * Command object for creating a new DeadlineTask object.
 */
public class DeadlineCommand extends Command {
    protected final String rawDescription;

    /**
     * Creates new DeadlineCommand object.
     * @param rawDescription The description for new DeadlineTask object, inclusive of date.
     */
    public DeadlineCommand(String rawDescription) {
        super("deadline");
        this.rawDescription = rawDescription;
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Input date format is incorrect. Not stonks!");
        }
    }

    private DeadlineTask addDeadline(String taskDescription) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty deadline task description. Not stonks!");
        }

        //Split the description into description and deadline
        String[] descriptionSplitArray = taskDescription.split("/by");
        try {
            LocalDate deadlineDate = this.parseDate(descriptionSplitArray[1].trim());

            //Create Deadline task
            return DeadlineTask.createNewDeadlineTask(descriptionSplitArray[0].trim(),
                    deadlineDate);
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/by'? Not stonks!");
        }
    }

    private String printOutput(Task task, int size) {
        String outputString = String.format("Meme Man has added deadline task: %s\n", task);
        outputString += String.format("Total number of tasks: %d\n", size);
        return outputString;
    }

    /**
     * Executes deadline command to generate new DeadlineTask object.
     * @param taskManagement TaskManagement object to which DeadlineTask is added.
     * @return String to be printed upon successful addition of DeadlineTask.
     */
    public String execute(TaskManagement taskManagement) {
        DeadlineTask addedTask = this.addDeadline(this.rawDescription);
        taskManagement.addTask(addedTask);
        return this.printOutput(addedTask, taskManagement.getNumberOfTasks());
    }

    /**
     * Describes usage of deadline command.
     * @return String describing the deadline command.
     */
    public static String displayHelp() {
        String outputString = "Given a description and deadline, stores deadline task.\n";
        outputString += "Format of arguments: deadline [description] /by [deadline]\n";
        outputString += "[deadline] must be of the form YYYY-MM-DD\n";
        return outputString;
    }
}
