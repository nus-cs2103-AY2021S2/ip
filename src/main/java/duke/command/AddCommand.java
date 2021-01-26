package duke;

public class AddCommand extends Command{

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(task);
        ui.showAddMessage(task, taskList.size());
        storage.saveFile(taskList);
    }
}
