package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the execution of List Command
 */
public class ListCmd extends Command {

    /**
     * Returns the response of the bot after execution of command
     *
     * @param lst TaskList
     * @return response
     */
    @Override
    public String execute(TaskList lst) {
        assert lst != null : "TaskList parameter should not be null";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            sb.append(String.format("%d. %s\n", i + 1, task.toString()));
        }
        return sb.toString();
    }
}
