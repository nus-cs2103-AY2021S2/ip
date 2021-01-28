import models.Task;
import models.Todo;

public class TodoCommand implements Command {
    private String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task curTask = new Todo(taskName);
        tasks.addTask(curTask);
        ui.printTaskListStatus(tasks, curTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
