package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.logic.Parser;
import snom.storage.StorageManager;
import snom.model.task.Task;
import snom.model.task.TaskList;
import snom.ui.Snomio;

public class DeleteCommand extends Command{
    public DeleteCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes delete commmand
     *
     * @param taskList         list of task
     * @param snomio           I/O of snom.model.Snom
     * @param storage          snom.files handler of snom
     * @return CommandResponse response after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        int[] deleteList = Parser.parseTaskNumbers(this.content);
        Task[] deletedTasks = taskList.deleteTask(deleteList);
        storage.saveFile(taskList);
        return new CommandResponse(snomio.getDeletedTasks(deletedTasks), false);
    }
}
