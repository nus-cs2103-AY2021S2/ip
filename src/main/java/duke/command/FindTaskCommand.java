package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindTaskCommand extends Command {

    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage) {
        TaskList filteredTaskList = tasks.filterByWord(keyword);
        String findTaskMessage = ui.generateFindTaskMessage(filteredTaskList);
        return new CommandResult(findTaskMessage,false);
    }
}
