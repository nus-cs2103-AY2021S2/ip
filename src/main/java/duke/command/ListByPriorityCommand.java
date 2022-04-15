package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Collections;
import java.util.LinkedList;

public class ListByPriorityCommand extends Command {
    /**
     * Constructor for ListCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public ListByPriorityCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it lists out all the current tasks and sorted by priority.
     *
     * @param taskList The current taskList in the program.
     * @return The Duke robot massage to the GUI.
     * @throws DukeException if there is no task in the list.
     */
    public String execute(TaskList taskList) throws DukeException {
        int numOfTasks = taskList.getNumOfTasks();
        if (numOfTasks == 0) {
            throw new DukeException("OOPS!!! No task right now!");
        }

        LinkedList<Task> tasks = taskList.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list sorted by priority\n");
        Collections.sort(tasks);
        Collections.reverse(tasks);
        for (int i = 0; i < numOfTasks; i++) {
            Task task = tasks.get(i);
            String taskName = task.toString();
            String icon = task.getStatusIcon();
            String index = Integer.toString(i + 1);
            String priorityIcon = task.getPriorityIcon();
            builder.append(index + ". " + "[" + icon + "]");
            builder.append(taskName + " " + priorityIcon + "\n");
        }

        String botMessage = builder.toString();
        return botMessage;

    }

}
