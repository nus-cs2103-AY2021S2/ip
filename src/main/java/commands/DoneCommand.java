package commands;

import java.io.IOException;

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
     * Marks the selected task as done and outputs the corresponding acknowledgement message
     * @param tasks
     * @param ui
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        if (indexToSetDone < 0 || indexToSetDone >= tasks.size()) {
            ui.write("Invalid number.");
            return;
        }

        Task task = tasks.get(indexToSetDone);
        task.markAsDone();
        ui.writeDoneTask(task);
    }
}
