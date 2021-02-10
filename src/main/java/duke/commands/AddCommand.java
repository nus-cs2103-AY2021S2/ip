package duke.commands;

import static duke.common.Messages.MESSAGE_TASK_ANOMALIES;
import static duke.common.Messages.MESSAGE_TASK_DUPLICATE;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TimedTask;
import duke.ui.Ui;

/**
 * Adds task to the task list.
 */
public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Performs adding of {@code Task} into the task list, prints a message to the user then save the file to storage.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     * @throws DukeException if there were errors encountered when saving the file.
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.checkForDuplicates(task)) {
            throw new DukeException(MESSAGE_TASK_DUPLICATE);
        }

        if (task instanceof TimedTask) {
            if (taskList.checkForAnomalies(((TimedTask) task).getTaskDateTime())) {
                throw new DukeException(MESSAGE_TASK_ANOMALIES);
            }
        }

        taskList.add(task);
        storage.saveFile(taskList);
        return new CommandResponse(ui.showAddMessage(task, taskList.size()));
    }
}
