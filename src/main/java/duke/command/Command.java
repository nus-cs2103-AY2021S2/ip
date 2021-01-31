package duke.command;

import duke.TaskList;

public abstract class Command {
    public abstract String[] run();
    public abstract TaskList.Action getType();
}
