package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.model.task.TaskList;
import snom.storage.StorageManager;
import snom.ui.Snomio;

/**
 * Closes Application.
 */
public class ExitCommand extends Command {
    public ExitCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes exit command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @return                 {@code CommandResponse} after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        return new CommandResponse(snomio.getExitMessage(), true);
    }
}
