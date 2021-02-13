package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkTaskAsDoneCommand extends Command {

    private static final String markAsDoneSuccess = "Task marked as done.";
    private static final Boolean toExit = false;

    private Integer taskIndex;

    /**
     * Initialises mark as done command by specifying
     * the task index to mark done.
     *
     * @param taskIndex Task index.
     */
    public MarkTaskAsDoneCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks specific task from the task list as done.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @return Command execution response.
     * @throws DukeException If invalid task index specified.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markDone(this.taskIndex);
        return new CommandResponse(MarkTaskAsDoneCommand.markAsDoneSuccess, MarkTaskAsDoneCommand.toExit);
    }
}
