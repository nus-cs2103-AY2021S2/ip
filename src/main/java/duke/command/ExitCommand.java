package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command for exit input
 */
public class ExitCommand extends Command {

    /**
     * Saves storage one last time and prints exit message
     * @param task
     * @param taskList the current instance of task list used by Duke
     * @param storage the storage instance used to save files into internal storage
     */
    @Override
    public void execute(Task task, TaskList taskList, Storage storage) {
        // do one final save of task list into storage
        storage.saveTasksToStorage(taskList);

        // print exit message
        Ui.printDukeExitMessage();
    }

    /**
     * Returns false since Duke is shutting down
     * @return false
     */
    @Override
    public boolean isDukeOnline() {
        return false;
    }

}
