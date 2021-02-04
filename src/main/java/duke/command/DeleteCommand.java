package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private final int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        Task task = tasks.delete(indexToDelete);
        try {
            storage.saveTasks(tasks);
        } catch (IOException err) {
            this.isExit = true;
        }
        String messageToDisplay = "Noted. I've removed this task:\n\t" + task
                + "\n" + Ui.getDisplayOfNumberOfTasks(tasks);
        ui.setDisplayMessage(messageToDisplay);
    }
}
