package duke.command;

import duke.TaskList.Action;

public class DoneCommand extends IndexedCommand{
    /**
     * Create command to mark Task in TaskList as done
     *
     * @param position One index position in TaskList of Task to mark as done
     */
    public DoneCommand(int position) {
        super(position);
    }

    @Override
    public Action getType() {
        return Action.DONE;
    }
}
