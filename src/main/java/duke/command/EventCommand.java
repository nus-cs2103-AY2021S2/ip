package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "event";
    private String dateTime;


    public EventCommand(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        assert description != null;
        assert dateTime != null;

        tasks.add(new Event(description, dateTime));
        return super.execute(tasks, ui, storage);
    }
}
