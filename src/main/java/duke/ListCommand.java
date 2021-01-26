package duke;

public class ListCommand extends Command {

    ListCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        ui.showAllTasks(taskManager.getList());
    }
}
