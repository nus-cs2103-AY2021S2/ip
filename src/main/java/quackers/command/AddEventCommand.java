package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {

    private static final boolean toExit = false;

    private Event event;

    public AddEventCommand(String desc, LocalDateTime dateTime) {
        this.event = new Event(desc, dateTime);
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        tasks.addTask(this.event);
        storage.save(tasks);

        String message = Ui.getAddTaskSuccess(this.event);
        return new CommandResponse(AddEventCommand.class,
                message, AddEventCommand.toExit);
    }
}
