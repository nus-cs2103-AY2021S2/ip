package bearbear.command;

import java.io.File;

import bearbear.bearbear.BearBear;

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
    public String run(File file, BearBear bot) {
        System.exit(0);
        return "";
    }
}
