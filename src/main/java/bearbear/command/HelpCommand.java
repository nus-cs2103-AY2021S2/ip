package bearbear.command;

import java.io.File;
import java.io.IOException;

import bearbear.bearbear.BearBear;
import bearbear.ui.Ui;

/**
 * Show help menu to user.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public String run(File file, BearBear bot) throws IOException {
        return Ui.showHelpMessage();
    }
}
