package duke.command;
import duke.task.Event;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

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
        int spaceIndex = userMessage.indexOf(" ");
        int dateTimeIndex = userMessage.indexOf('/');
        boolean noDateTime = dateTimeIndex == -1;
        boolean noEventName = spaceIndex == -1;
        boolean hasPriority = userMessage.substring(userMessage.length() - 4, userMessage.length() - 2).equals("-p");


        if (noDateTime) {
            throw new DukeException("OOPS!!! I can't find your event time.");
        }
        if (noEventName) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Got it! I've added this task:\n");
        Event event;

        if (hasPriority) {
            try {
                String eventName = userMessage.substring(spaceIndex + 1, dateTimeIndex - 1);
                String at = userMessage.substring(dateTimeIndex + 4, userMessage.length() - 5);
                int priority = Integer.parseInt(userMessage.substring(userMessage.length() - 1));
                event = new Event(eventName, at, false, priority);
            } catch (Exception e) {
                throw new DukeException("OOPS! The input format is wrong! Should be YYYY-MM-DD HH:MM");
            }
        } else {
            try {
                String eventName = userMessage.substring(spaceIndex + 1, dateTimeIndex - 1);
                String at = userMessage.substring(dateTimeIndex + 4);
                event = new Event(eventName, at);
            } catch (Exception e) {
                throw new DukeException("OOPS! The input format is wrong! Should be YYYY-MM-DD HH:MM");
            }
        }


        taskList.addTask(event);

        builder.append("[" + event.getStatusIcon() + "] " + event.toString() + " " + event.getPriorityIcon());
        builder.append("\nNow you have " + taskList.getNumOfTasks() + " tasks in the list.");
        String botMessage = builder.toString();
        return botMessage;
    }
}
