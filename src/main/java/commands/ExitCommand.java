package commands;

import java.io.IOException;

import data.TaskList;
import ui.TextUi;

public class ExitCommand extends Command {
    public static final String COMMAND_TEXT = "bye";

    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        ui.write("Bye. Hope to see you again soon!");
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
