package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.setTaskIsDone(index, true);
        storage.saveData(taskList);
        return ui.showTaskDone(taskList.getTask(index));
    }
}
