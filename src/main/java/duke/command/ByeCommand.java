package duke.command;

import java.io.File;

import duke.duke.Duke;
import duke.ui.Ui;

/**
 * Exits the application.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super("bye");
    }

    @Override
    public String run(File file, Duke bot) {
        return Ui.showExitMessage();
    }
}
