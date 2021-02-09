package duke.command;

import java.io.File;

import duke.duke.Duke;
import duke.ui.Ui;

/**
 * Exits the application.
 */
public class ByeCommand extends Command {
    private static final String usageMessage = "Command: bye\n"
            + "Description: Exits program\n";

    public ByeCommand() {
        super("bye");
    }

    public static String getUsageMessage() {
        return usageMessage;
    }

    @Override
    public String run(File file, Duke bot) {
        return Ui.showExitMessage();
    }
}
