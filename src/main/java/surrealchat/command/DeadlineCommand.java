package surrealchat.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import surrealchat.exception.SurrealException;
import surrealchat.task.DeadlineTask;
import surrealchat.task.Task;
import surrealchat.task.TaskManagement;
import surrealchat.task.TaskPriority;

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

    private LocalDateTime parseDate(String dateString) throws SurrealException {
        try {
            return LocalDateTime.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new SurrealException("Input date time format is incorrect. Not stonks!\n");
        }
    }

    private DeadlineTask addDeadline(String taskDescription) throws SurrealException {
        if (taskDescription.isEmpty()) {
            throw new SurrealException("Empty deadline task description. Not stonks!\n");
        }

        //Split the description into description, deadline and priority
        String[] descriptionSplitArray = taskDescription.split(";");
        try {
            LocalDateTime deadlineDateTime = parseDate(descriptionSplitArray[1].trim());
            int intPriority = Integer.valueOf(descriptionSplitArray[2].trim());
            TaskPriority taskPriority = TaskPriority.getPriorityType(intPriority);
            //Create Deadline task
            return DeadlineTask.createNewDeadlineTask(descriptionSplitArray[0].trim(),
                    deadlineDateTime, taskPriority);
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new SurrealException("Wrong formatting. Did you forget to put ';'? Not stonks!\n");
        } catch (NumberFormatException e) { //Happens if correct int is not passed in for priority
            throw new SurrealException("Priority argument must be integer in range 1-3! Not stonks!\n");
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
        try {
            DeadlineTask addedTask = addDeadline(rawDescription);
            taskManagement.addTask(addedTask);
            return printOutput(addedTask, taskManagement.getNumberOfTasks());
        } catch (SurrealException e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of deadline command.
     * @return String describing the deadline command.
     */
    public static String displayHelp() {
        String outputString = "Given a description and deadline, stores deadline task.\n";
        outputString += "Format of arguments: deadline [description] ; [deadline] ; [priority]\n";
        outputString += "[deadline] must be of the form {YYYY-MM-DD}T{HH:MM:SS} in 24 hour clock\n";
        return outputString;
    }
}
