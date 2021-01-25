package duke.command;

import duke.data.DataStorage;
import duke.exception.DukeException;
import duke.TaskList.TaskList;
import duke.UI.UI;

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
     * @throws DukeException
     */
    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage) throws DukeException {
        tasks.markAsDone(Integer.parseInt(input)-1);
        storage.save(tasks.getTaskListArray());
        return tasks;
    }
}
