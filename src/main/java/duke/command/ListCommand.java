package duke.command;

import duke.DukeException;

import static duke.Ui.displayAllTasks;

public class ListCommand extends Command {
    @Override
    public void process() throws DukeException {
        displayAllTasks();
    }
}
