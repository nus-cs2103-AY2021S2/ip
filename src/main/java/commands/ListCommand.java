package commands;

import data.TaskList;
import ui.TextUi;

public class ListCommand extends Command {
    public static final String COMMAND_TEXT = "list";

    /**
     * Returns the tasks message
     *
     * @param tasks
     * @param ui
     * @return response message
     */
    @Override
    public String execute(TaskList tasks, TextUi ui) {
        return ui.getTasksMessage(tasks);
    }
}
