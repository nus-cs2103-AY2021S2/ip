package duke.command;

import duke.DukeException;
import duke.DukeList;
import duke.Ui;
import duke.io.Storage;

public class CBye implements ICommand {
    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        return ui.sayGoodbye();
    }
}
