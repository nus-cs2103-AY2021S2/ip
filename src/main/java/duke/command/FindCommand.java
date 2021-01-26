package duke.command;

import duke.common.DukeString;
import duke.task.TaskList;

public class FindCommand implements Command {
    private final String string;

    public FindCommand(String str) {
        this.string = str;
    }

    @Override
    public String execute(TaskList taskList) {
        return DukeString.MESSAGE_FIND + taskList.tasksContaining(this.string);
    }
}
