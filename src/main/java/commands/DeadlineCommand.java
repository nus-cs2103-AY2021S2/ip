package commands;

import tasklist.TaskList;
import tasks.Deadline;

public class DeadlineCommand extends AddTaskWithTimeCommand {
    protected String timeArgDelimiter = "/by";

    public DeadlineCommand(String commandBody) {
        super("done", commandBody);
        // todo this hardcoded string...
        // actually why do we need it, why not put it in toString or something
    }

    @Override
    public void run(TaskList taskList) {
        try {
            handleSplittingArgs();
            taskList.addTask(new Deadline(secondArg, thirdArg));
        } catch (Exception e) {
            handleException(e);
        }
        
        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
