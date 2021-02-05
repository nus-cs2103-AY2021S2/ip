package surrealchat.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import surrealchat.task.EventTask;
import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

/**
 * Command object for creating a new EventTask object.
 */
public class EventCommand extends Command {
    protected final String rawDescription;

    /**
     * Creates new EventCommand object.
     * @param rawDescription The description for new EventTask object, inclusive of date.
     */
    public EventCommand(String rawDescription) {
        super("event");
        this.rawDescription = rawDescription;
    }

    private LocalDateTime parseDate(String dateString) {
        try {
            return LocalDateTime.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Input date time format is incorrect. Not stonks!\n");
        }
    }

    private EventTask addEvent(String taskDescription) {
        assert !taskDescription.isEmpty() : "Empty event task description. Not stonks!\n";

        //Split the description into description and event
        String[] descriptionSplitArray = taskDescription.split("/at");
        try {
            LocalDateTime eventDateTime = parseDate(descriptionSplitArray[1].trim());

            //Create Event task
            return EventTask.createNewEventTask(descriptionSplitArray[0].trim(),
                    eventDateTime);
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/at'? Not stonks!\n");
        }
    }

    private String printOutput(Task task, int size) {
        String outputString = String.format("Meme Man has added event task: %s\n", task);
        outputString += String.format("Total number of tasks: %d\n", size);
        return outputString;
    }

    /**
     * Executes event command to generate new EventTask object.
     * @param taskManagement TaskManagement object to which EventTask is added.
     * @return String to be printed upon successful addition of EventTask.
     */
    public String execute(TaskManagement taskManagement) {
        try {
            EventTask addedTask = addEvent(rawDescription);
            taskManagement.addTask(addedTask);
            return printOutput(addedTask, taskManagement.getNumberOfTasks());
        } catch (Throwable e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of event command.
     * @return String describing the event command.
     */
    public static String displayHelp() {
        String outputString = "Given a description and event date, stores event task.\n";
        outputString += "Format of arguments: event [description] /at [event date and time]\n";
        outputString += "[event date and time] must be of the form {YYYY-MM-DD}T{HH:MM:SS} in 24 hour clock\n";
        return outputString;
    }
}
