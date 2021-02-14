package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A ListCommand class to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructs ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the listing of tasks.
     *
     * @param tasks The list of tasks.
     * @param storage  The storage handler.
     * @return Output for GUI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.list();
    }
}
