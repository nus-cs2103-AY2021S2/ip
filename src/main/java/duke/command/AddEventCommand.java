package duke.command;
import duke.task.Event;
import duke.exception.DukeException;
import duke.task.TaskList;
/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it will receive the requests from the users
 * during the running of the program and starts add new events to the task list.
 */
public class AddEventCommand extends Command {

    /**
     * Constructor for AddEventCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public AddEventCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it will add a event object into the tasks.
     * If the input is not correct, it will raise an exception.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     * @throws DukeException if there are some cases such as no event time specified, no event task name, then
     * it will raise the DukeException.
     */
    public String execute(TaskList taskList) throws DukeException {
        StringBuilder builder = new StringBuilder();
        builder.append("Got it! I've added this task:\n");
        int spaceIndex = userMessage.indexOf(" ");
        int dateIndex = userMessage.indexOf('/');
        if (dateIndex == -1) {
            throw new DukeException("OOPS!!! I can't find your event time.");
        }
        if (spaceIndex == -1 || dateIndex - spaceIndex == 1) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        String eventName = userMessage.substring(spaceIndex + 1,dateIndex - 1);
        String at = userMessage.substring(dateIndex + 4);
        Event event;

        try {
            event = new Event(eventName, at);
        } catch (Exception e) {
            throw new DukeException("OOPS! The input format is wrong! Should be YYYY-MM-DD HH:MM");
        }

        taskList.addTasks(event);

        builder.append("[" + event.getStatusIcon() + "] " + event.toString());
        builder.append("\nNow you have " + Integer.toString(taskList.getNumOfTasks()) + " tasks in the list.");
        String botMessage = builder.toString();
        return botMessage;
    }
}
