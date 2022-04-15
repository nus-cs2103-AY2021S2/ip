package main.duke.command;

import main.duke.DukeList;
import main.duke.Ui;
import main.duke.io.Storage;

public class CBye implements ICommand {
    /**
     * Check if it is the bye command
     * @return true as it is the bye command
     */
    @Override
    public boolean isBye() {
        return true;
    }

    /**
     *
     * @param ui UI object that deal with the program output
     * @param dukeList Collection of tasks in list form
     * @param storage Storage object that deal with the file system
     */
    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) {
        return ui.sayGoodbye();
    }
}
