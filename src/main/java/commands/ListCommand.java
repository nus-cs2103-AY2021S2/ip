package commands;

import java.io.IOException;

import data.TaskList;
import ui.TextUi;

public class ListCommand extends Command {
    public static final String COMMAND_TEXT = "list";

    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        ui.writeTasks(tasks);
    }
}
