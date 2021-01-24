package duke.command;

import duke.TaskList;
import duke.TaskStorage;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    private int completedTaskIdx;

    public DoneCommand(int completedTaskIdx) {
        this.completedTaskIdx = completedTaskIdx;
    }

    public void execute(TaskList tasks, Ui ui, TaskStorage storage) {
        Task completedTask = tasks.getTasks().get(completedTaskIdx);
        completedTask.markDone();
        storage.storeData(tasks);
        ui.print("Nice! I have marked this task as done:\n\t\t " + completedTask);
    }
}
