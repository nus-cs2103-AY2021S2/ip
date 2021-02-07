package commands;

import tasklist.TaskList;
import tasks.Deadline;

public class DeadlineCommand extends AddTaskWithTimeCommand {
    protected String timeArgDelimiter = "/by";

    protected DeadlineCommand(String commandBody) {
        super("done", commandBody);
        // todo this hardcoded string...
        // actually why do we need it, why not put it in toString or something
    }

    @Override
    public void run(TaskList taskList) {
        try {
            handleSplittingArgs();
        } catch (Exception e) {
            handleException(e);
        }
        taskList.addTask(new Deadline(secondArg, thirdArg));
    }
}
