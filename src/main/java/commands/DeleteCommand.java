package commands;

import data.Task;
import data.TaskList;
import ui.TextUi;

public class DeleteCommand extends Command {
    public static final String COMMAND_TEXT = "delete";

    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Deletes the selected task from the given tasks and returns the corresponding acknowlegement message
     *
     * @param tasks
     * @param ui
     * @return response message
     */
    @Override
    public String execute(TaskList tasks, TextUi ui) {
        if (indexToDelete < 0 || indexToDelete >= tasks.size()) {
            return ui.getFormattedMessage("Invalid number.");
        }

        Task taskToDelete = tasks.get(indexToDelete);
        tasks.remove(indexToDelete);
        return ui.getDeleteTaskMessage(taskToDelete, tasks);
    }
}
