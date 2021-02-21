package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {

    private static final boolean toExit = false;

    private Event event;

    public AddEventCommand(String desc, LocalDateTime dateTime) {
        this.event = new Event(desc, dateTime);
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws DukeException {
        tasks.addTask(this.event);

        String message = Ui.getAddTaskSuccess(this.event);
        return new CommandResponse(AddEventCommand.class,
                message, AddEventCommand.toExit);
    }
}
