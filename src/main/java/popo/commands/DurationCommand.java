package popo.commands;

import static popo.utils.Messages.MESSAGE_ADDED_TASK;
import static popo.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

import java.time.Duration;

import popo.tasks.DurationTask;

/**
 * Creates a Duration task.
 */
public class DurationCommand extends Command {
    public static final String COMMAND_WORD = "duration";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Add a task with a specified duration\n"
            + "Usage 1: duration <task_description> /days <number>\n"
            + "Example: duration Read report /days 3\n"
            + "Usage 2: duration <task_description> /hours <number>\n"
            + "Example: duration Practice exam /hours 2\n"
            + "Usage 3: duration <task_description> /minutes <number>\n"
            + "Example: duration Eat lunch /minutes 30";

    private final String taskName;
    private final Duration duration;

    /**
     * Creates a {@code DurationCommand} object with the given task name and duration.
     *
     * @param taskName Name of the task.
     * @param duration Duration of the task.
     */
    public DurationCommand(String taskName, Duration duration) {
        this.taskName = taskName;
        this.duration = duration;
    }

    @Override
    public CommandResult execute() {
        assert taskList != null;
        DurationTask task = new DurationTask(taskName, duration);
        taskList.addTask(task);
        return new CommandResult(taskList, false,
                MESSAGE_ADDED_TASK + "\n",
                task.toString() + "\n",
                String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()));
    }
}
