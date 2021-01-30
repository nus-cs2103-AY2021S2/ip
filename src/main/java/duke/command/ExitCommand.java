package duke.command;

import duke.storage.DukeFileWriter;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {


    @Override
    public void execute(Task task, TaskList taskList, Storage storage) {
        // do one final save of task list into storage
        storage.saveTasksToStorage(taskList);

        // print exit message
        Ui.printDukeExitMessage();
    }

    @Override
    public boolean isDukeOnline() {
        return false;
    }

}
