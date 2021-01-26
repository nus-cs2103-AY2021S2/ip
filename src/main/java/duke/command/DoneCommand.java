package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

/**
 * Create done command class
 */
public class DoneCommand extends Command {

    /**
     * Constructor to create done command object
     */
    public DoneCommand(String input, TaskList taskList) {
        super(input, taskList);
    }

    /** Set and display task as completed
     * Store the new changes back to data file
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage) throws DukeException {
        tasks.markAsDone(Integer.parseInt(input) - 1);
        storage.save(tasks.getTaskListArray());
        return tasks;
    }
}
