package duke.command;

import duke.DukeException;
import duke.Ui;

public class ByeCommand extends Command {
    @Override
    public void process() throws DukeException {
        Ui.displayFarewell();
    }
}
