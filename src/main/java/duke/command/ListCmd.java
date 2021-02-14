package duke.command;

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
        return lst.toString();
    }
}
