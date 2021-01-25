package ekud.command;

import ekud.common.exception.*;
import ekud.storage.*;
import ekud.task.*;
import ekud.ui.*;

public abstract class ModificationCommand extends Command {
    /**
     * Generate summary of how many tasks are left
     *
     * @param tasks the list of tasks
     * @return string containing task summary
     */
    protected static String getTasksLeftString(TaskList tasks) {
        return String.format("Err you got %d task%s in total leh", tasks.size(), tasks.size() <= 1 ? "" : "s");
    }

    /**
     * Save all tasks to disk
     *
     * @param tasks   the list of tasks
     * @param ui      the user interface
     * @param storage the file writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
    }
}
