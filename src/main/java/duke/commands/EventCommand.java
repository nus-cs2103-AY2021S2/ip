package duke.commands;

import duke.tasks.EventTask;

/**
 * Creates an Event task.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:\n  ";
    private static final String TASKLIST_SIZE_MESSAGE_FORMAT = "Now you have %d tasks in your list.";

    private String taskName;
    private String eventTime;

    public EventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    @Override
    public CommandResult execute() {
        EventTask task = new EventTask(taskName, eventTime);
        taskList.addTask(task);
        return new CommandResult(ADDED_TASK_MESSAGE + task.toString() + "\n" +
                String.format(TASKLIST_SIZE_MESSAGE_FORMAT, taskList.size()), taskList);
    }
}
