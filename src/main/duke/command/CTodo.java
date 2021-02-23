package main.duke.command;
import main.duke.DukeException;
import main.duke.DukeList;
import main.duke.Ui;
import main.duke.io.Storage;
import main.duke.tasktype.Todo;
import main.duke.tasktype.Task;
import java.util.List;

public class CTodo implements ICommand {
    private String input;
    public CTodo(String command){
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
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        String name = input.substring(4);
        Task todo = new Todo(name.trim());
        taskList.add(todo);
        storage.addText(todo.convertToFile());
        return ui.showTaskAdded(todo.getStatus(), taskList);
    }
}
