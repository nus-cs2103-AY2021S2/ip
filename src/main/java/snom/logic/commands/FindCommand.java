package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.model.task.TaskList;
import snom.storage.StorageManager;
import snom.ui.Snomio;

/**
 * Searches for specific {@code Task} in the {@code TaskList}
 */
public class FindCommand extends Command {
    public FindCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes find command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @return                 {@code CommandResponse} after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        TaskList foundTaskList = new TaskList(taskList.findTask(this.content));
        return new CommandResponse(snomio.getMatchingTaskList(foundTaskList), false);
    }
}
