package commands;

import tasklist.TaskList;
import tasks.Deadline;

public class DeadlineCommand extends AddTaskWithTimeCommand {

    public DeadlineCommand(String commandBody) {
        super("done", commandBody);
        this.timeArgDelimiter = "/by";
    }

    @Override
    public void run(TaskList taskList) {
        try {
            handleSplittingArgs();
            this.commandOutputMsg = taskList.addTask(new Deadline(secondArg, thirdArg));
        } catch (Exception e) {
            handleException(e);
        }
    }
}
