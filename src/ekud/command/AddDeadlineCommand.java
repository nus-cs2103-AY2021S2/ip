package ekud.command;

import ekud.common.exception.DukeException;
import ekud.storage.Storage;
import ekud.task.Deadline;
import ekud.task.TaskList;
import ekud.ui.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends AddTimedTaskCommand {
    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    /**
     * Execute this task by adding a deadline into the list, followed by common procedures of all add commands
     * @param tasks   the list of tasks
     * @param ui the user interface
     * @param storage the file writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Deadline(description, dateTime));
        super.execute(tasks, ui, storage);
    }
}
