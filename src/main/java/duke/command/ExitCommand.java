package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command telling duke to close
 */
public class ExitCommand implements Command {

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        
        try {
            storage.store(tasks);
        } catch (IOException e) {
            throw new DukeException("Cannot save tasks. Save file not found");
        }

        ui.close();
    }
}
