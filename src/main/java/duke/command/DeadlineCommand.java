package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "deadline";
    private String date;

    private DeadlineCommand(String description) {
        super(description);
    }

    public DeadlineCommand(String description, String date) {
        this(description);
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Deadline(description, date));
        return super.execute(tasks, ui, storage);
    }
}
