package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.TaskList;
import ui.Snomio;

public class ExitCommand extends Command{
    public ExitCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes exit command
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @throws SnomException   if command execution failed
     * @return CommandResponse response after command execution
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        return new CommandResponse(snomio.showExitMessage(), true);
    }
}
