package commands;

import java.io.IOException;

import data.TaskList;
import ui.TextUi;

public class ListCommand extends Command {
    public static final String COMMAND_TEXT = "list";

    /**
     * Outputs the tasks
     * @param tasks
     * @param ui
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        String[] lines = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = (i + 1) + "." + tasks.get(i).toString();
        }

        ui.write(lines);
    }
}
