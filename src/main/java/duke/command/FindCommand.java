package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList printTaskList = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if(task.getDescription().contains(query)) {
                printTaskList.add(task);
            }
        }
        ui.showListMessage(printTaskList, true);
    }
}
