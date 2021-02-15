package snom.logic.commands;

import snom.common.exceptions.SnomException;
import snom.logic.Parser;
import snom.model.task.Task;
import snom.model.task.TaskList;
import snom.storage.StorageManager;
import snom.ui.Snomio;

/**
 * Marks specific {@code Task} as finished
 */
public class FinishCommand extends Command {
    public FinishCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes finish command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @return                 {@code CommandResponse} after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        int[] finishList = Parser.parseTaskNumbers(this.content);
        Task[] finishedTasks = taskList.finishTask(finishList);
        storage.saveFile(taskList);
        return new CommandResponse(snomio.getFinishedTasks(finishedTasks), false);
    }
}
