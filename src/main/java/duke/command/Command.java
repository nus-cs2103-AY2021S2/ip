package duke.command;

import duke.task.TaskList;

public abstract class Command {
    public abstract String execute(TaskList taskLst);

    public boolean isExit() {
        return false;
    }
}
