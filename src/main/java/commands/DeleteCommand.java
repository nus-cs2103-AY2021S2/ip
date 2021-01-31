package commands;

import exceptions.SnomException;
import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Snomio;

public class DeleteCommand extends Command{
    public DeleteCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes delete commmand
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @return CommandResponse response after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        int[] deleteList = Parser.parseTaskNumbers(this.content);
        Task[] deletedTasks = taskList.deleteTask(deleteList);
        storage.saveFile(taskList);
        return new CommandResponse(snomio.showDeletedTasks(deletedTasks), false);
    }
}
