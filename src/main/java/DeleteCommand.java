import java.io.IOException;

public class DeleteCommand extends Command{

    int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        ui.showDeleteTask(tasklist, taskIndex);
        tasklist.deleteTask(taskIndex);
        storage.writeToFile(tasklist);
    }
}
