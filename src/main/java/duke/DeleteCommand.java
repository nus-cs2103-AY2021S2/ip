package duke;

import java.io.IOException;

public class DeleteCommand extends Command {

    DeleteCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException, IOException {
        String taskNumber = super.parsedCommand[1];
        Task completedTask = taskManager.delete(Integer.parseInt(taskNumber));
        ui.showRemovedTask(completedTask, taskManager.getNumOfTasks());
        storage.store(taskManager.retrieveTasksforStorage());
    }

}
