package quackers.command;

import quackers.QuackersException;
import quackers.storage.Storage;
import quackers.tasklist.TaskList;
import quackers.ui.Ui;
import quackers.task.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {

    private static final boolean toExit = false;

    private Deadline deadline;

    public AddDeadlineCommand(String desc, LocalDateTime dateTime) {
        this.deadline = new Deadline(desc, dateTime);
    }

    @Override
    public CommandResponse getResponse(TaskList tasks,
                                       Storage storage) throws QuackersException {
        tasks.addTask(this.deadline);
        storage.save(tasks);

        String message = Ui.getAddTaskSuccess(this.deadline);
        return new CommandResponse(AddDeadlineCommand.class,
                message, AddDeadlineCommand.toExit);
    }
}
