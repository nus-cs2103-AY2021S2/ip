package duke.command;

import duke.data.DataStorage;
import duke.exception.DukeException;
import duke.TaskList.TaskList;
import duke.UI.UI;

/**
 * Create delete command object
 */
public class DeleteCommand extends Command {

    /**
     * Constructor to create delete command object
     */
    public DeleteCommand(String input, TaskList tl) {
        super(input,tl);
    }

    /** Delete task
     *  Store the new changes back to data file
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws DukeException
     */
    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage) throws DukeException {
        tasks.deleteTask(Integer.parseInt(input)-1);
        storage.save(tasks.getTaskListArray());
        return tasks;
    }
}
