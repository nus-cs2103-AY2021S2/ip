import java.io.IOException;

public class DoneCommand extends Command{
    int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        tasklist.markTask(taskIndex);
        ui.showMarkTask(tasklist.getList().get(taskIndex));
        storage.writeToFile(tasklist);
    }
}
