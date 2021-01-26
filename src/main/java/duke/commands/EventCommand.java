package duke.commands;

import duke.tasks.EventTask;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:\n  ";
    private static final String MESSAGE_TASKLIST_SIZE_FORMAT = "Now you have %d tasks in your list.";

    private final String taskName;
    private final String eventTime;

    public EventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    @Override
    public CommandResult execute() {
        EventTask task = new EventTask(taskName, eventTime);
        taskList.addTask(task);
        return new CommandResult(MESSAGE_ADDED_TASK + task.toString() + "\n" +
                String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()), taskList);
    }
}
