package commands;

import java.io.IOException;

import data.Deadline;
import data.TaskList;
import ui.TextUi;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_TEXT = "deadline";

    private Deadline deadline;

    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        tasks.add(deadline);
        ui.writeAddTask(deadline, tasks);
    }
}
