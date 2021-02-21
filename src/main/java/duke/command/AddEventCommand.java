package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {

    private static final Boolean toExit = false;

    private Event event;

    public AddEventCommand(String desc, LocalDateTime dateTime) {
        this.event = new Event(desc, dateTime);
    }

    @Override
    public CommandResponse getResponse(TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(this.event);

        String message = Ui.getAddTaskSuccess(this.event);
        return new CommandResponse(message, AddEventCommand.toExit);
    }
}
