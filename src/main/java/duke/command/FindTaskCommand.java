package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.MessageGenerator;

public class FindTaskCommand extends Command {

    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage) {
        TaskList filteredTaskList = tasks.filterByWord(keyword);
        String findTaskMessage = messageGenerator.generateFindTaskMessage(filteredTaskList);
        return new CommandResult(findTaskMessage, false);
    }
}
