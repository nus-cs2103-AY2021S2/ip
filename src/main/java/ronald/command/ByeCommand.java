package ronald.command;

import ronald.ui.Ui;

/**
 * Class containing data and methods specific to a Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Displays farewell message.
     */
    @Override
    public void process() {
        Ui.displayFarewell();
    }
}
