package duke.command;

import duke.*;

import java.util.ArrayList;

public class DoneCommand extends Command{
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.setTaskIsDone(index,true);
        System.out.printf(">>> Nice! I've marked this task as done:\n  [%s] [%s] %s\n",
                taskList..getTaskType(), newTask.getStatusIcon(), newTask.getTaskDescription());
    }
}
