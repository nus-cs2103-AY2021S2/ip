package commands;

import data.Deadline;
import data.TaskList;
import ui.TextUi;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_TEXT = "deadline";

    private Deadline deadline;

    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Adds Deadline into given tasks and returns corresponding acknowledgement message
     *
     * @param tasks
     * @param ui
     */
    @Override
    public String execute(TaskList tasks, TextUi ui) {
        tasks.add(deadline);
        return ui.getAddTaskMessage(deadline, tasks);
    }
}
