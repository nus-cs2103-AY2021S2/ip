package duke.command;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.TaskList;
/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it receives the requests from the users
 * during the running of the program and starts add new deadlines to the task list.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Constructor for AddDeadlineCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public AddDeadlineCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it adds a deadline object into the tasks.
     * If the input is not correct, it raises an exception.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     * @throws DukeException if there are some cases such as no deadline time specified, no deadline task name, then
     * it will raise the DukeException.
     */
    public String execute(TaskList taskList) throws DukeException {
        int spaceIndex = userMessage.indexOf(" ");
        int dateTimeIndex = userMessage.indexOf('/');
        int priorityIndex = userMessage.indexOf("-p");
        boolean noDateTime = dateTimeIndex == -1;
        boolean noEventName = spaceIndex == -1;
        boolean hasPriority = !(priorityIndex == -1);

        if (noDateTime) {
            throw new DukeException("OOPS!!! I can't find your deadline time.");
        }
        if (noEventName) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Got it! I've added this task:\n");

        Deadline deadline;

        if (hasPriority) {
            String deadlineName = userMessage.substring(spaceIndex + 1, dateTimeIndex - 1);
            String by = userMessage.substring(dateTimeIndex + 4, priorityIndex - 1);
            int priority;

            try {
                priority = Integer.parseInt(userMessage.substring(priorityIndex + 3));
                if (priority < 1 || priority > 5) {
                    throw new DukeException("OOPS!!! Please use integer range from 1-5 as the level of priority!");
                }
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Please use integer range from 1-5 as the level of priority!");
            }

            try {
                deadline = new Deadline(deadlineName, by, false, priority);
            } catch (Exception e) {
                throw new DukeException("OOPS! The input format is wrong! Should be YYYY-MM-DD HH:MM");
            }

        } else {
            try {
                String deadlineName = userMessage.substring(spaceIndex + 1, dateTimeIndex - 1);
                String by = userMessage.substring(dateTimeIndex + 4);
                deadline = new Deadline(deadlineName, by);
            } catch (Exception e) {
                throw new DukeException("OOPS! The input format is wrong! Should be YYYY-MM-DD HH:MM");
            }
        }
        taskList.addTask(deadline);

        builder.append("[" + deadline.getStatusIcon() + "] " + deadline.toString() + " " + deadline.getPriorityIcon());
        builder.append("\nNow you have " + taskList.getNumOfTasks() + " tasks in the list.");
        String botMessage = builder.toString();
        return botMessage;
    }
}
