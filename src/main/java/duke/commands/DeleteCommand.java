package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        super(CommandType.DELETE);
        this.taskNum = taskNum;
    }


    @Override
    public void excecute(TaskList list) {
        Task curr = list.delete(taskNum - 1);
        ui.printDeleteMsg(curr, list.size());
    }
}
