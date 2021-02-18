package bearbear.command;

import java.io.File;

import bearbear.bearbear.BearBear;

/**
 * Lists the current tasks.
 */
public class ListCommand extends Command {
    private static final String usageMessage = "Command: list\n"
            + "Description: Shows all available tasks\n";

    public ListCommand() {
        super("list");
    }

    public static String getUsageMessage() {
        return usageMessage;
    }

    @Override
    public String run(File file, BearBear bot) {
        return bot.showTasks();
    }
}
