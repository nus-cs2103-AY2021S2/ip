package commands;

import exceptions.SnomException;
import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Snomio;

public class FinishCommand extends Command{
    public FinishCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes finish command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @return CommandResponse response after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        int[] finishList = Parser.parseTaskNumbers(this.content);
        Task[] finishedTasks = taskList.finishTask(finishList);
        storage.saveFile(taskList);
        return new CommandResponse(snomio.showFinishedTasks(finishedTasks), false);
    }
}
