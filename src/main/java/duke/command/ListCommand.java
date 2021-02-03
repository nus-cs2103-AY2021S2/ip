package duke.command;

import java.io.File;

import duke.duke.Duke;

/**
 * Lists the current tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    @Override
    public String run(File file, Duke bot) {
        return bot.showTasks();
    }
}
