package duke.command;

import duke.Storage.Storage;
import duke.Model.TaskList;
import duke.Ui.MessageGenerator;

public class FindTaskCommand extends Command {

    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public CommandResult execute(MessageGenerator messageGenerator, TaskList tasks, Storage storage) {
        TaskList filteredTaskList = tasks.filterByWord(keyword);
        String findTaskMessage = messageGenerator.generateFindTaskMessage(filteredTaskList);
        return new CommandResult(findTaskMessage,false);
    }
}
