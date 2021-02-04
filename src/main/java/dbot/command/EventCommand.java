package dbot.command;

import dbot.ui.Ui;
import dbot.storage.Storage;
import dbot.task.Event;
import dbot.task.Task;
import dbot.tasklist.TaskList;

import java.time.LocalDate;

/**
 * A concrete subclass of Command that implements an Event Command.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private Task task;
    private LocalDate at;

    /**
     * Initializes an Event Command with the specified description String and date.
     *
     * @param description A String containing the description that this Command's relevant Task should have.
     * @param at A LocalDate representing that date of this Command's relevant Task.
     */
    public EventCommand(String description, LocalDate at) {
        super(description);
        this.at = at;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        quietExecute(tasks, storage);
        ui.printAddTask(task);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) {
        task = new Event(getDescription(), at);
        task.setDone(getIsDone());
        tasks.add(task);
    }
}
