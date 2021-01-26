package duke;
import java.io.IOException;

public class DoneCommand extends Command{

    DoneCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException, IOException {
        String taskNumber = super.parsedCommand[1];
        Task doneTask = taskManager.done(Integer.parseInt(taskNumber));
        ui.showDoneTask(doneTask);
        storage.store(taskManager.retrieveTasksforStorage());
    }
}
