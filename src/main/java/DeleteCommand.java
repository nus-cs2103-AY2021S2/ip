import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task toDelete = taskList.getTasks().get(this.taskNum - 1);
        taskList.deleteTask(this.taskNum - 1);
        storage.update(taskList);
        ui.printDeleteTask(toDelete, taskList);
    }
}
