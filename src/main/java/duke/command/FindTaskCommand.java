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
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        TaskList filteredTaskList = tasks.filterByWord(keyword);
        ui.setDisplayMessage("Here are the matching tasks in your list:" + "\n" +
                ui.getDisplayOfAllTasks(filteredTaskList) );
    }
}
