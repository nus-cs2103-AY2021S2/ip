package commands;

import java.io.IOException;

import data.Task;
import data.TaskList;
import ui.TextUi;

public class DeleteCommand extends Command {
    public static final String COMMAND_TEXT = "delete";

    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        if (indexToDelete < 0 || indexToDelete >= tasks.size()) {
            ui.write("Invalid number.");
            return;
        }

        Task taskToDelete = tasks.get(indexToDelete);
        tasks.remove(indexToDelete);
        ui.writeDeleteTask(taskToDelete, tasks);
    }
}
