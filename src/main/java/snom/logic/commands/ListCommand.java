package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.storage.StorageManager;
import snom.model.task.TaskList;
import snom.ui.Snomio;

public class ListCommand extends Command{
    public ListCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes list command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of snom.model.Snom
     * @param storage          snom.files handler of snom
     * @return CommandResponse response after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        return new CommandResponse(snomio.getTaskList(taskList), false);
    }
}
