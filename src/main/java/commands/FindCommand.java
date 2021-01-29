package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.TaskList;
import tasks.Todo;
import ui.Snomio;

public class FindCommand extends Command{
    public FindCommand(CommandEnum type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        String content = snomio.readContent(type.name());
        TaskList foundTaskList = new TaskList(taskList.findTask(content));
        snomio.showTaskList(foundTaskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}