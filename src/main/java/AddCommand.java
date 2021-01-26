import java.io.IOException;

public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        tasklist.addTask(task);
        ui.showAddTask(tasklist);
        storage.writeToFile(tasklist);
    }
}
