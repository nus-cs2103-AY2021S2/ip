package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.TaskList;
import duke.task.Task;

import duke.Ui;

class DoneCommand extends Command{

    DoneCommand(String index) {
        super(null, index, null,null, false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.markTaskAsDone(description);
        ui.showDone(t.toString(), tasks.getSize());
        storage.save(tasks.listOutTaskInString());
    }
}
