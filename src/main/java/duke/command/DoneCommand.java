package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DoneCommand extends Command{
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.setTaskIsDone(index,true);
        // System.out.printf(">>> Nice! I've marked this task as done:\n  [%s] [%s] %s\n",
        //         taskList..getTaskType(), newTask.getStatusIcon(), newTask.getTaskDescription());
        storage.saveData(taskList);
        return ui.printTaskDone(taskList.getTask(index));
    }
}
