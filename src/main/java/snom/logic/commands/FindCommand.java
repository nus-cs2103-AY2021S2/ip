package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.storage.StorageManager;
import snom.model.task.TaskList;
import snom.ui.Snomio;

public class FindCommand extends Command{
    public FindCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        TaskList foundTaskList = new TaskList(taskList.findTask(this.content));
        return new CommandResponse(snomio.getMatchingTaskList(foundTaskList), false);
    }
}