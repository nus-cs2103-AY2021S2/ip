package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Snomio;

public class DeleteCommand extends Command{
    public DeleteCommand(CommandEnum type) {
        super(type);
    }

    /**
     * Executes delete commmand
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @throws SnomException   if command execution failed
     */
    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        int[] deleteList = snomio.readContentWithNumbers(type.name());
        Task[] deletedTasks = taskList.deleteTask(deleteList);
        snomio.showDeletedTasks(deletedTasks);
        storage.saveFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
