package duke.command;

import duke.TaskList.Action;

public class DoneCommand extends IndexedCommand {
    public DoneCommand(int position) {
        super(position);
    }

    @Override
    public Action getType() {
        return Action.DONE;
    }
}
