package commands;

import tasklist.TaskList;
import tasks.Event;

public class EventCommand extends AddTaskWithTimeCommand {

    public EventCommand(String commandBody) {
        super("event", commandBody);
        this.timeArgDelimiter = "/at";
        this.missingThirdArgErrMsg = "missing date/time argument";
    }

    @Override
    public void run(TaskList taskList) {
        try {
            this.parseCommandBody();
            this.commandOutputMsg = taskList.addTask(
                    new Event(secondArg, parseToKiwiDateTime(thirdArg)));
        } catch (Exception e) {
            handleException(e);
        }
        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
