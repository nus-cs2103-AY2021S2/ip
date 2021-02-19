package duke.command;

import duke.TaskList;

public abstract class Command {
    public abstract String[] getCommandParameters();
    public abstract TaskList.Action getType();
}
