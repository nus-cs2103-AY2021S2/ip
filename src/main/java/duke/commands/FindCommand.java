package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.findTask(keyword);
        ui.showTaskList(filteredTasks, "matching ");
    }

    public boolean isExit() {
        return false;
    }
}
