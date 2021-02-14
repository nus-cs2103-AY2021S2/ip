package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.storage.StorageManager;
import snom.model.task.TaskList;
import snom.ui.Snomio;

public class ExitCommand extends Command{
    public ExitCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes exit command
     *
     * @param taskList         list of task
     * @param snomio           I/O of snom.model.Snom
     * @param storage          snom.files handler of snom
     * @throws SnomException   if command execution failed
     * @return CommandResponse response after command execution
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        return new CommandResponse(snomio.getExitMessage(), true);
    }
}
