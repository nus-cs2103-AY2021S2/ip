import java.io.IOException;

public class ListCommand extends Command{

    ListCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException, IOException {
        ui.showAllTasks(taskManager.getList());
    }
}
