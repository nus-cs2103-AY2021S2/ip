package commands;

import java.io.IOException;

import data.TaskList;
import ui.TextUi;

public class ExitCommand extends Command {
    public static final String COMMAND_TEXT = "bye";

    /**
     * Outputs exiting message
     * @param tasks
     * @param ui
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        ui.write("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if given command is an ExitCommand
     * @param command
     * @return
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
