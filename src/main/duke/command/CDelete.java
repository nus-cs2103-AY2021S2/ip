package duke.command;

import duke.DukeException;
import duke.DukeList;
import duke.Ui;
import duke.io.Storage;
import duke.tasktype.Task;
import java.util.List;

public class CDelete implements ICommand {
    private String input;
    public CDelete(String command){
        this.input = command;
    }
    @Override
    public boolean isBye() {
        return false;
    }
    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException{
        List<Task> taskList = dukeList.getTaskList();
        int index = Integer.parseInt(input.substring(7));
        if(index == 0 || index>= taskList.size() +1){
            throw new DukeException("This task does not exist");
        }
        Task removedTask = taskList.remove(index);
        storage.removeTask(removedTask.convertToFile());
        return ui.showTaskDeleted(removedTask.getStatus(), taskList);

    }


}
