import java.io.IOException;

public class DoneCommand extends Command {
    private final int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.markDone(this.taskNum - 1);
        storage.update(taskList);
        ui.printMarkDone(taskList.getTasks().get(this.taskNum - 1));
    }
}
