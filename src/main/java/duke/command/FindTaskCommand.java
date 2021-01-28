package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindTaskCommand extends Command {

    String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public boolean execute(Ui ui, TaskList tasks, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        TaskList filteredTaskList = tasks.filterByWord(keyword);
        ui.showUserAllTasks(filteredTaskList);
        return EXECUTION_SUCCESS;
    }
}
