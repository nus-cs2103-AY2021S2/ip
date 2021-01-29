package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Snomio;

public class FinishCommand extends Command{
    public FinishCommand(CommandEnum type) {
        super(type);
    }

    /**
     * Executes finish command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @throws SnomException   if command execution failed
     */
    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        int[] finishList = snomio.readContentWithNumbers(type.name());
        Task[] finishedTasks = taskList.finishTask(finishList);
        snomio.showFinishedTasks(finishedTasks);
        storage.saveFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
