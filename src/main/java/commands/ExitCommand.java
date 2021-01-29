package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.TaskList;
import ui.Snomio;

public class ExitCommand extends Command{
    public ExitCommand(CommandEnum type) {
        super(type);
    }

    /**
     * Executes exit command
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @throws SnomException   if command execution failed
     */
    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        snomio.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
