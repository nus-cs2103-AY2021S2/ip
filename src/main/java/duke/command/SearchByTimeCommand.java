package duke.command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;


/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it receives the requests from the users
 * during the running of the program and starts to search the task that matches the time
 * user asks.
 */
public class SearchByTimeCommand extends Command {
    /**
     * Constructor for SearchByTimeCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public SearchByTimeCommand(String userMessage) {
        super(userMessage);
    }


    /**
     * The execution after parsing, it searches relevant tasks based on the time.
     * If the input is not correct, it raises an exception.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     * @throws DukeException if there are some cases such as the input time format is wrong.
     */
    public String execute(TaskList taskList) throws DukeException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] info;
        LocalDateTime time;

        try {
            info = userMessage.split(" ", 3);
            time = LocalDateTime.parse(info[2], df);
        } catch (Exception e) {
            throw new DukeException("The search input format is wrong, the format should be: \n" + "search time yyyy-MM-dd HH:mm!");
        }

        LinkedList<Task> tasks = taskList.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the search results: \n");
        int numOfTasksFound = 0;

        for (Task single : tasks) {
            if (single instanceof ToDo) {
                continue;
            } else if (single instanceof Event) {
                LocalDateTime eventTime = ((Event) single).getAt();
                if (eventTime.isEqual(time)) {
                    builder.append("[" + single.getStatusIcon() + "]" + single.toString() + " " + single.getPriorityIcon() + "\n");
                    numOfTasksFound++;
                }
            } else if (single instanceof Deadline) {
                LocalDateTime deadlineTime = ((Deadline) single).getBy();
                if (deadlineTime.isEqual(time)) {
                    builder.append("[" + single.getStatusIcon() + "]" + single.toString() + " " + single.getPriorityIcon() + "\n");
                    numOfTasksFound++;
                }
            }
        }

        if (numOfTasksFound == 0) {
            throw new DukeException("OOPS! There is no task that matches the time.");
        }

        String botMessage = builder.toString();
        return botMessage;

    }
}
