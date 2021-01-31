package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.TaskList;
import ui.Snomio;

public class ListCommand extends Command{
    public ListCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes list command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @return CommandResponse response after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        return new CommandResponse(snomio.showTaskList(taskList), false);
    }
}
