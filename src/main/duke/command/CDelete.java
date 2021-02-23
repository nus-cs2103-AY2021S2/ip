package main.duke.command;

import main.duke.DukeException;
import main.duke.DukeList;
import main.duke.Ui;
import main.duke.io.Storage;
import main.duke.tasktype.Task;
import java.util.List;

public class CDelete implements ICommand {
    private String input;
    public CDelete(String command){
        this.input = command;
    }
    /**
     * Check if it is the bye command
     * @return false as it is not the bye command
     */
    @Override
    public boolean isBye() {
        return false;
    }
    /**
     *
     * @param ui UI object that deal with the program output
     * @param dukeList Collection of tasks in list form
     * @param storage Storage object that deal with the file system
     */
    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException{
        List<Task> taskList = dukeList.getTaskList();
        int index = Integer.parseInt(input.substring(7)) -1;
        if(index+1 == 0 || index>= taskList.size() +1){
            throw new DukeException("This task does not exist");
        }
        Task removedTask = taskList.remove(index);
        storage.removeTask(removedTask.convertToFile());
        return ui.showTaskDeleted(removedTask.getStatus(), taskList);

    }


}
