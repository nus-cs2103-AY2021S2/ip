package duke.command;

import duke.DukeException;
import duke.DukeList;
import duke.Ui;
import duke.io.Storage;
import duke.tasktype.Task;
import java.util.List;

public class CFind implements ICommand{
    private String input;
    public CFind(String command){
        this.input = command;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        return ui.showTasks(input,taskList);
    }
}
