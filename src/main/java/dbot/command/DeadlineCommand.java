package dbot.command;

import dbot.ui.Ui;
import dbot.storage.Storage;
import dbot.task.Deadline;
import dbot.task.Task;
import dbot.tasklist.TaskList;

import java.time.LocalDate;

/**
 * A concrete subclass of Command that implements a Deadline Command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private Task task;
    private LocalDate by;

    /**
     * Initializes a Deadline Command with the specified description String and date.
     *
     * @param description A String containing the description that this Command's relevant Task should have.
     * @param by A LocalDate representing that date of this Command's relevant Task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        quietExecute(tasks, storage);
        ui.printAddTask(task);
    }

    @Override
    public void quietExecute(TaskList tasks, Storage storage) {
        task = new Deadline(getDescription(), by);
        task.setDone(getIsDone());
        tasks.add(task);
    }
}
