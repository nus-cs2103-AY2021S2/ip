package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;


import java.util.LinkedList;

/**
 * It is a command object extends from Command for the Duke program.
 * When the parser calls it, it receives the requests from the users
 * during the running of the program and starts to search the task that matches the name
 * user asks.
 */
public class SearchByTaskNameCommand extends Command {
    /**
     * Constructor for SearchByTaskNameCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public SearchByTaskNameCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it searches relevant tasks based on the task name.
     * If the input is not correct, it raises an exception.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     * @throws DukeException if there are some cases such as the input time format is wrong.
     */
    public String execute(TaskList taskList) throws DukeException {
        String[] info;
        String name;

        try {
            info = userMessage.split(" ", 2);
            name = info[1];
        } catch (Exception e) {
            throw new DukeException("The search input format is wrong, the format should be: \n"
                    + "search name <The task name>");
        }

        LinkedList<Task> tasks = taskList.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the search results: \n");
        int numOfTasksFound = 0;

        // Search by loop
        for (Task task : tasks) {
            String singleName = task.getTaskName();
            if (singleName.contains(name)) {
                builder.append("[" + task.getStatusIcon() + "]" + task.toString() + " " + task.getPriorityIcon() + "\n");
                numOfTasksFound++;
            }
        }
        if (numOfTasksFound == 0) {
            throw new DukeException("OOPS! There is no task that matches the name.");
        }

        String botMessage = builder.toString();
        return botMessage;

    }
}
