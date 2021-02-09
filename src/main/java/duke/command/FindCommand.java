package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand implements Command {
    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredList = new TaskList();

        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDetail().indexOf(this.searchString) >= 0) {
                filteredList.add(currTask);
            }
        }

        ui.printMessage(filteredList.toString());
    }
    
}
