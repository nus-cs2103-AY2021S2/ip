package commands;

import data.Task;
import data.TaskList;
import ui.TextUi;

public class DoneCommand extends Command {
    public static final String COMMAND_TEXT = "done";

    private int indexToSetDone;

    public DoneCommand(int indexToSetDone) {
        this.indexToSetDone = indexToSetDone;
    }

    /**
     * Marks the selected task as done and returns the corresponding acknowledgement message
     *
     * @param tasks
     * @param ui
     * @return response message
     */
    @Override
    public String execute(TaskList tasks, TextUi ui) {
        if (indexToSetDone < 0 || indexToSetDone >= tasks.size()) {
            return ui.getFormattedMessage("Invalid number.");
        }

        Task task = tasks.get(indexToSetDone);
        task.markAsDone();
        return ui.getDoneTaskMessage(task);
    }
}
