package duke.command;

import java.io.File;
import java.io.IOException;

import duke.duke.Duke;
import duke.ui.Ui;

/**
 * Show help menu to user.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public String run(File file, Duke bot) throws IOException {
        return Ui.showHelpMessage();
    }
}
