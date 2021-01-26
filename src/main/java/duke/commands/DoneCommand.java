package duke.commands;

import duke.exceptions.IndexOutOfBoundException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(int taskNum) {
        super(CommandType.DONE);
        this.taskNum = taskNum;
    }

    @Override
    public void excecute(TaskList list) {
        Task curr = list.markCompleted(taskNum - 1);
        ui.printCompletedMsg(curr);
    }
}
