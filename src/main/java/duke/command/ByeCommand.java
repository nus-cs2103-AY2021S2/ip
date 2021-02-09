package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(null);
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasksToFile(tasks);
        String dukeResponse = "Bye bye!";
        return dukeResponse;
    }
}
