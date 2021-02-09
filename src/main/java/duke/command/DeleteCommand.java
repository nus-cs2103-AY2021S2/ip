package duke.command;

import duke.TaskList.Action;

public class DeleteCommand extends IndexedCommand{
    /**
     * Create command to delete Task from TaskList
     * @param position One index position in TaskList of Task to delete
     */
    public DeleteCommand(int position) {
        super(position);
    }

    @Override
    public Action getType() {
        return Action.DELETE;
    }
}
