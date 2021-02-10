package commands;

import exceptions.SnomException;
import files.Storage;
import tasks.TaskList;
import ui.Snomio;

public class FindCommand extends Command{
    public FindCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        TaskList foundTaskList = new TaskList(taskList.findTask(this.content));
        return new CommandResponse(snomio.getMatchingTaskList(foundTaskList), false);
    }
}