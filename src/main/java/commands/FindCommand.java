package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.TaskList;
import ui.Snomio;

public class FindCommand extends Command{
    public FindCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        String content = snomio.readContent(commandType.name());
        TaskList foundTaskList = new TaskList(taskList.findTask(content));
        return new CommandResponse(snomio.showTaskList(foundTaskList), false);
    }
}