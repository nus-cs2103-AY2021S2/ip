package duke.command;

import duke.common.DukeString;
import duke.task.TaskList;

public class ExitCommand implements Command {
    @Override
    public String execute(final TaskList taskList) {
        return DukeString.MESSAGE_BYE;
    }
}
