package surrealchat.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import surrealchat.exception.SurrealException;
import surrealchat.task.EventTask;
import surrealchat.task.Task;
import surrealchat.task.TaskManagement;
import surrealchat.task.TaskPriority;

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

    private LocalDateTime parseDate(String dateString) throws SurrealException {
        try {
            return LocalDateTime.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new SurrealException("Input date time format is incorrect. Not stonks!\n");
        }
    }

    private EventTask addEvent(String taskDescription) throws SurrealException {
        if (taskDescription.isEmpty()) {
            throw new SurrealException("Empty event task description. Not stonks!\n");
        }

        //Split the description into description, event and priority
        String[] descriptionSplitArray = taskDescription.split(";");
        try {
            LocalDateTime eventDateTime = parseDate(descriptionSplitArray[1].trim());
            int intPriority = Integer.valueOf(descriptionSplitArray[2].trim());
            TaskPriority taskPriority = TaskPriority.getPriorityType(intPriority);
            //Create Deadline task
            return EventTask.createNewEventTask(descriptionSplitArray[0].trim(),
                    eventDateTime, taskPriority);
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new SurrealException("Wrong formatting. Did you forget to put ';'? Not stonks!\n");
        } catch (NumberFormatException e) { //Happens if correct int is not passed in for priority
            throw new SurrealException("Priority argument must be integer in range 1-3! Not stonks!\n");
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
        } catch (SurrealException e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of event command.
     * @return String describing the event command.
     */
    public static String displayHelp() {
        String outputString = "Given a description and event date, stores event task.\n";
        outputString += "Format of arguments: event [description] ; [event date and time] ; [priority]\n";
        outputString += "[event date and time] must be of the form {YYYY-MM-DD}T{HH:MM:SS} in 24 hour clock\n";
        return outputString;
    }
}
