package duke.command;

import duke.TaskList.Action;

public class AddCommand extends Command {
    private final String[] args;

    /**
     * Create new Add command
     *
     * @param args Parameters describing what to add
     */
    public AddCommand(String[] args) {
        this.args = args;
    }

    @Override
    public String[] getCommandParameters() {
        return args;
    }

    @Override
    public Action getType() {
        return Action.ADD;
    }
}
