package duke.command;

import duke.common.DukeString;
import duke.task.TaskList;

public class ExitCommand implements Command {
    /**
     * Return the shutdown message.
     * @param taskList the task list to be modified by the command
     * @return the shutdown message for Duke
     */
    @Override
    public String execute(final TaskList taskList) {
        return DukeString.MESSAGE_BYE;
    }
}
