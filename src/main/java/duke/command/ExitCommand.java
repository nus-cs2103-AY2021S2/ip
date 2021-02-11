package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        storage.saveTasksToFile(tasks);
        String dukeResponse = "Bye bye!";
        return dukeResponse;
    }
}
