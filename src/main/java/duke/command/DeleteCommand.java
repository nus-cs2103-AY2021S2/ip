package duke.command;

import duke.TaskList.Action;

public class DeleteCommand extends IndexedCommand{
    public DeleteCommand(int position) {
        super(position);
    }

    @Override
    public Action getType() {
        return Action.DELETE;
    }
}
