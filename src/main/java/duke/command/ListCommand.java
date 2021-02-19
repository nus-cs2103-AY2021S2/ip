package duke.command;

import duke.TaskList.Action;

public class ListCommand extends Command {

    @Override
    public String[] getCommandParameters() {
        return new String[0];
    }

    @Override
    public Action getType() {
        return Action.LIST;
    }
}
