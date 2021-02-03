package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * A ByeCommand class to exit program.
 */
public class ByeCommand extends Command {
    /**
     * Constructs ByeCommand.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes program exit.
     *
     * @param taskList The list of tasks.
     * @param storage  The storage handler.
     * @return Output for GUI.
     * @throws DukeException If there is storage operation error.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        storage.clear();
        storage.save(taskList.getTaskList());
        return "     Bye. Hope to see you again soon!";
    }
}
