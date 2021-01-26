package duke;
import java.io.IOException;

public class AddCommand extends Command {

    AddCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws IOException {
        String taskType = super.parsedCommand[0];
        String description = super.parsedCommand[1];
        Task addedTask = taskManager.add(taskType, description, false);
        ui.showAddedTask(addedTask, taskManager.getNumOfTasks());
        storage.store(taskManager.retrieveTasksforStorage());
    }
}
