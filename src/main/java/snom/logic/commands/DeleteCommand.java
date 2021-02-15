package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.logic.Parser;
import snom.model.task.Task;
import snom.model.task.TaskList;
import snom.storage.StorageManager;
import snom.ui.Snomio;

/**
 * Deletes {@code Task} from {@code TaskList}
 */
public class DeleteCommand extends Command {
    public DeleteCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes delete command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of Snom
     * @return                 {@code CommandResponse} after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        int[] deleteList = Parser.parseTaskNumbers(this.content);
        Task[] deletedTasks = taskList.deleteTask(deleteList);
        storage.saveFile(taskList);
        return new CommandResponse(snomio.getDeletedTasks(deletedTasks), false);
    }
}
